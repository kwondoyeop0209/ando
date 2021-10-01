<template>
  <div id="map"></div>
</template>
<script>
export default {
  name: "KakaoMap",
  data() {
    return {
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
    }
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=f4b6845e3f93731cc1cbedd752449bd5";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.querySelector("#map");
      const options = {
        center: new kakao.maps.LatLng(37.517353, 127.037164),
        level: 3,
      }
      const map = new kakao.maps.Map(container, options);
      const markerPosition = new kakao.maps.LatLng(37.517353, 127.037164);

      const marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(map);
      
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
      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        const imageSize = new kakao.maps.Size(24, 35);
        // 마커 이미지를 생성합니다
        const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        
        // 마커를 생성합니다
        this.marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
      }

      // 여기는 오버레이랑 기타 컨트롤러 등을 세팅하는 부분!!

      // 커스텀 오버레이에 표시할 내용입니다
      // HTML 문자열 또는 Dom Element 입니다
      const content = '<div class ="label"><span class="left"></span><span class="center" style="color:black;">안전!</span><span class="right"></span></div>';

      // 커스텀 오버레이가 표시될 위치입니다 
      const position = new kakao.maps.LatLng(37.517353, 127.037164);

      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
        position: position,
        content: content,
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
  },
};
</script>
<style scoped>
#map {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
