<template>
  <div class="board-container">
    <div class="board-left">
      <div class="input-container">
        <label for="input-board-name">게시판 이름: </label>
        <input
          type="text"
          name="boardName"
          class="input-board-name"
          :value="board.boardName"
          @change="$emit('nameChange', [board.boardId, $event.target.value])"
        >
      </div>
      <div class="input-container">
        <label for="select-board-type">게시판 유형: </label>
        <select
          name="boardType"
          class="select-board-type"
          :value="board.boardType"
          @change="$emit('typeChange', [board.boardId, $event.target.value])"
        >
          <option value="0">
            일반형 게시판
          </option>
          <option value="1">
            갤러리형 게시판
          </option>
        </select>
      </div>
    </div>
    <div class="input-container">
      <button
        type="button"
        @click="$emit('delBoard', board.boardId)"
      >
        X
      </button>
    </div>
  </div>
</template>
<script>
export default {
    name: "BoardComponent",
    props: {
        board: Object,
    },
    data: () => {
        return {

        }
    },

}
</script>
<style>
.board-container {
    width: 50rem;
    border: 1px solid;
    display: flex;
    justify-content: space-between;
}
.board-left{
    width:80%;
    
}
input,select{width:70%}
.input-container{
    display:flex;
    justify-content: space-between;
    align-items: center;
}
</style>