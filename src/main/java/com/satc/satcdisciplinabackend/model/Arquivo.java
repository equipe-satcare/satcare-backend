package com.satc.satcdisciplinabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "arquivos")
public class Arquivo extends CommonEntity { }
