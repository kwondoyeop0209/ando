<template>
  <div class="home">
    <!-- 홈 메인 -->
    <div class="main">
      <img src="@/assets/main-bg.png" class="main-bg" />
      <div class="main-content">
        <p class="main-title">
          우리 동네가 <span class="light-red highlight">범죄</span>로부터
          <br />안전한지 궁금하신가요?
        </p>
        <p class="main-subtitle">
          범죄 현황 데이터와 환경 요소를 통해<br />
          다양한 분석 결과를 확인해보세요 :)
        </p>
        <router-link to="/safety" class="deco-none btn-go">
          <p>지금 보러 가기</p>
        </router-link>
      </div>
    </div>
    <!-- 홈 데이터 건수 -->
    <div class="data-cnt">
      <div class="data-content">
        <p class="cnt">
          <span class="white-blue" style="font-size: 48px; font-weight: 700">
            8,271,409건
          </span>
          의<br/>데이터 분석
        </p>
      </div>
    </div>
    <!-- 홈 구별 화면 UI -->
    <div>
      <div class="blank intro">
        <div class="el">
          <div>
            <div style="margin-left: 46px">
              <p class="explain-title white-blue">구별 범죄 현황 및 범죄 예측 분석</p>
              <p class="explain-content" style="margin-bottom: 100px;">서울시 자치구별 범죄 현황을 시각화하고<br/>범죄 데이터를 분석해<br/>시간, 장소, 유형에 따른 범죄를 예측합니다</p>
            </div>
            <img src="@/assets/intro2.png" :width="500"/>
          </div>
        </div>
        <div class="el">
          <div>
            <img src="@/assets/intro1.png" :width="500"/>
          </div>
        </div>
      </div>
    </div>
    <!-- 홈 안전지수 화면 UI -->
    <div class="intro-safety">
      <div class="blank intro">
        <div class="el">
          <div>
            <p class="explain-title white-blue">우리 동네 안전 지수</p>
            <p class="explain-content">각 행정동별로 환경 요소를 분석해<br/>범죄 안전 지대를 도출하고<br/>행정동내의 각 환경 요소의 분포 순위를 제공합니다</p>
          </div>
        </div>
        <div class="el">
          <div>
            <img src="@/assets/intro3.png" :width="550"/>
          </div>
        </div>
      </div>
    </div>
    <!-- 홈 환경요소 화면 UI -->
    <div>
      <div class="blank intro">
        <div class="el">
          <div>
            <p class="explain-title white-blue">우리 동네 각 환경요소 현황</p>
            <p class="explain-content">서울시 자치구별 범죄 현황을 시각화하고<br/>범죄 데이터를 분석해<br/>시간, 장소, 유형에 따른 범죄를 예측합니다</p>
          </div>
        </div>
        <div class="el">
          <img src="@/assets/intro4.png" :width="580"/>
        </div>
      </div>
    </div>
    <home-footer />
  </div>
</template>

<script>
import HomeFooter from "./components/HomeFooter.vue";
export default {
  name: "Home",
  components: {
    HomeFooter,
  },
};
</script>

<style scoped>
.home {
  background-color: #454d5e;
  min-width: 1450px;
}
.main {
  width: 1450px;
  margin: 0 auto;
  position: relative;
  padding: 72px 0;
}
.main-bg {
  width: 100%;
}
.main-content {
  position: absolute;
  top: 180px;
  left: 120px;
  z-index: 888;
}
.main-title {
  font-size: 36px;
}
.highlight {
  font-size: 56px;
  font-weight: 600;
}
.main-subtitle {
  font-size: 16px;
  margin-top: 68px;
}
.btn-go {
  display: inline-block;
  border-radius: 16px;
  padding: 12px 56px;
  background-color: #6a7daf;
  font-size: 16px;
  margin-top: 56px;
}
.blank {
  width: 1450px;
  margin: 0 auto;
}
.data-cnt {
  background-color: #59606e;
  padding: 200px 0;
}
.data-content {
  width: 1200px;
  margin: 0 auto;
}
.cnt {
  text-align: center;
  font-size: 36px;
}
.intro {
  padding: 200px 0;
  display: flex;
}
.explain-title {
  font-size: 28px;
  font-weight: 700;
  margin-top: 44px;
  margin-bottom: 24px;
}
.explain-content {
  font-size: 16px;
  margin-bottom: 32px;
}
.intro-safety {
  background-color: #59606e;
}
.el {
  flex: 1;
  display: flex;
  justify-content: center;
}
</style>
