<template>
  <div class="safety-detail">
    <!-- 순위 테이블 -->
    <div v-show="isRankSafety">
      <p>안전 지수</p><br />
      <div class="rank-table">
        <div
          class="rank-item"
          v-for="(item, idx) in rankList"
          :key="idx"
          @click="onSafetyDong(item.dong, item.safetyIndex)"
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
      <span style="font-size:24px; font-weight: 400">{{dong}} 안전지수</span>
      <br /><br />
      <div class="doughnut">
        <VueSvgGauge
          class="mini-gauge"
          :start-angle="-90"
          :end-angle="90"
          :min="0"
          :max="100"
          :value="47"
          :separator-step="0"
          :scale-interval="10"
          :inner-radius="70"
          :gauge-color="[{ offset: 0, color: '#6A7DAF'}]"
          base-Color="#EEEEEE"
        >
          <div class="inner-safetyIdx">
            <span>{{safetyIndex}} 점</span>
          </div>
        </VueSvgGauge>
      </div>
      <div class=rankDetail>
        <p style="margin-bottom:10px">동작구 내에서 <span style="font-size:30px; font-weight: 600">3위</span></p>
        <p>전체에서 <span style="font-size:30px; font-weight: 600">48위</span></p>
      </div><br />
      <hr class="one"><br />
      <div>
        <!-- Crime에 똑같은 거 있음 복붙하면 됨 -->
        <p> CCTV 보유 현황 3위 (밑에 게이지 차트는 나중에 데이터 들어오면 손보기로) </p>
        <p> 유흥지 분포 4위 (차트 추가) </p>
        <p> 파출소 분포 2위 (차트 추가) </p>
        <p> 보안등 분포 3위 (차트 추가) </p>
      </div>
    </div>
  </div>
</template>
<script src="../../../node_modules/vue-svg-gauge/dist/vue-svg-gauge.min.js"></script>
<script>
import { Chart } from "highcharts-vue";
import { VueSvgGauge } from "vue-svg-gauge";
import axios from "axios";
export default {
  name: "SafetyDetail",
  components: {
    highcharts: Chart,
    VueSvgGauge,
  },
  data() {
    return {
      isDongSafety: false,
      isRankSafety: true,
      rankList: [],
      dong: "",
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
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/safety/top")
      .then(res => {
        this.rankList = res.data.getTopSafetyListDtoList
        console.log(this.rankList)
      })
    
  },
  methods: {
    onSafetyDong(val, val2) {
      this.dong = val;
      this.safetyIndex = val2;
      this.isDongSafety = true;
      this.isRankSafety = false;
    }
  },
};
</script>
<style scoped>
.rank-item {
  display: flex;
  margin-bottom: 8px;
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
}
.doughnut{
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
.rankDetail{
  margin-left: 45px;
  font-size: 25px;
}
</style>
