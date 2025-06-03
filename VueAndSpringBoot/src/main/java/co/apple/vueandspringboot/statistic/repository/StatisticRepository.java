package co.apple.vueandspringboot.statistic.repository;

import co.apple.vueandspringboot.statistic.model.LogMenu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<LogMenu, Integer> {
}
