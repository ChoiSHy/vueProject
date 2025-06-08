<template>
  <div class="menu-editor">
    <h1>메뉴 편집기</h1>

    <draggable
      v-model="menus"
      group="menus"
      item-key="menuId"
      :move="checkMove"
      :fallbackOnBody="true"
      :animation="200"
    >
      <template #item="{ element }">
        <div class="menu-item">
          <input v-model="element.menuName" placeholder="메뉴 이름" />

          <select v-model="element.menuAuth">
            <option value="0">비회원</option>
            <option value="1">회원</option>
            <option value="2">일반관리자</option>
            <option value="3">슈퍼관리자</option>
          </select>

          <select v-model="element.menuUseYn">
            <option value="Y">활성화</option>
            <option value="N">비활성화</option>
          </select>

          <!-- 게시판 선택 -->
          <select v-model="element.boardId">
            <option :value="b.boardId" v-for="b in boardList" :key="b.boardId">{{ b.boardName }}</option>
            <option value="-1">직접 입력 (URL)</option>
          </select>

          <!-- boardId === -1일 때 URL 입력 -->
          <input
            v-if="element.boardId == -1"
            v-model="element.menuUrl"
            placeholder="메뉴 URL 입력"
          />

          <button @click="deleteMenu(element.menuId)">삭제</button>
        </div>
      </template>
    </draggable>

    <button @click="addMenu">메뉴 추가</button>
  </div>
</template>

<script>
import draggable from 'vuedraggable';
import { useBoardStore } from '@/stores/board/BoardStore';

export default {
  name: 'MenuEditor',
  components: { draggable },
  data() {
    return {
      menus: [
        {
          menuId: 1,
          menuName: '대메뉴1',
          menuAuth: 0,
          menuUseYn: 'Y',
          boardId: -1,
          menuUrl: '',
          children: []
        }
      ],
    };
  },
  computed:{
    boardList() {
      return useBoardStore().boards
    }
  },  
  methods: {
    checkMove(evt) {
  const getDepth = (item, currentDepth = 1) => {
    if (!item.children || item.children.length === 0) return currentDepth;
    return Math.max(...item.children.map(child => getDepth(child, currentDepth + 1)));
  };

  const dragged = evt.draggedContext.element;
  const related = evt.relatedContext;

  // 드롭될 목록 전체를 기준으로 판단 (예: 2단계로 드롭되면 자식이 3단계 됨)

  // 만약 드래그된 항목이 자식들을 포함하고 있다면 최대 depth 계산
  const maxDepthOfDraggedItem = getDepth(dragged);

  // 부모의 현재 depth (여기선 1단계로 본다)
  const parentDepth = related.component.__depth || 1;

  if (parentDepth + maxDepthOfDraggedItem - 1 > 3) {
    // console.warn('최대 3단계까지만 허용됩니다.');
    return false;
  }

  return true;
},
    addMenu() {
      this.menus.push({
        menuId: Date.now(),
        menuName: '',
        menuAuth: 0,
        menuUseYn: 'Y',
        boardId: -1,
        menuUrl: '',
        children: []
      });
    },
    deleteMenu(id) {
      const recursiveDelete = (list) =>
        list
          .filter(menu => menu.menuId !== id)
          .map(menu => ({
            ...menu,
            children: menu.children ? recursiveDelete(menu.children) : []
          }));

      this.menus = recursiveDelete(this.menus);
    }
  }
};
</script>

<style scoped>
.menu-editor {
  max-width: 800px;
  margin: auto;
  padding: 1rem;
  border: 1px solid #ccc;
}
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
