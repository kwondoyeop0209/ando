<template>
  <div class="space-detail">
    <!-- 구 동 선택하는 공간 -->
    <div>
      <div class="space-title">
        <img
          src="@/assets/ic-search.png"
          :width="30"
          :height="30"
          style="margin-left: 8px"
        />
        <p style="margin-left: 8px">{{ spaceTitle }}</p>
      </div>
      <div class="select-region" v-show="!isDong">
        <select class="select" v-model="selectGuID">
          <option selected value="-1">자치구</option>
          <option v-for="(gu, idx) in guList" :key="idx" :value="gu.id">
            {{ gu.gu }}
          </option>
        </select>

        <select class="select" v-model="dongId">
          <option selected value="-1">행정동</option>
          <option v-for="(dong, idx) in dongList" :key="idx" :value="dong.id">
            {{ dong.dong }}
          </option>
        </select>
      </div>
    </div>

    <!-- 갯수 보여주는 공간 -->
    <div class="space-info" v-show="isDong">
      <div style="display: flex">
        <img src="@/assets/ic-arrow-left.png" width="26" class="back" @click="back"/>
        <p style="font-size: 24px;"><span style="color: #A4B5E2; font-weight: 700">{{ selectGu.dong }}</span> 환경요소</p>
      </div>
      <p class="space-info-subtitle">{{ spaceTitle }} 비율</p>
      <p class="space-ratio">
        <span style="font-weight: 600">{{ selectGu.guName }}</span>
        내
        <span>
          총 {{ spaceData.guCnt }}개 중 {{ this.spaceData.dongCnt }}개
        </span>있네요!
      </p>

      <!-- 비율 보여주는 그래프 -->
      <div class="doughnut">
        <VueSvgGauge
          class="mini-gauge"
          :start-angle="0"
          :end-angle="360"
          :min="0"
          :max="100"
          :value="(this.spaceData.dongCnt / this.spaceData.guCnt) * 100"
          :separator-step="0"
          :scale-interval="0"
          :inner-radius="85"
          :gauge-color="[{ offset: 0, color: '#6A7DAF'}]"
          base-Color="#EEEEEE"
        >
          <div class="inner-text">
            <span >{{Math.round((this.spaceData.dongCnt / this.spaceData.guCnt)*100,2)}}%</span>
          </div>
        </VueSvgGauge>
      </div>

      <div style="margin: 24px">
        <p style="font-size: 16px">
          <span style="font-weight: 600">{{ selectGu.guName }}</span>
          내에서 {{ rankData.ranking }}위를 차지했어요!
        </p>
      </div>

      <div class="rank-info">
        <highcharts
          :options="rankSpot"
          :highcharts="Highcharts"
          ref="Highcharts"
          style="height: 200px"
        ></highcharts>
      </div>

      <!-- 상관관계 보여주는 공간 -->
      <div class="graph-info">
      <p style="margin-bottom:10px; font-size: 18px;"> 💥 범죄와의 상관 관계 </p> <br>
      <highcharts :options="crimeRelation" style="height: 300px"></highcharts>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { Chart } from "highcharts-vue";
//import VueHighcharts from "vue2-highcharts";
import Highcharts from "highcharts";
import Variablepie from "highcharts/modules/variable-pie";
import Highcharts3D from "highcharts/highcharts-3d";
import { VueSvgGauge } from "vue-svg-gauge";
import $axios from "axios";

Variablepie(Highcharts);
Highcharts3D(Highcharts);


