package gsc.backend.service;

import gsc.backend.domain.Choice;
import gsc.backend.domain.Quiz;
import gsc.backend.domain.QuizAnswer;
import gsc.backend.domain.User;
import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.request.QuizAnswerRequestDTO;
import gsc.backend.dto.response.quiz.QuizDataDTO;
import gsc.backend.dto.response.quiz.QuizOptionsDTO;
import gsc.backend.dto.response.quiz.QuizResponseDTO;
import gsc.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuizAnswerRepository quizAnswerRepository;
    private final UserQuizRepository userQuizRepository;
    private final ChoiceRepository choiceRepository;

    public QuizResponseDTO getQuiz(Long quizId) {

        // 퀴즈
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(RuntimeException::new);

        // 퀴즈 정답
        QuizAnswer quizAnswer = quizAnswerRepository.findByQuiz_Id(quiz.getId());

        // 퀴즈 선지
        List<Choice> choiceList = choiceRepository.findAllByQuiz_Id(quiz.getId());
        List<QuizOptionsDTO> optionsDTO = choiceList.stream()
                .map(m -> QuizOptionsDTO.builder()
                        .number(m.getChoiceNumber())
                        .description(m.getDescription())
                        .imageUrl(m.getImageUrl())
                        .build())
                .toList();

        QuizDataDTO quizDataDTO = QuizDataDTO.builder()
                .description(quiz.getQuestion())
                .answer(quizAnswer.getAnswer())
                .options(optionsDTO)
                .build();

        // 반환값 세팅
        QuizResponseDTO quizResponseDTO = QuizResponseDTO.builder()
                .id(quizId)
                .type(quiz.getQuizType())
                .data(quizDataDTO)
                .build();

        return quizResponseDTO;
    }

    public void addQuizAnswerResult(String userUuid, Long quizId, QuizAnswerRequestDTO request) {

        // 사용자
        User user = userRepository.findByUuid(userUuid);

        // 퀴즈
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(RuntimeException::new);

        // 사용자 - 퀴즈 정보
        UserQuiz userQuiz = userQuizRepository.findByUserAndQuiz(user, quiz);

        // 사용자의 퀴즈 정답 여부 저장
        if (request.getIsCorrect()) {
            userQuiz.updateIsSolved();
            userQuiz.updateIsCorrect(true);
        } else {
            userQuiz.updateIsSolved();
            userQuiz.updateIsCorrect(false);
        }
    }
}
