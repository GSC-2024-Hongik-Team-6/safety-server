package gsc.backend.dto.response.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationQuizDTO {

    // Quiz id
    private Long id;

    // Quiz 해결 여부
    private int isSolved;
}
