package com.mock.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "dob", columnDefinition = "DATE COMMENT 'date of birthday'")
    private LocalDate dob;

    @Column(name = "is_active", nullable = false,
            columnDefinition = "boolean DEFAULT true COMMENT 'true: active, false: deactivated'")
    private boolean isActive = true;

    @Column(name = "email")
    private String email;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    // auto setting when insert
    @PrePersist
    protected void onPrePersist() {
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    // auto setting when update
    @PreUpdate
    protected void onPreUpdate() {
        modifiedAt = LocalDateTime.now();
    }

    //private List<String> roles;
}
