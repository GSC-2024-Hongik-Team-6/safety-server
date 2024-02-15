package gsc.backend.dto.response.quiz;

import gsc.backend.domain.enums.QuizType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponseDTO {
    // Quiz Id
    private Long id;

    // Quiz Type
    private QuizType type;

    // Quiz 선지 리스트
    private QuizDataDTO item;
}