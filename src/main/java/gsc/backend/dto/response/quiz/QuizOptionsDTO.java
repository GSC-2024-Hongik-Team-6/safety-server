package gsc.backend.dto.response.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizOptionsDTO {
    private int number;
    private String description;
    private String imageUrl;
}
