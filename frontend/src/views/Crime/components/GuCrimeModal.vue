<template>
  <div class="guCharts">
    <div class="modal-content">
      <div class="modal_wrap" :style="{ width: expandWidth }">
        <div class="modal-title">
          <p style="font-size: 24px; font-weight: 600">범죄 현황</p>
          <div style="flex: 1"></div>
          <p id="detail_btn" @click="onClick">예측 분석 보기 ></p>
        </div>
        <div class="state-content">
          <!-- 범죄율 -->
          <div class="el">
            <p class="rateTitle">💡 범죄율</p>
            <p class="rateDetail">총 몇 건 중 몇 00건이 발생</p>
            <vc-donut
              :sections="crimeRate"
              :size="150"
              background="#454d5e"
              foreground="#EEEEEE"
              unit="px"
              :thickness="20"
              :total="100"
              :start-angle="0"
              :auto-adjust-text-size="true"
            >
              <h1>25%</h1>
            </vc-donut>
          </div>
          <!-- 검거율 -->
          <div class="el">
            <p class="rateTitle">💡 검거율</p>
            <p class="rateDetail">총 몇 건 중 몇 00건이 발생</p>
            <vc-donut
              :sections="arrestRate"
              :size="150"
              background="#454d5e"
              foreground="#EEEEEE"
              unit="px"
              :thickness="20"
              :total="100"
              :start-angle="0"
              :auto-adjust-text-size="true"
            >
              <h1>25%</h1>
            </vc-donut>
          </div>
          <!-- 범죄 유형 -->
          <div class="el">
            <p class="rateTitle">💡 범죄 유형</p>
            <vue-highcharts
              :options="chartTypeOfCrime"
              :highcharts="Highcharts"
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
                @click="onCrimeDetail(crime.type)"
              >
                <div class="crimeDetail">{{ idx + 1 }}</div>
                <div class="crime-content">
                  <p style="flex: 1; text-align: center">{{ crime.type }}</p>
                  <p>|</p>
                  <p style="flex: 1; text-align: center">{{ crime.num }}건</p>
                </div>
              </div>
            </div>
          </div>
          
          <!-- 유형에 따른 요일, 시간, 장소 -->
          <div class="el">
            <p class="rateTitle">💡 00 이/가 높은 요일</p>
            <highcharts :options="highestDay" style="height: 300px"></highcharts>
          </div>
          <div class="el">
            <p class="rateTitle">💡 00 이/가 높은 시간</p>
            <highcharts :options="highestTime" style="height: 300px"></highcharts>
          </div>
          <div class="el">
            <p class="rateTitle">💡 00 이/가 높은 발생 장소</p>
            <!-- <highcharts :options="highestSpot" style="height: 300px"></highcharts> -->
            <vue-highcharts
              :options="highestSpot"
              :highcharts="Highcharts"
              style="height: 400px"
            ></vue-highcharts>
          </div>
        </div>
      </div>
      <div class="arrow-area" @click="extend">
        <img class="arrow" :src="require(`@/assets/${arrowImg}.png`)" :width="20" />
      </div>
      <!-- 오른쪽 범죄예측 모달창 -->
      <div style="flex: 1"></div>
      <div class="predict-modal" v-show="isPredict">
        <div class="predict-title">
          <p><span style="color:#A4B5E2; font-size:18px; font-weight: 600">{{gu}}</span> 범죄 예측 분석</p>
          <div style="flex: 1"></div>
          <img src="@/assets/ic-close.png" width="20" @click="offClick" />
        </div>
        <!-- 예측테이블 -->
        <div>
          <table style="padding-top: 10px">
            <tr>
              <th style="width: 60px">유형</th>
              <th style="width: 60px">요일</th>
              <th style="width: 100px">장소</th>
              <th style="width: 80px">위험 지수</th>
            </tr>
            <tr v-for="pItem in predictList" :key="pItem.type">
              <td><span v-html="pItem.type"></span></td>
              <td><span v-html="pItem.day"></span></td>
              <td><span v-html="pItem.spot"></span></td>
              <td><span v-html="pItem.idx"></span></td>
            </tr>
          </table>
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
import VcDonut from "../../../../node_modules/vue-css-donut-chart/src/components/Donut.vue";

