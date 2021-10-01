<template>
  <div class="footer">
    <div class="blank">
      <div style="flex: 1; display: flex; justify-content: center">
        <div>
          <p>빅데이터 분석 트랙 - 서울 3반 5조</p>
          <p>팀원 | 권도엽 박형민 박채린 양혜선 정은진</p>
          <p>© SSAFY. All rights reserved.</p>
        </div>
      </div>
      <div style="flex: 1"></div>
    </div>
  </div>
</template>
<script>
export default {
  name: "HomeFooter",
};
</script>
<style scoped>
.footer {
  height: 200px;
  background-color: #59606e;
  color: white;
  display: flex;
  align-items: center;
}
.blank {
  min-width: 1450px;
  width: 1450px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
p {
  padding: 6px 0;
}
</style>
