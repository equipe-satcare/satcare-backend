package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.repository.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class FuncionarioService extends CommonServiceImpl<Funcionario>{

    @Autowired
    public FuncionarioService(ModelMapper modelMapper, FuncionarioRepository repository) {
        super(repository, Funcionario.class, modelMapper);
    }
}
