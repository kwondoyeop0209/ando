<template>
  <div class="charts">
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
      <div align="center">
        <p class="chart-subtitle">최근 3년간 발생 건수</p>
        <highcharts :options="chartLatest" style="height: 300px"></highcharts>
      </div>
      <div align="center" class="seoul-crime">
        <p class="chart-subtitle">서울시 5대 범죄</p>
        <div style="display: flex; justify-content: center">
          <p class="type5" style="background-color: #f57373">폭력</p>
        </div>
        <div style="display: flex; justify-content: center">
          <p class="type5" style="background-color: #fa9385">절도</p>
        </div>
        <div style="display: flex; justify-content: center">
          <p class="type5" style="background-color: #faaf85">강간</p>
        </div>
        <div style="display: flex; justify-content: center">
          <p class="type5" style="background-color: #facb85">강도</p>
        </div>
        <div style="display: flex; justify-content: center">
          <p class="type5" style="background-color: #fad985">살인</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Chart } from "highcharts-vue";
export default {
  name: "TotalCrimeModal",
  components: {
    highcharts: Chart,
  },
  data() {
    return {
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
            name: "발생건수",
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
            name: "발생건수",
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
            name: "발생건수",
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
    };
  },
};
</script>
<style scoped>
.charts {
  display: flex;
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
.seoul-crime {
  display: flex;
  flex-direction: column;
  margin-left: 24px;
}
.type5 {
  border-radius: 24px;
  padding: 12px 70px;
  font-size: 18px;
  margin-top: 20px;
}
</style>
