<template>
  <div class="p-4 space-y-4">
    <MenuItemList2 v-model="menuTree" />
    <button @click="addRootMenu" class="px-4 py-2 bg-blue-500 text-white rounded">최상위 메뉴 추가</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import MenuItemList2 from './MenuItemList2.vue'

const menuTree = ref([
  {
    id: 1,
    name: '대시보드',
    permission: 'ADMIN',
    active: true,
    children: []
  }
])

const addRootMenu = () => {
  menuTree.value.push({
    id: Date.now(),
    name: '새 메뉴',
    permission: 'USER',
    active: true,
    children: []
  })
}
</script>
<style>
</style>
