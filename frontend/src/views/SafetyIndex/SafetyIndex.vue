<template>
  <div class="safety-index">
    <!-- left -->
    <div class="info">
      안전지수
        <vue-good-table :columns="columns" :rows="rows" @on-row-click="onRowClick" theme="polar-bear" v-if="isShow"></vue-good-table>

        <div v-else>
          {{dong}}
          <div>
            <highcharts :options="safetyScore"></highcharts>
          </div>
          <div>
            동작구 내에서 3위 <br>
            전체에서 48위
          </div>
          <div>
            <p> CCTV 보유 현황 3위 (밑에 1자 차트? 저건 좀 더 고민해볼것!) </p>
            <p> 유흥지 분포 4위 (차트 추가) </p>
            <p> 파출소 분포 2위 (차트 추가) </p>
            <p> 보안등 분포 3위 (차트 추가) </p>
          </div>

        </div>
    </div>

    <!-- right -->
     <div id="map"></div>
      <div class="map">
      <button @click="changeSize(800)" style="color:black;">안전 지수</button>
      <button @click="changeSize(800)" style="color:black;">환경 지수</button>
    </div>
  </div>
  
</template>

<script>
import { VueGoodTable } from 'vue-good-table';
import {Chart} from "highcharts-vue";

export default {
  name: "SafetyIndex",
  components: {
    VueGoodTable,
    highcharts: Chart
  },
  data() {
    return {
      isShow: true,
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
                y: 4.7,
                color: "#6A7DAF",
              },
            ],
          },
        ],
      },


      map: null,
      markerPositions1: [
        [37.499590490909185, 127.0263723554437],
        [37.499427948430814, 127.02794423197847],
        [37.498553760499505, 127.02882598822454],
        [37.497625593121384, 127.02935713582038],
        [37.49629291770947, 127.02587362608637],
        [37.49754540521486, 127.02546694890695],
        [37.49646391248451, 127.02675574250912],
      ],
      markers: [],
      infowindow: null,

       columns: [
        {
          label: '순위',
          field: 'rank',
          type: 'number',
          
        },
        {
          label: '행정동',
          field: 'name',
        },
        {
          label: '점수',
          field: 'score',
        },
        
      ],
      rows: [
        { id:1, rank: 1, name:"신대방동", score: "8.7점"},
        { id:2, rank: 2, name:"역삼동", score: "8.4점"},
        { id:3, rank: 3, name:"상도3동", score: "7.9점"},
        { id:4, rank: 4, name:"이촌동", score: "7.2점"},
        { id:5, rank: 5, name:"신길1동", score: "5.4점"},
        { id:6, rank: 6, name:"상도2동", score: "5.2점"},
        { id:7, rank: 7, name:"사당4동", score: "5.1점"},
        { id:8, rank: 8, name:"홍제2동", score: "4.8점"},
        { id:9, rank: 9, name:"미아동", score: "4.5점"},
        { id:10, rank: 10, name:"혜화동", score: "4.2점"},
      ],


    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      console.log("여기는 mounted속 if문 돌고있는거임")
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = { //지도 중심좌표
        center: new kakao.maps.LatLng(37.517353,127.037164),
        level: 3,
      };
      //지도 생성
      this.map = new kakao.maps.Map(container, options);

      this.initMap();


    } else {
      console.log("여기는 mounted속 if문 안돌때임")
      const script = document.createElement("script");
      script.type = 'text/javascript'
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f4b6845e3f93731cc1cbedd752449bd5";
      document.head.appendChild(script);
    }
  },
  
  methods: {
    initMap() {
      const container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      const options = { //지도 중심좌표
        center: new kakao.maps.LatLng(37.517353,127.037164),
        level: 3,
      };
      //지도 생성
      this.map = new kakao.maps.Map(container, options);

      // 여기는 마커를 세팅하는 부분!! (이제 실제로 데이터들 받게되면 좌표들을 리스트로 쫙 풀고 한번에 보여주면 됨!)
      // 마커를 표시할 위치와 title 객체 배열입니다 
      const positions = [
          {
              title: '여긴어디지', 
              latlng: new kakao.maps.LatLng(37.517353,127.037164)
          },
          {
              title: '호잇', 
              latlng: new kakao.maps.LatLng(37.499590490909185, 127.0263723554437)
          },
          {
              title: '공원', 
              latlng: new kakao.maps.LatLng(37.499427948430814, 127.02794423197847)
          },
          {
              title: '뭐야 이건어디일까나',
              latlng: new kakao.maps.LatLng(37.498553760499505, 127.02882598822454)
          }
      ];

      // 마커 이미지의 이미지 주소입니다
      const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
          
      for (var i = 0; i < positions.length; i ++) {
          
          // 마커 이미지의 이미지 크기 입니다
          const imageSize = new kakao.maps.Size(24, 35); 
          
          // 마커 이미지를 생성합니다    
          const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 
          
          // 마커를 생성합니다
          this.marker = new kakao.maps.Marker({
              map: this.map, // 마커를 표시할 지도
              position: positions[i].latlng, // 마커를 표시할 위치
              title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              image : markerImage // 마커 이미지 
          });
      }


      // 여기는 오버레이랑 기타 컨트롤러 등을 세팅하는 부분!!

      // 커스텀 오버레이에 표시할 내용입니다     
      // HTML 문자열 또는 Dom Element 입니다 
      const content = '<div class ="label"><span class="left"></span><span class="center" style="color:black;">안전!</span><span class="right"></span></div>';

      // 커스텀 오버레이가 표시될 위치입니다 
      const position = new kakao.maps.LatLng(37.517353,127.037164);  

      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
          position: position,
          content: content   
      });

      // 커스텀 오버레이를 지도에 표시합니다
      customOverlay.setMap(this.map);


      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      this.mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(this.mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      this.zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(this.zoomControl, kakao.maps.ControlPosition.RIGHT);
      
      //여기까지가 기본 지도 세팅 완료
      
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size*2}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    onRowClick(params) {
      console.log(params.row.name);
      const dongName = params.row.name;
      this.dong = dongName;

      this.isShow = false;
    }


  },
};
</script>



