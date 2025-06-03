<template>
    <h1>메뉴 관리</h1>
    <div>
        <button type="button" @click="add()">메뉴 추가</button>
    </div>
    <div>
        <draggable v-model="menus_temp" item-key="menuId" animation="200">
            <template #item="{ element }">
                <MenuComp :menu="element" :boards="boards" :key="element.menuId" @nameChange="nameChange($event)"
                    @urlChange="urlChange($event)" @authChange="authChange($event)" @boardChange="boardChange($event)"
                    @useYnChange="useYnChange($event)" @delMenu="delMenu($event)" />
            </template>
        </draggable>
    </div>
    <div>
        <button type="button" @click="save()">저장</button>
        <button type="button" @click="reset()">초기화</button>
        <button type="button" @click="back()">취소</button>
    </div>
</template>
<script>
import { ref } from 'vue';
import draggable from 'vuedraggable'

import boards from '@/assets/board.js';
import MenuComp from '@/components/menu/MenuComp.vue';
// npm install vuedraggable@next

import { useMenuStore } from '@/stores/menu/MenuStore.js';

export default {
    name: "MenuConatiner",
    
    data: () => {
        return {
            menus_temp: [],
            delIds: [],
            boards: boards,
        }
    },
    created() {
        const menuStore = useMenuStore()
        this.menus_temp = ref([...menuStore.menus]);
    },
    components: {
        MenuComp: MenuComp,
        draggable,
    },
    methods: {
        nameChange([name, id]) {
            console.log(name, id)
            this.menus_temp.find(menu => {
                if (menu.menuId == id) {
                    console.log(`${menu.menuName} => ${name}`)
                    menu.menuName = name;
                }
            })

        },
        urlChange([url, id]) {
            this.menus_temp.find(menu => {
                if (menu.menuId == id)
                    menu.menuUrl = url;
            })
        },
        authChange([auth, id]) {
            this.menus_temp.find(menu => {
                if (menu.menuId == id) {
                    console.log(`${menu.menuAuth} => ${auth}`)
                    menu.menuAuth = auth;
                }
            })
        },
        boardChange([boardId, id]) {
            this.menus_temp.find(menu => {
                if (menu.menuId == id) {
                    console.log(`${menu.boardId} => ${boardId}`);
                    menu.boardId = boardId;
                }
            })
        },
        useYnChange([useYn, id]) {
            this.menus_temp.find(menu => {
                if (menu.menuId == id) {
                    console.log(`${menu.menuUseYn} => ${useYn}`);
                    menu.menuUseYn = useYn;
                }
            })
        },
        delMenu(menuId) {
            console.log(menuId);

            this.menus_temp = this.menus_temp.filter(menu => {
                return menu.menuId !== menuId;
            })
            console.log(this.menus_temp);
            this.delIds.push(menuId);
            console.log(this.delIds)
        },
        save() {
            const menuStore = useMenuStore();
            menuStore.setMenus(this.menus_temp);
            menuStore.setDelIds(this.delIds);
            menuStore.saveMenus();
        },
        reset() {
            const menuStore = useMenuStore();
            this.menus_temp = [...menuStore.menus];
            this.delIds = [];
        },
        back() {

        },
        add() {
            this.menus_temp.push(
                {
                    menuId: null,
                    menuName: null,
                    menuUseYn: "Y",
                    menuIdx: null,
                    menuUrl: null,
                    menuAuth: 0,
                    menuParentId: null,
                    boardId: -1,
                }
            )
        },
    }
}

</script>
<style></style>