export default {
  name: "SpaceDetail",
  props: {
    space: String,
    isSpace: Boolean,
    dong: String,
    dongId: Number,
  },
  components: {
    highcharts: Chart,
    VueSvgGauge,
  },

  data() {
    return {
      Highcharts,
      selectGu: "자치구",
      selectGuID: -1,
      selectDong: "행정동",
      isDong: false,
      guList: [],
      dongList: [],
      spaceData: [],
      rankData: [],
      graphData: [],
      selectSpaceName: "",
      spaceTitle: "📹 CCTV",
      //순위 차트
      rankSpot: {
        chart: {
          renderTo : 'rankSpot',
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
        colors: ["#6A7DAF"],
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
            text: "갯수",
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
            valueSuffix: ' 개'
        },
        series: [
          {
            name: '갯수',
            data: [],
          }
        ]
      },

      //상관관계 그래프!
      crimeRelation: {
        chart: {
          backgroundColor: "rgba(0,0,0,0)",
          
        },
        title: {
          text: "",
        },
        colors: ["#6A7DAF"],
        xAxis: {
          categories: [],
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
            type: 'scatter',
            data: [], //발생건수 데이터들
            color: "rgba(223, 83, 83, .5)"
          },
          {
            name: '체포횟수',
            type: 'scatter',
            data: [],
            color: "rgba(119, 152, 191, .5)"
          },
          

        ],
      },
    }
  },

  mounted() {
    // 검색을 위해 구 목록 리턴
    $axios
      .get("/main/sigungu")
      .then((respon) => {
        this.guList = respon.data.guList;
      })
      .catch((e) => {
        console.log('error : ', e);
      });
  },

  methods: {
    setGuName(val) {
      this.selectGu = val;
    },
    setDongName(val) {
      this.selectDong = val;
    },
    getSpaceList(val) {
      this.isDong = true;
      //환경 지수의 갯수 구하는 부분(구별, 동별)
      $axios
        .get("/space/count?id=" + this.dongId + "&type=" + val)
        .then((re) => {
          this.spaceData = re.data;
        })
        .catch((e) => {
          console.log('error : ', e);
        });

      // 해당 동 space 개수 순위 주변 5개 개수정보
      $axios
        .get("/space/ranking?id=" + this.dongId + "&type=" + val)
        .then((r) => {
          //x축 y축 초기화를 시켜줘야 그래프가 계속 5개씩 나옴!
          this.rankSpot.series[0].data = [];
          this.rankSpot.xAxis.categories = [];

          this.rankData = r.data;
          const fiveDong = this.rankData.list;
          for (var i = 0; i<fiveDong.length; i++) {
            this.rankSpot.xAxis.categories.push(fiveDong[i].dongname);
            this.rankSpot.series[0].data.push(fiveDong[i].count);

            //특정 값에 대한 색상 지정이 잘 안돼..
            if (this.rankSpot.xAxis.categories[i] == this.selectDong) {
              this.rankSpot.chart.colors = "rgba(255,0,0,0.2)";
            }
          }
        })
        .catch((e) => {
          console.log('error : ', e)
        });
      //space 상관관계 정보
      $axios
        .get("/space/graph?type=" + val)
        .then((respons) => {
          this.crimeRelation.xAxis.categories = []
          this.crimeRelation.series[0].data = []
          this.crimeRelation.series[1].data = []
          this.graphData = respons.data;

          const graphValue = this.graphData.countList;
          const graphValue2 = this.graphData.arrestList;
          for(var i=0; i<graphValue.length; i++) {
            this.crimeRelation.xAxis.categories.push(graphValue[i].gu) //구역들 x축으로
            this.crimeRelation.series[0].data.push(graphValue[i].cnt *3) //발생건수
            this.crimeRelation.series[1].data.push(graphValue2[i].cnt) //체포건수
          }
        })
        .catch((e) => {
          console.log('error : ', e)
        });
    },
    getGuName() {
      $axios
        .get("/safety/gu/" + this.dongId)
        .then((response) => {
          this.selectGu = response.data;
        })
        .catch((e) => {
          console.log('error : ', e);
        });
    },
    back() {
      this.dongId = -1;
      this.selectGuID = -1;
      this.isDong = false;
      this.$emit("selectDongId", -1);
      this.dongList = [];
    }
  },

  watch: {
    selectGuID: function (val) {
      // 해당 구의 행정동 리스트 가져오기
      this.dongList = [];
      this.dongId = -1;
      if (val != -1) {
        axios
          .get("/main/dong/" + val)
          .then((respond) => {
            this.dongList = respond.data.getDongListDtoList;
            this.isDong = false;
          })
          .catch((e) => {
            console.log('error : ', e);
          });
      } else {
        this.$emit("selectDongId", -1);
        this.isDong = false;
      }
    },
    dongId: function (val) {
      if (val != -1 && this.isSpace) {
        this.getGuName();
        this.getSpaceList(this.space);
        this.$emit("selectDongId", val);
      } else {
        this.isDong = false;
      }
    },

    space: function (val) {
      this.isSpace = true;
      this.selectSpaceName = val;
      this.isDong = false;
      if (val === "cctv") {
        this.spaceTitle = "📹 CCTV";
      } else if (val === "bar") {
        this.spaceTitle = "🍺 유흥지";
      } else if (val === "police") {
        this.spaceTitle = "🚨 파출소";
      } else if (val === "light") {
        this.spaceTitle = "💡 보안등";
      } else {
        this.spaceTitle = "🏠 지킴이집";
      }
    },

    isSpace: function (val) {
      this.isDong = false;
      console.log("space", this.space);
      if (!val) {
        this.getSpaceList("cctv");
        this.dongId = -1;
        this.selectGuID = -1;
      }
    },
  }
};
</script>
<style scoped>
.space-detail {
  height: calc(100% - 36px);
  overflow: auto;
  padding: 18px;
}

.select-region {
  display: flex;
}

.select {
  flex: 1;
  height: 42px;
  margin-right: 8px;
  background-color: #454d5e;
  border-radius: 5px;
  border: 1px solid rgba(255, 255, 255, 0.6);
  font-size: 16px;
}

.space-detail::-webkit-scrollbar {
  width: 10px;
}
.space-detail::-webkit-scrollbar-thumb {
  background-color: darkgray;
  border-radius: 24px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.space-detail::-webkit-scrollbar-track {
  background-color: #454d5e;
  border-radius: 24px;
}
.space-info {
  margin-top: 16px;
}

.space-info-subtitle {
  font-size: 18px;
  margin-top: 24px;
  margin-bottom: 18px;
}

.space-ratio {
  font-size: 16px;
  margin-left: 24px;
}

.doughnut {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 24px;
}
.inner-text {
  display: flex;
  margin-top: 80px;
  align-items: center;
  justify-content: center;
  font-size: 35px;
  font-weight: bold;
}
.mini-gauge {
  max-width: 180px;
}

.space-title {
  display: flex;
  border-radius: 5px;
  border: 1px solid rgba(255, 255, 255, 0.6);
  height: 42px;
  font-size: 18px;
  font-weight: 700;
  align-items: center;
  margin-bottom: 10px;
}
.back {
  margin-left: -4px;
  margin-right: 8px;
}
.back:hover {
  cursor: pointer;
}
</style>
