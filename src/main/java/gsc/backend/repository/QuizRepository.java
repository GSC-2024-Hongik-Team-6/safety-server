package gsc.backend.repository;

import gsc.backend.domain.Education;
import gsc.backend.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findAllByEducation(Education education);
}
