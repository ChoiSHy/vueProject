package co.kr.apple.vueandspringboot.ch06.option;

import co.kr.apple.vueandspringboot.ch06.option.model.Option;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OptionRepository extends CrudRepository<Option, Integer> {
    List<Option>findByQuestId(Integer questId);
}
