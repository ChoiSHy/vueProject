<template>
  <div class="question-edit">
    <!-- 문항 정보 -->
    <div>
      <div>
        <label>문항 ID:</label>
        <span>{{ modelValue.questId }}</span>
      </div>
      <div>
        <label>문항 번호: {{ modelValue.questNum }}</label>
      </div>
    </div>
    <div>
      <label v-if="!open" @click="open = !open">[열기]</label>
      <label v-else @click="open = !open">[닫기]</label>
      <div v-if="open">
        <label>문항 제목:</label>
        <input type="text" v-model="quest.questValue" />
      </div>

      <div v-if="modelValue.questType === 1 && open">
        <label>답변 개수:</label>
        <input type="number" v-model="quest.questLimit" min="1" />
      </div>
      <div v-if="open">
        <label>필수 여부:</label>
        <select v-model="quest.questRequireYn">
          <option value="Y">필수</option>
          <option value="N">선택</option>
        </select>
      </div>
      <div v-if="open">
        <label>문항 종류:</label>
        <select v-model="quest.questType">
          <option :value="0">섹션</option>
          <option :value="1">객관식</option>
          <option :value="2">주관식</option>
        </select>
      </div>
      <div v-if="open">
        <label>문항 분류:</label>
        <input type="text" v-model="quest.cateName" />
        <select v-model="quest.cateName">
          <option v-for="cate in questEditStore.codes.cateList" :key="cate.code" :value="cate.code">{{ cate.name }}
          </option>
          <option value="">미선택</option>
        </select>
      </div>
    </div>
    <div>
      <button @click="option_editor(modelValue.questId)">선택지 관리</button>
      <button @click="delete_question()">삭제</button>
    </div>
    <div v-if="modelValue.questParentId"><label>상위문항 ID: {{ modelValue.questParentId }}</label></div>
    <div v-if="props.depth == 0">
      <draggable v-model="quest.children" item-key="questId" :group="{ name: 'shared-group', pull: true, put: true }"
        @change="handleChange($event)" class="children-container">
        <template #item="{ element }">
          <QuestionEditComponent :modelValue="element" :depth="1" :parentId="modelValue.questId" />
        </template>
      </draggable>
    </div>
  </div>
</template>

<script setup>
import QuestionEditComponent from './QuestionEditComponent.vue'
import draggable from 'vuedraggable'
import { useQuestEditStore } from '@/stores/surv_quest/QuestEditStore';
import { computed, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter()
const questEditStore = useQuestEditStore()
const open = ref(false)
const props = defineProps({ modelValue: Object, depth: Number, parentId: Number });
const questId = props.modelValue.questId
const quest = computed(() => {
  if (props.depth == 0) {
    return questEditStore.getQuestByQuestId(questId)
  }
  else {
    const parentId = props.modelValue.questParentId
    const parentQuest = questEditStore.getQuestByQuestId(parentId)
    return parentQuest.children.filter(q => q.questId == questId)[0] || {}

  }
})
const clist = computed(() => quest.value.children)
const handleChange = (e) => {
  if (e.added) {
    e.added.element.questParentId = quest.value.questId;
  }
}
/* const children = computed(()=>quest.value.children || []) */
watch(clist, () => {
  console.log('change')
  quest.value.children.forEach((q, idx) => {
    q.questNum = idx + 1
  })
})
const option_editor = (questId) => {
  router.push({name:'OptionEditor', params: {survId: props.modelValue.survId, questId: questId,}})
}
const delete_question = () => {
  questEditStore.questList.splice(quest.value.questNum-1, 1)
}
</script>

<style scoped>
.question-edit {
  border: 1px solid #ccc;
  padding: 1rem;
  margin-bottom: 1rem;
  margin: 2rem;
}

.question-edit div {
  margin-bottom: 0.5rem;
}

.children-container {
  border: 1px solid;
}
</style>
