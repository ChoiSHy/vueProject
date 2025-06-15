<template>
  <div class="select-wrapper">
    <select
      name="date"
      :value="modelValue"
      @change="$emit('update:modelValue',$event.target.value)"
    >
      <option
        v-for="date in dates"
        :key="date"
        :value="date"
      >
        {{ date }}
      </option>
    </select>
    <label>일</label>
  </div>
</template>
<script setup>
import { computed } from 'vue'

// eslint-disable-next-line no-undef
const props = defineProps({
    modelValue: Number,
    lastDate : Number
})
// eslint-disable-next-line no-undef
defineEmits(
    ['update:modelValue']
)
const dates = computed(()=>{
    return Array.from({length: props.lastDate}, (_, i)=> i+1)
})

</script>
<style></style>