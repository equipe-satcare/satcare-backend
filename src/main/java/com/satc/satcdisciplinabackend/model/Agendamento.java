package com.satc.satcdisciplinabackend.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Agendamento {
	private LocalDate dataAgendamento;
	private List<Servico> servicos = new ArrayList<>();
	private Cliente cliente;
	private Funcionario funcionario;
	private BigDecimal valorTotal;
	private String observacao;
	private FormaPagamento formaPagamento;
    
}
