package co.kr.apple.vueandspringboot.ch06.question.repository;

import co.kr.apple.vueandspringboot.ch06.option.dto.OptionResponseDto;
import co.kr.apple.vueandspringboot.ch06.question.dto.QuestionOptionFlatDto;
import co.kr.apple.vueandspringboot.ch06.question.dto.QuestionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryTemplate {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<QuestionOptionFlatDto> findQuestionsBySurvId(int survId) {
        String sql = """
                SELECT * FROM(
                SELECT * FROM
                (SELECT QUEST_ID
                ,QUEST_NUM
                ,QUEST_VALUE
                ,QUEST_LIMIT
                ,QUEST_REQUIRE_YN
                ,QUEST_TYPE
                ,SURV_ID
                ,QUEST_PARENT_ID
                ,QUEST_CATE
                FROM TB_SURV_QUEST
                WHERE SURV_ID = :survId
                )START WITH QUEST_PARENT_ID IS NULL
                CONNECT BY PRIOR QUEST_ID = QUEST_PARENT_ID
                ORDER SIBLINGS BY QUEST_NUM
                                
                )
                LEFT JOIN
                (SELECT OPT_ID
                ,QUEST_ID
                ,OPT_NUM
                ,OPT_TYPE
                ,OPT_VALUE
                FROM TB_SURV_OPTION
                )
                USING(QUEST_ID)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("survId", survId);

        return namedParameterJdbcTemplate.query(sql, params, ((rs, rowNum) -> {
            return QuestionOptionFlatDto.builder()
                    .questId(rs.getInt("QUEST_ID"))
                    .questNum(rs.getInt("QUEST_NUM"))
                    .questValue(rs.getString("QUEST_VALUE"))
                    .questLimit(rs.getInt("QUEST_LIMIT"))
                    .questRequireYn(rs.getString("QUEST_REQUIRE_YN"))
                    .questType(rs.getInt("QUEST_TYPE"))
                    .survId(rs.getInt("SURV_ID"))
                    .questParentId(rs.getObject("QUEST_PARENT_ID", Integer.class))
                    .cateName(rs.getString("QUEST_CATE"))
                    .optId(rs.getInt("OPT_ID"))
                    .optValue(rs.getString("OPT_VALUE"))
                    .optNum(rs.getInt("OPT_NUM"))
                    .optType(rs.getInt("OPT_TYPE"))
                    .build();
        }));
    }
}
