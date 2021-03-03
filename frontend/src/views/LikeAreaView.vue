<template>
  <div>
    <div>
      <section id="banner">
        <h2 style="font-size: 70px;">Happy House</h2>
        <p>행복한 우리집 찾기</p>
      </section>
      <nav
        class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark text-white"
      >
        <div class="text-center nav-item active">
          <h5 style="color: #FFFFFF;">
            관심 지역 : {{ sido }} {{ gugun }} {{ dong }}
          </h5>
        </div>
      </nav>
    </div>
    <div class="row">
      <div class="card col-sm-5 mt-1">
        <br />
        <div class="row">
          <div @click="pharmacyOn" class="col-4 col-12-narrower">
            <input type="radio" id="pharmacy" name="health" checked />
            <label for="pharmacy">
              약국
            </label>
          </div>
          <div @click="hospitalOn" class="col-4 col-12-narrower">
            <input type="radio" id="hospital" name="health" />
            <label for="hospital">
              병원
            </label>
          </div>
          <div @click="carecenterOn" class="col-4 col-12-narrower">
            <input type="radio" id="carecenter" name="health" />
            <label for="carecenter">
              산후조리원
            </label>
          </div>
        </div>
        <div v-if="ispharmacy" class="card-body">
          <h3 style="padding-bottom: 5px;">{{ gugun }} 약국 정보</h3>
          <table class="table mt-2">
            <colgroup>
              <col width="30%" />
              <col width="70%" />
            </colgroup>
            <thead>
              <tr>
                <th>약국명</th>
                <th>전화번호</th>
              </tr>
            </thead>
          </table>
          <div style="height: 300px; overflow: auto;">
            <table class="table mt-2">
              <colgroup>
                <col width="30%" />
                <col width="70%" />
              </colgroup>
              <tbody
                id="searchResult"
                v-for="(pharmacy, index) in pharmacies"
                :key="index"
              >
                <th>
                  {{ pharmacy.PHARM_NAME }}
                </th>
                <th>
                  {{ pharmacy.TEL_NUM }}
                </th>
              </tbody>
            </table>
          </div>
        </div>
        <div v-if="ishospital" class="card-body">
          <h3 style="padding-bottom: 5px;">{{ gugun }} 병원 정보</h3>
          <table class="table mt-2">
            <colgroup>
              <col width="30%" />
              <col width="70%" />
            </colgroup>
            <thead>
              <tr>
                <th>병원명</th>
                <th>주소</th>
              </tr>
            </thead>
          </table>
          <div style="height: 300px; overflow: auto;">
            <table class="table mt-2">
              <colgroup>
                <col width="30%" />
                <col width="70%" />
              </colgroup>
              <tbody
                id="searchResult"
                v-for="(hospital, index) in hospitals"
                :key="index"
              >
                <th>
                  {{ hospital.BPLCNM }}
                </th>
                <th>
                  {{ hospital.SITEWHLADDR }}
                </th>
              </tbody>
            </table>
          </div>
        </div>
        <div v-if="iscarecenter" class="card-body">
          <h3 style="padding-bottom: 5px;">{{ gugun }} 산후조리원 정보</h3>
          <table class="table mt-2">
            <colgroup>
              <col width="30%" />
              <col width="70%" />
            </colgroup>
            <thead>
              <tr>
                <th>병원명</th>
                <th>주소</th>
              </tr>
            </thead>
          </table>
          <div style="height: 300px; overflow: auto;">
            <table class="table mt-2">
              <colgroup>
                <col width="30%" />
                <col width="70%" />
              </colgroup>
              <tbody
                id="searchResult"
                v-for="(center, index) in carecenters"
                :key="index"
              >
                <th>
                  {{ center.BPLCNM }}
                </th>
                <th>
                  {{ center.SITEWHLADDR }}
                </th>
              </tbody>
            </table>
          </div>
        </div>
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
        <br />
        <div class="row">
          <div @click="kinderOn" class="col-4 col-12-narrower">
            <input type="radio" id="kinder" name="building" checked />
            <label for="kinder">
              유원시설
            </label>
          </div>
          <div @click="cultureOn" class="col-4 col-12-narrower">
            <input type="radio" id="culture" name="building" />
            <label for="culture">
              문화시설
            </label>
          </div>
        </div>
        <br />
        <div v-if="iskinder">
          <h3 style="padding-bottom: 5px;">{{ gugun }} 유원시설 정보</h3>
          <table class="table mt-2">
            <colgroup>
              <col width="30%" />
              <col width="40%" />
              <col width="30%" />
            </colgroup>
            <thead>
              <tr>
                <th>시설명</th>
                <th>전화번호</th>
                <th>주소</th>
              </tr>
            </thead>
          </table>
          <div style="height: 300px; overflow: auto;">
            <table class="table mt-2">
              <colgroup>
                <col width="30%" />
                <col width="40%" />
                <col width="30%" />
              </colgroup>
              <tbody
                id="searchResult"
                v-for="(facility, index) in kidfacilities"
                :key="index"
              >
                <th>
                  {{ facility.BPLCNM }}
                </th>
                <th>
                  {{ facility.SITETEL }}
                </th>
                <th>
                  {{ facility.SITEWHLADDR }}
                </th>
              </tbody>
            </table>
          </div>
        </div>
        <div v-if="isculture">
          <h3 style="padding-bottom: 5px;">{{ gugun }} 문화시설 정보</h3>
          <table class="table mt-2">
            <colgroup>
              <col width="30%" />
              <col width="40%" />
              <col width="30%" />
            </colgroup>
            <thead>
              <tr>
                <th>시설명</th>
                <th>분류</th>
                <th>주소</th>
              </tr>
            </thead>
          </table>
          <div style="height: 300px; overflow: auto;">
            <table class="table mt-2">
              <colgroup>
                <col width="30%" />
                <col width="40%" />
                <col width="30%" />
              </colgroup>
              <tbody
                id="searchResult"
                v-for="(culture, index) in cultures"
                :key="index"
              >
                <th>
                  {{ culture.FAC_NAME }}
                </th>
                <th>
                  {{ culture.CODENAME }}
                </th>
                <th>
                  {{ culture.ADDR }}
                </th>
              </tbody>
            </table>
          </div>
        </div>
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
const CULTURE_KEY = '537a6b4b6f6b6b6738354c68776e42';
const HOSPITAL_KEY = '65546752786b6b6732397574734e4b';
const CARECENTER_KEY = '53524a6d4f6b6b673438584b674453';
const PHARNACY_KEY = '72477847496b6b673835506976647a';
const KIDFACIL_KEY = '6879706d6d6b6b67313233506d436474';
export default {
  data() {
    return {
      sido: '',
      gugun: '',
      dong: '',
      dongcode: '',
      apts: [],
      selectedApt: null,
      kinders: [],
      kinderTF: false,
      elements: [],
      elementTF: false,
      middles: [],
      middleTF: false,
      selectedinstitution: null,
      edus: [],
      kidfacilities: [],
      cultures: [],
      hospitals: [],
      carecenters: [],
      pharmacies: [],
      ispharmacy: true,
      ishospital: false,
      iscarecenter: false,
      iskinder: true,
      isculture: false,
    };
  },

  created() {
    axios
      .get(`${SERVER_URL}/likearea/select/` + localStorage.getItem('userId'))
      .then((response) => {
        this.sido = response.data.data.sido;
        this.gugun = response.data.data.gugun;
        this.dong = response.data.data.dong;
        console.log('setLikest success!');
        this.fillcontents();
      })
      .catch(() => {
        console.log('getting likearea of user fail!');
      });
  },

  methods: {
    fillcontents() {
      // var gname = this.gugun;
      var dname = this.dong;

      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            KINDER_KEY +
            '/json/SchulInfoKndrgr/1/1000/'
        )
        .then((response) => {
          this.kinders = response.data.SchulInfoKndrgr.row.filter(function(
            object
          ) {
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
          this.middles = response.data.SchulInfoMskul.row.filter(function(
            object
          ) {
            return object.ADRES.includes(dname);
          });
        })
        .catch((error) => {
          console.log(error);
        });

      axios
        .get(`${SERVER_URL}/map/dongcode/` + this.dong)
        .then((response) => {
          console.log(response.data);
          this.dongcode = response.data;
          this.getapts();
        })
        .catch(() => {
          console.log('getting likearea of user fail!');
        });

      // 서울시 유원시설 주소 검색 : (https://data.seoul.go.kr/dataList/OA-16029/S/1/datasetView.do)
      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            KIDFACIL_KEY +
            '/json/LOCALDATA_030708/1/500/'
        )
        .then((response) => {
          this.kidfacilities = [];
          var datas = response.data.LOCALDATA_030708.row;
          for (var data of datas) {
            if (data.SITEWHLADDR.includes(this.gugun)) {
              this.kidfacilities.push(data);
            }
          }
          // console.log('kidFacilities : ');
          // console.log(this.kidfacilities);
        })
        .catch((error) => {
          console.log(error);
        });

      // 서울시 문화위치정보 주소 검색 : (http://data.seoul.go.kr/dataList/OA-150/A/1/datasetView.do)
      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            CULTURE_KEY +
            '/json/SearchCulturalFacilitiesAddressService/1/100/' +
            this.gugun +
            '/'
        )
        .then((response) => {
          this.cultures =
            response.data.SearchCulturalFacilitiesAddressService.row;
          // console.log('cultures : ');
          // console.log(this.cultures);
        })
        .catch((error) => {
          console.log(error);
        });

      // 서울특별시 병원 인허가 정보 : (https://data.seoul.go.kr/dataList/OA-16479/S/1/datasetView.do)
      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            HOSPITAL_KEY +
            '/json/LOCALDATA_010101/1/850/'
        )
        .then((response) => {
          this.hospitals = [];
          var datas = response.data.LOCALDATA_010101.row;
          for (var data of datas) {
            if (data.SITEWHLADDR.includes(this.gugun)) {
              this.hospitals.push(data);
            }
          }

          // console.log('hospitals : ');
          // console.log(this.hospitals);
        })
        .catch((error) => {
          console.log(error);
        });

      // 서울특별시 산후조리원 인허가 정보 : (https://data.seoul.go.kr/dataList/OA-16482/S/1/datasetView.do)
      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            CARECENTER_KEY +
            '/json/LOCALDATA_010104/1/250/'
        )
        .then((response) => {
          this.carecenters = [];
          var datas = response.data.LOCALDATA_010104.row;
          for (var data of datas) {
            if (data.SITEWHLADDR.includes(this.gugun)) {
              this.carecenters.push(data);
            }
          }

          // console.log('carecenters : ');
          // console.log(this.carecenters);
        })
        .catch((error) => {
          console.log(error);
        });

      // 서울특별시 세이프약국 정보 : (https://data.seoul.go.kr/dataList/OA-15489/S/1/datasetView.do)
      var rand = Math.floor(Math.random() * 450);
      axios
        .get(
          'http://openapi.seoul.go.kr:8088/' +
            PHARNACY_KEY +
            '/json/pharmListViewInfo/' +
            rand +
            '/' +
            (rand + 50) +
            '/'
        )
        .then((response) => {
          this.pharmacies = response.data.pharmListViewInfo.row;
          console.log('pharmacies : ');
          console.log(this.pharmacies);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getapts() {
      var dcode = this.dongcode;
      console.log('selected dongcode : ' + dcode);
      const params = {
        LAWD_CD: dcode,
        DEAL_YMD: '202010',
        serviceKey: decodeURIComponent(API_KEY),
      };

      axios
        .get(API_URL, {
          params,
        })
        .then((response) => {
          console.log('get apts!!!!!');
          console.log(response);
          this.apts = response.data.response.body.items.item;
          this.selectedApt = null;
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

    pharmacyOn() {
      this.ispharmacy = true;
      this.ishospital = false;
      this.iscarecenter = false;
    },

    hospitalOn() {
      this.ispharmacy = false;
      this.ishospital = true;
      this.iscarecenter = false;
    },

    carecenterOn() {
      this.ispharmacy = false;
      this.ishospital = false;
      this.iscarecenter = true;
    },

    kinderOn() {
      this.iskinder = true;
      this.isculture = false;
    },

    cultureOn() {
      this.iskinder = false;
      this.isculture = true;
    },
  },
};
</script>

<style></style>
