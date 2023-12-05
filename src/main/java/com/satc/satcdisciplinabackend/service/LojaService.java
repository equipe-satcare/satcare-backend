package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Loja;
import com.satc.satcdisciplinabackend.repository.LojaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService extends CommonServiceImpl<Loja> {

    @Autowired
    public LojaService(ModelMapper modelMapper, LojaRepository repository) {
        super(repository, Loja.class, modelMapper);
    }

}
