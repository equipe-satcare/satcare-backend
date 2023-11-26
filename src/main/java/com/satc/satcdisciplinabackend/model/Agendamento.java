package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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

	@Column(name = "observacao")
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "forma_pagamento")
	private FormaPagamento formaPagamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;

//	private List<Servico> servicos = new ArrayList<>();
}
