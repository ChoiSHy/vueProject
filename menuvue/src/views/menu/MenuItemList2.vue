<template>
  <draggable
    v-model="innerItems"
    group="menus"
    item-key="id"
    :animation="200"
    :fallbackOnBody="true"
    :swapThreshold="0.65"
    class="space-y-2 pl-4"
  >
    <template #item="{ element }">
      <div class="border rounded p-2 bg-white shadow-sm">
        <div class="flex items-center gap-2">
          <input v-model="element.name" placeholder="메뉴 이름" class="border rounded px-2 py-1 w-40" />
          <select v-model="element.permission" class="border rounded px-2 py-1">
            <option value="3">슈퍼관리자</option>
            <option value="2">관리자</option>
            <option value="1">회원</option>
            <option value="0">비회원</option>
          </select>
          <label class="flex items-center gap-1">
            <input type="checkbox" v-model="element.active" />
            활성화
          </label>
          <button @click="removeItem(element.id)" class="text-red-500 hover:underline">삭제</button>
        </div>

        <!-- 자식 메뉴 -->
        <MenuItemList2 v-model="element.children" />
        <button
          v-if="depth < 2"
          @click="addChild(element)"
          class="mt-2 text-sm text-blue-500 hover:underline"
        >
          하위 메뉴 추가
        </button>
      </div>
    </template>
  </draggable>
</template>

<script setup>
import { computed, defineProps, defineEmits } from 'vue'
import draggable from 'vuedraggable'
import MenuItemList2 from './MenuItemList2.vue'

const props = defineProps({
  modelValue: Array,
  depth: {
    type: Number,
    default: 0
  }
})
const emit = defineEmits(['update:modelValue'])

const innerItems = computed({
  get: () => props.modelValue,
  set: (val) => emit('update:modelValue', val)
})

const addChild = (item) => {
  item.children ??= []
  item.children.push({
    id: Date.now(),
    name: '하위 메뉴',
    permission: 'USER',
    active: true,
    children: []
  })
}

const removeItem = (id) => {
  const remove = (items) => {
    const idx = items.findIndex(i => i.id === id)
    if (idx !== -1) return items.splice(idx, 1)
    items.forEach(i => remove(i.children ?? []))
  }
  remove(innerItems.value)
}
</script>

<style scoped>
/* 드래그 시 시각 효과 (선택 사항) */
.draggable-fallback {
  background-color: #f0f0f0;
}
</style>
