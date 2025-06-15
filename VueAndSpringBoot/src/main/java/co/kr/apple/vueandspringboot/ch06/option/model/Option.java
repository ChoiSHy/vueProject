package co.kr.apple.vueandspringboot.ch06.option.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("TB_SURV_OPTION")
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @Column("OPT_ID")
    private Integer optId;

    @Column("QUEST_ID")
    private Integer questId;

    @Column("OPT_VALUE")
    private String optValue;

    @Column("OPT_NUM")
    private Integer optNum;

    @Column("OPT_TYPE")
    private Integer optType;

    @Column("OPT_CREATE_DT")
    private LocalDateTime optCreateDt;

    @Column("OPT_UPDATE_DT")
    private LocalDateTime optUpdateDt;

    // Getters and Setters
}

