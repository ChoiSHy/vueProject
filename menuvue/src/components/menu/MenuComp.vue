<template>
    <div class="menu-comp-container">
        <form>
            <div class="input-col">
                <div class="input-area">
                    <label for="menu-name">
                        메뉴 이름: 
                    </label>
                    <input type="text" class="menu-name" :value="menu.menuName" @change="$emit('nameChange', [$event.target.value, menu.menuId])">
                </div>
                <div class="input-area url-area" v-if="menu.boardId == -1">
                    <label for="menu-url">
                        메뉴 URL: 
                    </label>
                    <input type="text" class="menu-url" :value="menu.menuUrl" @change="$emit('urlChange', [$event.target.value, menu.menuId])">
                </div>
            </div>
            <div class="input-col">
                <div class="input-area">
                    <label for="select-menu-auth">권한: </label>
                    <select name="menuAuth" class="select-menu-auth" :value="menu.menuAuth" @change="$emit('authChange',[$event.target.value, menu.menuId])">
                        <option value="0" v-if="menu.menuAuth == 0">비회원</option>
                        <option value="1">회원</option>
                        <option value="2">일반관리자</option>
                        <option value="3">슈퍼관리자</option>
                    </select>
                </div>
                <div class="input-area" v-if="menu.menuParentId == null">
                    <label for="select-board">연결 대상:</label>
                    <select name="board" class="select-board" @change="checkSelect($event.target.value, menu.menuId)"
                        :value="menu.boardId">
                        <option :key="b.boardId" :value="b.boardId" v-for="(b) in boards">{{ b.boardName }}</option>
                        <option value=-1>URL</option>
                    </select>
                </div>
                <div class="input-area">
                    <label for="select-use-yn">
                        활성화 여부
                    </label>
                    <select name="menuUseYn" class="select-use-yn" :value="menu.menuUseYn" @change="$emit('useYnChange', [$event.target.value, menu.menuId])">
                        <option value="Y">활성화</option>
                        <option value="N">비활성화</option>
                    </select>
                </div>

            </div>
            <div class="input-col">
                <button type="button" class="btn-delete" @click="$emit('delMenu', menu.menuId)">X</button>
            </div>
        </form>
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

    methods: {
        checkSelect(value, id) {
            if (value == null)
                this.isUrl = true;
            else 
                this.isUrl = false;

            this.$emit('boardChange',[value, id]);
        }
    },
    watch: {

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