<style scoped>
.safety-index {
  min-width: 1200px;
  width: 100%;
  display: flex;
}
.info {
  background-color: #454d5e;
  flex: 0 0 auto;
  width: 400px;
  overflow: hidden;
}
.map {
  background-color: #454d5e;
  height: 100%;
  flex-grow: 1;
}


.pop_table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0;
    border: none;
    border-bottom:5px solid #000;
    color:#000;
    margin: auto;
}
 
.pop_table caption{
    height: 60px;
    font-size: 1.2em;
    font-weight: bold;
    text-align: center;
    line-height: 52px;
    border-bottom: 5px solid #FFF;
    -webkit-border-radius: 8px 8px 0 0;
    -moz-border-radius: 8px 8px 0 0;
    border-radius: 8px 8px 0 0;
    background: #F94E5B;
}
 
.pop_table caption:before {
    content: '';
    display: block;
    height: 8px;
    -webkit-border-radius: 8px 8px 0 0;
    -moz-border-radius: 8px 8px 0 0;
    border-radius: 8px 8px 0 0;
    background-color: #000;
    
}
 
.pop_table th {
    padding: 15px;
    border: none;
    border-bottom: 2px solid #FFF;
    background: #507cda;
    font-weight: bold;
    text-align: center;
    vertical-align: middle;
}
 
.pop_table td {
    padding: 15px;
    border: none;
    border-bottom: 2px solid #000;
    text-align: center;
    vertical-align: baseline;
}
 
.pop_table tr:last-child th,
.pop_table tr:last-child td {
    border-bottom: none;
}
</style>
