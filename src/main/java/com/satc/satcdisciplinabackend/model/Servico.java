package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "servicos")
public class Servico extends CommonEntity {
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tempo")
    private Integer tempo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_servico")
    private TipoServico tipoServico;
}