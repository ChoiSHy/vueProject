package co.kr.apple.vueandspringboot.ch05.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("TB_LOG_SEARCH")
public class LogSearch {
    @Id
    @Column("LS_ID")
    private Integer lsId;
    @Column("LS_DT")
    private LocalDateTime lsDt;
    @Column("LS_KEYWORD")
    private String keyword;
}
