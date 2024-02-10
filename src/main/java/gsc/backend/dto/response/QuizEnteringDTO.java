package gsc.backend.dto.response;

import gsc.backend.dto.response.DataNumDTO;
import gsc.backend.dto.response.QuestionNumDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class QuizEnteringDTO {
    private List<QuestionNumDTO> data;
    private DataNumDTO meta;
}
