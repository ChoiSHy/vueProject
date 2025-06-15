import { createRouter, createWebHistory } from "vue-router";
import MenuContainer from "@/components/menu/MenuContainer.vue";
import HelloWorld from "@/components/HelloWorld.vue";
import BoardEditor from "@/components/board/BoardEditor.vue";
import BoardStatisticView from "@/views/statistic/BoardStatisticView.vue";
import SearchView from "@/views/search/SearchView.vue";
import QuestionEditView from "@/views/survey/question/QuestionEditView.vue";
import OptionEditView from "@/views/survey/question/OptionEditView.vue";

const routes = [
  { path: "/menu/editor", name: "MenuEditor", component: MenuContainer },
  { path: "/board/editor", name: "BoardEditor", component: BoardEditor },
  { path: "/hello", name: "Hello", component: HelloWorld },
  {
    path: "/statistic/view",
    name: "StatisticView",
    component: BoardStatisticView,
  },
  { path: "/search", name: "SearchView", component: SearchView },
  {
    path: "/surv/:survId/editor/questions",
    name: "QuestEditor",
    component: QuestionEditView
  },
  {
    path:"/surv/:survId/editor/questions/:questId/options",
    name:"OptionEditor",
    component: OptionEditView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
