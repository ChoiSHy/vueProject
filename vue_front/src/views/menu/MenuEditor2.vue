<template>
  <div class="menu-item">
    <input v-model="menu.menuName" placeholder="메뉴 이름" />

    <select v-model="menu.menuAuth">
      <option value="0">비회원</option>
      <option value="1">회원</option>
      <option value="2">일반관리자</option>
      <option value="3">슈퍼관리자</option>
    </select>

    <select v-model="menu.menuUseYn">
      <option value="Y">활성화</option>
      <option value="N">비활성화</option>
    </select>

    <select v-model="menu.boardId">
      <option :value="b.boardId" v-for="b in boardList" :key="b.boardId">{{ b.boardName }}</option>
      <option value="-1">직접 입력 (URL)</option>
    </select>

    <input v-if="menu.boardId == -1" v-model="menu.menuUrl" placeholder="메뉴 URL 입력" />

    <button @click="deleteMenu(menu.menuId)">삭제</button>

    <!-- 자식 메뉴 재귀 렌더링 -->
    <draggable
      v-if="menu.children && menu.children.length"
      v-model="menu.children"
      group="menus"
      item-key="menuId"
      :fallbackOnBody="true"
      :animation="200"
    >
      <template #item="{ element }">
        <!-- 재귀적으로 자신을 호출 -->
        <MenuEditor :menu="element" />
      </template>
    </draggable>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useBoardStore } from '@/stores/board/BoardStore'
import { useMenuStore } from '@/stores/menu/MenuStore'
import draggable from 'vuedraggable'

// 재귀 컴포넌트 등록 (자기 자신을 등록)
defineOptions({ name: 'MenuEditor' })
/* global defineProps, defineOptions */

defineProps({
  menu: Object,
})

const boardStore = useBoardStore()
const menuStore = useMenuStore()

const boardList = computed(() => boardStore.boards)

function deleteMenu(id) {
  menuStore.deleteMenu(id)
}
</script>

<style scoped>
.menu-item {
  padding: 1rem;
  margin: 0.5rem 0;
  border: 1px solid #aaa;
  border-radius: 5px;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}
</style>
