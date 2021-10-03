<template>
  <div class="safety-index">
    <div class="info">
      <safety-detail v-show="isSafety" />
      <space-detail v-show="isSpace" />
    </div>
    <div class="map">
      <!-- 탭-->
      <div class="tab">
        <div class="tab-top">
          <p class="tab-items" @click="onSafety">안전 지수</p>
          <p style="margin: 0 8px">|</p>
          <p class="tab-items" @click="onSpace">환경 요소</p>
        </div>
        <div class="tab-bottom" v-show="isSpace">
          <p class="tab-items" @click="onCctv" :isCctv="cctv"  >📹 CCTV</p>
          <p class="tab-items">🍺 유흥지</p>
          <p class="tab-items">🚨 파출소</p>
          <p class="tab-items">💡 보안등</p>
          <p class="tab-items">🏠 지킴이집</p>
        </div>
      </div>
      <kakao-map />
    </div>
  </div>
</template>

<script>
import KakaoMap from "./components/KakaoMap.vue";
import SafetyDetail from "./components/SafetyDetail.vue";
import SpaceDetail from "./components/SpaceDetail.vue";
export default {
  name: "SafetyIndex",
  components: {
    KakaoMap,
    SafetyDetail,
    SpaceDetail,
  },
  
  data() {
    return {
      isSafety: true,
      isSpace: false,
      isCctv: false,
    };
  },
  methods: {
    onSpace() {
      this.isSpace = true;
      this.isSafety = false;
    },
    onSafety() {
      this.isSpace = false;
      this.isSafety = true;
    },
    onCctv() {
      this.isCctv = true;
      console.log("부모에서 보내는거")
    }

  },
};
</script>

<style scoped>
.safety-index {
  height: 950px;
  display: flex;
}
.info {
  width: 400px;
  background-color: #454d5e;
  padding: 18px;
}
.map {
  flex-grow: 1;
  position: relative;
  background-color: #dedede;
}
.tab {
  position: absolute;
  top: 0;
  left: 0;
  z-index: 999;
  display: flex;
  margin-top: 16px;
}
.tab-top {
  display: flex;
  background-color: #454d5e;
  margin-left: 16px;
  border-radius: 8px;
  padding: 8px 24px;
  align-items: center;
}
.tab-items {
  margin: 0 8px;
}
.tab-items:hover {
  cursor: pointer;
}
.tab-bottom {
  display: flex;
  background-color: #454d5e;
  margin-left: 16px;
  border-radius: 8px;
  padding: 0 24px;
  align-items: center;
}
</style>
