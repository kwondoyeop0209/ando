<template>
  <div class="safety-detail">

    <!-- 순위 테이블 -->
    <div v-show="isRankSafety">
      <p style="font-size: 24px; font-weight: 600; margin-bottom: 16px">💡 안전 지수</p>
      <div class="rank-table">
        <div
          class="rank-item"
          v-for="(item, idx) in rankList"
          :key="idx"
          @click="selectDong(item.dong, item.id)"
        >
          <div class="rank">{{ idx + 1 }}</div>
          <div class="rank-content">
            <p style="flex: 1; text-align: center">{{ item.dong }}</p>
            <p>|</p>
            <p style="flex: 1; text-align: center">{{ item.safetyIndex }}점</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 표에서 셀 클릭하면 나오는 상세화면 -->
    <div v-show="isDongSafety">
      <div style="display: flex">
        <img src="@/assets/ic-arrow-left.png" width="26" class="back" @click="back"/>
        <p style="font-size: 24px;"><span style="color: #A4B5E2; font-weight: 700">{{ dong }}</span> 안전지수</p>
      </div>
      <div class="doughnut">
        <VueSvgGauge
          class="mini-gauge"
          :start-angle="-90"
          :end-angle="90"
          :min="0"
          :max="100"
          :value="this.safetyIndex * 10"
          :separator-step="0"
          :scale-interval="10"
          :inner-radius="70"
          :gauge-color="[{ offset: 0, color: '#6A7DAF' }]"
          base-Color="#EEEEEE"
        >
          <div class="inner-safetyIdx">
            <span>{{ safetyIndex }} 점</span>
          </div>
        </VueSvgGauge>
      </div>
      <div class="rankDetail">
        <p style="margin: 5px 30px">
          {{ this.guData.guName }} 내에서
          <span style="font-size: 28px; font-weight: 600">
            {{ this.rankingData.rankingOfGu }}위
          </span>
        </p>
        <p style="margin: 5px 30px 35px">
          전체 행정동에서
          <span style="font-size: 28px; font-weight: 600">
            {{ this.rankingData.rankingOfSeoul }}위
          </span>
        </p>
      </div>
      <div class="space-rank">
        <div class="space-rank-explain">
          <div class="blue-circle"></div>
          <div style="margin: 0 8px">평균</div>
          <div class="red-circle"></div>
          <div style="margin: 0 8px">우리동네</div>
        </div>
        <div class="space-rank-item" style="margin-top: 25px">
          <p>📹 CCTV 보유 현황
            <span style="font-size: 30px; font-weight: 600">
              {{ this.spaceData.cctvRanking }}위
            </span>
          </p>
          <div style="font-size: 12px; text-align: end; margin-top: 15px">
            <span class="white-blue">평균</span>
            : {{ this.spaceData.cctvAvg }}개,
            <span class="light-red">우리동네</span>
            : {{ this.dongSpaceCnt.cctv }}개
          </div>
          <div style="margin: 20px 0; position: relative">
            <div class="chart-line"></div>
            <div class="blue-circle chart-avg chart-avg-cctv"></div>
            <div class="red-circle chart-dong chart-dong-cctv"></div>
          </div>
        </div>
        <div class="space-rank-item">
          <p>🍺 유흥지 현황
            <span style="font-size: 30px; font-weight: 600">
              {{ this.spaceData.barRanking }}위
            </span>
          </p>
          <div style="font-size: 12px; text-align: end; margin-top: 15px">
            <span class="white-blue">평균</span>
            : {{ this.spaceData.barAvg }}개,
            <span class="light-red">우리동네</span>
            : {{ this.dongSpaceCnt.bar }}개
          </div>
          <div style="margin: 20px 0; position: relative">
            <div class="chart-line"></div>
            <div class="blue-circle chart-avg chart-avg-bar"></div>
            <div class="red-circle chart-dong chart-dong-bar"></div>
          </div>
        </div>
        <div class="space-rank-item">
          <p>🚨 파출소 현황
            <span style="font-size: 30px; font-weight: 600">
              {{ this.spaceData.policeRanking }}위
            </span>
          </p>
          <div style="font-size: 12px; text-align: end; margin-top: 15px">
            <span class="white-blue">평균</span>
            : {{ this.spaceData.policeAvg }}개,
            <span class="light-red">우리동네</span>
            : {{ this.dongSpaceCnt.police }}개
          </div>
          <div style="margin: 20px 0; position: relative">
            <div class="chart-line"></div>
            <div class="blue-circle chart-avg chart-avg-police"></div>
            <div class="red-circle chart-dong chart-dong-police"></div>
          </div>
        </div>
        <div class="space-rank-item">
          <p>💡 보안등 보유 현황
            <span style="font-size: 30px; font-weight: 600">
              {{ this.spaceData.lightRanking }}위
            </span>
          </p>
          <div style="font-size: 12px; text-align: end; margin-top: 15px">
            <span class="white-blue">평균</span>
            : {{ this.spaceData.lightAvg }}개,
            <span class="light-red">우리동네</span>
            : {{ this.dongSpaceCnt.light }}개
          </div>
          <div style="margin: 20px 0; position: relative">
            <div class="chart-line"></div>
            <div class="blue-circle chart-avg chart-avg-light"></div>
            <div class="red-circle chart-dong chart-dong-light"></div>
          </div>
        </div>
        <div class="space-rank-item">
          <p>🏠 지킴이집 현황
            <span style="font-size: 30px; font-weight: 600">
              {{ this.spaceData.guardRanking }}위
            </span>
          </p>
          <div style="font-size: 12px; text-align: end; margin-top: 15px">
            <span class="white-blue">평균</span>
            : {{ this.spaceData.guardAvg }}개,
            <span class="light-red">우리동네</span>
            : {{ this.dongSpaceCnt.guard }}개
          </div>
          <div style="margin: 20px 0; position: relative">
            <div class="chart-line"></div>
            <div class="blue-circle chart-avg chart-avg-guard"></div>
            <div class="red-circle chart-dong chart-dong-guard"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script src="../../../node_modules/vue-svg-gauge/dist/vue-svg-gauge.min.js"></script>
