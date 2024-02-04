package gsc.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeEducationDataResponseDTO {
    private Long educationId;
    private String educationName;
    private String educationDescription;
    private int solvedQuizCount;
    private int totalQuizCount;
}
