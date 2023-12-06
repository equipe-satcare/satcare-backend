package com.satc.satcdisciplinabackend.dto;


import java.time.DayOfWeek;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioAtendimentoDTO {
    private Integer id;
    private FuncionarioDTO funcionario;
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;
}
