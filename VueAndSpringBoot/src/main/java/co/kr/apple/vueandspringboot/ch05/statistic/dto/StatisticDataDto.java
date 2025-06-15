package co.kr.apple.vueandspringboot.ch05.statistic.dto;

import lombok.*;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StatisticDataDto {
    private String menuName;
    private Map<String, Integer> count;
}
