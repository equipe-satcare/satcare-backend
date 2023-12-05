package com.satc.satcdisciplinabackend.resource;

import com.satc.satcdisciplinabackend.dto.ServicoDTO;
import com.satc.satcdisciplinabackend.model.Servico;
import com.satc.satcdisciplinabackend.service.ServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ServicoService service;

    @GetMapping("{id}")
    public ResponseEntity<ServicoDTO> findById(@PathVariable("id") int id ) {
        Servico entity = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(entity, ServicoDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<ServicoDTO>> findPaginated(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Servico> pagina = service.findPaginated(page, size, filter);
        return ResponseEntity.ok(pagina.map(e -> modelMapper.map(e, ServicoDTO.class)));
    }


    @PostMapping
    public ResponseEntity<ServicoDTO> create(@RequestBody @Valid Servico entity){
        Servico save = service.save(entity);
        return ResponseEntity
                .created(URI.create("/api/servicos/"+ save.getId()))
                .body(modelMapper.map(save, ServicoDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") int id ) {
        service.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id,@RequestBody Servico entity) {
        Servico alterado = service.update(id, entity);
        return ResponseEntity.ok().body(modelMapper.map(alterado, ServicoDTO.class));
    }
}
