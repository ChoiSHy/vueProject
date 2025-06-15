package co.kr.apple.vueandspringboot.ch06.question.controller;

import co.kr.apple.vueandspringboot.ch06.option.dto.OptionResponseDto;
import co.kr.apple.vueandspringboot.ch06.question.dto.QuestionResponseDto;
import co.kr.apple.vueandspringboot.ch06.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apple/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/questList")
    public ResponseEntity<?> questList(@RequestParam(value = "survId") int survId){
        List<QuestionResponseDto> questList = questionService.questList(survId);
        return ResponseEntity.ok().body(questList);
    }
    @GetMapping("/{questId}/options")
    public ResponseEntity<?> optionList(@PathVariable("questId")int questId){
        List<OptionResponseDto> opionList = questionService.findOptionsByQuestId(questId);
        return ResponseEntity.ok(opionList);
    }
}
