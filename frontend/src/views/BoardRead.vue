<template>
  <div>
    <section id="main" class="container">
      <header>
        <h2>{{ item.title }}</h2>
        <p>게시글 상세</p>
      </header>
      <div class="box">
        <table class="table">
          <tr>
            <th>번호</th>
            <td>{{ item.no }}</td>
          </tr>
          <tr>
            <th>글쓴이</th>
            <td>{{ item.writer }}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>{{ item.title }}</td>
          </tr>
          <tr>
            <th>날짜</th>
            <td>{{ getFormatDate(item.regtime) }}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td colspan="2">
              {{ item.content }}
            </td>
          </tr>
          <tr></tr>
        </table>

        <div class="text-center" style="margin-top: 50px;">
          <p>실시간 댓글</p>
          <div class="row gtr-50 gtr-uniform">
            <div class="col-12">
              <table class="table text-center" style="text-align: center;">
                <colgroup>
                  <col width="15%" />
                  <col width="10%" />
                  <col width="65%" />
                  <col width="10%" />
                </colgroup>
                <tr class:>
                  <td>아이디</td>
                  <td>댓글 시간</td>
                  <td>댓글 내용</td>
                  <td></td>
                </tr>
                <tr v-for="(comment, index) in comments" :key="index">
                  <td>{{ comment.writer }}</td>
                  <td>{{ getFormatDate(comment.regtime) }}</td>
                  <td>{{ comment.content }}</td>
                  <td
                    v-if="getBoolean && candeletecomment(comment.writer)"
                    @click="deleteComment(comment.no)"
                  >
                    삭제
                  </td>
                  <td v-else></td>
                </tr>
              </table>
            </div>
            <div class="col-10 col-12-mobilep">
              <input
                type="text"
                id="nwecomment"
                v-model="newcomment"
                required
                placeholder="댓글을 입력하세요"
                style="margin-top: 50px;"
              />
            </div>
            <div class="col-2 col-12-mobilep">
              <button class="button special fit" @click="addcomment" style="margin-top: 50px;">
                댓글 달기
              </button>
            </div>
          </div>
        </div>
        <br />
        <br />
        <ul class="actions special">
          <li>
            <router-link to="/board/list" class="button special small">목록</router-link>
          </li>
          <li>
            <span v-if="getBoolean && isAuthor()">
              <router-link :to="'/board/update?no=' + item.no" class="button special small"
                >수정</router-link
              >
            </span>
          </li>
          <li>
            <span v-if="getBoolean && isAuthor()">
              <router-link :to="'/board/delete?no=' + item.no" class="button special small"
                >삭제</router-link
              >
            </span>
          </li>
        </ul>
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
      item: {},
      comments: [],
      newcomment: '',
    };
  },
  computed: {
    ...mapGetters(['getAccessToken', 'getUserId', 'getUserName', 'getBoolean']),
  },

  created() {
    axios.get(`${SERVER_URL}/board/${this.$route.params.no}`).then(({ data }) => {
      this.item = data;
      this.searchComments();
      console.log(this.comments);
    });
  },
  methods: {
    getFormatDate(regtime) {
      return moment(new Date(regtime)).format('YYYY.MM.DD HH:mm:ss');
    },
    getCommentFormatDate(regtime) {
      return moment(new Date(regtime)).format('MM.DD HH:mm:ss');
    },
    isAuthor() {
      // console.log('writer : ' + this.item.writer);
      // console.log('cur user : ' + localStorage.getItem('userId'));
      return this.item.writer == localStorage.getItem('userId');
    },
    searchComments() {
      axios.get(`${SERVER_URL}/comment/search/${this.$route.params.no}`).then(({ data }) => {
        this.comments = data.data;
      });
    },
    deleteComment(no) {
      axios.delete(`${SERVER_URL}/comment/delete/` + no).then(({ data }) => {
        let msg = '댓글 삭제 처리시 문제가 발생했습니다.';
        console.log(data);
        if (data === 'SUCCESS') {
          msg = '삭제가 완료되었습니다.';
        }
        alert(msg);
        this.searchComments();
      });
    },
    candeletecomment(writer) {
      if (writer == '(anonymous)' || writer == localStorage.getItem('userId')) {
        return true;
      } else {
        return false;
      }
    },
    addcomment() {
      var writer = localStorage.getItem('userId');
      if (writer == null || writer == 'undefined') {
        writer = '(anonymous)';
      }
      // console.log('boardno : ' + this.$route.params.no);
      // console.log('writer : ' + writer);
      // console.log('content : ' + this.newcomment);
      axios
        .post(`${SERVER_URL}/comment/insert`, {
          boardno: this.$route.params.no,
          writer: writer,
          content: this.newcomment,
        })
        .then((msg) => {
          this.newcomment = '';
          // console.log(msg);
          if (msg.data != "SUCCESS") {
            console.log("댓글 달기 실패");
          }
          this.searchComments();
        });
    },
  },
};
</script>
