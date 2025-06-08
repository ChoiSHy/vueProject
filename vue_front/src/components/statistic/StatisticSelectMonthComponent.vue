<template>
    <div class="select-wrapper">
        <select name="month" :value="modelValue" @change="$emit('update:modelValue', $event.target.value)">
            <option :value="month" v-for="(month) in 12" :key="month">{{ month }}</option>
        </select>
        <label>월</label>
    </div>
</template>
<script setup>
// eslint-disable-next-line no-undef
defineProps({
    modelValue: Number
})
// eslint-disable-next-line no-undef
defineEmits(
    ['update:modelValue']
)
</script>
<style></style>