package co.kr.apple.vueandspringboot.ch05.statistic.repository;

import co.kr.apple.vueandspringboot.ch05.statistic.model.LogMenu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<LogMenu, Integer> {
}
