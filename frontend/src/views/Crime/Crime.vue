<template>
  <div class="crime">
    <div class="main">
      <img :src="require(`@/assets/${selectGu}.png`)" class="map" />
      <div class="main-content">
        <div style="width: 1450px; margin: 0 auto;">
          <!-- 검색 -->
          <div>
            검색 :
            <select
              id="selectGu"
              @change="changeGu(), showMain()"
              v-model="selectGu"
            >
              <option selected value="전체">전체</option>
              <option v-for="(gu, idx) in guList" :key="idx" :value="gu">
                {{ gu }}
              </option>
            </select>
          </div>
          <!-- 전체 범죄 현황 차트 및 내용 -->
          <div class="charts" v-show="isMain" style="display: flex">
            <div class="left-content">
              <p class="mid">최근 3년간 서울시 범죄 발생
                <br />
                <span class="white highlight">296,177건</span>
              </p>
              <p class="mid" align="center"><b>검거율</b>이 높은 지역 (건수)</p>
              <highcharts :options="chartArrest"></highcharts>
              <p class="mid" align="center"><b>범죄율</b>이 높은 지역 (건수)</p>
              <highcharts :options="chartCrime"></highcharts>
            </div>
            <div style="flex: 1"></div>
            <div class="right-content" align="center">
              <p class="mid">최근 3년간 발생 건수</p>
              <highcharts :options="chartLatest"></highcharts>
              <p class="mid">서울시 5대 범죄</p>
              <p class="type5" style='background-color:#F57373'>폭력</p><br />
              <p class="type5" style='background-color:#FA9385'>절도</p><br />
              <p class="type5" style='background-color:#FAAF85'>강간</p><br />
              <p class="type5" style='background-color:#FACB85'>강도</p><br />
              <p class="type5" style='background-color:#FAD985'>살인</p>
            </div>
          </div>
        </div>
      </div>
      <div class="guCharts" v-show="isGu">
        <!-- <modal v-if="showModal" @close="showModal = false">
        <h3 slot="header">custom header</h3>
        </modal> -->
        <!-- <div class="black_bg"></div> -->
        <div>
          <div class="modal_wrap" style="overflow:auto; width:400px; height:700px;"> 
              <!-- <div class="modal_close" @click="offClick"><a href="#">close</a></div> -->
              <div>
                <br/>
                  <span class = "mid">범죄 현황</span>
                  <button type='button' id="detail_btn" @click="onClick"> > 예측분석</button>
                <br/><br/>
                  <span class = "rateTitle">💡 범죄율 </span>
              </div>
              <highcharts :options="chartArrest"></highcharts>
              <highcharts :options="chartArrest"></highcharts>
          </div>
          <div>화살표를만들라고</div>
          <div class="modal_wrap_detail">
              <div class="modal_close" @click="offClick"><a href="#">close</a></div>
              <div>
                <br/>
                  <span class = "mid"> 강남구 범죄예측분석333</span>
              </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 구 선택시 화면 -->
    <div></div>
  </div>
