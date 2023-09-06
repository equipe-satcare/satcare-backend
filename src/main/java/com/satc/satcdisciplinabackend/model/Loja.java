package com.satc.satcdisciplinabackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Loja extends CommonEntity {
    private String nome;
    private byte[] logo;
}
