import { createRouter, createWebHistory } from 'vue-router'
import MenuContainer from '@/components/menu/MenuContainer.vue'
import HelloWorld from '@/components/HelloWorld.vue'
import BoardEditor from '@/components/board/BoardEditor.vue'
import BoardStatisticView from '@/views/statistic/BoardStatisticView.vue'
import SearchView from '@/views/search/SearchView.vue'
import MenuEditor2 from '@/views/menu/MenuEditor2.vue'

const routes = [
  {path:'/menu/editor', name:'MenuEditor', component: MenuContainer},
  {path:'/menu/editor2',name:'MenuEditor2', component: MenuEditor2},
  {path:'/board/editor',name:'BoardEditor', component: BoardEditor},
  {path:'/hello',name:'Hello',component:HelloWorld},
  {path:'/statistic/view', name:'StatisticView', component:BoardStatisticView},
  {path:'/', name:'SearchView',component: SearchView},
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router