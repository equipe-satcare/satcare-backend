package com.satc.satcdisciplinabackend.model;

public abstract class Pessoa {
    private String nome;
    private String cpf;
    private String rg;
    private String telefone;
    private String endereco;
    private String email;

    private Boolean permiteComunicacaoEmail;

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