<!-- eslint-disable vue/no-mutating-props -->
<template>
  <div class="menu-item">
    <input
      v-model="menu.menuName"
      placeholder="메뉴 이름"
    >

    <select v-model="menu.menuAuth">
      <option value="0">
        비회원
      </option>
      <option value="1">
        회원
      </option>
      <option value="2">
        일반관리자
      </option>
      <option value="3">
        슈퍼관리자
      </option>
    </select>

    <select v-model="menu.menuUseYn">
      <option value="Y">
        활성화
      </option>
      <option value="N">
        비활성화
      </option>
    </select>

    <select v-model="menu.boardId">
      <option
        v-for="b in boardList"
        :key="b.boardId"
        :value="b.boardId"
      >
        {{ b.boardName }}
      </option>
      <option value="-1">
        직접 입력 (URL)
      </option>
    </select>

    <input
      v-if="menu.boardId == -1"
      v-model="menu.menuUrl"
      placeholder="메뉴 URL 입력"
    >

    <button @click="deleteMenu(menu.menuId)">
      삭제
    </button>

    <!-- 자식 메뉴가 있을 경우 재귀 렌더링 -->
    <draggable
      v-model="menu.children" 
      group="menus" 
      item-key="menuId"
      :animation="200"
    >
      <template #item="{ element }">
        <div>{{ element.menuName }}</div>
        <MenuItem :menu="element" />
      </template>
    </draggable>
    <!-- <draggable v-model="menu.children" 
        group="menus" 
        item-key="menuId"
            :fallbackOnBody="true" 
            :animation="200">
            <template #item="{ element }">
                <div>{{ element.menuName }}</div>
                <MenuItem :menu="element" />
            </template>
        </draggable> -->
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useBoardStore } from '@/stores/board/BoardStore';
import { useMenuStore } from '@/stores/menu/MenuStore';
import { watchEffect } from 'vue';
import { reactive } from 'vue';

// import MenuItem from './MenuItem.vue';
/* global defineProps */
// eslint-disable-next-line vue/no-mutating-props
const props = defineProps({
    menu: Object,
});

watchEffect(() => {
    if (props.menu && props.menu.children) {
        console.log('children:', props.menu.children);
    }
});
const reactiveMenu = reactive(props.menu);
console.log(reactiveMenu.children);

const boardStore = useBoardStore();
const menuStore = useMenuStore();

const boardList = computed(() => boardStore.boards);

function deleteMenu(id) {
    menuStore.deleteMenu(id);
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
