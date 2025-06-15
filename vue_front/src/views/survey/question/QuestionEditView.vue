<template>
  <div class="quest-edit-view">
    <button class="btn btn-primary" @click="add_question()">문항 추가</button>
    <button class="btn btn-outline-primary">저장</button>
    <!-- draggable 로 감싸기 -->
    <draggable v-model="questEditStore.questList" item-key="questId" :animation="400" class="elements-container"
      :group="{ name: 'shared-group', pull: true, put: true }" @change="handleChange($event)">
      <template #item="{ element }">
        <QuestionEditComponent :modelValue="element" :depth="0" />
      </template>
    </draggable>
  </div>
</template>
<script setup>
import draggable from 'vuedraggable'
import QuestionEditComponent from "./QuestionEditComponent.vue";
import { useQuestEditStore } from "@/stores/surv_quest/QuestEditStore";
import { computed, onBeforeMount, watch } from "vue";
import { useRoute } from "vue-router";

const route = useRoute()
const survId = route.params.survId

const questEditStore = useQuestEditStore()
onBeforeMount(async () => {
  questEditStore.survey.survId = survId
  await questEditStore.loadQuestList()
  await questEditStore.loadCodes()
})
const questList = computed(() => questEditStore.questList)
watch(questList, () => {
  console.log('update-quest-num')
  questEditStore.updateQuestNum()
})
const handleChange = (e) => {
  if (e.added) {
    e.added.element.questParentId = null;
  }
}
const add_question = () => {
  const newQuestion = 
       {
      cateName: null,
      children:[],
      options:[],
      questId:null,
      questLimit :1,
      questNum:questEditStore.questList.length,
      questParentId:null,
      questRequireYn: 'N',
      questType : 1,
      questValue:'',
      survId : survId
    };
  questEditStore.questList.push(newQuestion)
}
</script>
<style>
.quest-edit-view {}

.elements-container {
  display: flex;
  flex-wrap: wrap;
}
</style>