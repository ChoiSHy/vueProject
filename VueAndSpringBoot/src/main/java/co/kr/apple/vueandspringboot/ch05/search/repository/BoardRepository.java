package co.kr.apple.vueandspringboot.ch05.search.repository;

import co.kr.apple.vueandspringboot.ch05.search.model.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer> {
    List<Board> findAll();
}
