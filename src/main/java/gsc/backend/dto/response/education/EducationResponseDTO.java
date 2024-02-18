package gsc.backend.dto.response.education;

import gsc.backend.domain.EducationImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationResponseDTO {

    // Education id
    private Long id;

    // Education name
    private String title;

    // Education description
    private String description;

    // Education thumbUrl
    private String thumbUrl;

    // Education Detail Description
    private String detail;

    // Education imagesUrl List
    private List<String> images;

    // Education Quizzes Info List
    private List<EducationQuizDTO> quizzes;
}
