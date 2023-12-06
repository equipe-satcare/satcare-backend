package com.satc.satcdisciplinabackend.repository;

import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.model.HorarioAtendimento;
import com.satc.satcdisciplinabackend.model.QHorarioAtendimento;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Repository
public interface HorarioAtendimentoRepository extends CommonRepository<HorarioAtendimento> {
    default boolean funcionarioTrabalhaNoHorario(Funcionario funcionario, LocalDateTime horario) {
        QHorarioAtendimento qHorario = QHorarioAtendimento.horarioAtendimento;
        return this.exists(
                qHorario.funcionario.eq(funcionario)
                        .and(qHorario.diaSemana.eq(horario.getDayOfWeek()))
                        .and(qHorario.horaInicio.before(horario.toLocalTime().plusMinutes(1)))
                        .and(qHorario.horaFim.after(horario.toLocalTime()))
        );
    }
}
