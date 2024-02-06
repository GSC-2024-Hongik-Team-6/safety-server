package gsc.backend.controller;

import gsc.backend.domain.enums.QuizType;
import gsc.backend.dto.response.QuizMetaDTO;
import gsc.backend.dto.response.QuizMultiDataDTO;
import gsc.backend.dto.response.QuizMultiResponseDTO;
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

    @GetMapping("/quiz/{educationId}/multi")
    public ResponseEntity<QuizMultiResponseDTO> getMulti(@PathVariable("educationId") Long educationId) {

        // Quiz 데이터 조회
        QuizMultiDataDTO quizMultiDataDTO = quizService.getMulti(educationId);

        // Meta 세팅
        QuizMetaDTO quizMetaDTO = QuizMetaDTO.builder()
                .type(QuizType.MULTIPLE_CHOICE)
                .build();

        // 반환값 세팅
        QuizMultiResponseDTO quizMultiResponseDTO = QuizMultiResponseDTO.builder()
                .meta(quizMetaDTO)
                .item(quizMultiDataDTO)
                .build();

        return ResponseEntity.ok(quizMultiResponseDTO);
    }

}
