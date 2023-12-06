package com.satc.satcdisciplinabackend.resource;

import com.satc.satcdisciplinabackend.dto.ClienteDTO;
import com.satc.satcdisciplinabackend.model.Cliente;
import com.satc.satcdisciplinabackend.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController extends AbstractController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteService service;

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") int id ) {
        Cliente entity = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(entity, ClienteDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<ClienteDTO>> findPaginated(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Cliente> pagina = service.findPaginated(page, size, filter);
        return ResponseEntity.ok(pagina.map(e -> modelMapper.map(e, ClienteDTO.class)));
    }


    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Valid Cliente entity){
        Cliente save = service.save(entity);
        return ResponseEntity
                .created(URI.create("/api/clientes/"+ save.getId()))
                .body(modelMapper.map(save, ClienteDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") int id ) {
        service.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id,@RequestBody Cliente entity) {
        Cliente alterado = service.update(id, entity);
        return ResponseEntity.ok().body(modelMapper.map(alterado, ClienteDTO.class));
    }
}
