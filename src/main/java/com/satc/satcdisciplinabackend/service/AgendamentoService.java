package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Agendamento;
import com.satc.satcdisciplinabackend.model.Servico;
import com.satc.satcdisciplinabackend.repository.AgendamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AgendamentoService extends CommonServiceImpl<Agendamento> {
    @Autowired
    AgendamentoRepository repository;

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

        return super.save(agendamento);
    }
}
