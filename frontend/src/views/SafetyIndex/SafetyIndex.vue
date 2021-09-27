<template>
  <div class="safety-index">
    <!-- left -->
    <div class="info">
      안전지수
    </div>
    <!-- right -->
     <div id="map"></div>
    <div class="button-group">
      <button @click="changeSize(800)" style="color:black;">안전 지수</button>
      <button @click="changeSize(800)" style="color:black;">환경 지수</button>
      <button @click="displayMarker(markerPositions1)" style="color:black;">마카 보이기</button>
      <button @click="displayInfoWindow" style="color:black;">infowindow띄우기</button>
    </div>
  </div>
</template>
<script>
export default {
  name: "SafetyIndex",
  data() {
    return {
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
      this.initMap();
    } else {
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
      const container = document.getElementById("map"); //지도 표시할 div
      const options = { //지도 중심좌표
        center: new kakao.maps.LatLng(37.517353,127.037164),
        level: 3,
      };
      //지도 생성
      this.map = new kakao.maps.Map(container, options);

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

      this.polygonPath = [
          new kakao.maps.LatLng(37.52381, 127.0265),
          new kakao.maps.LatLng(37.517353,127.037164),
          new kakao.maps.LatLng(37.524399,127.050457),
          new kakao.maps.LatLng(37.511399,127.046255),
          new kakao.maps.LatLng(37.49604,127.04679) 
      ];

      // 지도에 표시할 다각형을 생성합니다
      this.polygon = new kakao.maps.Polygon({
          path:this.polygonPath, // 그려질 다각형의 좌표 배열입니다
          strokeWeight: 3, // 선의 두께입니다
          strokeColor: '#39DE2A', // 선의 색깔입니다
          strokeOpacity: 0.8, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
          strokeStyle: 'longdash', // 선의 스타일입니다
          fillColor: '#A2FF99', // 채우기 색깔입니다
          fillOpacity: 0.7 // 채우기 불투명도 입니다
      });

      // 지도에 다각형을 표시합니다
      this.polygon.setMap(this.map);
      
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size*2}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px; color:black;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
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
  background-color: gray;
  flex-grow: 1;
}
.button-group {
  margin: 5px 0px;
  background-color: gray;
  color: blue;
}


</style>
