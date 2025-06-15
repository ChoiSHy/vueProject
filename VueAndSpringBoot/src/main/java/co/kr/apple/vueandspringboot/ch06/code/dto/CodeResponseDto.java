package co.kr.apple.vueandspringboot.ch06.code.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeResponseDto {
    private String code;
    private String group;
    private String name;
}
