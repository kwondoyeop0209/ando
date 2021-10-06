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
      markerList: [],
    };
  },
  watch: {
    space: function (val) {
      if (this.polygon != undefined) {
        this.removePolygon();
      }
      this.removeMarker();
      this.getSpaceList(val);
    },
    isSpace: function (val) {
      if (!val) {
        if (this.polygon != undefined) {
          this.removePolygon();
        }
        this.removeMarker();
        this.getSpaceList("cctv");
        this.removeCustom();
      }
    },
    dongId: async function (val) {
      if (val === -1) {
        if (this.polygon != undefined) {
          this.removePolygon();
        }
        if (this.isSpace) {
          this.getSpaceList(this.space);
        } else {
          this.getSpaceList("cctv");
        }
      } else {
        this.removeCustom();
        const d = await this.overlayPolygon(val);
        console.log(d);
        if (this.isSpace) {
          const d2 = await this.overlayMarker(val);
          console.log(d2);
        }
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
        "https://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=23763f349d4c914839b08d29272dfb2d";
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

      if (!this.isSpace) { //안전지수 탭이면
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
        content.style = "background-color:#6A7DAF; border: 1px solid #454D5E; border-radius: 20px; padding: 8px 6px; font-size: 14px; display: flex; align-items: center";
        content.onclick = async () => {
          this.$emit("selectDong", item.dongname);
          this.$emit("selectDongId", item.dongId);

          this.removeCustom();
          //행정동 다각형 그리기
          // await this.overlayPolygon(item.dongId);
          // if (this.isSpace) {
          //   await this.overlayMarker(item.dongId);
          // }
        };

        if (this.isSpace) {
          const span1 = document.createElement("p");
          span1.style = "background-color: #ADADAD; border-radius: 16px; padding: 2px 8px; margin-right: 4px";
          let ic = "";
          if (this.space === "cctv") ic = "📹";
          else if (this.space === "bar") ic = "🍺";
          else if (this.space === "police") ic = "🚨";
          else if (this.space === "light") ic = "💡";
          else ic = "🏠";
          span1.innerText = ic + " " + item.count;
          content.appendChild(span1);
        }

        const span2 = document.createElement("p");
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
          console.log("polygon");
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    removePolygon() {
      this.polygon.setMap(null);
      this.$emit("selectDongId", -1);
    },
    overlayMarker(val) {
      $axios
        .get("/space/detail", {
          params: {
            id: val,
            type: this.space,
          },
        })
        .then((response) => {
          this.removeMarker();
          const imageSrc = "https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/ando/ic-"+ this.space +".png";
          const imageSize = new kakao.maps.Size(30, 30);
          const imageOption = { offset: new kakao.maps.Point(27, 69) };

          const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

          response.data.forEach((item) => {
            const markerPosition = new kakao.maps.LatLng(item.lat, item.lng);
            const marker = new kakao.maps.Marker({
              position: markerPosition,
              image: markerImage,
            });
            this.markerList.push(marker);
            marker.setMap(this.map);
          });
          console.log("marker");
        })
        .catch(() => {
          console.log("오류가 발생했습니다.");
        });
    },
    removeMarker() {
      this.markerList.forEach((item) => {
        item.setMap(null);
      });
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
