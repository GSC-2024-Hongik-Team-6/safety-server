package gsc.backend.service;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;

import gsc.backend.domain.enums.EducationType;
import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.response.QuestionNumDTO;

import gsc.backend.repository.EducationRepository;
import gsc.backend.repository.QuizRepository;
import gsc.backend.repository.UserRepository;
import gsc.backend.repository.UserQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional

public class QuizEnteringService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final UserQuizRepository userQuizRepository;
    private final EducationRepository educationRepository;

    public List<QuestionNumDTO> getQuizEnteringData(String userUuid, Long educationId) {

        //유저 정보 가져오기
        User nowUser = userRepository.findByUuid(userUuid);

        // Education 이름 조회
        EducationType educationName = EducationType.valueOf(educationRepository.findById(educationId).orElseThrow(RuntimeException::new).getName());

        // Quiz 조회 : educationName == quiz의 educationType
        List<Quiz> quizList = quizRepository.findByEducationType(educationName);
        return quizList.stream()
                .map(m -> {
                    //mapping 부터 다시
                    UserQuiz userQuiz = userQuizRepository.findByUserAndQuiz(nowUser,m);

                    return QuestionNumDTO.builder()
                            .id(m.getId())
                            .isSolved(false)
                            .build();
                })

                .collect(Collectors.toList());

    }
}