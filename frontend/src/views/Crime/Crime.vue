<template>
  <div class="crime">
    <div class="main">
      <img src="@/assets/seoul-map.png" class="seoul-map" />
      <div class="main-content">
        검색 :
        <select>
          <option>전체</option>
        </select>
        <p>검거율이 높은 지역</p>
        <highcharts :options="chartOptions"></highcharts>
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
      chartOptions: {
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
          categories: ["강남구", "영등포구", "동작구"],
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
            ],
          },
        ],
      },
    };
  },
};
</script>
<style scoped>
.crime {
  width: 100%;
}
.main {
  min-width: 1200px;
  position: relative;
}
.seoul-map {
  margin: 48px 0;
  width: 100%;
}
.main-content {
  position: absolute;
  top: 20px;
  left: 20px;
  z-index: 888;
  width: 400px;
}
</style>
