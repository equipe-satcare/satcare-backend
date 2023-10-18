package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.satc.satcdisciplinabackend.common.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "agendamentos")
public class Agendamento extends CommonEntity {

	@Column(name = "data_agendamento")
	private LocalDate dataAgendamento;

	@Column(name = "valor_total")
	private BigDecimal valorTotal;

	@Column(name = "descricao")
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento")
	private FormaPagamento formaPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private Funcionario funcionario;

//	private List<Servico> servicos = new ArrayList<>();
}
