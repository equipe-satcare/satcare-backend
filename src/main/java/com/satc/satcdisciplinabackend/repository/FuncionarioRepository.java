package com.satc.satcdisciplinabackend.repository;

import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.model.QFuncionario;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface FuncionarioRepository extends CommonRepository<Funcionario> {
}
