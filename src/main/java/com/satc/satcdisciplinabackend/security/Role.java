package com.satc.satcdisciplinabackend.security;

import com.satc.satcdisciplinabackend.model.CommonEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role extends CommonEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

}
