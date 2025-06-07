package co.kr.apple.vueandspringboot.menu.repository;

import co.kr.apple.vueandspringboot.menu.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Integer> {
}
