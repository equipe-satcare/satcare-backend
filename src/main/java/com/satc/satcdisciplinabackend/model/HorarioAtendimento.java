package com.satc.satcdisciplinabackend.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "horarios_atendimento")
public class HorarioAtendimento extends CommonEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana")
    private DayOfWeek diaSemana;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "hora_fim")
    private LocalTime horaFim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario")
    private Funcionario funcionario;
}
