package co.kr.apple.vueandspringboot.ch05.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticResponseDto {
    List<String> keyList;
    List<Integer> countList;
}
