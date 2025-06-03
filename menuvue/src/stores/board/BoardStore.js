import {defineStore} from 'pinia'
import axios from 'axios'
import boards from '@/assets/board.js';

export const useBoardStore = defineStore('boardStore', {
 state: ()=> ({
        boards:boards,
        delIds:[],
    }),
    actions:{
        setBoards(boardList){
            this.boards = boardList;
        },
        setDelIds(delIds){
            this.delIds = delIds;
        },
        async saveBoards(){
            try{
                const payload = {
                    boards: this.boards,
                    delIds: this.delIds
                }
                await axios.post('/api/apple/board/updBoard', payload)
                console.log("전송 성공");
            }
            catch(error){
                console.error('서버 저장 실패: '.error);
            }
        }
    }
})