package co.kr.apple.vueandspringboot.ch06.code.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CodeListDto {
    private List<CodeResponseDto> cateList;
    private List<CodeResponseDto> optList;
}
