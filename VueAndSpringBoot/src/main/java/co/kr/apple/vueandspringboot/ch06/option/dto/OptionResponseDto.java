package co.kr.apple.vueandspringboot.ch06.option.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OptionResponseDto {
    private Integer optId;
    private Integer questId;
    private String optValue;
    private Integer optNum;
    private Integer optType;
}
