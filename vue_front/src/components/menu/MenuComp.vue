<template>
  <div class="menu-comp-container">
    <div class="input-col">
      <div class="input-area">
        <label for="menu-name">
          메뉴 이름: 
        </label>
        <input
          type="text"
          class="menu-name"
          :value="menu.menuName"
          @change="$emit('nameChange', [$event.target.value, menu.menuId])"
        >
      </div>
      <div
        v-if="menu.boardId == -1"
        class="input-area url-area"
      >
        <label for="menu-url">
          메뉴 URL: 
        </label>
        <input
          type="text"
          class="menu-url"
          :value="menu.menuUrl"
          @change="$emit('urlChange', [$event.target.value, menu.menuId])"
        >
      </div>
    </div>
    <div class="input-col">
      <div class="input-area">
        <label for="select-menu-auth">권한: </label>
        <select
          name="menuAuth"
          class="select-menu-auth"
          :value="menu.menuAuth"
          @change="$emit('authChange',[$event.target.value, menu.menuId])"
        >
          <option
            v-if="menu.menuAuth == 0"
            value="0"
          >
            비회원
          </option>
          <option value="1">
            회원
          </option>
          <option value="2">
            일반관리자
          </option>
          <option value="3">
            슈퍼관리자
          </option>
        </select>
      </div>
      <div
        v-if="menu.menuParentId == null"
        class="input-area"
      >
        <label for="select-board">연결 대상:</label>
        <select
          name="board"
          class="select-board"
          :value="menu.boardId"
          @change="checkSelect($event.target.value, menu.menuId)"
        >
          <option
            v-for="(b) in boards"
            :key="b.boardId"
            :value="b.boardId"
          >
            {{ b.boardName }}
          </option>
          <option value="-1">
            URL
          </option>
        </select>
      </div>
      <div class="input-area">
        <label for="select-use-yn">
          활성화 여부
        </label>
        <select
          name="menuUseYn"
          class="select-use-yn"
          :value="menu.menuUseYn"
          @change="$emit('useYnChange', [$event.target.value, menu.menuId])"
        >
          <option value="Y">
            활성화
          </option>
          <option value="N">
            비활성화
          </option>
        </select>
      </div>
    </div>
    <div class="input-col">
      <button
        type="button"
        class="btn-delete"
        @click="$emit('delMenu', menu.menuId)"
      >
        X
      </button>
    </div>
    <div v-if="menu.menuList && menu.menuList.length">
      <MenuComp 
        v-for="child in menu.menuList"
        :key="child.menuId"
        :menu="child"
        :boards="boards"
        @name-change="$emit('nameChange', $event)"
        @url-change="$emit('urlChange', $event)"
        @auth-change="$emit('authChange', $event)"
        @board-change="$emit('boardChange', $event)"
        @use-yn-change="$emit('useYnChange', $event)"
        @del-menu="$emit('delMenu', $event)"
      />
    </div>
  </div>
</template>
<script>
export default {
    name: "MenuComp",
    props: {
        boards: Array,
        menu: Object,
    },
    data: () => {
        return {
            isUrl: false,
        }
    },
    watch: {

    },

    methods: {
        checkSelect(value, id) {
            if (value == null)
                this.isUrl = true;
            else 
                this.isUrl = false;

            this.$emit('boardChange',[value, id]);
        }
    }
}
</script>
<style>
form {
    display: flex;
    justify-content: space-between;
    align-items: center;

}

div {
    padding: 0.5em;
}

.input-col {
    padding: 1rem;

}

.input-area {
    display: flex;
    justify-content: space-between;

}

.url-area {
}

.menu-comp-container {
    width: 900px;
    margin: 1rem;
    border: 1px solid;

}
</style>