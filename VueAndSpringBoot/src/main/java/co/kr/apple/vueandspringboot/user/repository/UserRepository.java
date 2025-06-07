package co.kr.apple.vueandspringboot.user.repository;

import co.kr.apple.vueandspringboot.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
