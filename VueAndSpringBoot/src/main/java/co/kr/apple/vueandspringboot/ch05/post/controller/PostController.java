package co.kr.apple.vueandspringboot.ch05.post.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apple/post")
public class PostController {
    @RequestMapping("/postList")
    public ResponseEntity<?> postList(@RequestParam("boardId")int boardId){
        System.out.println("postList");
        return ResponseEntity.ok().body("hello");
    }
}
