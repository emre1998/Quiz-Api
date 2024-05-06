package com.App.Quizapi.Controller;


import com.App.Quizapi.Model.Question;
import com.App.Quizapi.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //belirli testin tüm sorularını getir
    @GetMapping("/{testId}")
    public ResponseEntity<List<Question>> getQuestionsByTestId(@PathVariable Long testId) {
        List<Question> questions = questionService.getQuestionsByTestId(testId);
        if(questions == null || questions.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(questions);
    }

}
