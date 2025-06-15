<template>
  <div
    v-for="(board, idx) in boardList"
    :key="board.boardId"
    class="data-container"
  >
    <h4>{{ board.boardName }}</h4>
    <SearchPostListLayout :post-list="board.postList" />
    <div class="btn-container">
      <button
        v-for="n in board.totalCount"
        :key="n"
        @click="getPage(n, idx, board.boardId)"
      >
        {{ n }}
      </button>
    </div>
  </div>
</template>
<script setup>
import { useSearchStore } from '@/stores/search/SearchStore'
import { storeToRefs } from 'pinia'
import SearchPostListLayout from './SearchPostListLayout.vue'

const searchStore = useSearchStore()
const { getBoards: boardList } = storeToRefs(searchStore)

const getPage = async (page, idx, boardId) => {
    await searchStore.getResultPage(page, idx, boardId)
}
</script>
<style>
h4 {
    text-align: left;
}

table {
    border-collapse: collapse;
    width: 80%;

}

th,
td {
    border: 1px solid;
}

.data-container {
    width: 100%;
}
</style>