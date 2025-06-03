package co.apple.vueandspringboot.menu.repository;

import co.apple.vueandspringboot.menu.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, Integer> {
}
