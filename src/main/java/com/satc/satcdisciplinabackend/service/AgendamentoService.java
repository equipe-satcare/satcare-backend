package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Agendamento;
import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.model.Servico;
import com.satc.satcdisciplinabackend.repository.AgendamentoRepository;
import com.satc.satcdisciplinabackend.repository.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService extends CommonServiceImpl<Agendamento> {
    @Autowired
    AgendamentoRepository repository;

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    public AgendamentoService(ModelMapper modelMapper, AgendamentoRepository repository) {
        super(repository, Agendamento.class, modelMapper);
    }

    @Override
    public Agendamento save(Agendamento agendamento) {
        int tempoAtendimento = agendamento.getServicos().stream()
                .mapToInt(Servico::getTempo)
                .sum();

        LocalDateTime dataHoraInicio = agendamento.getDataHoraInicio();
        LocalDateTime dataHoraFinal = dataHoraInicio.plusMinutes(tempoAtendimento);
        agendamento.setDataHoraFim(dataHoraFinal);

        boolean conflitoFuncionario = repository.possuiConflitoFuncionario(dataHoraInicio, dataHoraFinal, agendamento.getFuncionario());
        if (conflitoFuncionario) {
            System.out.println("Conflito de horario do funcionario");
            // todo mensagem de erro
            return null;
        }

        boolean conflitoCliente = repository.possuiConflitoCliente(dataHoraInicio, dataHoraFinal, agendamento.getCliente());
        if (conflitoCliente) {
            System.out.println("Conflito de horario do funcionario");
            // todo mensagem de erro
            return null;
        }

        Funcionario funcionario = funcionarioRepository.findById(agendamento.getFuncionario().getId()).get();
        List<Integer> servicosIdsFuncionario = funcionario.getServicos()
                .stream()
                .map(Servico::getId)
                .toList();

        // verifica se algum servicoId do agendamento nao esta na lista de servicos do funcionario
        boolean possuiServicoInvalido = agendamento.getServicos().stream()
                .anyMatch(servico -> !servicosIdsFuncionario.contains(servico.getId()));

        if (possuiServicoInvalido) {
            System.out.println("Lista de servicos invalida");
            // todo mensagem de erro
            return null;
        }

        return super.save(agendamento);
    }
}