</template>
<script>
import {Chart} from "highcharts-vue";
export default {
  name: "Crime",
  components: {
    highcharts: Chart,
  },
  data() {
    return {
      guList: [
        "강남구",
        "강동구",
        "강북구",
        "강서구",
        "관악구",
        "광진구",
        "구로구",
        "금천구",
        "노원구",
        "도봉구",
        "동대문구",
        "동작구",
        "마포구",
        "서대문구",
        "서초구",
        "성동구",
        "성북구",
        "송파구",
        "양천구",
        "영등포구",
        "용산구",
        "은평구",
        "종로구",
        "중구",
        "중랑구",
      ],
      selectGu: "전체",
      isMain: true,
      isGu: false,
      isChecked: "",
      chartArrest: {
        title: {
          text: "",
        },
        credits: {
          enabled: false,
        },
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: "bar",
        },
        xAxis: {
          categories: ["강남구", "영등포구", "동작구", "광진구", "송파구"],
          labels: {
            style: {
              fontsize: "14px",
              color: "#ffffff",
            },
          },
        },
        yAxis: {
          title: {
            text: "건수",
            style: {
              color: "#ffffff",
            },
          },
          gridLineColor: "rgba(0,0,0,0)",
          labels: {
            style: {
              color: "#ffffff",
            },
          },
        },
        plotOptions: {
          bar: {
              dataLabels: {
                  enabled: true,
              }
          }
        },
        legend: {
          enabled: false,
        },
        series: [
          {
            data: [
              {
                y: 321,
                color: "#aaaaaa",
              },
              {
                y: 221,
                color: "#bbbbbb",
              },
              {
                y: 123,
                color: "#cccccc",
              },
              {
                y: 333,
                color: "#dddddd",
              },
              {
                y: 444,
                color: "#eeeeee",
              },
            ],
          },
        ],
      },
      chartCrime: {
        title: {
          text: "",
        },
        credits: {
          enabled: false,
        },
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: "bar",
        },
        xAxis: {
          categories: ["강남구", "영등포구", "동작구", "광진구","송파구"],
          labels: {
            style: {
              color: "#ffffff",
            },
          },
        },
        yAxis: {
          title: {
            text: "건수",
            style: {
              color: "#ffffff",
            },
          },
          gridLineColor: "rgba(0,0,0,0)",
          labels: {
            style: {
              color: "#ffffff",
            },
          },
        },
        plotOptions: {
          bar: {
              dataLabels: {
                  enabled: true,
              }
          }
        },
        legend: {
          enabled: false,
        },
        series: [
          {
            data: [
              {
                y: 321,
                color: "#aaaaaa",
              },
              {
                y: 221,
                color: "#bbbbbb",
              },
              {
                y: 123,
                color: "#cccccc",
              },
              {
                y: 333,
                color: "#dddddd",
              },
              {
                y: 444,
                color: "#eeeeee",
              },
            ],
          },
        ],
      },
      chartLatest: {
        title: {
          text: "",
        },
        credits: {
          enabled: false,
        },
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: "column",
        },
        xAxis: {
          categories: ["2018년", "2019년", "2020년"],
          labels: {
            style: {
              color: "#ffffff",
            },
          },
        },
        yAxis: {
          min:0,
          gridLineColor: "rgba(0,0,0,0)",
          labels: {
            style: {
              color: "#ffffff",
            },
          },
        },
        plotOptions: {
          column: {
              dataLabels: {
                  enabled: true,
              }
          }
        },
        legend: {
          enabled: false,
        },
        series: [
          {
            data: [
              {
                y: 321,
                color: "#6A7DAF",
              },
              {
                y: 221,
                color: "#6A7DAF",
              },
              {
                y: 123,
                color: "#6A7DAF",
              },
            ],
          },
        ],
      },

    };
  },
  methods: {
    showMain() {
      const guSelect = this.selectGu;
      if (guSelect === "전체") {
        this.isMain = true;
        this.isGu = false;
      } else {
        this.isMain = false;
      }
    },
    changeGu() {
      const guSelect = this.selectGu;
      this.isGu = true;
      this.isMain = false;
      this.isChecked = guSelect;
      console.log(this.isChecked);
      document.querySelector('.modal_wrap').style.display ='block';
    },
    onClick() {
      document.querySelector('.modal_wrap_detail').style.display ='block';
    },
    offClick() {
      document.querySelector('.modal_wrap_detail').style.display ='none';
    },
  }
};
</script>
<style scoped>
.crime {
  width: 100%;
  height: 1200px;
}
.main {
  min-width: 1450px;
  position: relative;
}
.map {
  margin: 52px 0;
  width: 100%;
}
.main-content {
  position: absolute;
  top: 52px;
  left: 0;
  z-index: 888;
  width: 100%;
  height: 100%;
  margin: 0 auto;
}
.mid {
  font-size: 25px;
  padding: 20px 20px;
}
.highlight {
  font-size: 45px;
  font-weight: 600;
  padding: 20px 0px 0px 0px;
}
.rateTitle {
  font-size: 20px;
  padding: 10px 0px 0px 20px;
}
#selectGu {
  padding: 10px 6px 10px 6px;
  background-color: #454D5E;
  border-radius: 5px;
  border: 1px solid white;
  width: 150px;
  font-size: 16px;
}
.seoulCrime {
  position: absolute;
  padding: 30px 6px 50px 6px;
  bottom: 20px;
  right: 10px;
  z-index: 888;
  width: 300px;
}
.type5 {
  display: inline-block;
  border-radius: 16px;
  padding: 12px 70px;
  font-size: 18px;
  margin-top: 20px;
}
.modal_wrap {
  display: none;
  position: absolute;
  top: 110px;
  left: 35px;
  z-index: 888;
  width: 400px;
  height: 700px;
  background:#454D5E;
  border-radius: 5px;
  box-shadow: 0px 0px 20px #000;
}
#detail_btn {
  margin-left: 120px;
  padding: 10px 6px 10px 6px;
  background-color: #454D5E;
  border-radius: 5px;
  border: 1px solid white;
  width: 130px;
  font-size: 16px;
}
.modal_wrap_detail {
  display: none;
  position: absolute;
  top: 110px;
  right: 35px;
  z-index: 888;
  width: 400px;
  height: 300px;
  background: #454D5E;
  border-radius: 5px;
  box-shadow: 0px 0px 20px #000;
}
.modal_wrap::-webkit-scrollbar {
  width: 10px;
}
.modal_wrap::-webkit-scrollbar-thumb {
  background-color: darkgray;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.modal_wrap::-webkit-scrollbar-track {
  background-color: #454D5E;
  border-radius: 10px;
  /* box-shadow: inset 0px 0px 5px white; */
}
.black_bg {
  display: none;
  position: absolute;
  content: "";
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  top:0;
  left: 0;
  z-index: 1;
}
.modal_close {
  width: 26px;
  height: 26px;
  position: absolute;
  top: 10px;
  right: 10px;
}
.modal_close > a {
  display: block;
  width: 100%;
  height: 100%;
  /* background-image: url("../../assets/ic-close.png"); */
  background: url(https://img.icons8.com/metro/26/000000/close-window.png);
  text-indent: -9999px;
}
</style>
