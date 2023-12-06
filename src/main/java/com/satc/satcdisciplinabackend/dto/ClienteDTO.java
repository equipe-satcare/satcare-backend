package com.satc.satcdisciplinabackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteDTO {
    private Integer id;
    private String nome;
    private String telefone;
    private String email;
}
