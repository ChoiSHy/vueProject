package co.kr.apple.vueandspringboot.ch05.statistic.controller;

import co.kr.apple.vueandspringboot.ch05.statistic.service.StatisticService;
import co.kr.apple.vueandspringboot.ch05.statistic.dto.StatisticDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {
    private final StatisticService statisticService;
    @GetMapping("/getData")
    public ResponseEntity<?> getData(){
        try{
            List<StatisticDataDto> stcResDto = statisticService.getData();
            return ResponseEntity.ok().body(stcResDto);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
