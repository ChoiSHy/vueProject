<template>
    <div class="options-container">
        <button @click="addOption" class="btn btn-primary">선택지 추가</button>
        <draggable v-model="questEditStore.optionList">
            <template #item="{ element, index }">
                <div class="option-container">
                    <div>
                        <label>순서: {{ element.optNum }}</label>
                    </div>
                    <div>
                        <label>선택지 이름</label>
                        <input v-model="element.optValue">
                        <select v-model="element.optValue">
                            <option v-for="code in questEditStore.codes.optList" :key="code.code" :value="code.code">
                                {{ code.name }}</option>
                        </select>
                    </div>
                    <select v-model="element.optType">
                        <option value="0">없음</option>
                        <option value="1">일반</option>
                        <option value="2">기타</option>
                    </select>
                    <button @click="removeOption(index)" class="btn btn-danger">삭제</button>
                </div>
            </template>
        </draggable>
        
        <button class="btn btn-primary">저장</button>
        <button @click="router.back()" class="btn btn-secondary">뒤로가기</button>
    </div>
</template>
<script setup>
import { useQuestEditStore } from '@/stores/surv_quest/QuestEditStore';
import { computed, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import draggable from 'vuedraggable';
const router = useRouter()
const route = useRoute()
const questId = route.params.questId

const questEditStore = useQuestEditStore()
onMounted(async () => {

    await questEditStore.loadOptions(questId)
    await questEditStore.loadCodes()

})
const addOption = () => {
    const newOpt = {
        optId: null,
        questId: questId,
        optValue: '',
        optNum: optList.value.length+1,
        optType: 1,
    }
    optList.value.push(newOpt)
}
const removeOption = (idx) => {
    questEditStore.optionList.splice(idx,1)
}
const optList = computed(() => questEditStore.optionList)
watch(optList, () => {
    questEditStore.optionList.forEach((opt, idx) => {
        opt.optNum = idx + 1
    })
})
</script>
<style>.option-container{
    border: 1px solid;
}
</style>