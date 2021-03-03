<template>
  <div>
    <section id="main" class="container">
      <header>
        <h2>게시판</h2>
        <p>여러분들의 이야기를 들려주세요</p>
      </header>
      <div class="box">
        <span class="image featured"
          ><img src="@/assets/apart01.jpg" alt="" style="height: 350px;"
        /></span>
        <div v-if="items.length">
          <table class="table">
            <colgroup>
              <col width="10%" />
              <col width="50%" />
              <col width="15%" />
              <col width="25%" />
            </colgroup>
            <tr>
              <th>번호</th>
              <th>제목</th>
              <th>작성자</th>
              <th>날짜</th>
            </tr>
            <tr v-for="(board, index) in items" :key="index + '_items'">
              <td>{{ board.no }}</td>
              <td>
                <router-link :to="'/board/read/' + board.no">{{
                  board.title
                }}</router-link>
              </td>
              <td>{{ board.writer }}</td>
              <td>{{ getFormatDate(board.regtime) }}</td>
            </tr>
          </table>
        </div>
        <div v-else class="text-center">
          게시글이 없습니다.
        </div>
        <div class="text-right" v-if="getBoolean">
          <button class="button alt small" @click="movePage">등록</button>
        </div>
      </div>
    </section>
  </div>
</template>
<script>
import axios from 'axios';
import moment from 'moment';
import { mapGetters } from 'vuex';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  data() {
    return {
      items: [],
    };
  },

  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName', 'getBoolean']),
  },

  created() {
    axios.get(`${SERVER_URL}/board`).then(({ data }) => {
      this.items = data;
    });
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD');
    },
    movePage() {
      this.$router.push('/board/create');
    },
  },
};
</script>
<style></style>
