<template>
  <div class="space-element">
    <!-- left -->
    <div class="info">
      <div>
          <p>여기는 환경요소페이지</p>
        <button @click="changeSize(800)" style="background:#6a7daf; width:100px; font-size:20px; border-radius: 10%; float: right;">환경 지수</button>
        <button @click="goSafetyPage()" style="background:#6a7daf; width:100px; font-size:20px; border-radius: 10%; float: right;">안전 지수</button>
      </div>
      <br><br>
          <div>
            <highcharts :options="Score"></highcharts>
          </div>
    </div>
         
    <!-- right 지도가 원래 이러면 바로 나와야하는데 왜 안나오냐 대체-->
     <div id="map"></div>
      <div class="map">
    </div>
  </div>
</template>

<script>
import {Chart} from "highcharts-vue";
import 'vue-good-table/dist/vue-good-table.css'

export default {
  name: "SpaceElement",
  components: {
    highcharts: Chart
  },
  data() {
    return {
      isShow: true,
      isClick: false,
      dong: "",
      Score: {
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
          categories: ["갯수"],
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
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      console.log("여기는 mounted속 if문 돌고있는거임")
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
      this.isClick = true;
      const container = document.getElementById("map");
      container.style.width = `${size*2}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    goSafetyPage() {
        this.$router.push({
        name: "SafetyIndex"
      })

    }

  },
};
</script>



<style scoped>
.space-element {
  min-width: 1200px;
  width: 100%;
  display: flex;
  
}
.info {
  background-color: #454d5e;
  flex: 0 0 auto;
  width: 400px;
  font-size: 25px;
  padding: 20px 20px;
  overflow: hidden;
  text-align: center;
  vertical-align: middle;
  background: #454d5e;
}

.map {
  background-color: #454d5e;
  height: 100%;
  flex-grow: 1;
}

</style>
