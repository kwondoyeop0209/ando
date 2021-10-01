<template>
  <div class="safety-detail">
    <!-- 순위 테이블 -->
    <div v-show="isRankSafety">
      <p>안전 지수</p>
      <div class="rank-table">
        <div
          class="rank-item"
          v-for="(item, idx) in rankList"
          :key="idx"
          @click="onSafetyDong(item.dong)"
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
      {{ dong }}
      <div>
        <highcharts :options="safetyScore"></highcharts>
      </div>
      <div>
        동작구 내에서 3위 <br>
        전체에서 48위
      </div><br>
      <hr class="one"><br>
      <div>
        <p> CCTV 보유 현황 3위 (밑에 게이지 차트는 나중에 데이터 들어오면 손보기로) </p>
        <p> 유흥지 분포 4위 (차트 추가) </p>
        <p> 파출소 분포 2위 (차트 추가) </p>
        <p> 보안등 분포 3위 (차트 추가) </p>
      </div>
    </div>
  </div>
</template>
<script>
import { Chart } from "highcharts-vue";
export default {
  name: "SafetyDetail",
  components: {
    highcharts: Chart,
  },
  data() {
    return {
      isDongSafety: false,
      isRankSafety: true,
      rankList: [],
      dong: "",
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
    this.rankList = [
      {
        dong: "신대방동",
        safetyIndex: 8.4,
      },
      {
        dong: "역삼동",
        safetyIndex: 8,
      },
      {
        dong: "신사동",
        safetyIndex: 7.4,
      },
      {
        dong: "흑석동",
        safetyIndex: 6.4,
      },
    ];
  },
  methods: {
    onSafetyDong(val) {
      this.dong = val;
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
</style>