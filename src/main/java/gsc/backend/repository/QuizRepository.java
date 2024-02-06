package gsc.backend.repository;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.enums.EducationType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    // 해당 EducationType 의 Quiz 객체 조회
    @Query(value = "SELECT q FROM Quiz q WHERE q.educationType = :educationName AND q.quizType = 'MULTIPLE_CHOICE' ORDER BY RAND() limit 1")
    Quiz findByEducationTypeANDEducationName(@Param("educationName") EducationType educationName);

    List<Quiz> findAllByEducationType();
}
