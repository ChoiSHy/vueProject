package co.kr.apple.vueandspringboot.ch06.code.controller;

import co.kr.apple.vueandspringboot.ch06.code.service.CodeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/apple/code")
@RequiredArgsConstructor
public class CodeController {

    private final CodeService codeService;

    @RequestMapping("/all")
    public ResponseEntity<?> getAllCodes(){
        return ResponseEntity.ok().body(codeService.getAllCodes());
    }
}
