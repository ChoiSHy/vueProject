package co.kr.apple.vueandspringboot.search.model;

import co.kr.apple.vueandspringboot.menu.model.Menu;
import co.kr.apple.vueandspringboot.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table("TB_BOARD")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    @Id
    @Column("BOARD_ID")
    private Integer boardId;
    @Column("BOARD_NAME")
    private String boardName;
    @Column("BOARD_TYPE")
    private String boardType;
    @Column("BOARD_REGIST_DT")
    private LocalDateTime boardRegistDt;
    @Column("BOARD_UPDATE_DT")
    private LocalDateTime boardUpdateDt;
    @MappedCollection(idColumn = "BOARD_ID", keyColumn = "BOARD_ID")
    List<Post> postList = new ArrayList<>();

    @MappedCollection(idColumn = "BOARD_ID", keyColumn = "BOARD_ID")
    List<Menu> menuList = new ArrayList<>();
}
