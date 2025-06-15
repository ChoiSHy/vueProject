package co.kr.apple.vueandspringboot.ch05.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("TB_USER")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @Column("user_id")
    private Integer userId;
    @Column("user_username")
    private String userUsername;
    @Column("user_password")
    private String userPassword;
    @Column("user_name")
    private String userName;
    @Column("user_auth")
    private Integer userAuth;
    @Column("user_regist_dt")
    private LocalDateTime userRegistDt;
    @Column("user_update_dt")
    private LocalDateTime userUpdateDt;

}

