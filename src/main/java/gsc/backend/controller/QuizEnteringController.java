package gsc.backend.controller;

import gsc.backend.dto.response.DataNumDTO;
import gsc.backend.dto.response.QuestionNumDTO;
import gsc.backend.dto.response.QuizEnteringDTO;
import gsc.backend.service.QuizEnteringService;


import jakarta.websocket.server.PathParam;
import org.springframework.data.repository.query.Param;

import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class QuizEnteringController {
    //Get은 Form화면을 열어보고
    //Post는 데이터 등록이 목표

    private final QuizEnteringService quizEnteringService;


    @GetMapping("/quizzes")

    public ResponseEntity<List<QuizEnteringDTO>> getQuizEntering(@Param("educationId") Long educationId, Principal principal) {

        // 사용자 uuid 조회
        String userUuid = principal.getName();

        //quiz 조회
        List<QuestionNumDTO> quizDto = (List<QuestionNumDTO>) quizEnteringService.getQuizEnteringData(userUuid, educationId);

        // meta 세팅
        DataNumDTO dataNumDTO = DataNumDTO.builder()
                .count(quizDto.size())
                .build();

        // QuizEnteringDTO 세팅
        QuizEnteringDTO quizEnteringDTO = QuizEnteringDTO.builder()
                .meta(dataNumDTO)
                .data(quizDto)
                .build();

        return ResponseEntity.ok(Collections.singletonList(quizEnteringDTO));
    }
}