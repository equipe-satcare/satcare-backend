package com.satc.satcdisciplinabackend.resource;

import com.satc.satcdisciplinabackend.dto.FuncionarioDTO;
import com.satc.satcdisciplinabackend.model.Funcionario;
import com.satc.satcdisciplinabackend.service.FuncionarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FuncionarioService service;

    @GetMapping("{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable("id") int id ) {
        Funcionario entity = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(entity, FuncionarioDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<FuncionarioDTO>> findPaginated(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Funcionario> pagina = service.findPaginated(page, size, filter);
        return ResponseEntity.ok(pagina.map(e -> modelMapper.map(e, FuncionarioDTO.class)));
    }


    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@RequestBody @Valid Funcionario entity){
        Funcionario save = service.save(entity);
        return ResponseEntity
                .created(URI.create("/api/funcionarios/"+ save.getId()))
                .body(modelMapper.map(save, FuncionarioDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") int id ) {
        service.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id,@RequestBody Funcionario entity) {
        Funcionario alterado = service.update(id, entity);
        return ResponseEntity.ok().body(modelMapper.map(alterado, FuncionarioDTO.class));
    }
}
