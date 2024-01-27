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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // 문제의 출제 여부
    private boolean isSolved;

    // 사용자의 정답 여부
    private boolean isCorrect;

    // 사용자가 선택한 정답
    private String userAnswer;

}
