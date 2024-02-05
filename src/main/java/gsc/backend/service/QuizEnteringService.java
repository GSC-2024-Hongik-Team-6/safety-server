package gsc.backend.service;

import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;

import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.response.QuizEnteringDTO;
import gsc.backend.dto.response.DataNumDTO;
import gsc.backend.dto.response.QuestionNumDTO;

import gsc.backend.repository.QuizRepository;
import gsc.backend.repository.UserRepository;
import gsc.backend.repository.UserQuizRepository; //확인 필요
import lombok.RequiredArgsConstructor;
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

    public static QuizEnteringDTO getQuizEnteringData(String userUuid) {

        // 사용자 가져오기
        User user = userRepository.findByUuid(userUuid);
        if (user == null) {
            User newUser = User.createUser(userUuid);
            userRepository.save(newUser); //홈화면으로 가야할 것 같은데

            // UserQuiz 세팅 : 5개씩 받는 걸로
            List<Quiz> allQuizes = quizRepository.findAll();
            for (Quiz quiz : allQuizes) {
                UserQuiz userQuiz = UserQuiz.createUserQuiz(newUser, quiz);
                userQuizRepository.save(userQuiz);
            }
        }
        //퀴즈 스텝 조회로 바꾸기
        User nowUser = userRepository.findByUuid(userUuid);
        //DTO 가져오기
        // private int count;
        // private int userProgress;
        // Quiz-is solved 데이터 반환
        //
        List<Quiz> quizList = quizRepository.findAll();
        public boolean contains() {
            if (quizList.length !=0)
                return

        }

        return quizList.stream()
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
