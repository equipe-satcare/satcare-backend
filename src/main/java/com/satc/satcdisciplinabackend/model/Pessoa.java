package com.satc.satcdisciplinabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@MappedSuperclass()
@Getter
@Setter
public abstract class Pessoa extends CommonEntity {
    @Column()
    @NotNull
    private String nome;

    @Column()
    private String cpf;

    @Column()
    private String rg;

    @Column()
    private String telefone;

    @Column()
    private String endereco;

    @Column()
    @Email()
    private String email;

    @Column(name = "permite_comunicacao_email")
    private Boolean permiteComunicacaoEmail;

    @Column(name = "permite_comunicacao_whatsapp")
    private Boolean permiteComunicacaoWhatsapp;
    
    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}