import {defineStore} from 'pinia'
import axios from 'axios'
import menus from '@/assets/menu.js';

export const useMenuStore = defineStore('menuStore', {
    state: ()=> ({
        menus:menus,
        delIds:[],
    }),

    actions:{
        setMenus(menuList){
            this.menus = menuList;
        },
        setDelIds(delIds){
            this.delIds = delIds;
        },
        updateMenuOrder(){
            this.menus.forEach((menu, idx)=>{
                menu.menuIdx = idx;
            })
        },
        async saveMenus(){
            try{
                const payload = {
                    menus: this.menus,
                    delIds: this.delIds
                }
                await axios.post('/api/apple/menu/updMenu', payload)
                console.log("전송 성공");
            }
            catch(error){
                console.error('서버 저장 실패: '.error);
            }
        }

    }
})