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
public class SyllabusObjective implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "syllabus_object_id")
    private Long id;

    private String content;


    @OneToOne(mappedBy = "syllabusObjective")
    private Syllabus syllabus;// map voi Syllabus
}
