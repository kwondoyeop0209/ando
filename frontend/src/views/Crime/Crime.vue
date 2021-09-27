<template>
  <div class="crime">
    <div class="main">
      <div class="main-content">
      검색 :
        <select id="selectGu" @change="changeGu(), showMain()">
          <option selected>전체</option>
          <option>강남구</option>
          <option>광진구</option>
          <option>송파구</option>
          <option>관악구</option>
        </select>
      </div>
      <div class="charts" v-show="isMain">
        <img src="@/assets/seoul-map.png" class="seoul-map" />
      <div class="left-content">
        <br /><br />
        <p class="mid">최근 3년간 서울시 범죄 발생
          <br /><span class="white highlight">296,177건</span>
        </p>
        <br />
        <p class="mid" align = "center"><b>검거율</b>이 높은 지역 (건수)</p>
        <highcharts :options="chartArrest"></highcharts>
        <br />
        <p class="mid" align = "center"><b>범죄율</b>이 높은 지역 (건수)</p>
        <highcharts :options="chartCrime"></highcharts>
      </div>
      <div class="right-content" align = "center">
        <p class="mid">최근 3년간 발생 건수</p>
        <highcharts :options="chartLatest"></highcharts>
      </div>
      <div class="seoulCrime" align="center">
        <p class="mid">서울시 5대 범죄</p>
        <p class="type5" style='background-color:#F57373'>폭력</p><br />
        <p class="type5" style='background-color:#FA9385'>절도</p><br />
        <p class="type5" style='background-color:#FAAF85'>강간</p><br />
        <p class="type5" style='background-color:#FACB85'>강도</p><br />
        <p class="type5" style='background-color:#FAD985'>살인</p>
      </div>
      </div>
      <div class="guCharts" v-show="isGu">
        <img src="@/assets/gangnam.png" class="gangnam-map" />
        <!-- <modal v-if="showModal" @close="showModal = false">
        <h3 slot="header">custom header</h3>
        </modal> -->
        <div class="black_bg"></div>
        <div class="modal_wrap">
            <div class="modal_close" @click="offClick"><a href="#">close</a></div>
            <div>
                <span style="color:black">모달창</span>
            </div>
        </div> 
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
      isMain: true,
      isGu: false,
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
          categories: ["강남구", "영등포구", "동작구","광진구","송파구"],
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
              }
          }
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
          categories: ["강남구", "영등포구", "동작구","광진구","송파구"],
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
              }
          }
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
          min:0,
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
              }
          }
        },
        legend: {
          enabled: false,
        },
        series: [
          {
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
  methods:{
    showMain(){
      const guSelect = document.getElementById("selectGu");
      
      const selectedText = guSelect.options[guSelect.selectedIndex].text;
      if(selectedText=="전체"){
        this.isMain=true
        this.isGu=false
      }else{
        this.isMain=false
      }
    },
    changeGu(){
      const guSelect = document.getElementById("selectGu");
      
      const selectedText = guSelect.options[guSelect.selectedIndex].text;
      console.log(selectedText);
      this.isGu=true
      document.querySelector('.modal_wrap').style.display ='block';
      document.querySelector('.black_bg').style.display ='block';
    },
    offClick() {
        document.querySelector('.modal_wrap').style.display ='none';
        document.querySelector('.black_bg').style.display ='none';
    }    
  }
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
  top: 30px;
  left: 35px;
  z-index: 888;
  width: 300px;
}
.left-content{
  position: absolute;
  top: 50px;
  left: 20px;
  z-index: 888;
  width: 400px;
}
.right-content {
  position: absolute;
  top: 20px;
  right: 20px;
  z-index: 888;
  width: 400px;
}
.mid{
  font-size: 25px;
  padding: 10px 10px;
}
.highlight {
  font-size: 45px;
  font-weight: 600;
  padding: 20px 0px 0px 0px;
}
#selectGu{
  padding: 10px 6px 10px 6px;
  background-color: #454D5E;
  border-radius: 5px;
  border: 1px solid white;
  width: 150px;
  font-size: 16px;
}
.seoulCrime{
  position: absolute;
  padding: 30px 6px 50px 6px;
  bottom: 20px;
  right: 10px;
  z-index: 888;
  width: 300px;
}
.type5 {
  display: inline-block;
  border-radius: 16px;
  padding: 12px 70px;
  font-size: 18px;
  margin-top: 20px;
}
.modal_wrap{
        display: none;
        width: 500px;
        height: 500px;
        position: absolute;
        top:50%;
        left: 50%;
        margin: -250px 0 0 -250px;
        background:#eee;
        z-index: 2;
    }
    .black_bg{
        display: none;
        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        background-color:rgba(0, 0,0, 0.5);
        top:0;
        left: 0;
        z-index: 1;
    }
    .modal_close{
        width: 26px;
        height: 26px;
        position: absolute;
        top: -30px;
        right: 0;
    }
    .modal_close> a{
        display: block;
        width: 100%;
        height: 100%;
        background:url(https://img.icons8.com/metro/26/000000/close-window.png);
        text-indent: -9999px;
    }
</style>
