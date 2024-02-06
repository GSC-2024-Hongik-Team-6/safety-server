package gsc.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizMultiResponseDTO {
    private QuizMetaDTO meta;
    private QuizMultiDataDTO item;
}