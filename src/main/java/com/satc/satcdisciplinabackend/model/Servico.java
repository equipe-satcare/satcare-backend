package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servico {
    private String nome;
    private TipoServico tipoServico;
    private BigDecimal valor;
    private Integer tempo;
}