package gsc.backend.service;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;

import gsc.backend.domain.Page;
import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.response.QuizEnteringDTO;
import gsc.backend.dto.response.DataNumDTO;
import gsc.backend.dto.response.QuestionNumDTO;

import gsc.backend.repository.QuizRepository;
import gsc.backend.repository.UserRepository;
import gsc.backend.repository.UserQuizRepository; //확인 필요
import lombok.RequiredArgsConstructor;
//import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import static gsc.backend.domain.mapping.UserQuiz.isSolved;

@RequiredArgsConstructor
@Service
@Transactional

public class QuizEnteringService {

    private static final UserRepository userRepository = null;
    private static final QuizRepository quizRepository = null;
    private static final UserQuizRepository userQuizRepository = null;

    public static QuizEnteringDTO getQuizEnteringData(String userUuid, String Quiz) {

        //유저 정보 가져오기
        User nowUser = userRepository.findByUuid(userUuid);
        //DTO 가져오기

        // quiz 정보 5개씩 가져오기 [쿼리 상]
        //Quiz nowQuiz = quizRepository.findByQuiz(Quiz);
        int startNum =0 ;

        //stream을 통해서 페이지 list로 바꾼다!
        Page<Quiz> quizPage = quizRepository.findQuizPage(PageRequest.of(startNum, 5));
        return quizPage.stream()
                .map(m -> {
        //mapping 부터 다시
                    UserQuiz userQuiz = userQuizRepository.findUserAndQuiz(nowUser, m);

                    return DataNumDTO.builder()
                            .quizId(m.getId())
                            .isSolved((getSolved))
                            .build();
                })
                .collect(Collectors.toList());

    }
}
