package gsc.backend.repository;

import gsc.backend.domain.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    // 퀴즈 선지 찾기
    List<Choice> findAllByQuiz_Id(Long quizId);
}
