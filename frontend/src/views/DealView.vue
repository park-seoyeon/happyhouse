<template>
  <div>
    <div>
      <section id="banner">
        <h2 style="font-size: 70px;">Happy House</h2>
        <p>행복한 우리집 찾기</p>
      </section>
      <nav
        class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark"
      >
        <div class="text-center">
          <div class="dropdown">
            <div class="btn-group">
              <select id="sido" class="custom-select" @change="gugun">
                <option value="0">시/도</option>
                <option
                  v-for="(sido, index) in sidos"
                  :key="index"
                  :value="sido.sidoCode"
                >
                  {{ sido.sidoName }}
                </option>
              </select>
            </div>
            <div class="btn-group">
              <select id="gugun" class="custom-select" @change="dong">
                <option value="0">구/군</option>
                <option
                  v-for="(gugun, index) in guguns"
                  :key="index"
                  :value="gugun.gugunCode"
                >
                  {{ gugun.gugunName }}
                </option>
              </select>
            </div>
            <div class="btn-group">
              <select id="dong" class="custom-select" @change="apt">
                <option value="0">동</option>
                <option
                  v-for="(dong, index) in dongs"
                  :key="index"
                  :value="dong.code"
                >
                  {{ dong.dong }}
                </option>
              </select>
            </div>
          </div>
        </div>
      </nav>
    </div>
    <div class="row">
      <div class="card col-sm-5 mt-1">
        <div class="card-body">
          <table class="table mt-2">
            <colgroup>
              <col width="30%" />
              <col width="70%" />
            </colgroup>
            <thead>
              <tr>
                <th>일련번호</th>
                <th>아파트이름</th>
              </tr>
            </thead>
          </table>
          <div style="height: 600px; overflow: auto;">
            <table class="table mt-2">
              <colgroup>
                <col width="30%" />
                <col width="70%" />
              </colgroup>
              <tbody
                id="searchResult"
                v-for="(apt, index) in apts"
                :key="index"
              >
                <th @click="selectapt(apt)">{{ apt.일련번호 }}</th>
                <th @click="selectapt(apt)">{{ apt.아파트 }}</th>
              </tbody>
            </table>
          </div>
          <div v-if="kinderTF">
            <div class="card-body">
              <h3 style="padding-bottom: 5px;">유치원 정보</h3>
              <table class="table mt-2">
                <colgroup>
                  <col width="30%" />
                  <col width="70%" />
                </colgroup>
                <thead>
                  <tr>
                    <th>우편번호</th>
                    <th>학교명</th>
                  </tr>
                </thead>
              </table>
              <div style="height: 400px; overflow: auto;">
                <table class="table mt-2">
                  <colgroup>
                    <col width="30%" />
                    <col width="70%" />
                  </colgroup>
                  <tbody
                    id="searchResult"
                    v-for="(kinder, index) in kinders"
                    :key="index"
                  >
                    <th @click="selectinstitute(kinder)">{{ kinder.ZIP }}</th>
                    <th @click="selectinstitute(kinder)">
                      {{ kinder.SCHUL_NM }}
                    </th>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div v-if="elementTF">
            <div class="card-body">
              <h3 style="padding-bottom: 5px;">초등학교 정보</h3>
              <table class="table mt-2">
                <colgroup>
                  <col width="30%" />
                  <col width="70%" />
                </colgroup>
                <thead>
                  <tr>
                    <th>우편번호</th>
                    <th>학교명</th>
                  </tr>
                </thead>
              </table>
              <div style="height: 400px; overflow: auto;">
                <table class="table mt-2">
                  <colgroup>
                    <col width="30%" />
                    <col width="70%" />
                  </colgroup>
                  <tbody
                    id="searchResult"
                    v-for="(element, index) in elements"
                    :key="index"
                  >
                    <th @click="selectinstitute(element)">
                      {{ element.ZIP }}
                    </th>
                    <th @click="selectinstitute(element)">
                      {{ element.SCHUL_NM }}
                    </th>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div v-if="middleTF">
            <div class="card-body">
              <h3 style="padding-bottom: 5px;">중학교 정보</h3>
              <table class="table mt-2">
                <colgroup>
                  <col width="30%" />
                  <col width="70%" />
                </colgroup>
                <thead>
                  <tr>
                    <th>우편번호</th>
                    <th>학교명</th>
                  </tr>
                </thead>
              </table>
              <div style="height: 400px; overflow: auto;">
                <table class="table mt-2">
                  <colgroup>
                    <col width="30%" />
                    <col width="70%" />
                  </colgroup>
                  <tbody
                    id="searchResult"
                    v-for="(middle, index) in middles"
                    :key="index"
                  >
                    <th @click="selectinstitute(middle)">{{ middle.ZIP }}</th>
                    <th @click="selectinstitute(middle)">
                      {{ middle.SCHUL_NM }}
                    </th>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="card col-sm-7" style="min-height: 850px;">
        <div v-if="selectedApt" style="padding-left: 20px">
          <br />
          <h3 style="padding-bottom: 10px">
            {{ selectedApt.아파트 }}
          </h3>
          <br />
          <div class="row">
            <div class="col-sm-5">
              <img
                src="@/assets/apart.jpg"
                alt="아파트"
                width="350px"
                height="350px"
              />
            </div>
            <div class="col-sm-7">
              <table class="table" style="width: 300px;">
                <colgroup>
                  <col width="40%" />
                  <col width="60%" />
                </colgroup>
                <tr>
                  <td>거래금액</td>
                  <td>{{ selectedApt.거래금액 }}&nbsp;만원</td>
                </tr>
                <tr>
                  <td>건축년도</td>
                  <td>{{ selectedApt.건축년도 }}&nbsp;년도</td>
                </tr>
                <tr>
                  <td>등록일자</td>
                  <td>
                    {{ selectedApt.년 }}년 {{ selectedApt.월 }}월
                    {{ selectedApt.일 }}일
                  </td>
                </tr>
                <tr>
                  <td>전용면적</td>
                  <td>{{ selectedApt.전용면적 }}&nbsp;m²</td>
                </tr>
                <tr>
                  <td>층</td>
                  <td>{{ selectedApt.층 }}&nbsp;층</td>
                </tr>
              </table>
            </div>
          </div>
          <br />
          <br />

          <hr />
          <br />
          <div class="text-center">
            <b-button type="button" variant="dark" class="m-1" @click="kinder">
              유치원 현황
            </b-button>
            <b-button type="button" variant="dark" class="m-1" @click="element">
              초등학교 현황
            </b-button>
            <b-button type="button" variant="dark" class="m-1" @click="middle">
              중학교 현황
            </b-button>
          </div>
          <br />
          <br />
          <div v-if="selectedinstitution" style="padding-top: 50px;">
            <div v-if="kinderTF">
              <br />
              <h3 style="padding-bottom: 10px">
                {{ selectedinstitution.SCHUL_NM }}
              </h3>
              <div class="row">
                <div class="col-sm-5">
                  <img
                    src="@/assets/kinder.jpg"
                    alt="유치원"
                    width="350px"
                    height="350px"
                  />
                </div>
                <div class="col-sm-7">
                  <table class="table" style="width: 500px;">
                    <colgroup>
                      <col width="40%" />
                      <col width="60%" />
                    </colgroup>
                    <tr>
                      <td>설립</td>
                      <td>{{ selectedinstitution.FOND }}</td>
                    </tr>
                    <tr>
                      <td>총 학생 수</td>
                      <td>{{ selectedinstitution.STDNT_CO_SM }}명</td>
                    </tr>
                    <tr>
                      <td>교실 수</td>
                      <td>{{ selectedinstitution.CLAS_CO }}명</td>
                    </tr>
                    <tr>
                      <td>교원 수</td>
                      <td>{{ selectedinstitution.FRL_TCHER_CO_SM }}명</td>
                    </tr>
                    <tr>
                      <td>주소</td>
                      <td>{{ selectedinstitution.ADRES }}</td>
                    </tr>
                    <tr>
                      <td>전화번호</td>
                      <td>{{ selectedinstitution.TELNO }}</td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
            <div v-if="elementTF">
              <br />
              <h3 style="padding-bottom: 10px">
                {{ selectedinstitution.SCHUL_NM }}
              </h3>
              <div class="row">
                <div class="col-sm-5">
                  <img
                    src="@/assets/element.png"
                    alt="초등학교"
                    width="350px"
                    height="350px"
                  />
                </div>
                <div class="col-sm-7">
                  <table class="table" style="width: 500px;">
                    <colgroup>
                      <col width="40%" />
                      <col width="60%" />
                    </colgroup>
                    <tr>
                      <td>설립</td>
                      <td>{{ selectedinstitution.FOND }}</td>
                    </tr>
                    <tr>
                      <td>평균 학생 수</td>
                      <td>{{ selectedinstitution.CLAS_AVG_STDNT_CO }}명</td>
                    </tr>
                    <tr>
                      <td>교실 수</td>
                      <td>{{ selectedinstitution.NRMLTY_CLSSRM_CO }}개</td>
                    </tr>
                    <tr>
                      <td>교원 수</td>
                      <td>{{ selectedinstitution.TCHER_CO_SM }}명</td>
                    </tr>
                    <tr>
                      <td>주소</td>
                      <td>{{ selectedinstitution.ADRES }}</td>
                    </tr>
                    <tr>
                      <td>전화번호</td>
                      <td>{{ selectedinstitution.TELNO }}</td>
                    </tr>
                    <tr>
                      <td>홈페이지</td>
                      <td>
                        <a
                          :href="selectedinstitution.HMPG"
                          target="_blank"
                          style="color: #A3A0ED;"
                          >{{ selectedinstitution.HMPG }}</a
                        >
                      </td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
            <div v-if="middleTF">
              <br />
              <h3 style="padding-bottom: 10px">
                {{ selectedinstitution.SCHUL_NM }}
              </h3>
              <div class="row">
                <div class="col-sm-5">
                  <img
                    src="@/assets/middle.jpg"
                    alt="중학교"
                    width="350px"
                    height="350px"
                  />
                </div>
                <div class="col-sm-7">
                  <table class="table" style="width: 500px;">
                    <colgroup>
                      <col width="40%" />
                      <col width="60%" />
                    </colgroup>
                    <tr>
                      <td>설립</td>
                      <td>{{ selectedinstitution.FOND }}</td>
                    </tr>
                    <tr>
                      <td>평균 학생 수</td>
                      <td>{{ selectedinstitution.CLAS_AVG_STDNT_CO }}명</td>
                    </tr>
                    <tr>
                      <td>교실 수</td>
                      <td>{{ selectedinstitution.NRMLTY_CLSSRM_CO }}개</td>
                    </tr>
                    <tr>
                      <td>교원 수</td>
                      <td>{{ selectedinstitution.FRL_TCHER_CO_SM }}명</td>
                    </tr>
                    <tr>
                      <td>주소</td>
                      <td>{{ selectedinstitution.ADRES }}</td>
                    </tr>
                    <tr>
                      <td>전화번호</td>
                      <td>{{ selectedinstitution.TELNO }}</td>
                    </tr>
                    <tr>
                      <td>홈페이지</td>
                      <td>
                        <a
                          :href="selectedinstitution.HMPG"
                          target="_blank"
                          style="color: #A3A0ED;"
                          >{{ selectedinstitution.HMPG }}</a
                        >
                      </td>
                    </tr>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const API_KEY =
  'UXySeOCPg8S62W1bHVoIVwCIo86p%2B84h%2FFcazLzYabzz86LM%2FtTo0gW5N5lKFjZqSn4kZYnvqNKxJKZ9dOnGIQ%3D%3D';
