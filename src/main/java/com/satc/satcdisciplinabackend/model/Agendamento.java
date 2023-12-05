package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "agendamentos")
public class Agendamento extends CommonEntity {

	@Column(name = "data_hora_inicio")
//	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHoraInicio;

	@Column(name = "data_hora_fim")
	private LocalDateTime dataHoraFim;

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

	@ManyToMany
	@JoinTable(
			name = "agendamento_servicos",
			joinColumns = @JoinColumn(name = "agendamento_id"),
			inverseJoinColumns = @JoinColumn(name = "servico_id")
	)
	private List<Servico> servicos = new ArrayList<>();
}
