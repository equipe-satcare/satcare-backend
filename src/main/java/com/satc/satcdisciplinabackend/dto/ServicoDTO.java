package com.satc.satcdisciplinabackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoDTO {
    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private Integer tempo;
    private TipoServicoDTO tipoServico;
}
