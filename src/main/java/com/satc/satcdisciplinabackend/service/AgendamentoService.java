package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Agendamento;
import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.model.Servico;
import com.satc.satcdisciplinabackend.repository.AgendamentoRepository;
import com.satc.satcdisciplinabackend.repository.FuncionarioRepository;
import com.satc.satcdisciplinabackend.repository.ServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
    public AgendamentoService(ModelMapper modelMapper, AgendamentoRepository repository) {
        super(repository, Agendamento.class, modelMapper);
    }

    @Override
    public Agendamento save(Agendamento agendamento) {
        List<Servico> servicos = servicoRepository.findAllById(agendamento.getServicos().stream().map(Servico::getId).collect(Collectors.toList()));
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



        return super.save(agendamento);
    }
}
