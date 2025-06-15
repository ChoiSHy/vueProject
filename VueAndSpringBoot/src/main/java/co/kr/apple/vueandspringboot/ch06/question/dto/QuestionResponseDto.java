package co.kr.apple.vueandspringboot.ch06.question.dto;


import co.kr.apple.vueandspringboot.ch06.option.dto.OptionResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class QuestionResponseDto {
    // pk
    private Integer questId;
    // 순서
    private Integer questNum;
    // 문항 제목
    private String questValue;
    // 답변 개수
    private Integer questLimit;
    // 필수 여부
    private String questRequireYn;
    // 문항 종류 (섹션: 0/객관식: 1/주관식: 2)
    private Integer questType;
    // 소속 설문조사
    private Integer survId;
    // 상위 문항 ID
    private Integer questParentId;
    // 문항 분류
    private String cateName;
    //하위 문항
    List<QuestionResponseDto> children;
    // 선택지들
    List<OptionResponseDto> options;
}
