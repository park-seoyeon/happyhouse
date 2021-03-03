<template>
  <div id="app">
    <div id="page-wrapper">
      <nav-header></nav-header>
      <router-view></router-view>
      <nav-footer></nav-footer>
    </div>
  </div>
</template>
<script>
import Vue from 'vue';
import NavHeader from '@/views/NavHeader.vue';
import NavFooter from '@/views/NavFooter.vue';
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';

//mapGetters 등록
import { mapGetters } from 'vuex';

export default {
  components: {
    NavHeader,
    NavFooter,
  },

  data() {
    return {
      isLogin: false,
    };
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName']),
  },
  methods: {
    onClickLogout() {
      this.$store
        //dispatch를 통해 actions에 접근
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
    },
  },

  watch: {
    $route(to, from) {
      to;
      from;
      var atoken = localStorage.getItem('accessToken');
      var uid = localStorage.getItem('userId');
      var uname = localStorage.getItem('userName');
      if (atoken != null && atoken != undefined) {
        this.$store.state.accessToken = atoken;
        this.$store.state.userId = uid;
        this.$store.state.userName = uname;
      }
    },
  },
};
</script>
<style></style>
