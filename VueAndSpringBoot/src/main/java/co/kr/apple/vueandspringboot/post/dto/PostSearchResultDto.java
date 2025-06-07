package co.kr.apple.vueandspringboot.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostSearchResultDto {
    private Integer postId;
    private String postTitle;
    private String postContent;
    private String userName;
    private String postRegistDt;
    private Integer boardId;
    private String boardName;
    private Integer totalCount;
}
