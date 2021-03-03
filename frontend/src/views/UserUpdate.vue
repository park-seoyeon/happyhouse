<template>
  <div>
    <section id="main" class="container medium">
      <header>
        <h2>회원정보 수정</h2>
      </header>
      <div class="box">
        <div>
          <div class="row gtr-50 gtr-uniform">
            <div class="col-12">
              <label for="writer">아이디</label>
              <input type="text" id="edit_usrid" name="id" :value="user.userid" readonly />
            </div>
            <div class="col-12">
              <label for="title">비밀번호</label>
              <input type="password" id="edit_usrpw" name="password" v-model="user.userpwd" />
            </div>
            <div class="col-12">
              <label for="content">이름</label>
              <input type="text" id="edit_usrn" name="name" v-model="user.username" />
            </div>
            <div class="col-12">
              <label for="content">주소</label>
              <input type="text" id="edit_usrad" name="address" v-model="user.address" />
            </div>
            <div class="col-12">
              <label for="content">이메일</label>
              <input type="email" id="edit_usrc" name="email" v-model="user.email" />
            </div>
            <div class="col-12">
              <ul class="actions special">
                <li>
                  <button class="button special small" @click="userupdate">
                    수정
                  </button>
                </li>
                <li>
                  <button class="button special small" @click="userdelete">
                    탈퇴
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
      user: null,
    };
  },
  created() {
    // 얘는 Actions에서 하지 않는 이유 : store에 저장 하고 작업 하는 것이 아니라
    // 서버에서 가져와서 그냥 바로 뿌리는 것이기 때문에
    // 이 때 토큰을 담지 않는 이유는 Login할 대 토큰을 담기 때문이다.
    axios
      .get(`${SERVER_URL}/user/view/` + localStorage.getItem('userId'))
      .then((response) => {
        this.user = response.data.user;
        console.log(this.user);
      })
      .catch(() => {
        console.log('no user : ' + this.user);
        this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
      });
  },

  methods: {
    userupdate: function() {
      axios
        .put(`${SERVER_URL}/user/update`, {
          userid: this.user.userid,
          username: this.user.username,
          userpwd: this.user.userpwd,
          email: this.user.email,
          address: this.user.address,
        })
        .then(() => {
          this.$router.replace('/');
        })
        .catch(() => {
          console.log('error!');
          this.$router.replace('/');
        });
    },

    userdelete: function() {
      axios
        .delete(`${SERVER_URL}/likearea/delete/` + this.user.userid)
        .then(() => {
          axios
            .delete(`${SERVER_URL}/user/delete/` + this.user.userid)
            .then((response) => {
              this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
              response;
            })
            .catch(() => {
              console.log('error!');
              this.$router.replace('/');
            });
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
