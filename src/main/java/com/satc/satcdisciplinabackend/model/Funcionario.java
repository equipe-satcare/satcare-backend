package com.satc.satcdisciplinabackend.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Getter
@Setter
@Entity(name = "funcionarios")
public class Funcionario extends Pessoa {
    // ainda precisa decidir se havera uma classe de usuario ou se as informacoes ficarao aqui mesmo
//    private String usuario;
//    private String senha;
    @ManyToMany
    @JoinTable(
            name = "funcionario_servicos",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos = new ArrayList<>();
}

