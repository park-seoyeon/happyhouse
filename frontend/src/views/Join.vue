<template>
  <div>
    <section id="main" class="container medium">
      <header>
        <h2>회원가입</h2>
      </header>
      <div class="box">
        <div>
          <div class="row gtr-50 gtr-uniform">
            <div class="col-12">
              <label for="writer">아이디</label>
              <input type="text" id="usrid" name="id" v-model="userid" />
            </div>
            <div class="col-12">
              <label for="title">비밀번호</label>
              <input
                type="password"
                id="usrpw"
                name="password"
                placeholder="영문 숫자 포함 6자리 이상"
                v-model="userpwd"
              />
            </div>
            <div class="col-12">
              <label for="content">이름</label>
              <input
                type="text"
                id="usrn"
                name="name"
                placeholder="User Name"
                v-model="username"
              />
            </div>
            <div class="col-12">
              <label for="content">주소</label>
              <input
                type="text"
                id="usrad"
                name="address"
                placeholder="OO시 OO구 OO동"
                v-model="address"
              />
            </div>
            <div class="col-12">
              <label for="content">이메일</label>
              <input
                type="email"
                id="usrc"
                name="email"
                placeholder="example@ssafy.com"
                v-model="email"
              />
            </div>
            <div class="col-12">
              <ul class="actions special">
                <li>
                  <button class="button special small" @click="userregist">
                    가입
                  </button>
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
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      userid: '',
      username: '',
      userpwd: '',
      email: '',
      address: '',
    };
  },
  methods: {
    userregist: function() {
      axios
        .post(`${SERVER_URL}/user/register`, {
          userid: this.userid,
          username: this.username,
          userpwd: this.userpwd,
          email: this.email,
          address: this.address,
        })
        .then((response) => {
          axios
            .post(`${SERVER_URL}/likearea/register`, {
              userid: this.userid,
              sido: '시',
              gugun: '구',
              dong: '동',
            })
            .then(() => {
              console.log('insertLikearea success!');
            })
            .catch(() => {
              console.log('getting likearea of user fail!');
            });

          this.$router.replace('/');
          response;
        })
        .catch(() => {
          console.log('error!');
          this.$router.replace('/');
        });
    },
  },
};
</script>

<style></style>
