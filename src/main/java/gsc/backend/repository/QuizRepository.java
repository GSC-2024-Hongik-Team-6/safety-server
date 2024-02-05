package gsc.backend.repository;

import gsc.backend.domain.Page;
import gsc.backend.domain.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    //Quiz findByQuiz(String Quiz);
    Page findQuizPage(Pageable pageable); //5개씩 가져올 수 있다
    //Pageable;// 파라미터 값을 소문자로
    //PageRequest
    //PageRequest of(int pageNumber, int pageSize)
}