package co.kr.apple.vueandspringboot.ch05.search.service;

import co.kr.apple.vueandspringboot.ch05.post.dto.PostSearchResultDto;
import co.kr.apple.vueandspringboot.ch05.search.dto.BoardSearchResponseDto;
import co.kr.apple.vueandspringboot.ch05.search.repository.BoardRepository;
import co.kr.apple.vueandspringboot.ch05.search.repository.SearchJdbcTemplateRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final SearchJdbcTemplateRepository searchJdbcTemplateRepository;
    private final BoardRepository boardRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);
    public List<BoardSearchResponseDto> searchBoardInit(String keyword, int size, HttpSession session) {
        LOGGER.info("keyword: {}, size: {}",keyword, size);
        Map<Integer, BoardSearchResponseDto> resultMap = new HashMap<>();
        List<PostSearchResultDto> flatList = searchJdbcTemplateRepository.getSearchResultInit(keyword, size);
        LOGGER.info("flatList.size = {}", flatList.size());
        for (PostSearchResultDto post : flatList) {
            int boardId = post.getBoardId();
            resultMap.computeIfAbsent(boardId, id ->    //boardId가 존재하지 않으면, id를 매개변수로 한 람다식 실행 (<--> computeIfPresent(K key, Function<? super K, ? extends V> mappingFunction) )
                    BoardSearchResponseDto.builder()
                            .boardId(id)
                            .boardName(post.getBoardName())
                            .postList(new ArrayList<>())
                            .totalCount((int) Math.ceil((double) post.getTotalCount() / (double) size))
                            .build()
            );
            resultMap.get(boardId).getPostList().add(post);
        }
        boardRepository.findAll().forEach(board -> {
            resultMap.computeIfAbsent(board.getBoardId(), id->
                    BoardSearchResponseDto.builder()
                            .boardId(board.getBoardId())
                            .boardName(board.getBoardName())
                            .totalCount(0)
                            .build());
        });
        return resultMap.values().stream().toList();
    }

    public BoardSearchResponseDto searchBoardPaging( int boardId, String keyword, int page, int size, HttpSession session) {
        LOGGER.info("boardId: {}, keyword: {}, page: {}, size: {}", boardId,keyword,page,size);
        BoardSearchResponseDto board = searchJdbcTemplateRepository.searchByBoardId(boardId, keyword, page, size);
        board.getPostList().forEach(System.out::println);
        return board;
    }

    public List<String> getHotKeywords(){
        LOGGER.info("get-hot-keyword");
        return searchJdbcTemplateRepository.getHotKeywords();
    }
}
