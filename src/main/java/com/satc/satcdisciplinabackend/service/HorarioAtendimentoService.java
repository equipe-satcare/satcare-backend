package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.HorarioAtendimento;
import com.satc.satcdisciplinabackend.repository.HorarioAtendimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioAtendimentoService extends CommonServiceImpl<HorarioAtendimento> {

    @Autowired
    public HorarioAtendimentoService(ModelMapper modelMapper, HorarioAtendimentoRepository repository) {
        super(repository, HorarioAtendimento.class, modelMapper);
    }

}
