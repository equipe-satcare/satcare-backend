package com.satc.satcdisciplinabackend.model;

import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa {
    // ainda precisa decidir se havera uma classe de usuario ou se as informacoes ficarao aqui mesmo
    private String usuario;
    private String senha;
    private List<Servico> servicos = new ArrayList();
}

