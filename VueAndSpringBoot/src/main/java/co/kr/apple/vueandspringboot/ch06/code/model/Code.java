package co.kr.apple.vueandspringboot.ch06.code.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("TB_COMM_CODE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Code {
    @Id
    @Column("CODE_CODE")
    private String codeCode;
    @Column("GRP_CODE")
    private String grpCode;
    @Column("CODE_NAME")
    private String codeName;
    @Column("CODE_CREATE_DT")
    private LocalDateTime codeCreateDt;
    @Column("CODE_UPDATE_DT")
    private LocalDateTime codeUpdateDt;

}
