<template>
  <div class="guCharts">
    <div class="modal-content">
      <div class="modal_wrap" :style="{ width: expandWidth }">
        <div class="modal-title">
          <p style="font-size: 24px; font-weight: 600">범죄 현황</p>
          <div style="flex: 1"></div>
          <p id="detail_btn" @click="onClick(guSelected)">
            범죄발생 위험군 보기 >
          </p>
        </div>
        <div class="state-content">
          <!-- 범죄율 -->
          <div class="el">
            <p class="rateTitle">💡 범죄율</p>
            <p class="rateDetail">
              총 {{ totalCrime | comma }} 건 중 {{ guCrime | comma }}건이 발생
            </p>
            <div class="doughnut">
              <VueSvgGauge
                class="mini-gauge"
                :start-angle="0"
                :end-angle="360"
                :min="0"
                :max="100"
                :value="(guCrime / totalCrime) * 100"
                :separator-step="0"
                :scale-interval="0"
                :inner-radius="85"
                :gauge-color="[{ offset: 0, color: '#F57272' }]"
                base-Color="#EEEEEE"
              >
                <div class="inner-text">
                  <span>
                    {{ Math.round((guCrime / totalCrime) * 100, 2) }}%
                  </span>
                </div>
              </VueSvgGauge>
            </div>
          </div>
          <!-- 검거율 -->
          <div class="el">
            <p class="rateTitle">💡 검거율</p>
            <p class="rateDetail">
              총 {{ guCrime | comma }} 건 중 {{ guArrest | comma }}건이 검거
            </p>
            <div class="doughnut">
              <VueSvgGauge
                class="mini-gauge"
                :start-angle="0"
                :end-angle="360"
                :min="0"
                :max="100"
                :value="(guArrest / guCrime) * 100"
                :separator-step="0"
                :scale-interval="0"
                :inner-radius="85"
                :gauge-color="[{ offset: 0, color: '#6A7DAF' }]"
                base-Color="#EEEEEE"
              >
                <!-- 76줄은 단색  80줄은 그라데이션-->
                <!-- :gauge-color="[{ offset: 0, color: '#de3a21'}, { offset: 100, color: '#f4c009'}]" -->
                <div class="inner-text">
                  <span>
                    {{ Math.round((guArrest / guCrime) * 100, 2) }}%
                  </span>
                </div>
              </VueSvgGauge>
            </div>
          </div>
          <!-- 범죄 유형 -->
          <div class="el">
            <p class="rateTitle">💡 범죄 유형</p>
            <vue-highcharts
              :options="chartTypeOfCrime"
              :highcharts="Highcharts"
              ref="chartTypeOfCrime"
              style="height: 400px"
            ></vue-highcharts>
          </div>
          <!-- 범죄유형 리스트 -->
          <div class="el" style="display: flex; align-items: center">
            <div style="width: 100%">
              <div
                class="crime-item"
                v-for="(crime, idx) in crimeTypeList"
                :key="crime.type"
                @click="onCrimeDetail(crime.type, crime.typeIdx)"
              >
                <div class="crime-detail" :id="'detail' + crime.typeIdx">{{ idx + 1 }}</div>
                <div class="crime-content" :id="'content' + crime.typeIdx">
                  <p style="flex: 1; text-align: center">{{ crime.type }}</p>
                  <p>|</p>
                  <p style="flex: 1; text-align: center">{{ crime.num | comma }}건</p>
                </div>
              </div>
            </div>
          </div>
          <!-- 유형에 따른 요일, 시간, 장소 -->
          <div class="el">
            <p class="rateTitle">💡 <span style="color: #F57272; font-weight: 700">{{ type5Name }}</span> 이/가 높은 요일</p>
            <highcharts :options="highestDay" style="height: 300px"></highcharts>
          </div>
          <div class="el">
            <p class="rateTitle">💡 <span style="color: #F57272; font-weight: 700">{{ type5Name }}</span> 이/가 높은 시간</p>
            <highcharts :options="highestTime" style="height: 300px"></highcharts>
          </div>
          <div class="el">
            <p class="rateTitle">💡 <span style="color: #F57272; font-weight: 700">{{ type5Name }}</span> 이/가 높은 발생 장소</p>
            <!-- <highcharts :options="highestSpot" style="height: 300px"></highcharts> -->
            <vue-highcharts
              :options="highestSpot"
              :highcharts="Highcharts"
              ref="highestSpot"
            ></vue-highcharts>
          </div>
        </div>
      </div>
      <div class="arrow-area" @click="extend">
        <img
          class="arrow"
          :src="require(`@/assets/${arrowImg}.png`)"
          :width="20"
        />
      </div>
      <!-- 오른쪽 범죄예측 모달창 -->
      <div style="flex: 1"></div>
      <div class="predict-modal" v-show="isPredict">
        <div class="predict-title">
          <p> 범죄 유형별 위험군</p>
          <div style="flex: 1"></div>
          <img src="@/assets/ic-close.png" width="20" @click="offClick" class="close-btn"/>
        </div>
        <!-- 예측테이블 -->
        <div>
          <table style="padding-top: 10px">
            <tr>
              <th style="width: 60px">유형</th>
              <th style="width: 60px">요일</th>
              <th style="width: 100px">장소</th>
              <th style="width: 100px">시간</th>
              <th style="width: 100px">범죄발생 지수</th>
            </tr>
            <tr v-for="pItem in predictList" :key="pItem.type">
              <td style="background-color: #B8B8B8"><span v-html="pItem.type"></span></td>
              <td><span v-html="pItem.day"></span></td>
              <td><span v-html="pItem.spot"></span></td>
              <td><span v-html="pItem.time"></span></td>
              <td><span v-html="pItem.idx"></span></td>
            </tr>
          </table>
          <div class="explain">
          ※ 범죄발생 지수 : 조건별(지역, 장소, 요일, 시간) 위험지수에 기반한 수치
          <br />
          <span class ="maxminavg" style="color:#A4B5E2;">최댓값: 2.8762, 최솟값: 0.7163, 평균값 : 1.464</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Chart } from "highcharts-vue";
