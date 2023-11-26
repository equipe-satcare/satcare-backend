package com.satc.satcdisciplinabackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "tipos_servicos")
public class TipoServico extends CommonEntity {

	@Column(name = "descricao", length = 50, nullable = false)
	private String descricao;

}
