<template>
  <div class="safety-detail">

    <!-- 순위 테이블 -->
    <div v-show="isRankSafety">
      <p style="font-size:30px; font-weight: 600">💡 안전 지수</p><br />
      <div class="rank-table">
        <div
          class="rank-item"
          v-for="(item, idx) in rankList"
          :key="idx"
          @click="onSafetyDong(item.dong, item.safetyIndex, item.id)"
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
      <span style="font-size:25px; font-weight: 400">{{dong}} 안전지수</span>
      <br /><br />
      <div class="doughnut">
        <VueSvgGauge
          class="mini-gauge"
          :start-angle="-90"
          :end-angle="90"
          :min="0"
          :max="100"
          :value="this.safetyIndex*10"
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
      <div class=rankDetail >
        <p style="margin-bottom:10px">{{this.guData}} 내에서 <span style="font-size:30px; font-weight: 600">{{this.rankingData.rankingOfGu}}위</span></p>
        <p>전체에서 <span style="font-size:30px; font-weight: 600">{{this.rankingData.rankingOfSeoul}}위</span></p>
      </div><br />
      <hr class="one"><br />
      <div>

        <!-- 차트? 그거 추가해야함!! -->
        <p> CCTV 보유 현황 <span style="font-size:30px; font-weight: 600">{{this.spaceData.cctvRanking}}위</span></p>
        <p> 유흥지 분포 <span style="font-size:30px; font-weight: 600">{{this.spaceData.barRanking}}위</span></p>
        <p> 파출소 분포 <span style="font-size:30px; font-weight: 600">{{this.spaceData.policeRanking}}위</span></p>
        <p> 보안등 분포 <span style="font-size:30px; font-weight: 600">{{this.spaceData.lightRanking}}위</span></p>
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
      dongID: "",
      rankingData:[],
      spaceData:[],
      guData:"",
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
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/safety/top")
      .then(res => {
        this.rankList = res.data.getTopSafetyListDtoList
        //console.log(this.rankList)
      })
    
  },
  methods: {
    onSafetyDong(val, val2, val3) {
      this.dong = val;
      this.safetyIndex = val2;
      this.dongID = val3;
      this.isDongSafety = true;
      this.isRankSafety = false;

      //해당 동의 안전지수, 구 순위, 전체 순위 반환
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/safety/point/"+ this.dongID)
      .then(res => {
        this.rankingData = res.data
        //console.log(this.rankingData)
      })
      .catch(e => {
          console.log('error : ', e)
          })


      //환경 요소 순위 보여주기
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/safety/detail/"+ this.dongID)
      .then(response => {
        this.spaceData = response.data
        //console.log(this.spaceData)
      })
      .catch(e => {
          console.log('error : ', e)
          })


      // 해당 동이 속해있는 구 이름 반환
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/safety/gu/"+ this.dongID)
      .then(respond => {
        this.guData = respond.data
        //console.log(this.guData)
      })
      .catch(e => {
          console.log('error : ', e)
          })
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
  font-size: 20px;
  font-weight: 200;
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
