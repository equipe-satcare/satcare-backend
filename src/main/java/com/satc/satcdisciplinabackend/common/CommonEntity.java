package com.satc.satcdisciplinabackend.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class CommonEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

}
