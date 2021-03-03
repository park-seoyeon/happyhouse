<template>
  <div>
    <div>
      <section id="banner">
        <h2 style="font-size: 70px;">Happy House</h2>
        <p>행복한 우리집 찾기</p>
      </section>
      <section id="main" class="container">
        <section class="box special">
          <header class="major">
            <h2>
              서울 날씨
            </h2>
            <div>
              <br />
              <br />
              <i :class="weatherIcon" style="font-size:50px"></i><br />
              <p style="font-size:22px">날씨 : {{ weather }}</p>
              <br />
              <p style="font-size:22px">기온 : {{ temper }}℃</p>
              <br />
              <p style="font-size:22px">최저기온 : {{ tempermin }}℃</p>
              <br />
              <p style="font-size:22px">최고기온 : {{ tempermax }}℃</p>
              <br />
            </div>
          </header>
          <span class="image featured"
            ><img src="@/assets/apart01.jpg" alt="" style="height: 400px;"
          /></span>
        </section>

        <section class="box special features">
          <div class="features-row">
            <section v-if="articles[0]">
              <span class="icon solid major fa-newspaper accent2"></span>
              <h5>
                <a :href="articles[0].url" target="_blank">{{ articles[0].title }}</a>
              </h5>
              <br />
              <p>
                {{ articles[0].summary }}
              </p>
            </section>
            <section v-if="articles[1]">
              <span class="icon solid major fa-file-alt accent3"></span>
              <h5>
                <a :href="articles[1].url" target="_blank">{{ articles[1].title }}</a>
              </h5>
              <br />
              <p>
                {{ articles[1].summary }}
              </p>
            </section>
          </div>
          <div class="features-row">
            <section v-if="articles[2]">
              <span class="icon solid major fa-external-link-square-alt accent4"></span>
              <h5>
                <a :href="articles[2].url" target="_blank">{{ articles[2].title }}</a>
              </h5>
              <br />
              <p>
                {{ articles[2].summary }}
              </p>
            </section>
            <section v-if="articles[3]">
              <span class="icon solid major fa-kiwi-bird accent5"></span>
              <h5>
                <a :href="articles[3].url" target="_blank">{{ articles[3].title }}</a>
              </h5>
              <br />
              <p>
                {{ articles[3].summary }}
              </p>
            </section>
          </div>
        </section>

        <div class="text-center">
          <h3>학원 정보</h3>
          <br />
          <nav id="nav" class="navbar navbar-expand-sm bg-dark justify-content-center navbar-dark">
            <div class="text-center">
              <div class="dropdown">
                <div class="btn-group">
                  <select id="sido" class="custom-select" @change="gugun">
                    <option value="0">시/도</option>
                    <option v-for="(sido, index) in sidos" :key="index" :value="sido.sidoCode">
                      {{ sido.sidoName }}
                    </option>
                  </select>
                </div>
                <div class="btn-group">
                  <select id="gugun" class="custom-select" @change="dong">
                    <option value="0">구/군</option>
                    <option v-for="(gugun, index) in guguns" :key="index" :value="gugun.gugunCode">
                      {{ gugun.gugunName }}
                    </option>
                  </select>
                </div>
              </div>
            </div>
          </nav>
          <table class="table mt-2">
            <thead>
              <tr>
                <th>우편번호</th>
                <th>학원 이름</th>
                <th>주소</th>
                <th>등록년도</th>
                <th>전화번호</th>
              </tr>
            </thead>
            <tbody id="searchResult" v-for="(edu, index) in edus" :key="index">
              <th>
                {{ edu.POST }}
              </th>
              <th>
                {{ edu.NM }}
              </th>
              <th>
                {{ edu.ADDR }}
              </th>
              <th>
                {{ edu.YEAR }}
              </th>
              <th>
                {{ edu.TEL }}
              </th>
            </tbody>
          </table>
        </div>
      </section>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import cheerio from 'cheerio';
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
const EDU_KEY = '6a6b584b416b6b67313237514e71494c';
export default {
  data() {
    return {
      view: true,
      city: '',
      weatherIcon: '',
      weather: '',
      temper: 0,
      tempermin: 0,
      tempermax: 0,
      weatherIcons: [
        'fas fa-sun',
        'fas fa-cloud',
        'fas fa-cloud-rain',
        'fas fa-snowflake',
        'fas fa-cloud-sun',
      ],
      weatherList: ['Clear', 'Clouds', 'Rain', 'Snow'],
      articles: [],
      articleindex: 0,
      sidos: [],
      guguns: [],
      dongs: [],
      edus: [],
    };
  },
  computed: {
    hasResult: function() {
      return this.posts.length > 0;
    },
  },
  created() {
    const BASE_URL =
      'https://cors-anywhere.herokuapp.com/http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=5dc753fbb35d7e99e7fd80b06a9a18a7';
    axios.get(`${BASE_URL}`).then((result) => {
      this.city = result.data.name;
      this.weather = result.data.weather[0].main;
      this.temper = (result.data.main.temp - 273.15).toFixed(1);
      this.tempermin = (result.data.main.temp_min - 273.15).toFixed(1);
      this.tempermax = (result.data.main.temp_max - 273.15).toFixed(1);
      // this.view = true;
      var weatheridx = 4;
      for (var i = 0; i < 4; i++) {
        if (this.weather == this.weatherList[i]) {
          weatheridx = i;
          break;
        }
      }
      this.weatherIcon = this.weatherIcons[weatheridx];
    });

    const getHtml = async () => {
      try {
        return await axios.get('https://land.naver.com/news/breaking.nhn');
      } catch (error) {
        console.error(error);
      }
    };

    getHtml().then((html) => {
      let ulList = [];
      const $ = cheerio.load(html.data);
      const $bodyList = $('div.section_headline ul').children('li');
      $bodyList.each(function(i, elem) {
        elem;
        ulList[i] = {
          title: $(this)
            .find('dl dt a')
            .text(),
          url:
            'https://land.naver.com/' +
            $(this)
              .find('dl dt a')
              .attr('href'),
          image_url: $(this)
            .find('dl dt.photo a img')
            .attr('src'),
          image_alt: $(this)
            .find('dl dt.photo a img')
            .attr('alt'),
          summary: $(this)
            .find('dl dd')
            .text()
            .slice(0, -18),
          date: $(this)
            .find('di dd span.writing')
            .text(),
        };
      });
      this.articles = ulList;
    });
    this.articleindex = 0;

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
    // isarticle: function(index) {
    //   return parseInt(index / 5) == this.articleindex;
    // },
    // changepage: function(pagenum) {
    //   this.articleindex = pagenum;
    // },
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
      var gname = gugunelement.options[gugunelement.selectedIndex].text;

      console.log('selected guguncode : ' + guguncode);
      axios
        .get(`${SERVER_URL}/map/dong/` + guguncode)
        .then((response) => {
          this.dongs = response.data.data;
        })
        .catch(() => {
          console.log('dong get fail');
        });
      axios
        .get('http://openapi.seoul.go.kr:8088/' + EDU_KEY + '/json/InstutBuildInfo/1/1000/')
        .then((response) => {
          this.edus = response.data.InstutBuildInfo.row.filter(function(object) {
            const edugu = object.ADDR_OLD.split(' ');
            return edugu[1] == gname;
          });
          console.log('Edus : ' + this.edus);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
