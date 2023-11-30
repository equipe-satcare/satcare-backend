package com.satc.satcdisciplinabackend.model;

import com.satc.satcdisciplinabackend.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "usuarios")
public class Usuario extends CommonEntity {
    @Column()
    private String nome;

    @Column()
    private String username;

    @Column()
    private String telefone;

    @Column()
    private String email;

    @Column()
    private String senha;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Usuario(String username, String email, String senha) {
        this.username = username;
        this.email = email;
        this.senha = senha;
    }
}
