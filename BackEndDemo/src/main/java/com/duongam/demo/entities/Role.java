package com.duongam.demo.entities;


import com.duongam.demo.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    private Set<User> users;

    @OneToOne(mappedBy = "role", cascade = CascadeType.PERSIST)
    private Permission permission;

    public Role(ERole name) {
        this.name = name;
    }

    public Role(ERole name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
