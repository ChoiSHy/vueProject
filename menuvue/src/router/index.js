import { createRouter, createWebHistory } from 'vue-router'
import MenuContainer from '@/components/menu/MenuContainer.vue'
import HelloWorld from '@/components/HelloWorld.vue'
import BoardEditor from '@/components/board/BoardEditor.vue'
import BoardStatisticView from '@/views/statistic/BoardStatisticView.vue'

const routes = [
  {path:'/menu/editor', name:'MenuEditor', component: MenuContainer},
  {path:'/board/editor',name:'BoardEditor', component: BoardEditor},
  {path:'/hello',name:'Hello',component:HelloWorld},
  {path:'/', name:'StatisticView', component:BoardStatisticView}
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router