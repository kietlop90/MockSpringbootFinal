package com.duongam.demo.entities;


import com.duongam.demo.dto.request.forcreate.CRequestUser;
import com.duongam.demo.entities.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import javax.swing.text.DateFormatter;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User{
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
    @Column(name = "create_date", updatable = false)
    private Timestamp createdDate;
    private String modifiedBy;

    @UpdateTimestamp
    @Column(name = "modified_date", updatable = false)
    private Timestamp modifiedDate;

    @Enumerated(EnumType.STRING)
    private EGender gender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Role role;

    @OneToMany(mappedBy = "userId")
    private List<ClassUser> classUser;

    @OneToMany(mappedBy = "createdBy")
    private List<TrainingProgram> trainingProgram;

    @OneToMany(mappedBy = "createdBy")
    private List<Syllabus> syllabusList;

    @OneToMany(mappedBy = "createdBy")
    private List<Class> classList;


    @Transient
    public String roleName() {
        return role.getName().toString();
    }

    @Transient
    public String genderText(){
        return gender.toString();
    }

    @Transient
    public String dobText(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dob.format(formatter);
    }

    public User(CRequestUser cRequestUser) {
        this.username = cRequestUser.getUsername();
        this.password = cRequestUser.getPassword();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // auto setting when insert
    @PrePersist
    public void onPrePersist() {
        createdDate = Timestamp.valueOf(LocalDateTime.now());
        modifiedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    // auto setting when update
    @PreUpdate
    public void onPreUpdate() {
        modifiedDate = Timestamp.valueOf(LocalDateTime.now());
    }
}
