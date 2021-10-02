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
        <highcharts
          :options="chartLatest"
          ref="chartLatest"
          style="height: 300px"
        >
        </highcharts>
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
import $axios from "axios";
const chartColor = ["#6A7DAF", "#8F9FC8", "#AFBBD9", "#CED6EA", "#E9EDF5"];
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
          categories: [],
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
            data: [],
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
            data: [],
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
  created() {
    //서버 통신
    $axios
      .get("/crime/top")
      .then((response) => {
        //검거율
        //카테고리 반환
        this.chartArrest.xAxis.categories = response.data.arrestList.map(
          (item) => item.gu
        );
        //데이터 반환
        this.chartArrest.series[0].data = response.data.arrestList.map(
          (item, idx) => {
            return {
              y: item.count,
              color: chartColor[idx],
            };
          }
        );

        //범죄율
        //카테고리 반환
        this.chartCrime.xAxis.categories = response.data.crimeList.map(
          (item) => item.gu
        );
        //데이터 반환
        this.chartCrime.series[0].data = response.data.crimeList.map(
          (item, idx) => {
            return {
              y: item.count,
              color: chartColor[idx],
            };
          }
        );
      })
      .catch(() => {
        console.log("오류가 발생했습니다.");
      });
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
