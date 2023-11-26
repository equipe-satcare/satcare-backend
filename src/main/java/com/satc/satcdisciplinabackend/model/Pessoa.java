package com.satc.satcdisciplinabackend.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass()
public abstract class Pessoa extends CommonEntity {
    @Column()
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