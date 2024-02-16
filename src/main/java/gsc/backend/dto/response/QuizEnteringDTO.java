package gsc.backend.dto.response;

import gsc.backend.domain.enums.QuizType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizEnteringDTO {

    //private QuizType type;

    private List<QuestionNumDTO> data;
    private DataNumDTO meta;


}