<template>
    <td><a v-html="props.post.postTitle" href="#" @click="postDetail(props.post.postId)"></a></td>
    <td>{{ props.post.userName }}</td>
    <td>{{ props.post.postRegistDt }}</td>

</template>
<script setup>
import router from '@/router'
import { defineProps } from 'vue'

const props = defineProps({
    post: Object,
})
const postDetail= (postId)=>{
    router.push(postId)
}
</script>
<style>
.highlight {
    box-shadow: inset 0 -10px #d0e420;
}
</style>