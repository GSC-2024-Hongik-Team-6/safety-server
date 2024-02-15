package gsc.backend.service;

import gsc.backend.domain.Education;
import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;
import gsc.backend.domain.mapping.UserEducation;
import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.response.home.HomeEducationDataDTO;
import gsc.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HomeService {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    private final UserEducationRepository userEducationRepository;
    private final QuizRepository quizRepository;
    private final UserQuizRepository userQuizRepository;

    public List<HomeEducationDataDTO> getHomeData(String userUuid) {

        // 사용자 조회
        User user = userRepository.findByUuid(userUuid);
        if (user == null) { // 저장된 사용자가 없을 시 DB 에 새로 저장
            User newUser = User.createUser(userUuid);
            userRepository.save(newUser);

            // UserEducation 세팅
            List<Education> allEducations = educationRepository.findAll();
            for (Education education : allEducations) {
                UserEducation userEducation = UserEducation.createUserEduaction(newUser, education);
                userEducationRepository.save(userEducation);
            }

            // UserQuiz 세팅
            List<Quiz> allQuizzes = quizRepository.findAll();
            for (Quiz quiz : allQuizzes) {
                UserQuiz userQuiz = UserQuiz.createUserQuiz(newUser, quiz);
                userQuizRepository.save(userQuiz);
            }
        }

        User nowUser = userRepository.findByUuid(userUuid);

        // Education 데이터 반환
        // User, Education 연관 관계의 UserEducation & 각각 해당 Education Id 의 data
        List<Education> educationList = educationRepository.findAll();
        return educationList.stream()
                .map(m -> {

                    UserEducation userEducation = userEducationRepository.findByUserAndEducation(nowUser, m);

                    return HomeEducationDataDTO.builder()
                            .educationId(m.getId())
                            .educationName(m.getName())
                            .educationDescription(m.getDescription())
                            .educationDetail(m.getDetail())
                            .solvedQuizCount(userEducation.getUserSolvedQuizCount())
                            .totalQuizCount(m.getTotalQuizCount())
                            .build();
                })
                .collect(Collectors.toList());

    }
}
