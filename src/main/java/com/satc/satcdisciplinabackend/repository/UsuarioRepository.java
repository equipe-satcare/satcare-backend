package com.satc.satcdisciplinabackend.repository;

import com.satc.satcdisciplinabackend.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CommonRepository<Usuario> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByTelefone(String email);
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String username);
}
