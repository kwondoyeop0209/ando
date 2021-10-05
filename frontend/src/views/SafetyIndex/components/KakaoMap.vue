<template>
  <div id="map"></div>
</template>
<script>
import $axios from "axios";
export default {
  name: "KakaoMap",
  props: {
    space: String,
    isSpace: Boolean,
    dongId: Number,
  },
  data() {
    return {
      container: undefined,
      options: undefined,
      map: undefined,
      overlayList: [],
      polygon: undefined,
    }
  },
  watch: {
    space: function (val) {
      this.removePolygon();
      this.getSpaceList(val);
    },
    isSpace: function (val) {
      if (!val) {
        this.removePolygon();
        this.getSpaceList("cctv");
        this.removeCustom();
      }
    },
    dongId: function (val) {
      if (val === -1) {
        this.removePolygon();
        this.getSpaceList("cctv");
      } else {
        this.removeCustom();
        this.overlayPolygon(val);
      }
    },
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
      this.container = document.querySelector("#map");
      this.options = {
        center: new kakao.maps.LatLng(37.532612, 126.990182),
        level: 7,
      };
      this.map = new kakao.maps.Map(this.container, this.options);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      this.mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(this.mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      this.zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(this.zoomControl, kakao.maps.ControlPosition.RIGHT);
      //여기까지가 기본 지도 세팅 완료

      if(!this.isSpace) { //안전지수 탭이면
        //행정동 마커 찍기
        this.getSpaceList("cctv");
      }
    },
    getSpaceList(val) {
      $axios
        .get("/space?type=" + val)
        .then((response) => {
          this.overlayCustom(response.data.list);
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    overlayCustom(data) {
      this.container = document.querySelector("#map");
      this.options = {
        center: new kakao.maps.LatLng(37.532612, 126.990182),
        level: 7,
      };
      this.map = new kakao.maps.Map(this.container, this.options);
      this.removeCustom();
      data.forEach((item) => {
        const content = document.createElement("div");
        content.style = "background-color:#6A7DAF; border: 1px solid #454D5E; border-radius: 16px; padding: 8px; font-size: 14px";
        content.onclick = () => {
          this.$emit("selectDong", item.dongname);
          this.$emit("selectDongId", item.dongId);

          this.removeCustom();
          //행정동 다각형 그리기
          this.overlayPolygon(item.dongId);
        };

        if (this.isSpace) {
          const span1 = document.createElement("span");
          span1.style = "background-color: #ADADAD; border-radius: 16px; padding: 2px 8px; margin-right: 4px";
          span1.innerText = item.count;
          content.appendChild(span1);
        }

        const span2 = document.createElement("span");
        span2.innerText = item.dongname;

        content.appendChild(span2);

        const position = new kakao.maps.LatLng(item.lat, item.lng);

        const overlay = new kakao.maps.CustomOverlay({
          map: this.map,
          clickable: true,
          position: position,
          content: content,
          yAnchor: 1,
        });
        this.overlayList.push(overlay);
      });
    },
    removeCustom() {
      this.overlayList.forEach((item) => {
        item.setMap(null);
      });
    },
    overlayPolygon(val) {
      $axios
        .get("/main/polygon/" + val)
        .then((response) => {
          this.options = {
            center: new kakao.maps.LatLng(response.data.dongLatLng.lat, response.data.dongLatLng.lng),
            level: 4,
          };
          this.map = new kakao.maps.Map(this.container, this.options);
          let polygonPath = [];

          response.data.polygonList.forEach((item) => {
            let tmp = item.split(",");
            tmp[0] = tmp[0].replace("[", "");
            tmp[1] = tmp[1].replace("]", "");

            polygonPath.push(new kakao.maps.LatLng(tmp[1], tmp[0]));
          });

          this.polygon = new kakao.maps.Polygon({
            path: polygonPath,
            strokeWeight: 3,
            strokeColor: '#454D5E',
            strokeOpacity: 0.8,
            strokeStyle: 'longdash', 
            fillColor: '#454D5E',
            fillOpacity: 0.3
          });
          this.polygon.setMap(this.map);
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    removePolygon() {
      this.polygon.setMap(null);
      this.$emit("selectDongId", -1);
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
