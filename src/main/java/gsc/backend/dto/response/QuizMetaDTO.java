package gsc.backend.dto.response;

import gsc.backend.domain.enums.QuizType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizMetaDTO {
    private QuizType type;
}
