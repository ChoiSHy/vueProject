<template>
    <div class="table-wraaper">
    <table>
        <thead>
            <tr>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일시</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="post in props.postList" :key="post.postId">
                <search-post-list-component :post="post" />
            </tr>
            <tr v-if="props.postList == null || props.postList.length == 0">
                <td colspan="3">데이터가 없습니다.</td>
            </tr>
            
        </tbody>
    </table>
</div>
</template>
<script setup>
import SearchPostListComponent from './SearchPostListComponent.vue';
import { defineProps } from 'vue'

const props = defineProps({
    postList: Array
})
</script>
<style>
.table-wraaper{
    display: flex;
    justify-content: center;
}
</style>