<template>
  <div class="space-detail">
    <!-- 구 동 선택하는 공간 -->
    <div class="select-region">
      <select class="select" @change="changeGu" v-model="selectGu">
              <option selected value="자치구">자치구</option>
              <option v-for="(gu, idx) in GuList" :key="idx" :value="gu">
                {{ gu.gu }}
              </option>
            </select>&nbsp;

       <select class="select" v-show="isGu" v-model="selectDong">
         <option selected value="행정동">행정동</option>
              <option v-for="(dong, idx) in DongList" :key="idx" :value="dong">
                {{ dong }}
              </option>
       </select>
       <br /><br />

       <!-- 갯수 보여주는 공간 -->
       <div class = "space-info" v-show="isSpace">
      <p style="margin-bottom:10px; font-size:30px; font-weight: 600">{{selectDong}}<span style="font-size: 20px">의</span>
      {{space}} 비율 <br> </p>
      <p style="margin-bottom:10px;">{{selectGu.gu}} 내
        <span style="font-size:20px; font-weight: 400"> 총 {{this.spaceData.guCnt}}개 중
          <br> {{this.spaceData.dongCnt}}개 </span> 설치가 되어 있어요!
      </p>

      <!-- 비율 보여주는 그래프 -->
      <div class="doughnut">
              <VueSvgGauge
                class="mini-gauge"
                :start-angle="0"
                :end-angle="360"
                :min="0"
                :max="100"
                :value="(this.spaceData.dongCnt / this.spaceData.guCnt)*100"
                :separator-step="0"
                :scale-interval="0"
                :inner-radius="85"
                :gauge-color="[{ offset: 0, color: '#2F488A'}]"
                base-Color="#EEEEEE"
              >
                <div class="inner-text" style="padding:45%">
                  <span >{{Math.round((this.spaceData.dongCnt / this.spaceData.guCnt)*100,2)}}%</span>
                </div>
              </VueSvgGauge>
            </div> <br>
        
       <div>
        <p style="margin-bottom:10px; font-size:20px; font-weight: 400"> {{selectGu.gu}} 내에서 {{this.rankData.ranking}}위를 차지했어요! <br> </p>
      </div>
      
      <!-- 5개 순위 보여주는 그래프(값 수정 필요) -->
      <div class="rank-info">
       <highcharts
              :options="highestSpot"
              :highcharts="Highcharts"
              ref="Highcharts"
              style="height: 200px"
            ></highcharts>

            <p style="padding-left: 30%"> &lt; 주변 환경 요소 Top5&gt; </p><br><br>
      </div>

      <!-- 상관관계 보여주는 공간 (수정 필요) -->
      <div class="graph-info">
      <p style="margin-bottom:10px; font-size:30px; font-weight: 600"> {{space}}와 범죄의 상관 관계 <br> </p>
      
      </div>


      
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


Variablepie(Highcharts);
Highcharts3D(Highcharts);


export default {
  name: "SpaceDetail",
  props: {
    space: String,
    isSpace: Boolean
  },
  components: {
    highcharts: Chart,
    
    VueSvgGauge,
  },

  data() {
    return {
      Highcharts,
      selectGu: "자치구",
      selectGuID : "",
      selectDong: "행정동",
      selectDongID: "",
      isMain: true,
      isGu: false,
      isDong: false,
      GuList: [],
      DongList: [],
      spaceData: [],
      rankData: [],
      graphData: [],
      selectSpaceName:"",

      highestSpot: {
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
    
    }
  },

  mounted() {
    // 검색을 위해 구 목록 리턴
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/main/sigungu")
      .then(respon => {
        this.guList = respon.data.guList
        for(const idx in this.guList) {
          const guName = this.guList[idx];
          this.GuList.push(guName)
        }
      })
      .catch(e => {
          console.log('error : ', e)
          })
  },

  methods: {
    changeGu() {
       const guSelect = this.selectGu;
      if (guSelect == "자치구") {
        this.isMain = true;
        this.isGu = false;
      } else {
        this.isMain = false;
        this.isGu = true;
      }

      //선택한 구랑 구 아이디 저장
      for (let i=0; i<this.GuList.length; i++) {
        if(this.GuList[i].gu == guSelect.gu) {
          const GuID =  this.GuList[i].id;
          this.selectGuID = GuID;
          //console.log(this.selectGuID)
        }
      }
      
    },

    getSpaceList(val) {
      //동 아이디도 저장!
        const dongSelect = this.selectDong;
        console.log(this.dongList)
        console.log(dongSelect)
        for (let i=0; i<this.dongList.length; i++) {
        if(this.dongList[i].dong == dongSelect) {
          const DongID =  this.dongList[i].id;
          this.selectDongID = DongID;
          console.log(this.selectDongID)
      }
        }

      //환경 지수의 갯수 구하는 부분(구별, 동별) 
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/space/count?id=" + this.selectDongID + "&type=" + val)
      .then(re => {
        this.spaceData = re.data
        console.log(this.spaceData)
        
      })
      .catch(e => {
          console.log('error : ', e)
      })
    

    // 해당 동 space 개수 순위 주변 5개 개수정보
    
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/space/ranking?id=" + this.selectDongID + "&type=" + val)
      .then(r => {
        //x축 y축 초기화를 시켜줘야 그래프가 계속 5개씩 나옴!
        this.highestSpot.series[0].data = []
        this.highestSpot.xAxis.categories = []
        this.rankData = r.data
        console.log(this.rankData)
        const fiveDong = this.rankData.list
        for(var i=0; i<fiveDong.length; i++) {
          this.highestSpot.xAxis.categories.push(fiveDong[i].dongname);
          this.highestSpot.series[0].data.push(fiveDong[i].count);

        //특정 값에 대한 색상 지정이 잘 안돼..
          if(this.highestSpot.xAxis.categories[i] == this.selectDong) {
            this.highestSpot.chart.colors = "rgba(255,0,0,0.2)"
          }

        }
        //console.log(this.rankData)
        //console.log(this.highestSpot.xAxis.categories)
       

      })
      .catch(e => {
          console.log('error : ', e)
      })

      
    
    
    //space 상관관계 정보
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/space/graph?type=" + this.selectDongID)
      .then(rs => {
        this.graphData = rs.data
        console.log(this.graphData)
      })
      .catch(e => {
          console.log('error : ', e)
          })

        
    },

  },

  watch: {
      selectGuID: function (val) {
        
        // 해당 구의 행정동 리스트 가져오기
      this.DongList = []
      
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/main/dong/" + val)
      .then(respond => {
        this.dongList = respond.data.getDongListDtoList
        //console.log(this.dongList)
        for(const idx in this.dongList) {
          const dongName = this.dongList[idx].dong;
          this.DongList.push(dongName)
        }
        //console.log(this.DongList)
        

        }
      )

      .catch(e => {
          console.log('error : ', e)
          })
      },

    space: function (val) {
      this.getSpaceList(val);
      console.log(val)
      this.isSpace = true;
      this.selectSpaceName = val;
      
    },

    isSpace: function (val) {
      //console.log(val)
      if (!val) {
        this.getSpaceList("cctv");
      }
    },

    

  }
};

</script>
<style scoped>
.space-detail {
  display: flex;
}

.select {
  padding: 10px 6px 10px 6px;
  background-color: #454d5e;
  border-radius: 5px;
  border: 1px solid white;
  width: 150px;
  font-size: 16px;
}
.doughnut {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 36px;
}

.mini-gauge {
  max-width: 50%;
}




</style>
