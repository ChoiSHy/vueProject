package co.apple.vueandspringboot.statistic.controller;

import co.apple.vueandspringboot.statistic.dto.StatisticDataDto;
import co.apple.vueandspringboot.statistic.dto.StatisticResponseDto;
import co.apple.vueandspringboot.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
