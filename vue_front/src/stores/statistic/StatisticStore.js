import {defineStore} from 'pinia'
import axios from 'axios'

export const useStatisticStore = defineStore('statisticStore', {
    state:() => ({
        dataList:[],
        years:[],
    }),
    actions:{
        async getDatas(mode, year, month, date){
            const payload = {
                mode: mode,
                year: year,
                month: month,
                date: date,
            }
            const response = await axios.get('http://localhost:8080/statistic/getData', payload)
            if(response.status === 200){
                this.dataList = [...response.data]
                this.years = [...(Object.keys(this.dataList[0].count))]
            }
            else{
                console.error(response)
            }
            return response;
            
        }
    }
})