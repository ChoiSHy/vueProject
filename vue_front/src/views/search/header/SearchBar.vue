<template>
  <header>
    <div class="search-bar-container">
      <div class="search-bar-input-container">
        <input
          v-model="keyword"
          type="text"
          placeholder="검색어를 입력하세요"
          @keyup.enter="search()"
        >
        <button @click="search()">
          검색
        </button>
      </div>
      <div class="hot-key-container">
        <div
          @mouseleave="hotVisible = false"
          @mouseover="hotVisible = true"
        >
          <span>인기 검색어: </span>
          <span
            class="hot-keyword-container"
            @click=" keyword = hotKeyword[hotKeyIdx];"
          >{{
            hotKeyword[hotKeyIdx] }}</span>
          <ul v-if="hotVisible">
            <li
              v-for="hotKey in hotKeyword"
              :key="hotKey"
            >
              <a
                href="#"
                @click="keyword = hotKey;"
              >{{
                hotKey }}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </header>
</template>
<script setup>
import { useSearchStore } from "@/stores/search/SearchStore.js"
import { onMounted, ref } from "vue";
import { storeToRefs } from "pinia";

const searchStore = useSearchStore()
const { hotKeyword } = storeToRefs(searchStore)
const hotKeyIdx = ref(0)
const interval = ref(null)
const hotVisible = ref(false)

onMounted(async () => {
    await searchStore.getHotKeywords()
    interval.value = setInterval(() => {
        hotKeyIdx.value = (hotKeyIdx.value + 1) % (hotKeyword != null ? hotKeyword.value.length : 1)
    }, 3000)
})
</script>
<script>

export default {
    data: () => {
        return {
            keyword: ref(''),
            searchStore: useSearchStore()
        }
    },
    watch: {
        keyword(val) {
            // eslint-disable-next-line no-undef
            this.searchStore.searchInfo.keyword = val
        }
    }, methods: {
        search() {
            this.searchStore.search()
        }
    },
}
</script>
<style>
.hot-keyword-container {
    cursor: pointer;
}

ul {
    list-style-type: none;
    float: unset;
}
</style>