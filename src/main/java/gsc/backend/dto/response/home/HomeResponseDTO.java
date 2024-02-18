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
public class HomeResponseDTO {
    private HomeMetaDTO meta;
    private List<HomeEducationDataDTO> data;
}
