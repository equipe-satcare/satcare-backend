package com.satc.satcdisciplinabackend.resource;

import com.satc.satcdisciplinabackend.dto.HorarioAtendimentoDTO;
import com.satc.satcdisciplinabackend.model.HorarioAtendimento;
import com.satc.satcdisciplinabackend.service.HorarioAtendimentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/tipos-servicos")
public class HorarioAtendimentoController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private HorarioAtendimentoService service;

    @GetMapping("{id}")
    public ResponseEntity<HorarioAtendimentoDTO> findById(@PathVariable("id") int id ) {
        HorarioAtendimento entity = service.findById(id);
        return ResponseEntity.ok(modelMapper.map(entity, HorarioAtendimentoDTO.class));
    }

    @GetMapping()
    public ResponseEntity<Page<HorarioAtendimentoDTO>> findPaginated(
            @RequestParam(required = false) String filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<HorarioAtendimento> pagina = service.findPaginated(page, size, filter);
        return ResponseEntity.ok(pagina.map(e -> modelMapper.map(e, HorarioAtendimentoDTO.class)));
    }


    @PostMapping
    public ResponseEntity<HorarioAtendimentoDTO> create(@RequestBody @Valid HorarioAtendimento entity){
        HorarioAtendimento save = service.save(entity);
        return ResponseEntity
                .created(URI.create("/api/tipos-servicos/"+ save.getId()))
                .body(modelMapper.map(save, HorarioAtendimentoDTO.class));
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathVariable("id") int id ) {
        service.deleteOne(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id,@RequestBody HorarioAtendimento entity) {
        HorarioAtendimento alterado = service.update(id, entity);
        return ResponseEntity.ok().body(modelMapper.map(alterado, HorarioAtendimentoDTO.class));
    }
}
