package co.kr.apple.vueandspringboot.ch06.question.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("TB_SURV_QUEST")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    @Id
    @Column("QUEST_ID")
    private Integer questId;
    @Column("QUEST_NUM")
    private Integer questNum;
    @Column("QUEST_VALUE")
    private String questValue;
    @Column("QUEST_LIMIT")
    private Integer questLimit;
    @Column("QUEST_REQUIRE_YN")
    private String questRequireYn;
    @Column("QUEST_TYPE")
    private Integer questType;
    @Column("SURV_ID")
    private Long survId;
    @Column("QUEST_PARENT_ID")
    private Long questParentId;
    @Column("QUEST_CREATE_DT")
    private LocalDateTime questCreateDt;
    @Column("QUEST_UPDATE_DT")
    private LocalDateTime questUpdateDt;
    @Column("QUEST_CATE")
    private String questCate;
}

