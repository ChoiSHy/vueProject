import axios from "axios"
import { defineStore } from "pinia"
import { ref } from "vue"

export const useQuestEditStore = defineStore('questEditStore', () =>{
    const codes = ref({})
    const survey = ref({
        survId: null,

    })
    const optionList = ref([])
    const questList = ref([])
    const getQuestByQuestId = (questId) => {
        return questList.value.filter(q=>q.questId == questId)[0]
    }
    const loadCodes = async () => {
        const response = await axios(`http://localhost:8080/api/apple/code/all`)
        codes.value = response.data
    }
    const loadQuestList = async () =>{
        const response = await axios(`http://localhost:8080/api/apple/question/questList?survId=${survey.value.survId}`)
        if(response.data){
            questList.value = response.data
        }
    }
    const updateQuestNum = ()=>{
        questList.value.forEach((quest,index) => {
            quest.questNum=index+1
        })
    }
    const loadOptions = async (questId) => {
           const response = await axios(`http://localhost:8080/api/apple/question/${questId}/options`)
        optionList.value = response.data
    }
    return {codes,optionList, survey,questList,loadOptions,loadQuestList,loadCodes, getQuestByQuestId,updateQuestNum}
})