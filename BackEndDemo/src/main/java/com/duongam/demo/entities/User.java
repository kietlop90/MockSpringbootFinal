package com.duongam.demo.entities;

import com.duongam.demo.dto.request.forcreate.CRequestUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> roles;

	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "gender")
	private String gender;

	@Column(name = "status", nullable = false,
			columnDefinition = "boolean DEFAULT true COMMENT 'true: active, false: deactivated'")
	private boolean status;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name = "modified_date")
	private LocalDate modifiedDate;

	@Column(unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Transient
	public List<String> getListOfRoles(){
		ArrayList<String> roleList = new ArrayList<>();
		roles.forEach(role -> {
			roleList.add(role.getName().toString());
		});
		return roleList;
	}

	public User(CRequestUser cRequestUser){
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
		createdDate = LocalDate.now();
		modifiedDate = LocalDate.now();
	}

	// auto setting when update
	@PreUpdate
	public void onPreUpdate() {
		modifiedDate = LocalDate.now();
	}
}