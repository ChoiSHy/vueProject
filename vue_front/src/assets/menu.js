const menus = [
  {
    menuId: 1,
    menuName: "메뉴1",
    menuUseYn: "Y",
    menuIdx: 0,
    menuUrl: "/menu/goBoard/menuId=1",
    menuAuth: 0,
    menuParentId: null,
    children: [
      {
        menuId: 3,
        menuName: "메뉴3",
        menuUseYn: "Y",
        menuIdx: 2,
        menuUrl: "/menu/goBoard/menuId=3",
        menuAuth: 2,
        menuParentId: 1,
        boardId: 2,
        children: [],
      },
      {
        menuId: 4,
        menuName: "Google",
        menuUseYn: "Y",
        menuIdx: 3,
        menuUrl: "https://www.google.com",
        menuAuth: 0,
        menuParentId: 1,
        boardId: -1,
        children: [],
      },
    ],
    boardId: 0,
  },
  {
    menuId: 2,
    menuName: "메뉴2",
    menuUseYn: "Y",
    menuIdx: 1,
    menuUrl: "/menu/goBoard/menuId=2",
    menuAuth: 1,
    menuParentId: null,
    boardId: 1,
    children: [],
  },
];

export default menus;
