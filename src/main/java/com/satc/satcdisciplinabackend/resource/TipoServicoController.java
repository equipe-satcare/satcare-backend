package com.satc.satcdisciplinabackend.resource;

import com.satc.satcdisciplinabackend.dto.TipoServicoDTO;
import com.satc.satcdisciplinabackend.model.TipoServico;
import com.satc.satcdisciplinabackend.service.TipoServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/tipos-servicos")
public class TipoServicoController extends AbstractController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TipoServicoService service;

    @GetMapping("{id}")
    public ResponseEntity<TipoServicoDTO> findById(@PathVariable("id") int id ) {
        TipoServico entity = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(entity, TipoServicoDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<TipoServicoDTO>> findPaginated(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TipoServico> pagina = service.findPaginated(page, size, filter);
        return ResponseEntity.ok(pagina.map(e -> modelMapper.map(e, TipoServicoDTO.class)));
    }


    @PostMapping
    public ResponseEntity<TipoServicoDTO> create(@RequestBody @Valid TipoServico entity){
        TipoServico save = service.save(entity);
        return ResponseEntity
                .created(URI.create("/api/tipos-servicos/"+ save.getId()))
                .body(modelMapper.map(save, TipoServicoDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") int id ) {
        service.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id,@RequestBody TipoServico entity) {
        TipoServico alterado = service.update(id, entity);
        return ResponseEntity.ok().body(modelMapper.map(alterado, TipoServicoDTO.class));
    }
}
