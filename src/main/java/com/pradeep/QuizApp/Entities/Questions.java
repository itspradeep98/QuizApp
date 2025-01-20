package com.pradeep.QuizApp.Entities;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Questions")
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question-title")
    private String questionTitle;

    @Column(name = "option1")
    private String option1;

    @Column(name = "option2")
    private String option2;

    @Column(name = "option3")
    private String option3;

    @Column(name = "option4")
    private String option4;

    @Column(name = "right-Answer")
    private String rightAnswer;

    @Column(name = "difficulty-level")
    private String difficultyLevel;
    
    @Column(name = "category")
    private String category;

}
