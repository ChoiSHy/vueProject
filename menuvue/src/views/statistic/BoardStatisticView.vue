<template >
    <div>
       <h1>메뉴별 통계</h1>
       <div>
            <statistic-select-component/>
       </div> 
       <hr>
       <div>
          <board-statistic-data-view/>
       </div>
    </div>
</template>
<script>
import StatisticSelectComponent from '@/components/statistic/StatisticSelectComponent.vue'
import BoardStatisticDataView from './BoardStatisticDataView.vue'
export default {
  components: { StatisticSelectComponent, BoardStatisticDataView },
    
}
</script>
<style >
    
</style>