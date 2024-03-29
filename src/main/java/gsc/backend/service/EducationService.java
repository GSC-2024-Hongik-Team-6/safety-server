package gsc.backend.service;

import gsc.backend.domain.Education;
import gsc.backend.domain.EducationImage;
import gsc.backend.domain.Quiz;
import gsc.backend.domain.User;
import gsc.backend.domain.mapping.UserEducation;
import gsc.backend.domain.mapping.UserQuiz;
import gsc.backend.dto.response.education.EducationQuizDTO;
import gsc.backend.dto.response.education.EducationResponseDTO;
import gsc.backend.dto.response.home.HomeEducationDataDTO;
import gsc.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EducationService {

    private final UserRepository userRepository;
    private final EducationRepository educationRepository;
    private final UserEducationRepository userEducationRepository;
    private final QuizRepository quizRepository;
    private final UserQuizRepository userQuizRepository;
    private final EducationImageRepository educationImageRepository;

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

                    List<String> images =  educationImageRepository.findAllByEducation_Id(m.getId())
                            .stream()
                            .map(EducationImage::getEducationImageUrl)
                            .collect(Collectors.toList());

                    return HomeEducationDataDTO.builder()
                            .id(m.getId())
                            .title(m.getName())
                            .description(m.getDescription())
                            .thumbUrl(m.getImage()) // thumbUrl
                            .detail(m.getDetail())
                            .images(images) // detail 정보에 들어가는 images
                            .solvedQuizCount(userEducation.getUserSolvedQuizCount())
                            .totalQuizCount(m.getTotalQuizCount())
                            .build();
                })
                .collect(Collectors.toList());

    }

    public EducationResponseDTO getEducationDetail(String userUuid, Long educationId) {

        // 사용자
        User user = userRepository.findByUuid(userUuid);

        // 교육 유형
        Education education = educationRepository.findById(educationId).orElseThrow(RuntimeException::new);

        // 교육 유형 detail image
        List<String> imageList = educationImageRepository.findAllByEducation_Id(educationId)
                .stream()
                .map(EducationImage::getEducationImageUrl)
                .collect(Collectors.toList());

        // 퀴즈
        List<Quiz> quizList = quizRepository.findAllByEducation(education);

        List<UserQuiz> userQuizList = userQuizRepository.findAllByUserAndQuizIn(user, quizList);

        List<EducationQuizDTO> quizDTOList = new ArrayList<>();
        for (UserQuiz userQuiz : userQuizList) {
            if (!userQuiz.isSolved()) {
                quizDTOList.add(EducationQuizDTO.builder()
                                .id(userQuiz.getQuiz().getId())
                                .isSolved(0)
                                .build());
            } else if (!userQuiz.isCorrect()) {
                quizDTOList.add(EducationQuizDTO.builder()
                        .id(userQuiz.getQuiz().getId())
                        .isSolved(2)
                        .build());
            } else {
                quizDTOList.add(EducationQuizDTO.builder()
                        .id(userQuiz.getQuiz().getId())
                        .isSolved(1)
                        .build());
            }
        }

        return EducationResponseDTO.builder()
                .id(education.getId())
                .title(education.getName())
                .description(education.getDescription())
                .thumbUrl(education.getImage())
                .detail(education.getDetail())
                .images(imageList)
                .quizzes(quizDTOList)
                .build();
    }
}
