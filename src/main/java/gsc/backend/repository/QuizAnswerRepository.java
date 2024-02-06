package gsc.backend.repository;

import gsc.backend.domain.QuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, Long> {
    
    // 퀴즈 정답 찾기
    QuizAnswer findByQuiz_Id(Long quizId);
}
