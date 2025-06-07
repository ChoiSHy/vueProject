package co.kr.apple.vueandspringboot.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@SuperBuilder
@Getter
@Table("TB_MENU_NAME")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuName {
    @Id
    @Column("MENU_ID")
    private Integer menuId;
    @Column("MENU_NAME")
    private String menuName;
}
