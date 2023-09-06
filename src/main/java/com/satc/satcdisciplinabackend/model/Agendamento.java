package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Agendamento {
	private LocalDate dataAgendamento;
	private List<Servico> servicos = new ArrayList<>();
	private Cliente cliente;
	private Funcionario funcionario;
	private BigDecimal valorTotal;
	private String observacao;
	private FormaPagamento formaPagamento;
    
}
