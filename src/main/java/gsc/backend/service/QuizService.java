package gsc.backend.service;

import gsc.backend.domain.*;
import gsc.backend.domain.mapping.UserEducation;
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
    private final UserEducationRepository userEducationRepository;
    private final EducationRepository educationRepository;

    public QuizResponseDTO getQuiz(String userUuid, Long quizId) {

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

        // answerStatus 세팅
        User user = userRepository.findByUuid(userUuid);
        UserQuiz userQuiz = userQuizRepository.findByUserAndQuiz(user, quiz);
        int answerStatus;
        if (!userQuiz.isSolved()) {
            answerStatus = 0;
        } else if (!userQuiz.isCorrect()) {
            answerStatus = 2;
        } else {
            answerStatus = 1;
        }

        // 반환값 세팅
        QuizResponseDTO quizResponseDTO = QuizResponseDTO.builder()
                .id(quizId)
                .answerStatus(answerStatus)
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

        // 사용자의 퀴즈 정답 여부 저장 & userSolvedQuizCount 업데이트
        if (request.getIsCorrect()) {
            Education education = educationRepository.findById(quiz.getEducation().getId()).orElseThrow(RuntimeException::new);
            UserEducation userEducation = userEducationRepository.findByUserAndEducation(user, education);
            if (!userQuiz.isSolved()) {
                userEducation.updateQuizCount();
            }

            userQuiz.updateIsSolved();
            userQuiz.updateIsCorrect(true);
        } else {
            userQuiz.updateIsSolved();
            userQuiz.updateIsCorrect(false);
        }
    }
}
