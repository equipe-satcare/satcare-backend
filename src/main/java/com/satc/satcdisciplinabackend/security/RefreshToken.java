package com.satc.satcdisciplinabackend.security;

import com.satc.satcdisciplinabackend.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity(name = "refreshtoken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private long id;


    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario user;


    @Column(nullable = false, unique = true)
    private String token;


    @Column(nullable = false)
    private Instant expiryDate;
}

