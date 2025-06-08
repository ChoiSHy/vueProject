package co.kr.apple.vueandspringboot.post.repository;

import co.kr.apple.vueandspringboot.post.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
