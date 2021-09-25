<template>
  <div class="safety-index">
    <!-- left -->
    <div class="info">
      안전지수
    </div>
    <!-- right -->
    <div class="map">
      지도
    </div>
  </div>
</template>
<script>
export default {
  name: "SafetyIndex",
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
</style>
