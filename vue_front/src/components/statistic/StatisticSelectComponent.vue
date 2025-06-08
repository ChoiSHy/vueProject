<template>
    <div class="select-container">
        <div class="select-wrapper">
            <select name="mode" v-model="mode">
                <option value="0">연도별</option>
                <option value="1">월별</option>
                <option value="2">일별</option>
                <option value="3">시간별</option>
            </select>
        </div>
    </div>
    <div class="select-container date-container">
        <statistic-select-year-component v-if="mode >= 1" v-model="year" :years="years" />
        <statistic-select-month-component v-if="mode >= 2" v-model="month" />
        <statistic-select-date-component v-if="mode >= 3" v-model="date" :last-date="lastDateOfMonth" />

    </div>
</template>
<script setup>
import { useStatisticStore } from '@/stores/statistic/StatisticStore';
import { onMounted, ref, watch, computed } from 'vue';
const statisticStore = useStatisticStore()
let years=[];
const today = new Date()
const year=ref(today.getFullYear());
const month=ref(today.getMonth() + 1);
const date=ref(today.getDate());

const lastDateOfMonth = computed(() => {
    let lastDate= new Date(year.value, month.value, 0).getDate()
    console.log(lastDate)
    return lastDate
})
onMounted(async () => {
    await statisticStore.getDatas(0, year.value, month.value, date.value)
    years = ref(statisticStore.years);
})


watch([year, month], () => {
    if (date.value > lastDateOfMonth.value) {
        date.value = lastDateOfMonth.value
    }
})
</script>
<script>
import StatisticSelectMonthComponent from './StatisticSelectMonthComponent.vue';
import StatisticSelectDateComponent from './StatisticSelectDateComponent.vue';
import StatisticSelectYearComponent from './StatisticSelectYearComponent.vue';
export default {
    data: () => {
        return {
            mode: 0,
        }
    },
    components: { StatisticSelectDateComponent, StatisticSelectMonthComponent, StatisticSelectYearComponent },

}
</script>
<style>
.select-container {
    display: flex;
    justify-content: right;
}

.select-wrapper {
    width: 8%;
}
</style>