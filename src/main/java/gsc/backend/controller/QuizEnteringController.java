package gsc.backend.controller;

import gsc.backend.dto.response.DataNumDTO;
import gsc.backend.dto.response.QuestionNumDTO;
import gsc.backend.dto.response.QuizEnteringDTO;
import gsc.backend.service.QuizEnteringService;

import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class QuizEnteringController {
    //Get은 Form화면을 열어보고
    //Post는 데이터 등록이 목표

    private final QuizEnteringService quizEnteringService;
    @GetMapping("/quiz")

    public ResponseEntity<List<QuizEnteringDTO>> getQuizEntering(Principal principal) {

        // 사용자 uuid 조회
        String userUuid = principal.getName();

        QuizEnteringDTO questionDto = QuizEnteringService.getQuizEnteringData(userUuid);

        // DataNumDTO 세팅
        DataNumDTO dataNumDTO = DataNumDTO.builder()
                .isSolved(questionDto.contains())
                .build( );

        // QuizEnteringDTO 세팅
        QuizEnteringDTO quizEnteringDTO = QuizEnteringDTO.builder()
                .dataNum(dataNumDTO)
                .questionNum(questionDto.getQuestionNum())
                .build();

        return ResponseEntity.ok(Collections.singletonList(quizEnteringDTO));
    }

}
