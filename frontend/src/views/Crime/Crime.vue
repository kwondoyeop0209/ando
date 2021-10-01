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
              <highcharts :options="chartArrest" style="height: 300px"></highcharts>
              <p class="chart-subtitle"><b>범죄율</b>이 높은 지역 (건수)</p>
              <highcharts :options="chartCrime" style="height: 300px"></highcharts>
            </div>
            <div style="flex: 1"></div>
            <div class="right-content">
              <div>
                <p class="chart-subtitle">최근 3년간 발생 건수</p>
                <highcharts :options="chartLatest" style="height: 300px"></highcharts>
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
                  
                  <p style="font-size: 24px; font-weight: 600"><span style="color:#A4B5E2; font-size:24px; font-weight: 600">{{selectGu}}</span> 범죄 현황</p>
                  <div style="flex: 1"></div>
                  <p id="detail_btn" @click="onClick">예측 분석 보기 ></p>
                </div>
                <!-- 범죄율 -->
                <div>
                  <p class="rateTitle">💡 범죄율</p><br />
                  <p class="rateDetail"> 총 몇 건 중 몇 00건이 발생</p><br />
                  <vc-donut :sections="crimeRate" :size="150" 
                  background="#454d5e" foreground="#EEEEEE"
                  unit="px" :thickness="20"
                  :total="100"
                  :start-angle="0" :auto-adjust-text-size="true">
                    <h1>25%</h1>
                  </vc-donut>
                </div><br />
                <!-- 검거율 -->
                <div>
                  <p class="rateTitle">💡 검거율</p><br />
                  <p class="rateDetail"> 총 몇 건 중 몇 00건이 발생</p><br />
                  <vc-donut :sections="arrestRate" :size="150" 
                  background="#454d5e" foreground="#EEEEEE"
                  unit="px" :thickness="20"
                  :total="100"
                  :start-angle="0" :auto-adjust-text-size="true">
                    <h1>25%</h1>
                  </vc-donut>
                </div><br />
                <!-- 범죄 유형 -->
                <div>
                  <p class="rateTitle">💡 범죄 유형</p><br />
                    <vue-highcharts
                      :options="chartTypeOfCrime"
                      :highcharts="Highcharts"
                      style="height: 400px"
                    ></vue-highcharts>
                </div><br />
                <!-- 범죄유형 리스트 -->
                <ul style="list-style: none; padding-left: 0px;">
                  <li class="cList" v-for="(crime,i) in crimeTypeList" :key="crime.type">
                    <a href="#" style="display: flex; text-decoration:none">
                    <div class="number" style="margin-right: 5px">{{i+1}} </div>
                    <div class="number" style="flex: 1; width:200px; text-align:center">{{crime.type}}  | {{crime.num}}</div>
                    </a>
                  </li> 
                </ul>
                <div>
                  <p class="rateTitle">💡 00 이/가 높은 요일</p><br />
                  <highcharts :options="highestDay" style="height: 300px"></highcharts>
                </div><br />
                <div>
                  <p class="rateTitle">💡 00 이/가 높은 시간</p><br />
                  <highcharts :options="highestTime" style="height: 300px"></highcharts>
                </div><br />
                <div>
                  <p class="rateTitle">💡 00 이/가 높은 발생 장소</p><br />
                  <!-- <highcharts :options="highestSpot" style="height: 300px"></highcharts> -->
                    <vue-highcharts
                      :options="highestSpot"
                      :highcharts="Highcharts"
                      style="height: 400px"
                    ></vue-highcharts>
                </div><br />
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
                  <p><span style="color:#A4B5E2; font-size:18px; font-weight: 600">{{selectGu}}</span> 범죄 예측 분석</p>
                  <div style="flex: 1"></div>
                  <img
                    src="@/assets/ic-close.png"
                    :width="20"
                    @click="offClick"
                  />
                </div>
                <!-- 예측테이블 -->
                <div>
                  <table style="padding-top:10px">
                    <tr>
                      <th style="width:60px">유형</th>
                      <th style="width:60px">요일</th>
                      <th style="width:100px">장소</th>
                      <th style="width:80px">위험 지수</th>
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
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import VcDonut from "../../../node_modules/vue-css-donut-chart/src/components/Donut.vue";
import { Chart } from "highcharts-vue";
import VueHighcharts from "vue2-highcharts";
import Highcharts from "highcharts";
import Variablepie from "highcharts/modules/variable-pie";
import Highcharts3D from "highcharts/highcharts-3d";

