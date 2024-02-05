package gsc.backend.domain;

import gsc.backend.domain.common.BaseEntity;
import gsc.backend.domain.enums.EducationType;
import gsc.backend.domain.enums.QuizType;
import gsc.backend.domain.mapping.UserQuiz;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Quiz extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<UserQuiz> userQuizList = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Choice> choiceList = new ArrayList<>();

    // 퀴즈 질문
    private String question;

    // 퀴즈 난이도
    private int difficultyLevel;

    // 퀴즈 유형
    @Enumerated(EnumType.STRING)
    private QuizType quizType;

    // 교육 유형
    @Enumerated(EnumType.STRING)
    private EducationType educationType;


}
