package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Cliente;
import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.repository.ClienteRepository;
import com.satc.satcdisciplinabackend.repository.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends CommonServiceImpl<Cliente>{

    @Autowired
    public ClienteService(ModelMapper modelMapper, ClienteRepository repository) {
        super(repository, Cliente.class, modelMapper);
    }
}
