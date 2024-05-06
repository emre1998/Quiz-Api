package com.App.Quizapi.Controller;

import com.App.Quizapi.DTO.SubmitAnswerRequestDTO;
import com.App.Quizapi.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping("/submit-answer")
    public ResponseEntity<Integer> submitAnswerAndGetScore(@RequestHeader("Bearer ") String token, @RequestBody SubmitAnswerRequestDTO request) {
        int score = scoreService.submitAnswerAndGetScore(token, request);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

    @GetMapping("/score")
    public ResponseEntity<Integer> getScoreByUserIdAndTestId(@RequestParam Long userId, @RequestParam Long testId) {
        int score = scoreService.getScoreByUserIdAndTestId(userId, testId);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

}
