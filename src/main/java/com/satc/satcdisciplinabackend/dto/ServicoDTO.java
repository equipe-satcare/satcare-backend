package com.satc.satcdisciplinabackend.dto;

import com.satc.satcdisciplinabackend.model.TipoServico;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoDTO {
    private String descricao;
    private BigDecimal valor;
    private Integer tempo;
    private TipoServico tipoServico;
}
