package gsc.backend.repository;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;
import gsc.backend.domain.mapping.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {

    UserQuiz findByUserAndQuiz(User user, Quiz quiz);
}
