package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.repository.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService extends CommonServiceImpl<Funcionario>{

    @Autowired
    public FuncionarioService(ModelMapper modelMapper, FuncionarioRepository repository) {
        super(repository, Funcionario.class, modelMapper);
    }
}
