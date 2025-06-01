<template>
    <div>
        <h1>게시판 편집</h1>
        <button @click="add()">게시판 추가</button>
        <div v-for="(board) in boards_temp" :key="board.boardId">
            <BoardComponent :board="board" 
            @nameChange="changeName($event)" 
            @typeChange="changeType($event)"
                @delBoard="delBoard($event)" />
        </div>
        <div>
            <button @click="save()">저장</button>
            <button @click="reset()">초기화</button>
            <button @click="back()">취소</button>
        </div>
    </div>
</template>
<script>
import BoardComponent from '@/components/board/BoardComponent.vue';
import { useBoardStore } from '@/stores/board/BoardStore';

export default {
    name: "BoardEditor",
    data: () => {
        return {
            boards_temp: [],
            delIds: [],
        }
    },
    created(){
        const boardStore = useBoardStore();
        this.boards_temp = [...boardStore.boards]
    },
    components: {
        BoardComponent: BoardComponent,
    },
    methods: {
        add() {
            this.boards_temp.push({
                boardId: null,
                boardName: null,
                boardType: null,
            })
        },
        changeName([boardId, name]) {
            this.boards_temp.find(board => {
                if (board.boardId == boardId) {
                    console.log(`${board.boardName}=> ${name}`);
                    board.boardName = name;
                }
            })
        },
        changeType([boardId, type]) {
            this.boards_temp.find(board => {
                if (board.boardId == boardId) {
                    console.log(`${board.boardType}=> ${type}`);
                    board.boardType = type;
                }
            })
        },
        delBoard(boardId) {
            this.boards_temp = this.boards_temp.filter((board) => board.boardId !== boardId);
            this.delIds.push(boardId);

        },
        save() {
            const boardStore = useBoardStore();
            boardStore.setBoards(this.boards_temp);
            boardStore.setDelIds(this.delIds);
            boardStore.saveBoards();
        }, 
        reset() {
            const boardStore = useBoardStore();
            this.boards_temp = [...boardStore.boards]
            this.delIds = []
        }, 
        back() {

        },
    }


}
</script>
<style></style>