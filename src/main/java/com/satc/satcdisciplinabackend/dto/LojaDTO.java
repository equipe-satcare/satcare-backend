package com.satc.satcdisciplinabackend.dto;

import com.satc.satcdisciplinabackend.model.Arquivo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LojaDTO {
    private String nome;
    private Arquivo logo;
}
