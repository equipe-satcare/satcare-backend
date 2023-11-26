package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.TipoServico;
import com.satc.satcdisciplinabackend.repository.TipoServicoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoServicoService extends CommonServiceImpl<TipoServico> {

    @Autowired
    public TipoServicoService(ModelMapper modelMapper, TipoServicoRepository repository) {
        super(repository, TipoServico.class, modelMapper);
    }

}
