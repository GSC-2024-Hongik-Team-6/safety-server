package gsc.backend.controller;

import gsc.backend.dto.response.QuizDataDTO;
import gsc.backend.dto.response.QuizResponseDTO;
import gsc.backend.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<QuizResponseDTO> getMulti(@PathVariable("quizId") Long quizId) {

        // Quiz 데이터 조회
        QuizResponseDTO quizResponseDTO = quizService.getQuiz(quizId);

        return ResponseEntity.ok(quizResponseDTO);
    }

}
