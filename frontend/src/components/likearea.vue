<template>
  <div>
    <div class="text-center" style="margin: 50px;">
      <h3 style="margin: 20px;">
        관심 지역 설정
      </h3>
      <div class="text-center">
        <p style="margin: 20px;">
          기존 관심 지역 : {{ presido }} {{ pregugun }} {{ predong }}
        </p>
        <div class="dropdown">
          <div class="btn-group">
            <select id="likesido" class="custom-select" @change="gugun">
              <option value="0">시/도</option>
              <option
                v-for="(likesido, index) in likesidos"
                :key="index"
                :value="likesido.sidoCode"
              >
                {{ likesido.sidoName }}
              </option>
            </select>
          </div>
          <div class="btn-group">
            <select id="likegugun" class="custom-select" @change="dong">
              <option value="0">구/군</option>
              <option
                v-for="(likegugun, index) in likeguguns"
                :key="index"
                :value="likegugun.gugunCode"
              >
                {{ likegugun.gugunName }}
              </option>
            </select>
          </div>
          <div class="btn-group">
            <select id="likedong" class="custom-select">
              <option value="0">동</option>
              <option
                v-for="(likedong, index) in likedongs"
                :key="index"
                :value="likedong.code"
              >
                {{ likedong.dong }}
              </option>
            </select>
          </div>
        </div>
      </div>
      <div class="text-center" style="margin-top: 40px">
        <input
          class="btn btn-primary"
          @click="updateLikeArea"
          type="button"
          value="등록"
        />&nbsp;
        <input
          class="btn btn-danger"
          @click="$emit('close')"
          type="button"
          value="취소"
        />
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export default {
  data: function() {
    return {
      likesidos: [],
      likeguguns: [],
      likedongs: [],
    };
  },
  created() {
    axios
      .get(`${SERVER_URL}/map/sido`)
      .then((response) => {
        this.likesidos = response.data.data;
      })
      .catch(() => {
        console.log('likesido get fail');
      });
    console.log(this.presido);
    console.log(this.pregugun);
    console.log(this.predong);
    console.log(this.userid);
  },

  props: ['presido', 'pregugun', 'predong', 'userid'],
  methods: {
    updateLikeArea() {
      var sidoelement = document.getElementById('likesido');
      var sidoname = sidoelement.options[sidoelement.selectedIndex].text;
      var gugunelement = document.getElementById('likegugun');
      var gugunname = gugunelement.options[gugunelement.selectedIndex].text;
      var dongelement = document.getElementById('likedong');
      var dongname = dongelement.options[dongelement.selectedIndex].text;
      axios
        .put(`${SERVER_URL}/likearea/update`, {
          userid: this.userid,
          sido: sidoname,
          gugun: gugunname,
          dong: dongname,
        })
        .then(() => {
          console.log('updateLikearea success!');
        })
        .catch(() => {
          console.log('getting likearea of user fail!');
        });
      this.$emit('close');
      //   this.$router.push(this.$route.path);
    },

    gugun() {
      var sidoelement = document.getElementById('likesido');
      console.log(sidoelement.options[sidoelement.selectedIndex]);
      var sidocode = sidoelement.options[sidoelement.selectedIndex].value;
      console.log('selected sidocode : ' + sidocode);
      axios
        .get(`${SERVER_URL}/map/gugun/` + sidocode)
        .then((response) => {
          this.likeguguns = response.data.data;
        })
        .catch(() => {
          console.log('gugun get fail');
        });
    },

    dong() {
      var gugunelement = document.getElementById('likegugun');
      var guguncode = gugunelement.options[gugunelement.selectedIndex].value;

      console.log('selected guguncode : ' + guguncode);
      axios
        .get(`${SERVER_URL}/map/dong/` + guguncode)
        .then((response) => {
          this.likedongs = response.data.data;
        })
        .catch(() => {
          console.log('dong get fail');
        });
    },

    islogin() {
      if (this.userid == null || this.userid == 'undefined') {
        return false;
      } else return true;
    },
  },
};
</script>
