package co.kr.apple.vueandspringboot.ch05.post.repository;

import co.kr.apple.vueandspringboot.ch05.post.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
