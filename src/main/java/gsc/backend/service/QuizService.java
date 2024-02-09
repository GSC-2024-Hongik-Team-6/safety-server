package gsc.backend.service;

import gsc.backend.domain.Choice;
import gsc.backend.domain.Quiz;
import gsc.backend.domain.QuizAnswer;
import gsc.backend.domain.enums.QuizType;
import gsc.backend.dto.response.QuizDataDTO;
import gsc.backend.dto.response.QuizOptionsDTO;
import gsc.backend.dto.response.QuizResponseDTO;
import gsc.backend.repository.ChoiceRepository;
import gsc.backend.repository.EducationRepository;
import gsc.backend.repository.QuizAnswerRepository;
import gsc.backend.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {

    private final EducationRepository educationRepository;
    private final QuizRepository quizRepository;
    private final QuizAnswerRepository quizAnswerRepository;
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
                        .id(m.getChoiceNumber())
                        .description(m.getDescription())
                        .imageUrl(m.getImageUrl())
                        .build())
                .toList();

        QuizDataDTO quizDataDTO = QuizDataDTO.builder()
                .id(quiz.getId())
                .description(quiz.getQuestion())
                .answer(quizAnswer.getAnswer())
                .options(optionsDTO)
                .build();

        // 반환값 세팅
        QuizResponseDTO quizResponseDTO = QuizResponseDTO.builder()
                .id(quizId)
                .type(quiz.getQuizType())
                .item(quizDataDTO)
                .build();

        return quizResponseDTO;
    }
}
