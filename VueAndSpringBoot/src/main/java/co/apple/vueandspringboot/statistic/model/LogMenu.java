package co.apple.vueandspringboot.statistic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("TB_LOG_MENU")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LogMenu {
    @Id
    @Column("LM_ID")
    private Integer lmId;
    @Column("LM_DT")
    private LocalDateTime lmDt;
    @Column("LM_SESSION_ID")
    private String lmSessionId;
    @Column("MENU_ID")
    private Integer menuId;
    @Column("USER_ID")
    private Integer userId;
}
