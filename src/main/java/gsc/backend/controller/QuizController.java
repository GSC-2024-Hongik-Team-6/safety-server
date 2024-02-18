package gsc.backend.controller;

import gsc.backend.dto.request.QuizAnswerRequestDTO;
import gsc.backend.dto.response.quiz.QuizResponseDTO;
import gsc.backend.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Tag(name = "퀴즈", description = "퀴즈 관련 API")
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz/{quizId}")
    @Operation(summary = "퀴즈 조회 API", description = "퀴즈 ID 에 해당하는 퀴즈가 반환되는 API 입니다")
    public ResponseEntity<QuizResponseDTO> getMulti(@PathVariable("quizId") Long quizId) {

        // Quiz 데이터 조회
        QuizResponseDTO quizResponseDTO = quizService.getQuiz(quizId);

        return ResponseEntity.ok(quizResponseDTO);
    }

    // Quiz Answer
    @PostMapping("/quiz/{quizId}")
    @Operation(summary = "퀴즈 정답 결과 등록 API", description = "사용자의 퀴즈 정답 결과를 등록하는 API 입니다")
    public ResponseEntity<String> addQuizAnswerResult(Principal principal,
                                                      @PathVariable("quizId") Long quizId,
                                                      @RequestBody @Valid QuizAnswerRequestDTO request) {

        // 사용자
        String userUuid = principal.getName();

        quizService.addQuizAnswerResult(userUuid, quizId, request);
        return ResponseEntity.ok("Success");
    }
}
