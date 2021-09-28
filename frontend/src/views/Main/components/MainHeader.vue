<template>
  <div class="header">
    <div>
      <router-link to="/" class="deco-none">안도</router-link>
    </div>
    <div style="flex: 1"></div>
    <div style="margin-right: 36px">
      <router-link to="/crime" class="deco-none">
        구역별 범죄 현황
      </router-link>
    </div>
    <div>
      <router-link to="/safety" class="deco-none">
        우리 동네 안전 지수
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "MainHeader",
};
</script>

<style scoped>
.header {
  display: flex;
  align-items: center;
  height: 59px;
  border-bottom: 1px solid #6d6d6d;
  font-size: 18px;
  color: white;
  padding-left: 24px;
  padding-right: 24px;
}
</style>
