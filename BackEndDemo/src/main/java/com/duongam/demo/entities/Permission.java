//package com.duongam.demo.entities;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//public class Permission {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role;
//}
