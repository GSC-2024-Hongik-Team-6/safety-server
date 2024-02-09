package gsc.backend.dto.response;

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
    private List<QuestionNumDTO> data;
    private DataNumDTO meta;


    //QuestionNum과 data부분 분리시키기
}