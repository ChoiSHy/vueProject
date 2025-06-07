package co.kr.apple.vueandspringboot.common.config;

import co.kr.apple.vueandspringboot.search.model.LogSearch;
import co.kr.apple.vueandspringboot.search.repository.SearchJdbcTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {
    private final JdbcAggregateTemplate jdbcAggregateTemplate;
    private final SearchJdbcTemplateRepository searchJdbcTemplateRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @After("execution(* co.kr.apple..SearchController.*(..)) && args(keyword,..)")
    public void logAfterSearch(String keyword) {
        String[] keywords = keyword.split(" ");
        for (String kw : keywords) {
            Integer nextId = searchJdbcTemplateRepository.getNextLogSearchId();
            LogSearch logSearch = LogSearch.builder()
                    .lsId(nextId)
                    .lsDt(LocalDateTime.now())
                    .keyword(kw.toLowerCase())
                    .build();
            jdbcAggregateTemplate.insert(logSearch);
            LOGGER.info("---insert---{}", logSearch);
        }
    }
}
