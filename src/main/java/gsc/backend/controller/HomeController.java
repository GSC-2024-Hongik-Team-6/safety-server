package gsc.backend.controller;

import gsc.backend.dto.response.HomeEducationDataDTO;
import gsc.backend.dto.response.HomeResponseDTO;
import gsc.backend.dto.response.HomeMetaDTO;
import gsc.backend.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

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

    private final HomeService homeService;

    @GetMapping("/education")
    public ResponseEntity<List<HomeResponseDTO>> getHome(Principal principal) {

        // 사용자 uuid 조회
        String userUuid = principal.getName();

        // Education 데이터 조회
        List<HomeEducationDataDTO> educationDto = homeService.getHomeData(userUuid);

        // Meta 세팅
        HomeMetaDTO homeMetaDTO = HomeMetaDTO.builder()
                .count(educationDto.size())
                .build();

        // 반환값 세팅
        HomeResponseDTO homeResponseDTO = HomeResponseDTO.builder()
                .meta(homeMetaDTO)
                .data(educationDto)
                .build();

         return ResponseEntity.ok(Collections.singletonList(homeResponseDTO));
    }

}
