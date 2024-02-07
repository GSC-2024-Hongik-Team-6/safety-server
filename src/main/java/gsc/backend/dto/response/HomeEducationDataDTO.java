package gsc.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeEducationDataDTO {
    private Long educationId;
    private String educationName;
    private String educationDescription;
    private String educationDetail;
    private int solvedQuizCount;
    private int totalQuizCount;
}