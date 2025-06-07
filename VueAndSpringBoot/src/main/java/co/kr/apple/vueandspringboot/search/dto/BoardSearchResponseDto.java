package co.kr.apple.vueandspringboot.search.dto;

import co.kr.apple.vueandspringboot.post.dto.PostSearchResultDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardSearchResponseDto {
    private Integer boardId;
    private String boardName;
    private Integer totalCount;
    private List<PostSearchResultDto> postList;
}
