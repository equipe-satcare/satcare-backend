package com.satc.satcdisciplinabackend.model;

import com.satc.satcdisciplinabackend.common.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "lojas")
public class Loja extends CommonEntity {
    @Column()
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "logo")
    private Arquivo logo;
}
