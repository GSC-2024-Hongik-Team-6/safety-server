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
    private Long id;
    private String title;
    private String description;
    private String thumbUrl;
    private String detail;
    private List<String> images;
    private int solvedQuizCount;
    private int totalQuizCount;
}
