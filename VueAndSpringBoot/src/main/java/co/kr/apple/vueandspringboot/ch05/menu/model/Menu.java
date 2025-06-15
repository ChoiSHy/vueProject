package co.kr.apple.vueandspringboot.ch05.menu.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table("TB_MENU")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "menuList")
public class Menu {
    @Id
    @Column("MENU_ID")
    private Integer menuId;
    @MappedCollection(idColumn = "MENU_ID")
    private MenuName menuName;
    @Column("MENU_USE_YN")
    private String menuUseYn;
    @Column("MENU_IDX")
    private Integer menuIdx;
    @Column("MENU_URL")
    private String menuUrl;
    @Column("MENU_REGIST_DT")
    private LocalDateTime menuRegistDt;
    @Column("MENU_UPDATE_DT")
    private LocalDateTime menuUpdateDt;
    @Column("MENU_AUTH")
    private Integer menuAuth;
    @Column("MENU_PARENT_ID")
    private Integer menuParentId;
    @Column("BOARD_ID")
    private Integer boardId;
    @MappedCollection(idColumn = "MENU_PARENT_ID",keyColumn = "MENU_ID")
    List<Menu> menuList = new ArrayList<>();

}
