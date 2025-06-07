package co.kr.apple.vueandspringboot.statistic.repository;

import co.kr.apple.vueandspringboot.statistic.dto.StatisticDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StatisticTemplateRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<StatisticDataDto> getLogMenuData(List<Integer> years) {
        String pivotClause = years.stream()
                .map(y -> String.valueOf(y))
                .collect(Collectors.joining(", "));

        String sql = String.format("""
                SELECT *
                FROM
                (
                SELECT
                		CASE
                			WHEN menu_idx IS NULL THEN '[삭제] ' || MENU_NAME
                			ELSE MENU_NAME
                		END MENU_NAME,
                		to_char(lm_dt, 'YYYY') lm_dt
                	FROM (
                		SELECT *
                		FROM TB_LOG_MENU tlm
                		LEFT JOIN TB_MENU_NAME tmn
                				USING (menu_id)
                		LEFT JOIN TB_MENU tm
                				USING(menu_id)
                	)
                )
                pivot(
                	count(*)
                	FOR lm_dt IN (2022,2023,2024,2025)
                )
                                
                """, pivotClause);
        return jdbcTemplate.query(sql, (rs) -> {
            List<StatisticDataDto> results = new ArrayList<>();

            while (rs.next()) {
                String menuName = rs.getString("MENU_NAME");
                Map<String, Integer> countByYear = new LinkedHashMap<>();
                for (Integer year : years) {
                    int count = rs.getInt(String.valueOf(year));
                    countByYear.put(String.valueOf(year), count);
                }
                results.add(new StatisticDataDto(menuName, countByYear));
            }
            return results;
        });
    }
}
