package gsc.backend.controller;

import gsc.backend.domain.enums.QuizType;
import gsc.backend.dto.request.QuizAnswerRequestDTO;
import gsc.backend.dto.response.QuizMetaDTO;
import gsc.backend.dto.response.QuizDataDTO;
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
        QuizDataDTO quizDataDTO = quizService.getQuiz(quizId);

        // Meta 세팅
        QuizMetaDTO quizMetaDTO = QuizMetaDTO.builder()
                .type(quizService.getQuizType(quizId))
                .build();

        // 반환값 세팅
        QuizResponseDTO quizResponseDTO = QuizResponseDTO.builder()
                .meta(quizMetaDTO)
                .item(quizDataDTO)
                .build();

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
