package co.kr.apple.vueandspringboot.ch06.question.service;

import co.kr.apple.vueandspringboot.ch06.option.OptionRepository;
import co.kr.apple.vueandspringboot.ch06.option.dto.OptionResponseDto;
import co.kr.apple.vueandspringboot.ch06.option.model.Option;
import co.kr.apple.vueandspringboot.ch06.question.dto.QuestionOptionFlatDto;
import co.kr.apple.vueandspringboot.ch06.question.dto.QuestionResponseDto;
import co.kr.apple.vueandspringboot.ch06.question.repository.QuestionRepositoryTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepositoryTemplate questionRepositoryTemplate;
    private final OptionRepository optionRepository;
    public List<QuestionResponseDto> questList(int survId) {
        List<QuestionOptionFlatDto> flatDtoList = questionRepositoryTemplate.findQuestionsBySurvId(survId);

        Map<Integer, QuestionResponseDto> resultMap = new LinkedHashMap<>();

        for (QuestionOptionFlatDto flatDto : flatDtoList) {
            Integer questId = flatDto.getQuestId();
            System.out.println(questId+","+flatDto.getQuestParentId());
            // 부모가 없을 경우
            if (flatDto.getQuestParentId() == null) {
                resultMap.computeIfAbsent(questId, id -> {
                    return QuestionResponseDto.builder()
                            .questId(flatDto.getQuestId())
                            .questNum(flatDto.getQuestNum())
                            .questValue(flatDto.getQuestValue())
                            .questLimit(flatDto.getQuestLimit())
                            .questRequireYn(flatDto.getQuestRequireYn())
                            .questType(flatDto.getQuestType())
                            .survId(flatDto.getSurvId())
                            .questParentId(flatDto.getQuestParentId())
                            .cateName(flatDto.getCateName())
                            .children(new ArrayList<>())
                            .options(new ArrayList<>())
                            .build();
                });
                resultMap.get(questId).getOptions().add(
                        OptionResponseDto.builder()
                                .optId(flatDto.getOptId())
                                .optNum(flatDto.getOptNum())
                                .optValue(flatDto.getOptValue())
                                .optType(flatDto.getOptType())
                                .questId(questId)
                                .build());
            }// 부모 존재시
            else {
                List<QuestionResponseDto> children = resultMap.get(flatDto.getQuestParentId()).getChildren();
                QuestionResponseDto me = children.stream()
                        .filter(q -> q.getQuestId() == flatDto.getQuestId())
                        .findFirst()
                        .orElse(null);
                if (me == null) {
                   me = QuestionResponseDto.builder()
                            .questId(flatDto.getQuestId())
                            .questNum(flatDto.getQuestNum())
                            .questValue(flatDto.getQuestValue())
                            .questLimit(flatDto.getQuestLimit())
                            .questRequireYn(flatDto.getQuestRequireYn())
                            .questType(flatDto.getQuestType())
                            .survId(flatDto.getSurvId())
                            .questParentId(flatDto.getQuestParentId())
                            .cateName(flatDto.getCateName())
                            .children(new ArrayList<>())
                            .options(new ArrayList<>())
                            .build();
                    children.add(me);
                }
                me.getOptions().add(
                        OptionResponseDto.builder()
                                .optId(flatDto.getOptId())
                                .optNum(flatDto.getOptNum())
                                .optValue(flatDto.getOptValue())
                                .optType(flatDto.getOptType())
                                .questId(questId)
                                .build());
            }
        }
        return resultMap.values().stream().collect(Collectors.toList());
    }
    public List<OptionResponseDto> findOptionsByQuestId(int questId){
        List<Option> optionList = optionRepository.findByQuestId(questId);

        return  optionList.stream().map(o->OptionResponseDto.builder()
                .optId(o.getOptId())
                .optNum(o.getOptNum())
                .optValue(o.getOptValue())
                .optType(o.getOptType())
                .questId(o.getQuestId())
                .build()).collect(Collectors.toList());
    }
}
