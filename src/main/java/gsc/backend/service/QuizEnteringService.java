package gsc.backend.service;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;

import gsc.backend.domain.enums.EducationType;
import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.response.QuestionNumDTO;

import gsc.backend.dto.response.QuizEnteringDTO;
import gsc.backend.repository.EducationRepository;
import gsc.backend.repository.QuizRepository;
import gsc.backend.repository.UserRepository;
import gsc.backend.repository.UserQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional

public class QuizEnteringService {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    private final QuizRepository quizRepository;
    private final UserQuizRepository userQuizRepository;

    public QuizEnteringDTO getQuizEnteringData(String userUuid, Long educationId) {

        // 사용자 가져오기
        User nowUser = userRepository.findByUuid(userUuid);
        // Education 이름 조회
        EducationType educationName = EducationType.valueOf(educationRepository.findById(educationId).orElseThrow(RuntimeException::new).getName());
        // Quiz 조회 : educationName == quiz의 educationType
        List<Quiz> quizList = quizRepository.findByEducationType(educationName);

        return (QuizEnteringDTO) quizList.stream()
                .map(m -> {
                    // 정적 변수가 아니라서 생략 UserQuiz userQuiz = UserQuizRepository.findByUserAndQuiz(nowUser, m);-퀴즈 조회이기 때문에
                    return QuestionNumDTO.builder()
                            .id(m.getId())
                            .isSolved(false)
                            .build();
                })//quizId와 isSolved를 무분별 리스트로 반환한다

                .collect(Collectors.toList());

    }
}