import VueHighcharts from "vue2-highcharts";
import Highcharts from "highcharts";
import Variablepie from "highcharts/modules/variable-pie";
import Highcharts3D from "highcharts/highcharts-3d";
import { VueSvgGauge } from "vue-svg-gauge";
import $axios from "axios";
const chartColor = ["#6A7DAF", "#8F9FC8", "#AFBBD9", "#CED6EA", "#E9EDF5"];

Variablepie(Highcharts);
Highcharts3D(Highcharts);

export default {
  name: "GuCrimeModal",
  components: {
    highcharts: Chart,
    VueHighcharts,
    VueSvgGauge,
  },
  props: {
    gu: Number,
    year: String,
  },
  data() {
    return {
      Highcharts,
      isPredict: false,
      isExpand: false,
      expandWidth: "450px",
      arrowImg: "ic-arrow-right",

      guSelected: "",
      totalCrime: "",
      guCrime: "",
      totalArrest: "",
      guArrest: "",
      type5Name: "",
      type5Idx: "",
      spotList:[],

      chartTypeOfCrime: {
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: "variablepie",
        },
        title: {
          text: "",
        },
        credits: {
          enabled: false,
        },
        series: [{
            minPointSize: 10,
            innerSize: "20%",
            zMin: 0,
            name: "범죄유형",
            data: [],
          },
        ],
      },
      highestDay: {
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: "areaspline",
        },
        title: {
          text: "",
        },
        colors: ["#6A7DAF"],
        xAxis: {
          categories: ["월", "화", "수", "목", "금", "토", "일"],
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
        tooltip: {
          shared: true,
          valueSuffix: " 건",
        },
        credits: {
          enabled: false,
        },
        legend: {
          enabled: false,
        },
        plotOptions: {
          areaspline: {
            // fillColor:'#A4B5E2',
            fillOpacity: 0.5,
          },
        },
        series: [
          {
            name: '발생건수',
            data: [],
          },
        ],
      },
      highestTime:{
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: 'areaspline',
        },
        title: {
          text: "",
        },
        colors: ["#6A7DAF"],
        xAxis: {
          categories: [
            '00:00~02:59',
            '03:00~05:59',
            '06:00~08:59',
            '09:00~11:59',
            '12:00~14:59',
            '15:00~17:59',
            '18:00~20:59',
            '21:00~23:59',
          ],
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
        tooltip: {
          shared: true,
          valueSuffix: ' 건'
        },
        credits: {
            enabled: false,
        },
        legend: {
          enabled: false,
        },
        plotOptions: {
          areaspline: {
            fillOpacity: 0.5,
          },
        },
        series: [
          {
            name: '발생건수',
            data: [],
          },
        ],
      },
      highestSpot: {
        chart: {
          renderTo: 'highestSpot',
          backgroundColor: "rgba(0,0,0,0)",
          type: 'column',
          options3d: {
            enabled: true,
            alpha: 15,
            beta: 15,
            depth: 50,
            viewDistance: 25,
          },
        },
        title: {
          text: "",
        },
        plotOptions: {
          column: {
            depth: 25,
          },
        },
        legend: {
          enabled: false,
        },
        credits: {
          enabled: false,
        },
        colors: ["#6A7DAF"],
        xAxis: {
          categories: [],
          gridLineColor: "rgba(0,0,0,0)",
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
          gridLineColor: "rgb(255, 255, 255)",
          labels: {
            style: {
              color: "#ffffff",
            },
          },
        },
        tooltip: {
          shared: true,
          valueSuffix: ' 건',
        },
        series: [
          {
            name: '발생건수',
            data: [],
          }
        ]
      },

      crimeTypeList: [],
      predictList: [],
    };
  },
  watch: {
    gu: function (val) {
      this.guSelected = this.gu;

      if(this.year == null) {
      this.$emit("initYear");
      }

      this.isPredict = false;

      if (val != "전체") {
        //범죄율, 검거율
        this.getCrimeArrestCount();

        //범죄 유형
        this.getCrimeType();

        //예측분석
        this.getCrimePrediction();
      }
    },
    year: function () {
      this.isPredict = false;
      //범죄율, 검거율
      this.getCrimeArrestCount();

      //범죄 유형
      this.getCrimeType();

      //예측분석
      this.getCrimePrediction();
    },
  },
  mounted() {
    this.gu = 1;
    this.year = "2020";
  },
  methods: {
    onClick(selected) {
      this.isPredict = true;
      //예측분석
      $axios
        .get("/crime/forecast/" + selected, {
          params: {
            sigungu: selected,
          },
        })
        .then((response) =>{
          this.predictList = response.data.list.map((item) => {
            return {
              type: item.crimeType,
              day: item.day,
              spot: item.spot,
              time: item.time,
              idx: item.prtds,
            };
          });
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    offClick() {
      this.isPredict = false;
    },
    extend() {
      if (this.isExpand) {
        this.arrowImg = "ic-arrow-right";
        this.expandWidth = "450px";
      } else {
        this.arrowImg = "ic-arrow-left";
        this.expandWidth = "900px";
      }
      this.isExpand = !this.isExpand;
    },
    onCrimeDetail(name, idx) {
      this.type5Name = name;
      this.type5Idx = idx;
      //색 바꾸기
      document.getElementsByClassName("crime-detail").forEach((item) => {
        item.style.backgroundColor = "#B8B8B8";
      });
      document.getElementsByClassName("crime-content").forEach((item) => {
        item.style.backgroundColor = "#B8B8B8";
      });
      document.getElementById("detail" + idx).style.backgroundColor = "#F57272";
      document.getElementById("content" + idx).style.backgroundColor = "#F57272";

      //요일
      this.getCrimeDay();
      //시간
      this.getCrimeTime();
      //장소
      this.getCrimeSpot();
    },
    getCrimeArrestCount() {
      //범죄율, 검거율
      $axios
        .get("/crime/rate", {
          params: {
            gu: this.gu,
            year: this.year,
          },
        })
        .then((response) => {
          this.totalCrime = response.data.getRateDto.totalCrimeCount;
          this.guCrime = response.data.getRateDto.guCrimeCount;
          this.totalArrest = response.data.getRateDto.totalArrestCount;
          this.guArrest = response.data.getRateDto.guArrestCount;
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    getCrimeType() {
      let chartTypeOfCrime = this.$refs.chartTypeOfCrime;
      chartTypeOfCrime.removeSeries();
      //범죄유형
      $axios
        .get("/crime", {
          params: {
            gu: this.gu,
            year: this.year,
          },
        })
        .then((response) => {
          let len = response.data.list.length * 200;

          const crimeType = response.data.list.map((item, idx) => {
            return {
              name: item.crimeType,
              y: item.count,
              z: (len -= 200),
              color: chartColor[idx % 5],
            };
          });
          chartTypeOfCrime.addSeries({
            minPointSize: 10,
            innerSize: "30%",
            zMin: 0,
            name: "범죄유형",
            data: crimeType,
          });

          this.crimeTypeList = response.data.list.map((item) => {
            return {
              type: item.crimeType,
              num: item.count,
              typeIdx: item.crimeTypeId,
            };
          });

          //1위 유형
          this.type5Name = response.data.list[0].crimeType;
          this.type5Idx = response.data.list[0].crimeTypeId;

          this.onCrimeDetail(this.type5Name, this.type5Idx);
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    getCrimeDay() {
      $axios
        .get("/crime/day", {
          params: {
            type: this.type5Idx,
            year: this.year,
          },
        })
        .then((response) => {
          this.highestDay.series[0].data = response.data.list.map((item) => {
            return {
              y: item.count,
            };
          });
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    getCrimeTime() {
      $axios
        .get("/crime/time", {
          params: {
            type: this.type5Idx,
            year: this.year,
          },
        })
        .then((response) => {
          this.highestTime.series[0].data = response.data.list.map((item) => {
            return {
              y: item.count,
            };
          });
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    getCrimeSpot() {
      const highestSpot = this.$refs.highestSpot;
      highestSpot.removeSeries();
      //this.highestSpot.xAxis.categories=[]
      $axios
        .get("/crime/spot", {
          params: {
            type: this.type5Idx,
            year: this.year,
          },
        })
        .then((response) => {
          console.log(response.data.list)

          this.highestSpot.xAxis.categories.splice(0)
          
          const hotSpot = response.data.list
          for (var i=0; i<7; i++) {
            this.highestSpot.xAxis.categories.push(hotSpot[i].spot)
          }
          console.log(this.highestSpot.xAxis.categories)

          const data = response.data.list.map((item) => {
            return {
              name: item.spot,
              y: item.count,
            };
          });
          highestSpot.addSeries({
            name: "발생 장소",
            data: data,
          });
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    getCrimePrediction() {
      $axios
        .get("/crime/forecast/" + this.gu, {
          params: {
            sigungu: this.gu,
          },
        })
        .then((response) => {
          this.predictList = response.data.list.map((item) => {
            return{
              type: item.crimeType,
              day: item.day,
              spot: item.spot,
              time: item.time,
              idx: item.prtds,
            };
          });
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
  },
  filters: {
    comma(val) {
      return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>
<style scoped>
.modal-content {
  display: flex;
  margin-top: 24px;
}
.modal-title {
  display: flex;
  height: 60px;
  align-items: center;
  padding: 0 18px;
  border-bottom: 1px solid #686868;
}
#detail_btn {
  font-size: 16px;
  font-weight: 600;
}
#detail_btn:hover {
  cursor: pointer;
}
.rateTitle {
  font-size: 20px;
  margin-top: 16px;
  margin-bottom: 8px;
}
.rateDetail {
  font-size: 16px;
  margin-left: 28px;
  margin-bottom: 8px;
}
.modal_wrap {
  z-index: 888;
  width: 450px;
  height: 800px;
  background: #454d5e;
  border-radius: 12px;
  box-shadow: 0px 0px 16px 3px rgba(26, 31, 41, 0.45);
}
.state-content {
  overflow: auto;
  height: calc(100% - 60px);
  padding: 0 18px;
  display: flex;
  flex-wrap: wrap;
}
.explain{
  margin-top : 10px;
}
.el {
  width: 400px;
}
.state-content::-webkit-scrollbar {
  width: 10px;
}
.state-content::-webkit-scrollbar-thumb {
  background-color: darkgray;
  border-radius: 24px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.state-content::-webkit-scrollbar-track {
  background-color: #454d5e;
  border-radius: 24px;
}
.doughnut {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 24px;
}
.mini-gauge {
  max-width: 180px;
}
.inner-text {
  display: flex;
  margin-top: 80px;
  align-items: center;
  justify-content: center;
  font-size: 35px;
  font-weight: bold;
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
  z-index: 999;
  box-shadow: 12px 0px 20px -7px rgba(26, 31, 41, 0.45);
}
.arrow:hover {
  cursor: pointer;
}
.close-btn:hover {
  cursor: pointer;
}
.predict-modal {
  z-index: 888;
  width: 520px;
  height: 320px;
  background: #454d5e;
  border-radius: 5px;
  box-shadow: 0px 0px 16px 3px rgba(26, 31, 41, 0.45);
  padding: 24px;
}
.predict-title {
  display: flex;
  font-size: 18px;
  font-weight: 600;
}
.crime-item {
  display: flex;
  margin-bottom: 8px;
}
.crime-item:hover {
  cursor: pointer;
}
.crime-detail {
  background-color: #B8B8B8;
  padding: 8px 12px;
  border-radius: 4px;
}
.crime-content {
  background-color: #B8B8B8;
  padding: 8px 5px;
  border-radius: 4px;
  margin-left: 8px;
  flex: 1;
  display: flex;
}
th {
  padding: 10px;
  background-color: #999999;
  border-radius: 5px;
}
td {
  padding: 10px;
  background-color: #FF9D9D;
  border-radius: 5px;
  text-align: center;
}
</style>
