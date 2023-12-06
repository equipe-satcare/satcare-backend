package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity(name = "agendamentos")
@SQLDelete(sql = "UPDATE agendamentos SET deleted_at = now() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class Agendamento extends CommonEntity {

	@NotNull
	@Column(name = "data_hora_inicio")
	private LocalDateTime dataHoraInicio;

	@Column(name = "data_hora_fim")
	@JsonIgnore
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

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;
}
