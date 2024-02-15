package gsc.backend.domain.mapping;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;
import gsc.backend.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserQuiz extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_quiz_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // 문제의 출제 여부
    private boolean isSolved;

    // 사용자의 정답 여부
    private boolean isCorrect;

    public void updateIsSolved() {
        this.isSolved = true;
    }

    public void updateIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public static UserQuiz createUserQuiz(User user, Quiz quiz) {
        UserQuiz userQuiz = UserQuiz.builder()
                .user(user)
                .quiz(quiz)
                .isSolved(false)
                .isCorrect(false)
                .build();
        return userQuiz;
    }

}
