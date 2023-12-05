package com.satc.satcdisciplinabackend.repository;

import com.satc.satcdisciplinabackend.model.Agendamento;
import com.satc.satcdisciplinabackend.model.Cliente;
import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.model.QAgendamento;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends CommonRepository<Agendamento> {
    default boolean possuiConflitoFuncionario(LocalDateTime inicio, LocalDateTime fim, Funcionario funcionario) {
        fim = fim.minusMinutes(1); // tolerancia de 1 minuto para permitir que agendamentos comecem no mesmo minuto

        return this.exists(
                QAgendamento.agendamento.funcionario.eq(funcionario).and(
                        QAgendamento.agendamento.dataHoraInicio.between(inicio, fim).or(
                                QAgendamento.agendamento.dataHoraFim.between(inicio.plusMinutes(1), fim)
                        )
                )
        );
    }

    default boolean possuiConflitoCliente(LocalDateTime inicio, LocalDateTime fim, Cliente cliente) {
        fim = fim.minusMinutes(1); // tolerancia de 1 minuto para permitir que agendamentos comecem no mesmo minuto

        return this.exists(
                QAgendamento.agendamento.cliente.eq(cliente).and(
                        QAgendamento.agendamento.dataHoraInicio.between(inicio, fim).or(
                                QAgendamento.agendamento.dataHoraFim.between(inicio.plusMinutes(1), fim)
                        )
                )
        );
    }
}
