package gsc.backend.service;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;

import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.response.QuizEnteringDTO;
import gsc.backend.dto.response.QuestionNumDTO;

import gsc.backend.repository.EducationRepository;
import gsc.backend.repository.QuizRepository;
import gsc.backend.repository.UserRepository;
import gsc.backend.repository.UserQuizRepository; //확인 필요
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
    private final EducationRepository educationRepository;
    private final QuizRepository quizRepository;
    private final UserQuizRepository userQuizRepository;

    public QuizEnteringDTO getQuizEnteringData(String userUuid, Long educationId) {

        //유저 정보 가져오기
        User nowUser = userRepository.findByUuid(userUuid);

        // Education 이름 조회
        String educationName = educationRepository.findById(educationId).orElseThrow(RuntimeException::new).getName();

        // Quiz 조회 : educationName == quiz의 educationType
        List<Quiz> quizList = quizRepository.findAllByEducationType(); //함수 쓰고 데이터반환!

            return (QuizEnteringDTO) quizList.stream()
                    .map(m -> {
            //mapping 부터 다시
                        UserQuiz userQuiz = userQuizRepository.findUserAndQuiz(nowUser, m);

                        return QuestionNumDTO.builder()
                                .id(m.getId())
                                .isSolved(m.equals(educationName))
                                .build();
                    })//quizId와 isSolved를 무분별 리스트로 반환한다

                    .collect(Collectors.toList());

    }
}
