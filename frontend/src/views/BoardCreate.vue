<template>
  <div>
    <section id="main" class="container medium">
      <header>
        <h2>게시글 작성</h2>
      </header>
      <div class="box">
        <div>
          <div class="row gtr-50 gtr-uniform">
            <div class="col-12">
              <label for="writer">작성자</label>
              <input
                type="text"
                id="writer"
                ref="writer"
                placeholder="작성자를 입력하세요"
                v-model="writer"
                readonly
              />
            </div>
            <div class="col-12">
              <label for="title">제목</label>
              <input
                type="text"
                id="title"
                ref="title"
                placeholder="제목을 입력하세요"
                v-model="title"
              />
            </div>
            <div class="col-12">
              <label for="content">내용</label>
              <textarea
                type="text"
                id="contnet"
                ref="content"
                placeholder="내용을 입력하세요"
                v-model="content"
                rows="6"
              ></textarea>
            </div>
            <div class="col-12">
              <ul class="actions special">
                <li>
                  <button class="button special small" @click="checkHandler">
                    등록
                  </button>
                </li>
                <li>
                  <button class="button special small" @click="moveList">
                    목록
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
      no: '',
      regtime: '',
      writer: localStorage.getItem('userId'),
      title: '',
      content: '',
    };
  },
  methods: {
    checkHandler() {
      let err = true;
      let msg = '';
      !this.writer &&
        ((msg = '작성자를 입력해주세요'),
        (err = false),
        this.$refs.writer.focus());
      err &&
        !this.title &&
        ((msg = '제목 입력해주세요'), (err = false), this.$refs.title.focus());
      err &&
        !this.content &&
        ((msg = '내용 입력해주세요'),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else this.createHandler();
    },
    createHandler() {
      axios
        .post(`${SERVER_URL}/board`, {
          writer: this.writer,
          title: this.title,
          content: this.content,
        })
        .then(({ data }) => {
          let msg = '등록 처리시 문제가 발생했습니다.';
          if (data === 'success') {
            msg = '등록이 완료되었습니다.';
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push('/board/list');
    },
  },
};
</script>
