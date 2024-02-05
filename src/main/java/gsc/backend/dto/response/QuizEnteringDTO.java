package gsc.backend.dto.response;

import gsc.backend.dto.response.DataNumDTO;
import gsc.backend.dto.response.QuestionNumDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizEnteringDTO {
    private QuestionNumDTO questionNum;
    private DataNumDTO dataNum;


    //QuestionNum과 data부분 분리시키기
}