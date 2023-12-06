package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "servicos")
public class Servico extends CommonEntity {
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Min(0)
    @Column(name = "valor")
    private BigDecimal valor;

    @Min(0)
    @NotNull
    @Column(name = "tempo")
    private Integer tempo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_servico_id")
    private TipoServico tipoServico;
}