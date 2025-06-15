package co.kr.apple.vueandspringboot.ch05.user.repository;

import co.kr.apple.vueandspringboot.ch05.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
