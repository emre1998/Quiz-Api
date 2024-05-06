package com.App.Quizapi.Service;

import com.App.Quizapi.DTO.QuestionAnswerDTO;
import com.App.Quizapi.DTO.SubmitAnswerRequestDTO;
import com.App.Quizapi.Model.Question;
import com.App.Quizapi.Model.Score;
import com.App.Quizapi.Repository.QuestionRepository;
import com.App.Quizapi.Repository.ScoreRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private QuestionService questionService;

    public int submitAnswerAndGetScore(String token, SubmitAnswerRequestDTO request) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Long userId = Long.parseLong(claims.getSubject());

        int score = calculateScore(request.getQuestions());

        Score scoreEntity = new Score();
        scoreEntity.setUserId(userId);
        scoreEntity.setScore(score);
        scoreRepository.save(scoreEntity);

        return score;
    }

    private int calculateScore(List<QuestionAnswerDTO> answers) {
        int totalScore = 0;
        for(QuestionAnswerDTO answer : answers) {
            if(answer.isCorrect()) {
                totalScore += 25;
                if(totalScore > 100) {
                    totalScore = 100;
                    break;
                }
                }
            }
        return totalScore;
        }

    public int getScoreByUserIdAndTestId(Long userId, Long testId) {
        Optional<Score> optionalScore = scoreRepository.findByUserIdAndTestId(userId, testId);
        return optionalScore.map(Score::getScore).orElse(0);
    }
}
