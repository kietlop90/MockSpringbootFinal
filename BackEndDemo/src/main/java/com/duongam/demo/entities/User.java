package com.duongam.demo.entities;


import com.duongam.demo.entities.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private String phone;

    private LocalDate dob;

    private Boolean status;

    private String createdBy;

    @CreationTimestamp
    @Column(name = "create_date", insertable = false, updatable = false)
    private Timestamp createdDate;
    private String modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_date", insertable = false, updatable = false)
    private Timestamp modifiedDate;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @ManyToOne
//            (cascade = CascadeType.PERSIST)
    private Role role;


    @OneToMany(mappedBy = "userId")
    private List<ClassUser> classUser;


    @OneToMany(mappedBy = "userId")
    private List<TrainingProgram> trainingProgram;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getRole() {
        return this.role.getName().name();
    }
}
