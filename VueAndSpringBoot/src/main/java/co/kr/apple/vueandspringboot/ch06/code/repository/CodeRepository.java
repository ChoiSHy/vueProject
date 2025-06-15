package co.kr.apple.vueandspringboot.ch06.code.repository;

import co.kr.apple.vueandspringboot.ch06.code.model.Code;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CodeRepository extends CrudRepository<Code, String> {
    List<Code> findByGrpCode(String grpCode);
}
