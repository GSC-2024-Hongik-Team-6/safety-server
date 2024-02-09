package gsc.backend.controller;

import gsc.backend.dto.request.QuizAnswerRequestDTO;
import gsc.backend.dto.response.QuizResponseDTO;
import gsc.backend.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    // Quiz Answer
    @PostMapping("/quiz/{quizId}")
    public ResponseEntity<String> addQuizAnswerResult(Principal principal,
                                                      @PathVariable("quizId") Long quizId,
                                                      @RequestBody @Valid QuizAnswerRequestDTO request) {

        // 사용자
        String userUuid = principal.getName();

        quizService.addQuizAnswerResult(userUuid, quizId, request);
        return ResponseEntity.ok("Success");
    }
}
