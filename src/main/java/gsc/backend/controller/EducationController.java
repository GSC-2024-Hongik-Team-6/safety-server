package gsc.backend.controller;

import gsc.backend.dto.response.education.EducationResponseDTO;
import gsc.backend.dto.response.home.HomeEducationDataDTO;
import gsc.backend.dto.response.home.HomeResponseDTO;
import gsc.backend.dto.response.home.HomeMetaDTO;
import gsc.backend.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "교육 유형", description = "교육 유형 관련 API")
public class EducationController {

    /* jwt 토큰으로 현재 사용자 uuid 조회

    1. principal.getName()

    @GetMapping("/app/test")
    public String test(Principal principal) {
        return principal.getName();
    }

    2.
    @GetMapping("/app/test")
    public String test() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
     */

    private final EducationService educationService;

    @GetMapping("/education")
    @Operation(summary = "Education API", description = "모든 교육 유형을 조회하는 API 입니다")
    public ResponseEntity<HomeResponseDTO> getEducation(Principal principal) {

        // 사용자 uuid 조회
        String userUuid = principal.getName();

        // Education 데이터 조회
        List<HomeEducationDataDTO> educationDto = educationService.getHomeData(userUuid);

        // Meta 세팅
        HomeMetaDTO homeMetaDTO = HomeMetaDTO.builder()
                .count(educationDto.size())
                .build();

        // 반환값 세팅
        HomeResponseDTO homeResponseDTO = HomeResponseDTO.builder()
                .meta(homeMetaDTO)
                .data(educationDto)
                .build();

         return ResponseEntity.ok(homeResponseDTO);
    }

    @GetMapping("/education/{educationId}")
    @Operation(summary = "Education Detail API", description = "교육 유형의 자세한 정보를 조회하는 API 입니다")
    public ResponseEntity<EducationResponseDTO> getEducationDetail(Principal principal,
                                                             @PathVariable("educationId") Long educationId) {
        // 사용자 uuid 조회
        String userUuid = principal.getName();

        EducationResponseDTO response = educationService.getEducationDetail(userUuid, educationId);
        return ResponseEntity.ok(response);
    }
}
