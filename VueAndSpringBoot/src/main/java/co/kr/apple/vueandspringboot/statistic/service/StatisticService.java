package co.kr.apple.vueandspringboot.statistic.service;

import co.kr.apple.vueandspringboot.statistic.dto.StatisticDataDto;
import co.kr.apple.vueandspringboot.statistic.repository.StatisticRepository;
import co.kr.apple.vueandspringboot.statistic.repository.StatisticTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final StatisticTemplateRepository jdbcTemplateRepository;

    public List<StatisticDataDto> getData(){
        LocalDateTime now = LocalDateTime.now();
        int nowYear = now.getYear();
        List<Integer> years = new ArrayList<>();
        years.add(nowYear);
        years.add(nowYear-1);
        years.add(nowYear-2);
        List<StatisticDataDto> results= jdbcTemplateRepository.getLogMenuData(years);

        results.stream().forEach(e->{
            System.out.println(e);
        });
        return results;
    }
}
