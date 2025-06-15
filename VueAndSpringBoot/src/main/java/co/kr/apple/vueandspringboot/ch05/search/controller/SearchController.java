package co.kr.apple.vueandspringboot.ch05.search.controller;

import co.kr.apple.vueandspringboot.ch05.search.dto.BoardSearchResponseDto;
import co.kr.apple.vueandspringboot.ch05.search.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/apple/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping(value = "/init/{target}")
    public ResponseEntity<?> getSearchResultInit(@RequestParam("keyword") String keyword,
                                                 @RequestParam(value = "size", defaultValue = "3")int size,
                                                 @PathVariable("target") String target,
                                                 HttpSession session){
        try {
            List<BoardSearchResponseDto> boardList = searchService.searchBoardInit(keyword,size,session);
            return ResponseEntity.ok().body(boardList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("검색에 실패했습니다.");
        }
    }
    @GetMapping(value="/data/{target}/{boardId}")
    public ResponseEntity<?> getSearchResultPaging(@RequestParam("keyword") String keyword,
                                                   @RequestParam(value="size", defaultValue = "3")int size,
                                                   @RequestParam(value="page", defaultValue = "1")int page,
                                                   @PathVariable("target")String target,
                                                   @PathVariable("boardId")int boardId,
                                                   HttpSession session){
        try {
            BoardSearchResponseDto board = searchService.searchBoardPaging(boardId, keyword, page, size,session);
            return ResponseEntity.ok().body(board);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("검색에 실패했습니다.");

        }
    }
    @GetMapping(value = "/hot-keys")
    public ResponseEntity<?> getHotKeywords(){

        try{
            return ResponseEntity.ok().body(searchService.getHotKeywords());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("데이터를 가져올 수 없습니다.");
        }
    }
}
