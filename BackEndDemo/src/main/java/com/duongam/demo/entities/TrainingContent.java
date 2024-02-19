package com.duongam.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class TrainingContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_content_id")
    private Long id;

    private String content;

    private String deliveryType;

    private Integer duration;

    private String trainingFormat;

    private String note;


    @ManyToOne
    @JoinColumn(name = "training_unit_code")
    private TrainingUnit unitCode;


    @OneToMany(mappedBy = "trainingContent")
    private List<LearningObjective> learningObjective;

}