<script>
import { Chart } from "highcharts-vue";
import { VueSvgGauge } from "vue-svg-gauge";
import $axios from "axios";

export default {
  name: "SafetyDetail",
  components: {
    highcharts: Chart,
    VueSvgGauge,
  },
  props: {
    dong: String,
    dongId: Number,
    isSafety: Boolean,
  },
  watch: {
    dongId: function (val) {
      if (val != -1 && this.isSafety) {
        this.onSafetyDong(this.dong, val);
      }
    },
    isSafety: function (val) {
      if (!val) {
        this.isDongSafety = false;
        this.isRankSafety = true;
      }
    },
  },
  data() {
    return {
      isDongSafety: false,
      isRankSafety: true,
      rankList: [],
      dongID: "",
      rankingData: [],
      spaceData: [],
      dongSpaceCnt: {
        cctv: -1,
        bar: -1,
        police: -1,
        light: -1,
        guard: -1,
      },
      guData: "",
      safetyIndex: "",
      safetyScore: {
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
          categories: ["안전지수 점수"],
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
                y: 4.7,
                color: "#6A7DAF",
              },
            ],
          },
        ],
      },
    };
  },
  mounted() {
    //안전지수 상위 10개 목록
    $axios.get("/safety/top").then((res) => {
      this.rankList = res.data.getTopSafetyListDtoList;
      //console.log(this.rankList)
    });
  },
  methods: {
    onSafetyDong(val, val3) {
      this.$emit("selectDongId", val3);
      this.$emit("selectDong", val);
      this.dong = val;
      this.dongID = val3;
      this.isDongSafety = true;
      this.isRankSafety = false;

      //해당 동의 안전지수, 구 순위, 전체 순위 반환
      $axios
        .get("/safety/point/" + this.dongID)
        .then((res) => {
          this.rankingData = res.data;
          this.safetyIndex = res.data.safetyIndex;
          //console.log(this.rankingData)
        })
        .catch(e => {
          console.log('error : ', e);
        });

      //환경 요소 순위 보여주기
      $axios
        .get("/safety/detail/" + this.dongID)
        .then((response) => {
          this.spaceData = response.data;
          this.spaceData.cctvAvg = this.spaceData.cctvAvg.toFixed(2);
          this.spaceData.barAvg = this.spaceData.barAvg.toFixed(2);
          this.spaceData.policeAvg = this.spaceData.policeAvg.toFixed(2);
          this.spaceData.lightAvg = this.spaceData.lightAvg.toFixed(2);
          this.spaceData.guardAvg = this.spaceData.guardAvg.toFixed(2);

          this.drawLineChart("cctv", this.spaceData.cctvAvg);
          this.drawLineChart("bar", this.spaceData.barAvg);
          this.drawLineChart("police", this.spaceData.policeAvg);
          this.drawLineChart("light", this.spaceData.lightAvg);
          this.drawLineChart("guard", this.spaceData.guardAvg);
        })
        .catch(e => {
          console.log('error : ', e);
        });

      // 해당 동이 속해있는 구 이름 반환
      $axios
        .get("/safety/gu/" + this.dongID)
        .then((respond) => {
          this.guData = respond.data;
        })
        .catch(e => {
          console.log('error : ', e);
        });
    },
    back() {
      this.isSafety = true;
      this.isDongSafety = false;
      this.isRankSafety = true;
      this.$emit("selectDongId", -1);
      this.$emit("selectDong", "");
    },
    async drawLineChart(type, avg) {
      const d = await this.getDongSpaceCnt(type);
      let min = -1;
      let max = -1;
      let cal2 = -1;
      const width = document.querySelector(".chart-line").offsetWidth;

      if (type === "cctv") {
        min = 3;
        max = 753;
        cal2 = width * ((this.dongSpaceCnt.cctv - min) / (max - min));
      } else if (type === "bar") {
        min = 0;
        max = 191;
        cal2 = width * ((this.dongSpaceCnt.bar - min) / (max - min));
      } else if (type === "police") {
        min = 0;
        max = 5;
        cal2 = width * ((this.dongSpaceCnt.police - min) / (max - min));
      } else if (type === "light") {
        min = 0;
        max = 2169;
        cal2 = width * ((this.dongSpaceCnt.light - min) / (max - min));
      } else {
        min = 0;
        max = 33;
        cal2 = width * ((this.dongSpaceCnt.guard - min) / (max - min));
      }
      const cal = width * ((avg - 0) / (max - min));
      //평균
      let result1 = Math.round(cal);
      result1 = result1 > width ? width : result1;
      result1 = result1 < 0 ? 0 : result1;
      document.querySelector(".chart-avg-" + type).style = "left: " + result1 + "px";
      //우리동네
      let result2 = Math.round(cal2);
      result2 = result2 > width ? width : result2;
      result2 = result2 < 0 ? 0 : result2;
      document.querySelector(".chart-dong-" + type).style = "left: " + result2 + "px";
    },
    async getDongSpaceCnt(type) {
      const d = await $axios
        .get("/space/count", {
          params: {
            id: this.dongId,
            type: type,
          },
        })
        .then((response) => {
          if (type === "cctv") {
            this.dongSpaceCnt.cctv = response.data.dongCnt;
          } else if (type === "bar") {
            this.dongSpaceCnt.bar = response.data.dongCnt;
          } else if (type === "police") {
            this.dongSpaceCnt.police = response.data.dongCnt;
          } else if (type === "light") {
            this.dongSpaceCnt.light = response.data.dongCnt;
          } else {
            this.dongSpaceCnt.guard = response.data.dongCnt;
          }
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    selectDong(val, val3) {
      this.$emit("selectDongId", val3);
      this.$emit("selectDong", val);
      this.dong = val;
      this.dongID = val3;
      this.isDongSafety = true;
      this.isRankSafety = false;
    },
  },
};
</script>
<style scoped>
.safety-detail {
  padding: 18px;
  height: calc(100% - 36px);
  overflow: auto;
}
.safety-detail::-webkit-scrollbar {
  width: 10px;
}
.safety-detail::-webkit-scrollbar-thumb {
  background-color: darkgray;
  border-radius: 24px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
.safety-detail::-webkit-scrollbar-track {
  background-color: #454d5e;
  border-radius: 24px;
}
.rank-item {
  display: flex;
  margin-bottom: 8px;
}
.rank-item:hover {
  cursor: pointer;
}
.rank {
  background-color: #6A7DAF;
  padding: 8px 12px;
  border-radius: 4px;
}
.rank-content {
  background-color: #6A7DAF;
  padding: 8px 5px;
  border-radius: 4px;
  margin-left: 8px;
  flex: 1;
  display: flex;
  font-size: 16px;
  font-weight: 200;
}
.doughnut {
  margin-top: 50px;
  margin-bottom: 60px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.mini-gauge {
  max-width: 300px;
}
.inner-safetyIdx {
  display: flex;
  margin-top: 80px;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: bold;
}
.rankDetail {
  margin: 0 18px;
  font-size: 22px;
  border-bottom: 0.5px solid #888888;
}
.back {
  margin-right: 8px;
}
.back:hover {
  cursor: pointer;
}
.space-rank {
  margin: 0 18px;
  font-size: 18px;
}
.space-rank-explain {
  display: flex;
  justify-content: end;
  margin: 10px 0;
  align-items: center;
  font-size: 14px;
}
.blue-circle {
  width: 13px;
  height: 13px;
  border-radius: 50%;
  background-color: #6A7DAF;
}
.red-circle {
  width: 13px;
  height: 13px;
  border-radius: 50%;
  background-color: #F57272;
}
.space-rank-item {
  margin: 55px 30px;
}
.chart-line {
  background-color: #C4C4C4;
  width: 100%;
  height: 4px;
  border-radius: 8px;
  position: absolute;
  top: 4px;
}
.chart-avg {
  z-index: 999;
  position: absolute;
}
.chart-dong {
  z-index: 999;
  position: absolute;
}
</style>
