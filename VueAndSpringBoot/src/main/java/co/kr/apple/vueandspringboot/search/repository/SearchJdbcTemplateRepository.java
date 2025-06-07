package co.kr.apple.vueandspringboot.search.repository;

import co.kr.apple.vueandspringboot.search.dto.BoardSearchResponseDto;
import co.kr.apple.vueandspringboot.post.dto.PostSearchResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@RequiredArgsConstructor
public class SearchJdbcTemplateRepository {
    private final NamedParameterJdbcTemplate jdbcParameterTemplate;
    private final JdbcTemplate jdbcTemplate;

    public List<PostSearchResultDto> getSearchResultInit(String keyword, int size) {
        String sql = """
                SELECT *
                FROM (
                    SELECT p.POST_ID,
                           p.POST_TITLE,
                           p.POST_CONTENT,
                           p.POST_REGIST_DT,
                           p.BOARD_ID,
                           b.BOARD_NAME,
                           p.USER_ID,
                           u.USER_NAME,
                           ROW_NUMBER() OVER (PARTITION BY p.BOARD_ID ORDER BY p.POST_REGIST_DT DESC) rn,
                           COUNT(*) OVER (PARTITION BY p.BOARD_ID) AS TOTAL_COUNT
                    FROM TB_POST p
                    LEFT JOIN TB_USER u ON p.USER_ID = u.USER_ID
                    LEFT JOIN TB_BOARD b ON p.BOARD_ID = b.BOARD_ID
                    WHERE replace(lower(p.POST_TITLE),' ','') LIKE '%' || replace(lower(:keyword),' ','') || '%'
                    AND POST_DEL_YN = 'N'
                )
                WHERE rn <= :size
                ORDER BY BOARD_ID, POST_REGIST_DT DESC
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("keyword", keyword)
                .addValue("size", size);

        Highlighter highlighter = new Highlighter(keyword);

        List<PostSearchResultDto> flatList = jdbcParameterTemplate.query(sql, params, (rs, rowNum) -> {
                    String postTitle = rs.getString("POST_TITLE");

                    postTitle = highlighter.highlightTitle(postTitle);
                    return PostSearchResultDto.builder()
                            .postId(rs.getInt("POST_ID"))
                            .postTitle(postTitle)
                            .postContent(rs.getString("POST_CONTENT"))
                            .postRegistDt(rs.getString("POST_REGIST_DT"))
                            .userName(rs.getString("USER_NAME"))
                            .boardId(rs.getInt("BOARD_ID"))
                            .boardName(rs.getString("BOARD_NAME"))
                            .totalCount(rs.getInt("TOTAL_COUNT"))
                            .build();
                }
        );
        return flatList;

    }

    public BoardSearchResponseDto searchByBoardId(
            int boardId, String keyword, int page, int size) {

        int start = (page - 1) * size + 1;
        int end = page * size;

        String sql = """
                SELECT * FROM (
                    SELECT 
                        p.POST_ID,
                        p.POST_TITLE,
                        p.POST_CONTENT,
                        p.POST_REGIST_DT,
                        p.POST_UPDATE_DT,
                        p.USER_ID,
                        u.USER_NAME,
                        p.BOARD_ID,
                        ROW_NUMBER() OVER (ORDER BY p.POST_REGIST_DT DESC) AS rn,
                        COUNT(*) OVER() AS total_count
                    FROM TB_POST p
                    LEFT JOIN TB_USER u ON p.USER_ID = u.USER_ID
                    WHERE p.BOARD_ID = :boardId
                    AND p.POST_DEL_YN = 'N'
                    AND LOWER(REPLACE(p.POST_TITLE, ' ', '')) LIKE '%' || LOWER(REPLACE(:keyword, ' ', '')) || '%'
                )
                WHERE rn BETWEEN :start AND :end
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("boardId", boardId)
                .addValue("keyword", keyword)
                .addValue("start", start)
                .addValue("end", end);

        Highlighter highlighter = new Highlighter(keyword);

        List<PostSearchResultDto> posts = jdbcParameterTemplate.query(sql, params, (rs, rowNum) -> {
                    String postTitle = rs.getString("POST_TITLE");

                    postTitle = highlighter.highlightTitle(postTitle);

                    return PostSearchResultDto.builder()
                            .postId(rs.getInt("POST_ID"))
                            .postTitle(postTitle)
                            .postContent(rs.getString("POST_CONTENT"))
                            .postRegistDt(rs.getString("POST_REGIST_DT"))
                            .userName(rs.getString("USER_NAME"))
                            .totalCount(rs.getInt("total_count"))
                            .boardId(rs.getInt("BOARD_ID"))
                            .build();
                }
        );

        int totalCount = posts.isEmpty() ? 0 : posts.get(0).getTotalCount();

        return BoardSearchResponseDto.builder()
                .boardId(boardId)
                .postList(posts)
                .totalCount(totalCount)
                .build();
    }

    public Integer getNextLogSearchId() {
        return jdbcTemplate.queryForObject("SELECT SEQ_LS.NEXTVAL FROM DUAL", Integer.class);
    }

    public List<String> getHotKeywords() {
        String sql = """
                SELECT
                    LS_KEYWORD
                FROM
                    TB_LOG_SEARCH
                WHERE
                    LS_DT >= SYSDATE - 7
                GROUP BY
                    LS_KEYWORD
                HAVING
                    COUNT(*) >= 10
                ORDER BY
                    COUNT(*) DESC
                FETCH FIRST 5 ROWS ONLY
                """;
        return jdbcParameterTemplate.queryForList(sql, Collections.emptyMap(), String.class);
    }


}

class Highlighter {
    String regex;
    Pattern pattern;

    public Highlighter(String keyword) {
        this.regex = buildRegex(keyword);
        this.pattern = Pattern.compile(regex);
    }

    public String highlightTitle(String title) {
        Matcher matcher = pattern.matcher(title);

        StringBuffer highlightedTitle = new StringBuffer();

        while (matcher.find()) {
            String matched = matcher.group();
            matcher.appendReplacement(highlightedTitle, "<label class='highlight'>" + Matcher.quoteReplacement(matched) + "</label>");  // 하이라이트 태그 추가
        }
        matcher.appendTail(highlightedTitle);
        return highlightedTitle.toString();
    }

    private String buildRegex(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("(?i)"); // 대소문자 무시
        for (char c : keyword.replaceAll(" ", "").toCharArray()) {
            sb.append(Pattern.quote(String.valueOf(c)));
            sb.append("[\\s]*");
        }
        return sb.toString();
    }
}
