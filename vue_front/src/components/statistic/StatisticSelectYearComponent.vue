<template>
  <div class="select-wrapper">
    <select
      name="year"
      :value="modelValue"
      @change="$emit('update:modelValue', Number($event.target.value))"
    >
      <option
        v-for="(year, idx) in years"
        :key="idx"
        :value="year"
      >
        {{ year }}
      </option>
    </select>
    <label>년</label>
  </div>
</template>

<script setup>
// eslint-disable-next-line no-undef
defineProps({
    years: {
        type: Array,
        required: true
    },
    modelValue: Number
})
// eslint-disable-next-line no-undef
defineEmits(
    ['update:modelValue']
)
</script>
<style></style>