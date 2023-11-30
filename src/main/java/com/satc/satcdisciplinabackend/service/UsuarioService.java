package com.satc.satcdisciplinabackend.service;

import com.satc.satcdisciplinabackend.model.Usuario;
import com.satc.satcdisciplinabackend.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends CommonServiceImpl<Usuario> {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(ModelMapper modelMapper, UsuarioRepository repository) {
        super(repository, Usuario.class, modelMapper);
        this.usuarioRepository = repository;
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).get();
    }

    public Usuario findByTelefone(String telefone) {
        return usuarioRepository.findByTelefone(telefone).get();
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username).get();
    }
}
