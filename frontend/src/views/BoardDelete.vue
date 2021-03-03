<template>
  <div>
    <br />
    <br />
    <div class="spinner-border text-secondary" style="margin: 100px;"></div>
  </div>
</template>
<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export default {
  created() {
    axios.delete(`${SERVER_URL}/comment/deleteAll/${this.$route.query.no}`).then(({ data }) => {
      let msg = '댓글 삭제 처리시 문제가 발생했습니다.';
      if (data === 'success') {
        msg = '삭제가 완료되었습니다.';
      }
      axios.delete(`${SERVER_URL}/board/${this.$route.query.no}`).then(({ data }) => {
        msg = '게시글 삭제 처리시 문제가 발생했습니다.';
        if (data === 'success') {
          msg = '삭제가 완료되었습니다.';
        }
        alert(msg);
        this.$router.push('/board/list');
      });
    });
  },
};
</script>
