package com.App.Quizapi.DTO;

import lombok.Data;

import java.util.List;
@Data
public class SubmitAnswerRequestDTO {
    private List<QuestionAnswerDTO> questions;
}
