package com.satc.satcdisciplinabackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FuncionarioDTO {
    private Integer id;
    private List<ServicoDTO> servicos;
    private String nome;
    private String telefone;
    private String email;
}
