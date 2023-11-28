package com.satc.satcdisciplinabackend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.satc.satcdisciplinabackend.model.Agendamento;
import com.satc.satcdisciplinabackend.model.TipoServico;
import com.satc.satcdisciplinabackend.repository.AgendamentoRepository;
import com.satc.satcdisciplinabackend.repository.TipoServicoRepository;

@Service
public class AgendamentoService extends CommonServiceImpl<AgendamentoService> {
    @Autowired
    public AgendamentoService(ModelMapper modelMapper, AgendamentoRepository repository) {
        super(repository, Agendamento.class, modelMapper);
    }
}
