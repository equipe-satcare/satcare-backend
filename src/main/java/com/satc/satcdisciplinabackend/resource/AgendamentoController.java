package com.satc.satcdisciplinabackend.resource;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satc.satcdisciplinabackend.dto.AgendamentoDTO;
import com.satc.satcdisciplinabackend.dto.TipoServicoDTO;
import com.satc.satcdisciplinabackend.model.Agendamento;
import com.satc.satcdisciplinabackend.model.TipoServico;
import com.satc.satcdisciplinabackend.service.AgendamentoService;
import com.satc.satcdisciplinabackend.service.TipoServicoService;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController extends AbstractController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AgendamentoService service;

    @GetMapping("{id}")
    public ResponseEntity<AgendamentoDTO> findById(@PathVariable("id") int id ) {
        Agendamento entity = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(entity, AgendamentoDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<AgendamentoDTO>> findPaginated(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Agendamento> pagina = service.findPaginated(page, size, filter);
        return ResponseEntity.ok(pagina.map(e -> modelMapper.map(e, AgendamentoDTO.class)));
    }


    @PostMapping
    public ResponseEntity<AgendamentoDTO> create(@RequestBody @Valid Agendamento entity){
        Agendamento save = service.save(entity);
        return ResponseEntity
                .created(URI.create("/api/agendamentos/"+ save.getId()))
                .body(modelMapper.map(save, AgendamentoDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") int id ) {
        service.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id,@RequestBody Agendamento entity) {
        Agendamento alterado = service.update(id, entity);
        return ResponseEntity.ok().body(modelMapper.map(alterado, AgendamentoDTO.class));
    }
}
