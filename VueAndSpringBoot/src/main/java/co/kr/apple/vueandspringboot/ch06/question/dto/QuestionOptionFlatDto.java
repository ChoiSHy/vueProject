package co.kr.apple.vueandspringboot.ch06.question.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionOptionFlatDto {
    // Question fields
    private Integer questId;
    private Integer questNum;
    private String questValue;
    private Integer questLimit;
    private String questRequireYn;
    private Integer questType;
    private Integer survId;
    private Integer questParentId;
    private String cateName;

    // Option fields
    private Integer optId;
    private String optValue;
    private Integer optNum;
    private Integer optType;
}
