package com.satc.satcdisciplinabackend.security;

import com.satc.satcdisciplinabackend.model.Usuario;
import com.satc.satcdisciplinabackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));


        return UserDetailsImpl.build(user);
    }


}

