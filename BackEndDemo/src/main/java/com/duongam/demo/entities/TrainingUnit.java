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

public class TrainingUnit {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "training_unit_code")
    private String unitCode;

    private String name;

    private Integer dayNumber;


    @ManyToOne
    @JoinColumn(name = "syllabus_code")
    private Syllabus syllabus;


    @OneToMany(mappedBy = "unitCode")
    private List<TrainingContent> trainingContent;
}
