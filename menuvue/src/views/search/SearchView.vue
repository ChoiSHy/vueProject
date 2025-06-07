<template>

    <search-bar />
    <div class="result-container">
        <search-board-component />
    </div>
</template>
<script>
import SearchBar from '@/views/search/header/SearchBar.vue'
import SearchBoardComponent from '@/views/search/SearchBoardComponent.vue'
import { useSearchStore } from '@/stores/search/SearchStore.js'
export default {
    data: () => {
        return {
            searchStore: useSearchStore()
        }
    },
    components: {
        SearchBar, SearchBoardComponent
    }
}
</script>
<style>
.result-container{
    margin: 0 auto;
    width: 80%;
}
</style>