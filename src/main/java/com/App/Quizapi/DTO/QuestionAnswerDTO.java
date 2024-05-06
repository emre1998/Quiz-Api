package com.App.Quizapi.DTO;

import lombok.Data;

@Data
public class QuestionAnswerDTO {
    private Long questionId;
    private String answerText;
    private boolean isCorrect;
}
