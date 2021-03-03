<template>
  <div>
    <section id="main" class="container medium">
      <header>
        <h2>로그인</h2>
        <p>로그인 해주세요</p>
      </header>
      <div class="box">
        <div>
          <div class="row gtr-50 gtr-uniform">
            <div class="col-12">
              <label for="userid">아이디</label>
              <input
                type="text"
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디 입력...."
              />
            </div>
            <div class="col-12">
              <label for="userpwd">비밀번호</label>
              <input
                type="password"
                id="userpwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호 입력...."
                @keypress.enter="login"
              />
            </div>
            <div class="col-12">
              <ul class="actions special" style="margin-top: 80px;">
                <li>
                  <button class="button special small" @click="login">
                    로그인
                  </button>
                </li>
                <li>
                  <router-link to="/join" class="button special small">
                    회원가입
                  </router-link>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

export default {
  name: 'Login',
  data: function() {
    return {
      user: {
        userid: '',
        userpwd: '',
      },
      message: '',
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : '';
    },
  },
  methods: {
    login: function() {
      console.log(this.nextRoute);
      // LOGIN 액션 실행
      // 서버와 통신(axios)을 해 토큰값을 얻어야 하므로 Actions를 호출.
      this.$store
        .dispatch('LOGIN', this.user)
        .then(() => this.$router.replace(`/${this.nextRoute}`))
        .catch(({ message }) => (this.msg = message));
    },
  },
};
</script>

<style></style>
