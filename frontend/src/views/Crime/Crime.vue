<template>
  <div class="crime">
    <div class="main">
      <img :src="require(`@/assets/${selectGu}.png`)" class="map" />
      <div class="main-content">
        <div style="width: 1450px; margin: 0 auto">
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
              <p class="chart-subtitle">최근 3년간 서울시 범죄 발생</p>
              <p class="white highlight">296,177건</p>
              <p class="chart-subtitle"><b>검거율</b>이 높은 지역 (건수)</p>
              <highcharts :options="chartArrest"></highcharts>
              <p class="chart-subtitle"><b>범죄율</b>이 높은 지역 (건수)</p>
              <highcharts :options="chartCrime"></highcharts>
            </div>
            <div style="flex: 1"></div>
            <div class="right-content">
              <div>
                <p class="chart-subtitle">최근 3년간 발생 건수</p>
                <highcharts :options="chartLatest"></highcharts>
              </div>
              <div align="center">
                <p class="chart-subtitle">서울시 5대 범죄</p>
                <p class="type5" style="background-color: #f57373">폭력</p><br />
                <p class="type5" style="background-color: #fa9385">절도</p><br />
                <p class="type5" style="background-color: #faaf85">강간</p><br />
                <p class="type5" style="background-color: #facb85">강도</p><br />
                <p class="type5" style="background-color: #fad985">살인</p>
              </div>
            </div>
          </div>
          <!-- 구 선택시 모달 창-->
          <div class="guCharts" v-show="isGu">
            <!-- <modal v-if="showModal" @close="showModal = false">
            <h3 slot="header">custom header</h3>
            </modal> -->
            <!-- <div class="black_bg"></div> -->
            <div class="modal-content">
              <div class="modal_wrap">
                <!-- <div class="modal_close" @click="offClick"><a href="#">close</a></div> -->
                <div class="modal-title">
                  <p style="font-size: 24px; font-weight: 600">범죄 현황</p>
                  <div style="flex: 1"></div>
                  <p id="detail_btn" @click="onClick">예측 분석 보기 ></p>
                </div>
                <!-- 범죄율 -->
                <div>
                  <p class="rateTitle">💡 범죄율</p>
                  <vc-donut :sections="sections" :size="100"></vc-donut>
                </div>
                <!-- 검거율 -->
                <highcharts :options="chartArrest"></highcharts>
                <!-- 범죄 유형 -->
              </div>
              <div class="arrow-area">
                <img
                  class="arrow"
                  src="@/assets/ic-arrow-right.png"
                  :width="20"
                />
              </div>
              <div style="flex: 1"></div>
              <div class="predict-modal" v-show="isPredict">
                <div class="predict-title">
                  <p><span>자치구</span>범죄 예측 분석</p>
                  <div style="flex: 1"></div>
                  <img
                    src="@/assets/ic-close.png"
                    :width="20"
                    @click="offClick"
                  />
                </div>
                <div>테이블</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import VcDonut from "../../../node_modules/vue-css-donut-chart/src/components/Donut.vue";
import { Chart } from "highcharts-vue";
export default {
  name: "Crime",
  components: {
    highcharts: Chart,
    VcDonut,
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
      isPredict: false,
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
            },
          },
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
          categories: ["강남구", "영등포구", "동작구", "광진구", "송파구"],
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
            },
          },
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
          min: 0,
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
            },
          },
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
      sections: [{ label: "label", value: 25 }],
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
    },
    onClick() {
      this.isPredict = true;
    },
    offClick() {
      this.isPredict = false;
    },
  },
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
  padding: 52px 0;
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
.right-content {
  width: 350px;
}
.chart-subtitle {
  font-size: 25px;
  margin-top: 28px;
  margin-bottom: 8px;
}
.highlight {
  font-size: 45px;
  font-weight: 600;
}
.rateTitle {
  font-size: 20px;
}
#selectGu {
  padding: 10px 6px 10px 6px;
  background-color: #454d5e;
  border-radius: 5px;
  border: 1px solid white;
  width: 150px;
  font-size: 16px;
}
.type5 {
  display: inline-block;
  border-radius: 16px;
  padding: 12px 70px;
  font-size: 18px;
  margin-top: 20px;
}
.modal-content {
  display: flex;
  margin-top: 24px;
}
.modal_wrap {
  z-index: 888;
  width: 400px;
  height: 100%;
  background: #454d5e;
  border-radius: 5px;
  box-shadow: 0px 0px 20px #000;
  overflow: auto;
  padding: 24px;
}
.arrow-area {
  display: flex;
  justify-content: center;
  align-items: center;
}
.arrow {
  padding: 24px 12px;
  background: #454d5e;
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
}
.modal-title {
  display: flex;
  margin-bottom: 16px;
}
#detail_btn {
  font-size: 16px;
}
.predict-modal {
  z-index: 888;
  width: 400px;
  height: 300px;
  background: #454d5e;
  border-radius: 5px;
  box-shadow: 0px 0px 20px #000;
  padding: 24px;
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
  background-color: #454d5e;
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
  top: 0;
  left: 0;
  z-index: 1;
}
.predict-title {
  display: flex;
  font-size: 18px;
}
</style>
