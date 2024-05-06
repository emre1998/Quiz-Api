package com.App.Quizapi.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id",nullable = false,unique = true)
    private Long questionId;

    @Column(name = "text",nullable = false)
    private String questionText;

    @Column(name = "correct_ans",nullable = false)
    private String correctAnswer;

    @Column(name = "qstn_score",nullable = false)
    private int questionScore;

    @Column(name = "tst_id",nullable = false,unique = true)
    private Long testId;
}
