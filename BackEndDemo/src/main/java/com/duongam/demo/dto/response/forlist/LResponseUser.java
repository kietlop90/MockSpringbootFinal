package com.duongam.demo.dto.response.forlist;

import com.duongam.demo.entities.Role;
import com.duongam.demo.entities.User;
import com.duongam.demo.entities.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseUser {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String role;
    private String dob; // Đã định dạng theo dd/MM/yyyy
    private String gender;
    private Boolean status;
    private String createdBy;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;

    // Constructor để chuyển đổi từ User sang UserDTO
    public LResponseUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.role = user.getRole() != null ? user.getRole().getName().name() : null; // Giả sử Role có phương thức getName()
        this.dob = user.getDob() != null ? user.getDob().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
        this.gender = user.getGender() != null ? user.getGender().toString() : null;
        this.status = user.getStatus();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.modifiedBy = user.getModifiedBy();
        this.modifiedDate = user.getModifiedDate();
    }
}
