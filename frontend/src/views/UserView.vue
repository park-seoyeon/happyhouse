<template>
  <div>
    <section id="main" class="container" style="margin-bottom: 140px;">
      <header>
        <h2>회원 정보 확인</h2>
      </header>
      <div class="box">
        <table class="table">
          <tr>
            <th>아이디</th>
            <td>{{ user.userid }}</td>
          </tr>
          <tr>
            <th>이름</th>
            <td>{{ user.username }}</td>
          </tr>
          <tr>
            <th>주소</th>
            <td>{{ user.address }}</td>
          </tr>
          <tr>
            <th>이메일</th>
            <td>{{ user.email }}</td>
          </tr>
        </table>

        <br />
        <ul class="actions special">
          <li>
            <router-link to="/" class="button special small">확인</router-link>
          </li>
          <li>
            <router-link to="/userupdate" class="button special small"
              >수정</router-link
            >
          </li>
        </ul>
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
      })
      .catch(() => {
        this.$store.dispatch('LOGOUT').then(() => this.$router.replace('/'));
      });
  },
};
</script>

<style></style>
