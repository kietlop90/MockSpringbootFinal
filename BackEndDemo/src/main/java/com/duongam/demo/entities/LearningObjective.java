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

public class LearningObjective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learningObjective_id")
    private Long id;

    private String code;

    private String name;

    private String type;

    private String description;


    @ManyToOne
    @JoinColumn(name = "training_content_id")
    private TrainingContent trainingContent;
}
