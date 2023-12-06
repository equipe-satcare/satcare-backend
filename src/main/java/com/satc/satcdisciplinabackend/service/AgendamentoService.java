package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.enterprise.ResourceException;
import com.satc.satcdisciplinabackend.model.Agendamento;
import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.model.Servico;
import com.satc.satcdisciplinabackend.repository.AgendamentoRepository;
import com.satc.satcdisciplinabackend.repository.FuncionarioRepository;
import com.satc.satcdisciplinabackend.repository.HorarioAtendimentoRepository;
import com.satc.satcdisciplinabackend.repository.ServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AgendamentoService extends CommonServiceImpl<Agendamento> {
    @Autowired
    AgendamentoRepository repository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    ServicoRepository servicoRepository;

    @Autowired
    HorarioAtendimentoRepository horarioAtendimentoRepository;

    @Autowired
    public AgendamentoService(ModelMapper modelMapper, AgendamentoRepository repository) {
        super(repository, Agendamento.class, modelMapper);
    }

    @Override
    public Agendamento save(Agendamento agendamento) {
        if (agendamento.getDataHoraInicio().isBefore(LocalDateTime.now())) {
            throw new ResourceException("Data do agendamento inválida");
        }

        List<Servico> servicos = servicoRepository.findAllById(agendamento.getServicos().stream().map(Servico::getId).collect(Collectors.toList()));
        if(servicos.isEmpty()) {
            throw new ResourceException("Lista de servicos inválida");
        }

        // calcula tempo de atendimento e valor total do servico
        int tempoAtendimento = servicos.stream()
                .mapToInt(Servico::getTempo)
                .sum();

        BigDecimal valorTotal = servicos.stream()
                .map(Servico::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LocalDateTime dataHoraInicio = agendamento.getDataHoraInicio();
        LocalDateTime dataHoraFinal = dataHoraInicio.plusMinutes(tempoAtendimento);
        agendamento.setDataHoraFim(dataHoraFinal);
        agendamento.setValorTotal(valorTotal);


        // faz validacoes relacionadas ao horario do atendimento
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(agendamento.getFuncionario().getId());
        if (optionalFuncionario.isEmpty()) {
            throw new NotFoundException("Funcionario nao encontrado");
        }
        Funcionario funcionario = optionalFuncionario.get();

        // verifica se o funcionario trabalha no horario escolhido
        boolean funcionarioTrabalha = horarioAtendimentoRepository.funcionarioTrabalhaNoHorario(funcionario, agendamento.getDataHoraInicio());
        if (!funcionarioTrabalha) {
            throw new ResourceException("O funcionário não trabalha no horário escolhido");
        }


        // verifica se há conflito de horario com o funcionario
        boolean conflitoFuncionario = repository.possuiConflitoFuncionario(dataHoraInicio, dataHoraFinal, agendamento.getFuncionario());
        if (conflitoFuncionario) {
            throw new ResourceException("O funcionário já possui outro agendamento neste horário");
        }

        // verifica se há conflito de horario com o cliente
        boolean conflitoCliente = repository.possuiConflitoCliente(dataHoraInicio, dataHoraFinal, agendamento.getCliente());
        if (conflitoCliente) {
            throw new ResourceException("O cliente já possui outro agendamento neste horário");
        }


        List<Integer> servicosIdsFuncionario = funcionario.getServicos()
                .stream()
                .map(Servico::getId)
                .toList();

        // verifica se algum servicoId do agendamento nao esta na lista de servicos do funcionario
        boolean possuiServicoInvalido = agendamento.getServicos().stream()
                .anyMatch(servico -> !servicosIdsFuncionario.contains(servico.getId()));

        if (possuiServicoInvalido) {
            throw new ResourceException("O funcionário não presta um ou mais serviços da lista");
        }

        return super.save(agendamento);
    }


    @Override
    public void deleteOne(Integer id) {
        Optional<Agendamento> optionalAgendamento = repository.findById(id);
        if (optionalAgendamento.isEmpty()) {
            throw new NotFoundException();
        }
        Agendamento agendamento = optionalAgendamento.get();

        Duration duration = Duration.between(LocalDateTime.now(), agendamento.getDataHoraInicio());
        if (duration.toDays() < 3) {
            throw new ResourceException("Não é possivel cancelar um agendamento com menos de 3 dias de antecedencia");
        }
        super.deleteOne(id);
    }
}
