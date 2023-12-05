package com.satc.satcdisciplinabackend.resource;

import com.satc.satcdisciplinabackend.dto.LojaDTO;
import com.satc.satcdisciplinabackend.model.Loja;
import com.satc.satcdisciplinabackend.service.LojaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/loja")
public class LojaController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LojaService service;

    @GetMapping("{id}")
    public ResponseEntity<LojaDTO> findById(@PathVariable("id") int id ) {
        Loja entity = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(entity, LojaDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<LojaDTO>> findPaginated(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Loja> pagina = service.findPaginated(page, size, filter);
        return ResponseEntity.ok(pagina.map(e -> modelMapper.map(e, LojaDTO.class)));
    }


    @PostMapping
    public ResponseEntity<LojaDTO> create(@RequestBody @Valid Loja entity){
        Loja save = service.save(entity);
        return ResponseEntity
                .created(URI.create("/api/loja/"+ save.getId()))
                .body(modelMapper.map(save, LojaDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") int id ) {
        service.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id,@RequestBody Loja entity) {
        Loja alterado = service.update(id, entity);
        return ResponseEntity.ok().body(modelMapper.map(alterado, LojaDTO.class));
    }
}
