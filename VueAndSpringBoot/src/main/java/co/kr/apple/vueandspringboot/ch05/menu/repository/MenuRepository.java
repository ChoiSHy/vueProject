package co.kr.apple.vueandspringboot.ch05.menu.repository;

import co.kr.apple.vueandspringboot.ch05.menu.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Integer> {
}
