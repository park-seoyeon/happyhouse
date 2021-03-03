import Vue from "vue";
import VueRouter from "vue-router";

import Index from "@/views/Index.vue";
import DealView from "@/views/DealView.vue";
import Join from "@/views/Join.vue";
import Login from "@/views/Login.vue";
import UserUpdate from "@/views/UserUpdate.vue";
import UserView from "@/views/UserView.vue";
import BoardList from '@/views/BoardList.vue';
import BoardCreate from '@/views/BoardCreate.vue';
import BoardRead from '@/views/BoardRead.vue';
import BoardUpdate from '@/views/BoardUpdate.vue';
import BoardDelete from '@/views/BoardDelete.vue';
import LikeAreaView from '@/views/LikeAreaView.vue';
import store from "../store";


Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
// 메인에서 localhost/me 친다고 하면 to는 me, from은 /??
const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;
  // 이거 말고도 local storage? 세션스토리지? 에서도 ||으로 확인하는거 넣어야 페이지 이동 시 로그인이 풀리지 않는다
  // 이 때 local storage에서 그냥 브라우저를 닫으면 토큰이 삭제되지 않으니까 로그인 할 때마다 토큰을
  // 유효한 토큰 이외에는 삭제하는 방법을 사용해야 한다.
  if (store.getters.getAccessToken || (localStorage.getItem('accessToken') != null || localStorage.getItem('accessToken') != "undefined")) {
    return next();
  } else next("/login" + nextRoute);
};


const routes = [
  { path: '/', name: 'index', component: Index },
  { path: '/dealview', name: 'dealview', component: DealView },
  { path: '/join', name: 'join', component: Join },
  { path: '/login', name: 'login', component: Login },
  { path: '/userupdate', name: 'userupdate', component: UserUpdate, beforeEnter: requireAuth() },
  { path: '/userview', name: 'userview', component: UserView, beforeEnter: requireAuth() },
  { path: '/board/list', name: 'boardlist', component: BoardList },
  { path: '/board/create', name: 'boardcreate', component: BoardCreate },
  { path: '/board/read/:no', name: 'boardread', component: BoardRead },
  { path: '/board/update', name: 'boardupdate', component: BoardUpdate },
  { path: '/board/delete', name: 'boarddelete', component: BoardDelete },
  { path: '/likeareaview', name: 'likeareaview', component: LikeAreaView },

];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