const API_URL =
  'https://cors-anywhere.herokuapp.com/http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev';
const KINDER_KEY = '765348516e6b6b6732397248764d4c';
const ELEMENT_KEY = '7a594972506b6b673130354252696473';
const MIDDLE_KEY = '65765176706b6b673239676b774845';
export default {
  data() {
    return {
      sidos: [],
      guguns: [],
      dongs: [],
      apts: [],
      selectedApt: null,
      kinders: [],
      kinderTF: false,
      elements: [],
      elementTF: false,
      middles: [],
      middleTF: false,
      selectedinstitution: null,
    };
  },

  created() {
    axios
      .get(`${SERVER_URL}/map/sido`)
      .then((response) => {
        this.sidos = response.data.data;
      })
      .catch(() => {
        console.log('sido get fail');
      });
  },

  methods: {
    gugun() {
      var sidoelement = document.getElementById('sido');
      var sidocode = sidoelement.options[sidoelement.selectedIndex].value;
      console.log('selected sidocode : ' + sidocode);
      axios
        .get(`${SERVER_URL}/map/gugun/` + sidocode)
        .then((response) => {
          this.guguns = response.data.data;
        })
        .catch(() => {
          console.log('gugun get fail');
        });
    },

    dong() {
      var gugunelement = document.getElementById('gugun');
      var guguncode = gugunelement.options[gugunelement.selectedIndex].value;

      console.log('selected guguncode : ' + guguncode);
      axios
        .get(`${SERVER_URL}/map/dong/` + guguncode)
        .then((response) => {
          this.dongs = response.data.data;
        })
        .catch(() => {
          console.log('dong get fail');
        });
    },

    apt() {
      var dongelement = document.getElementById('dong');
      var dongcode = dongelement.options[dongelement.selectedIndex].value;
      var dname = dongelement.options[dongelement.selectedIndex].text;
      console.log('selected dongcode : ' + dongcode);
      console.log('selected dongname : ' + dname);
      const params = {
        LAWD_CD: dongcode,
        DEAL_YMD: '202010',
        serviceKey: decodeURIComponent(API_KEY),
      };

      axios
        .get(API_URL, {
          params,
        })
        .then((response) => {
          console.log(response);
          this.apts = response.data.response.body.items.item;
          this.selectedApt = null;
        })
        .catch((error) => {
          console.log(error);
        });

      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            KINDER_KEY +
            '/json/SchulInfoKndrgr/1/1000/'
        )
        .then((response) => {
          console.log('kinder : ' + response.data);
          this.kinders = response.data.SchulInfoKndrgr.row.filter(function(
            object
          ) {
            console.log(object.ADRES);
            return object.ADRES.includes(dname);
          });
          console.log('Kidners : ' + this.kinders);
        })
        .catch((error) => {
          console.log(error);
        });

      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            ELEMENT_KEY +
            '/json/SchulInfoElesch/1/1000/'
        )
        .then((response) => {
          console.log('element : ' + response.data);
          this.elements = response.data.SchulInfoElesch.row.filter(function(
            object
          ) {
            return object.ADRES.includes(dname);
          });
        })
        .catch((error) => {
          console.log(error);
        });

      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            MIDDLE_KEY +
            '/json/SchulInfoMskul/1/1000/'
        )
        .then((response) => {
          console.log('middle : ' + response.data);
          this.middles = response.data.SchulInfoMskul.row.filter(function(
            object
          ) {
            return object.ADRES.includes(dname);
          });
        })
        .catch((error) => {
          console.log(error);
        });
    },

    selectapt(apt) {
      this.selectedApt = apt;
      this.kinderTF = false;
      this.elementTF = false;
      this.middleTF = false;
      this.selectedinstitution = null;
    },

    kinder() {
      console.log('call kinder!');

      this.kinderTF = true;
      this.elementTF = false;
      this.middleTF = false;
      this.selectedinstitution = null;
      console.log(this.kinders);
    },

    element() {
      this.kinderTF = false;
      this.elementTF = true;
      this.middleTF = false;
      this.selectedinstitution = null;
    },

    middle() {
      console.log('call middle!');
      this.kinderTF = false;
      this.elementTF = false;
      this.middleTF = true;
      this.selectedinstitution = null;
    },

    selectinstitute(institution) {
      console.log(institution);
      this.selectedinstitution = institution;
    },
  },
};
</script>

<style></style>
