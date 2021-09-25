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
          3,260,000건
          <br/>
          <span style="font-size: 36px">데이터 분석</span>
        </p>
      </div>
    </div>
    <!-- 홈 구별 화면 UI -->
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
.main {
  min-width: 1200px;
  position: relative;
  padding: 72px 0;
}
.main-bg {
  width: 100%;
}
.main-content {
  position: absolute;
  top: 180px;
  left: 140px;
  z-index: 888;
}
.main-title {
  font-size: 42px;
}
.highlight {
  font-size: 56px;
  font-weight: 600;
}
.main-subtitle {
  font-size: 18px;
  margin-top: 68px;
}
.btn-go {
  display: inline-block;
  border-radius: 16px;
  padding: 12px 56px;
  background-color: #6a7daf;
  font-size: 18px;
  margin-top: 56px;
}
.data-cnt {
  background-color: #59606e;
  padding: 240px 0;
}
.data-content {
  width: 1200px;
  margin: 0 auto;
}
.cnt {
  text-align: center;
  font-size: 48px;
}
</style>
