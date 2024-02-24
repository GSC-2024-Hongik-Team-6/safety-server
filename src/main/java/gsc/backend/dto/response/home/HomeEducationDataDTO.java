package gsc.backend.dto.response.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeEducationDataDTO {
    private Long educationId;
    private String educationName;
    private String educationDescription;
    private String educationDetail;
    private List<String> images;
    private String thumbUrl;
    private int solvedQuizCount;
    private int totalQuizCount;
}
