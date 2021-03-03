<template>
  <div>
    {{ findlogin() }}
    <header id="header">
      <nav id="nav">
        <ul>
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/board/list">게시판</router-link></li>
          <li><router-link to="/dealview">주변 탐방</router-link></li>
          <li>
            <a
              href="#"
              v-if="this.$route.path != '/likeareaview'"
              @click="showLikeModal()"
              >관심지역설정</a
            >
          </li>
          <li>
            <router-link v-if="getBoolean" to="/likeareaview"
              >관심지역보기</router-link
            >
          </li>
          <li v-show="getBoolean">
            <a href="#" class="button" @click="logout">Logout</a>
          </li>
          <li v-show="getBoolean">
            <router-link to="/userview" class="button">{{
              getUserId
            }}</router-link>
          </li>
          <li v-show="!getBoolean">
            <router-link to="/join" class="button">Sign Up</router-link>
          </li>
          <li v-show="!getBoolean">
            <router-link to="/login" class="button">Login</router-link>
          </li>
        </ul>
      </nav>
    </header>
  </div>
</template>
<script>
//mapGetters 등록
import axios from 'axios';
import { mapGetters } from 'vuex';
import LikeModal from '../components/likearea.vue';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      isLogin: false,
      sido: '',
      gugun: '',
      dong: '',
      userid: null,
    };
  },

  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName', 'getBoolean']),
  },
  methods: {
    findlogin: function() {
      var atoken = localStorage.getItem('accessToken');
      var uid = localStorage.getItem('userId');
      var uname = localStorage.getItem('userName');
      if (atoken != null && atoken != 'undefined') {
        this.$store.dispatch('SETTOKEN', atoken);
        this.$store.dispatch('SETID', uid);
        this.$store.dispatch('SETNAME', uname);
        this.userid = uid;
      }
      this.setLikes();
      console.log(this.$route);
    },
    logout: function() {
      this.$store
        //dispatch를 통해 actions에 접근
        .dispatch('LOGOUT')
        .then(() => this.$router.replace('/').catch(() => {}));
    },
    setLikes: function() {
      axios
        .get(`${SERVER_URL}/likearea/select/` + localStorage.getItem('userId'))
        .then((response) => {
          this.sido = response.data.data.sido;
          this.gugun = response.data.data.gugun;
          this.dong = response.data.data.dong;
          console.log('setLikest success!');
        })
        .catch(() => {
          console.log('getting likearea of user fail!');
        });
    },
    showLikeModal: function() {
      if (this.$store.getters.getBoolean) {
        this.$modal.show(
          LikeModal,
          {
            modal: this.$modal,
            presido: this.sido,
            pregugun: this.gugun,
            predong: this.dong,
            userid: this.userid,
          },
          {
            name: 'dynamic-modal',
            width: '500px',
            height: '300px',
            draggable: true,
          },
          {
            closed: this.setLikes,
          }
        );
      } else {
        alert('로그인 후 이용 가능합니다!');
      }
    },
    // showLikeArea: function() {
    //   if (this.$store.getters.getBoolean) {
    //     this.$router.replace('/likeareaview');
    //   } else {
    //     alert('로그인 후 이용 가능합니다!');
    //   }
    // },
  },
};
</script>
<style></style>
