package com.App.Quizapi.Repository;

import com.App.Quizapi.Model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long> {

    List<Score> findByUserId(Long userId);

    List<Score> findByTestId(Long testId);

    Optional<Score> findByUserIdAndTestId(Long userId, Long testId);
}
