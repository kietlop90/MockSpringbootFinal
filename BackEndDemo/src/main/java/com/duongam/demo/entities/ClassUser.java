package com.duongam.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "class_user")
public class ClassUser implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "classUser_id")
    private Long id;

    private Integer userType; // role of user

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private ClassForProject classId;

    @Transient
    public String userName() {
        return userId.getName();
    }
}
