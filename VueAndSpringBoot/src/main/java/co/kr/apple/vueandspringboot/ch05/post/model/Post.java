package co.kr.apple.vueandspringboot.ch05.post.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("TB_POST")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @Column("POST_ID")
    private Integer postId;
    @Column("POST_TITLE")
    private String postTitle;
    @Column("POST_CONTENT")
    private String postContent;
    @Column("POST_REGIST_DT")
    private LocalDateTime postRegistDt;
    @Column("POST_UPDATE_DT")
    private LocalDateTime postUpdateDt;
    @Column("POST_DEL_YN")
    private String postDelYn;

    @Column("BOARD_ID")
    private Integer boardId;
    @Column("USER_ID")
    private Integer userId;
}
