package co.kr.apple.vueandspringboot.statistic.repository;

import co.kr.apple.vueandspringboot.statistic.model.LogMenu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<LogMenu, Integer> {
}
