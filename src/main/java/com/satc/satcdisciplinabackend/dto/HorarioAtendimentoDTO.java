package com.satc.satcdisciplinabackend.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import com.satc.satcdisciplinabackend.model.Cliente;
import com.satc.satcdisciplinabackend.model.FormaPagamento;
import com.satc.satcdisciplinabackend.model.Funcionario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HorarioAtendimentoDTO {
    private Integer id;
    private LocalDate dataAtendimento;
    private BigDecimal valorTotal;
    private String observacao;
    private FormaPagamento formaPagamento;
    private Cliente cliente;
    private Funcionario funcionario;
}
