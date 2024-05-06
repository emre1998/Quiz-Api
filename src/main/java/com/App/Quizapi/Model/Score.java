package com.App.Quizapi.Model;

import com.App.Quizapi.Repository.ScoreRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "score")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id", nullable = false, unique = true)
    private Long scoreId;

    @ManyToOne
   @JoinColumn(name = "test_id", nullable = false) // Test tablosuyla One-to-One ilişkisi
    private Question test;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // User tablosuyla One-to-One ilişkisi
    private User user;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public void setUserId(Long userId) {
        this.user = new User();
        this.user.setUserId(userId);
    }

    public void save(ScoreRepository scoreRepository) {
        scoreRepository.save(this);
    }
}
