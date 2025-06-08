<template>
  <MenuEditor2 :menu="rootMenu" />
</template>

<script setup>
import MenuEditor2 from './MenuEditor2.vue'

const menuStore = useMenuStore()

// 예: store에서 rootMenu 하나를 불러왔다고 가정
const rootMenu = computed(() => menuStore.menus[0])  // 혹은 원하는 root
</script>

