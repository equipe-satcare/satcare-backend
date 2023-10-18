package com.satc.satcdisciplinabackend.model;

import com.satc.satcdisciplinabackend.common.CommonEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "arquivos")
public class Arquivo extends CommonEntity { }
