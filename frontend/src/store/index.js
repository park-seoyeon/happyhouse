import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default new Vuex.Store({
  state: {
    accessToken: null,
    userId: '',
    userName: '',
    exptime: '',
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserId(state) {
      return state.userId;
    },
    getUserName(state) {
      return state.userName;
    },
    getExpTime(state) {
      return state.exptime;
    },
    getBoolean(state) {
      if (state.accessToken == null || state.accessToken == 'undefined')
        return null;
      else return state.accessToken;
    },
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload['auth-token'];
      state.userId = payload['user-id'];
      state.userName = payload['user-name'];

      localStorage.setItem('accessToken', state.accessToken);
      localStorage.setItem('userId', state.userId);
      localStorage.setItem('userName', state.userName);
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = '';
      state.userName = '';
      localStorage.removeItem('accessToken');
      localStorage.removeItem('userId');
      localStorage.removeItem('userName');
    },
    SETTOKEN(state, token) {
      state.accessToken = token;
    },
    SETID(state, id) {
      state.userId = id;
    },
    SETNAME(state, name) {
      state.userName = name;
    },
  },
  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/confirm/login`, user)
        .then((response) => {
          // commit을 통해 matations에 접근
          context.commit('LOGIN', response.data);
          // axios를 실행할 때 header에다가 담아라
          // 즉 token을 헤더에다가 담는 작업이다.
          axios.defaults.headers.common[
            'auth-token'
          ] = `${response.data['auth-token']}`;
        });
    },
    LOGOUT(context) {
      context.commit('LOGOUT');
      axios.defaults.headers.common['auth-token'] = null;
    },
    SETTOKEN(context, token) {
      if (token != null || token != 'undefined') {
        context.commit('SETTOKEN', token);
        axios.defaults.headers.common['auth-token'] = `${token}`;
      } else {
        context.commit('SETTOKEN', null);
        axios.defaults.headers.common['auth-token'] = null;
      }
    },
    SETID(context, id) {
      context.commit('SETID', id);
    },
    SETNAME(context, name) {
      context.commit('SETNAME', name);
    },
  },
  modules: {},
});
