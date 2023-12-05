package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Servico;
import com.satc.satcdisciplinabackend.repository.ServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService extends CommonServiceImpl<Servico> {

    @Autowired
    public ServicoService(ModelMapper modelMapper, ServicoRepository repository) {
        super(repository, Servico.class, modelMapper);
    }

}
