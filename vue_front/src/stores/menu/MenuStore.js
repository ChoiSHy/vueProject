import { defineStore } from "pinia";
import axios from "axios";
import menus from "@/assets/menu.js";
import { ref } from "vue";

function flattenMenu(menuList = []) {
  return menuList.flatMap((menu) => {
    const children = Array.isArray(menu.children)
      ? flattenMenu(menu.children)
      : [];
    return [menu, ...children];
  });
}

export const useMenuStore = defineStore("menuStore", {
  state: () => ({
    menus: ref(menus),
    delIds: [],
  }),

  actions: {
    addMenu(parent = null) {
      const newMenu = {
        menuId: Date.now(),
        menuName: "",
        menuAuth: 0,
        menuUseYn: "Y",
        boardId: -1,
        menuUrl: "",
        menuParentId: parent ? parent.menuId : null,
        children: [],
      };
      if (parent) {
        parent.children.push(newMenu);
      } else {
        this.menus.push(newMenu);
      }
    },
    updateMenuOrder(menus = this.menus) {
      menus.forEach((menu, idx) => {
        menu.menuIdx = idx;
        if (menu.children && menu.children.length > 0) {
          this.updateMenuOrder(menu.children);
        }
      });
    },
    setMenus(menuList) {
      this.menus = menuList;
    },
    setDelIds(delIds) {
      this.delIds = delIds;
    },
    async saveMenus() {
      try {
        const payload = {
          menus: this.menus,
          delIds: this.delIds,
        };
        await axios.post("/api/apple/menu/updMenu", payload);
        console.log("전송 성공");
      } catch (error) {
        console.error("서버 저장 실패: ".error);
      }
    },
    deleteMenu(menuId) {
      const recursiveDelete = (list) =>
        list
          .filter((m) => m.menuId !== menuId)
          .map((m) => ({
            ...m,
            children: recursiveDelete(m.children || []),
          }));
      this.menus = recursiveDelete(this.menus);
    },
  },
  getters: {
    menusFlat(state) {
      if (!Array.isArray(state.menus)) return [];
      return flattenMenu(state.menus);
    },
  },
});
