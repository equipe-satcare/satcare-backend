package com.satc.satcdisciplinabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
// ainda nao sabemos se vamos utilizar a classe clientes ou salvar apenas os registros da Pessoa
@Entity(name = "clientes")
public class Cliente extends Pessoa {
}