Variablepie(Highcharts);
Highcharts3D(Highcharts);


export default {
  name: "Crime",
  components: {
    highcharts: Chart,
    VcDonut,
    VueHighcharts,
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

      Highcharts,
      
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
            name: '발생건수',
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
            name: '발생건수',
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
          title: {
            text: "건수",
            style: {
              color: "#ffffff",
            },
          },
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
            name: '발생건수',
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
      crimeRate: [{ label: "label", value: 25, color:'#F57272' }],
      arrestRate: [{ label: "label", value: 25, color:'#2F488A' }],
      chartTypeOfCrime:{
          chart: {
            backgroundColor: "rgba(0,0,0,0)",
            type: 'variablepie'

          },
          title: {
              text: ''
          },
          credits: {
            enabled: false,
          },
          series: [{
              minPointSize: 10,
              innerSize: '20%',
              zMin: 0,
              name: '범죄유형',
              data: [{
                  name: '폭력',
                  y: 505370,
                  z: 92.9,
                  color: '#6A7DAF'
              }, {
                  name: '절도',
                  y: 551500,
                  z: 118.7,
                  color: '#8F9FC8'
              }, {
                  name: '강제추행',
                  y: 312685,
                  z: 124.6,
                  color: '#AFBBD9'
              }, {
                  name: '강도',
                  y: 78867,
                  z: 137.5,
                  color: '#CED6EA'
              }, {
                  name: '살해',
                  y: 301340,
                  z: 201.8,
                  color: '#E9EDF5'
              },
            ],
          },
        ],
      },
      highestDay:{
        chart: {
            backgroundColor: "rgba(0,0,0,0)",
            type: 'areaspline'
        },
        title: {
            text: ''
        },
        xAxis: {
          categories: [
                '월',
                '화',
                '수',
                '목',
                '금',
                '토',
                '일'
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
              // fillColor:'#A4B5E2',
              fillOpacity: 0.5,
            },
        },
        series: [{
            name: '발생건수',
            data: [3, 4, 3, 5, 4, 10, 12]
        },
        ],
      },
      highestTime:{
        chart: {
            backgroundColor: "rgba(0,0,0,0)",
            type: 'areaspline'
        },
        title: {
            text: ''
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
        series: [{
            name: '발생건수',
            data:[10, 4, 2, 5, 4, 10, 12, 12],
        },
        ],
      },
      highestSpot:{ 
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
            text: ''
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
        series: [{
            name: '발생건수',
            data: [29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4]
        }]
      },

      crimeTypeList:[
        {type: '폭력', num:'145건'},
        {type: '절도', num:'125건'},
        {type: '강간', num:'80건'},
        {type: '강도', num:'65건'},
        {type: '살해', num:'15건'},
      ],
      predictList:[
        {type: '폭력', day:'토요일', spot:'노상', idx:'1.95'},
        {type: '절도', day:'토요일', spot:'노상', idx:'1.95'},
        {type: '살해', day:'토요일', spot:'노상', idx:'1.95'},
        {type: '강도', day:'토요일', spot:'노상', idx:'1.95'},
        {type: '추행', day:'토요일', spot:'노상', idx:'1.95'},
      ]

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
  background-color: #454d5e;
}
.main {
  min-width: 1450px;
  height: 100%;
  position: relative;
  background-color: #454d5e;
}
.map {
  padding-top: 52px;
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
.rateDetail {
  font-size: 18px;
  margin-left: 28px;
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
  height: 800px;
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
  z-index: 999;
  box-shadow: 12px 0px 20px -7px #000;
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
.predict-title {
  display: flex;
  font-size: 18px;
}
.number{
  margin-block: 5px;
  display:block;
  padding:10px;
  border-radius: 5px;
  width:10px;
  background:#B8B8B8
}
.cList a :hover{
  background-color: #F57272;
}
th{
  padding:10px;
  background-color: #B8B8B8;
  border-radius: 5px;
}
td{
  padding:10px;
  background-color: #FF9D9D;
  border-radius: 5px;
  text-align: center;
}
</style>
