package co.kr.apple.vueandspringboot.ch06.code.service;

import co.kr.apple.vueandspringboot.ch06.code.dto.CodeListDto;
import co.kr.apple.vueandspringboot.ch06.code.dto.CodeResponseDto;
import co.kr.apple.vueandspringboot.ch06.code.model.Code;
import co.kr.apple.vueandspringboot.ch06.code.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final CodeRepository codeRepository;

    public CodeListDto getAllCodes() {
        List<Code> cateList = codeRepository.findByGrpCode("SV-CATE");
        List<Code> optList = codeRepository.findByGrpCode("SV-OPT");

        return CodeListDto.builder()
                .cateList(cateList.stream().map(cate -> buildCodeDto(cate)).collect(Collectors.toList()))
                .optList(optList.stream().map(opt->buildCodeDto(opt)).collect(Collectors.toList()))
                .build();
    }
    private CodeResponseDto buildCodeDto(Code code){
        return CodeResponseDto.builder()
                .code(code.getCodeCode())
                .name(code.getCodeName())
                .group(code.getGrpCode())
                .build();
    }
}
