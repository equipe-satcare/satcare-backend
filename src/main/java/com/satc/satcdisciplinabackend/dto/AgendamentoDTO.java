package com.satc.satcdisciplinabackend.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.satc.satcdisciplinabackend.model.FormaPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AgendamentoDTO {
    private Integer id;
    private BigDecimal valorTotal;
    private String observacao;
    private FormaPagamento formaPagamento;
//    private ClienteD cliente;
    private FuncionarioDTO funcionario;
    private List<ServicoDTO> servicos;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHoraFim;
}
