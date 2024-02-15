package gsc.backend.dto.response.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDataDTO {
    private Long id;
    private String description;
    private int answer;
    private List<QuizOptionsDTO> options;
}
