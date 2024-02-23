package com.duongam.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainingProgramSyllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_program_syllabus_id")
    private Long id;

    private String sequence; // cai ma lop, no duoc ket hop boi syllabus va tang dan theo moi khoa

    @ManyToOne
    @JoinColumn(name = "syllabus_code")
    private Syllabus syllabusCode;

    @ManyToOne
    @JoinColumn(name = "training_program")
    private TrainingProgram trainingProgramCode;
}