Variablepie(Highcharts);
Highcharts3D(Highcharts);

export default {
  name: "GuCrimeModal",
  components: {
    highcharts: Chart,
    VueHighcharts,
    VcDonut,
  },
  props: {
    gu: String,
  },
  data() {
    return {
      Highcharts,
      isPredict: false,
      isExpand: false,
      expandWidth: "450px",
      arrowImg: "ic-arrow-right",
      crimeRate: [{ label: "label", value: 25, color:'#F57272' }],
      arrestRate: [{ label: "label", value: 25, color:'#2F488A' }],
      chartTypeOfCrime: {
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: "variablepie",
        },
        title: {
          text: ""
        },
        credits: {
          enabled: false,
        },
        series: [{
            minPointSize: 10,
            innerSize: "20%",
            zMin: 0,
            name: "범죄유형",
            data: [
              {
                name: '폭력',
                y: 505370,
                z: 92.9,
                color: '#6A7DAF'
              },
              {
                name: '절도',
                y: 551500,
                z: 118.7,
                color: '#8F9FC8'
              },
              {
                name: '강제추행',
                y: 312685,
                z: 124.6,
                color: '#AFBBD9'
              },
              {
                name: '강도',
                y: 78867,
                z: 137.5,
                color: '#CED6EA'
              },
              {
                name: '살해',
                y: 301340,
                z: 201.8,
                color: '#E9EDF5'
              },
            ],
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
            data: [3, 4, 3, 5, 4, 10, 12],
          },
        ],
      },
      highestTime:{
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          type: 'areaspline'
        },
        title: {
          text: "",
        },
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
            data: [10, 4, 2, 5, 4, 10, 12, 12],
          },
        ],
      },
      highestSpot: {
        chart: {
          renderTo : 'highestSpot',
          backgroundColor: "rgba(0,0,0,0)",
          type: 'column',
          options3d: {
            enabled: true,
            alpha: 15,
            beta: 15,
            depth: 50,
            viewDistance: 25
          }
        },
        title: {
          text: "",
        },
        plotOptions: {
          column: {
            depth: 25
          }
        },
        legend: {
          enabled: false,
        },
        credits: {
          enabled: false,
        },
        xAxis: {
          categories: ["a동", "b동", "c동", "d동", "e동"],
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
        series: [
          {
            name: '발생건수',
            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4],
          }
        ]
      },

      crimeTypeList:[
        { type: '폭력', num:'145' },
        { type: '절도', num:'125' },
        { type: '강간', num:'80' },
        { type: '강도', num:'65' },
        { type: '살해', num:'15' },
      ],
      predictList:[
        { type: '폭력', day:'토요일', spot:'노상', idx:'1.95' },
        { type: '절도', day:'토요일', spot:'노상', idx:'1.95' },
        { type: '살해', day:'토요일', spot:'노상', idx:'1.95' },
        { type: '강도', day:'토요일', spot:'노상', idx:'1.95' },
        { type: '추행', day:'토요일', spot:'노상', idx:'1.95' },
      ]
    };
  },
  methods: {
    onClick() {
      this.isPredict = true;
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
    onCrimeDetail(){
      // 00에 전달하는 값
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
.arrow-area {
  display: flex;
  justify-content: center;
  align-items: center;
}
.arrow-arrea:hover {
  cursor: pointer;
}
.arrow {
  padding: 24px 12px;
  background: #454d5e;
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
  z-index: 999;
  box-shadow: 12px 0px 20px -7px rgba(26, 31, 41, 0.45);
}
.predict-modal {
  z-index: 888;
  width: 400px;
  height: 300px;
  background: #454d5e;
  border-radius: 5px;
  box-shadow: 0px 0px 16px 3px rgba(26, 31, 41, 0.45);
  padding: 24px;
}
.predict-title {
  display: flex;
  font-size: 18px;
}
.crime-item {
  display: flex;
  margin-bottom: 8px;
}
.crimeDetail {
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
  padding:10px;
  background-color: #B8B8B8;
  border-radius: 5px;
}
td {
  padding:10px;
  background-color: #FF9D9D;
  border-radius: 5px;
  text-align: center;
}
</style>
