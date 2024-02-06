package gsc.backend.service;

import gsc.backend.domain.Choice;
import gsc.backend.domain.Education;
import gsc.backend.domain.Quiz;
import gsc.backend.domain.QuizAnswer;
import gsc.backend.domain.enums.EducationType;
import gsc.backend.dto.response.QuizMultiDataDTO;
import gsc.backend.dto.response.QuizMultiOptionsDTO;
import gsc.backend.repository.ChoiceRepository;
import gsc.backend.repository.EducationRepository;
import gsc.backend.repository.QuizAnswerRepository;
import gsc.backend.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {

    private final EducationRepository educationRepository;
    private final QuizRepository quizRepository;
    private final QuizAnswerRepository quizAnswerRepository;
    private final ChoiceRepository choiceRepository;

    public QuizMultiDataDTO getMulti(Long educationId) {

        // educationId 를 가지고 있는 education 의 name == quiz 의 eduType && quizType 이 multichoice
        Education education = educationRepository.findById(educationId).orElseThrow(RuntimeException::new);
        EducationType educationName = EducationType.valueOf(education.getName());

        // 랜덤 퀴즈
        Quiz quiz = quizRepository.findByEducationTypeANDEducationName(educationName);

        // 퀴즈 정답
        QuizAnswer quizAnswer = quizAnswerRepository.findByQuiz_Id(quiz.getId());

        // 퀴즈 선지
        List<Choice> choiceList = choiceRepository.findAllByQuiz_Id(quiz.getId());
        List<QuizMultiOptionsDTO> optionsDTO = choiceList.stream()
                .map(m -> QuizMultiOptionsDTO.builder()
                        .id(m.getChoiceNumber())
                        .description(m.getDescription())
                        .imageUrl(m.getImageUrl())
                        .build())
                .toList();

        QuizMultiDataDTO quizMultiDataDTO = QuizMultiDataDTO.builder()
                .id(quiz.getId())
                .description(quiz.getQuestion())
                .answer(quizAnswer.getAnswer())
                .options(optionsDTO)
                .build();

        return quizMultiDataDTO;
    }
}
