<template>
  <div class="space-detail">
    <select v-model="gu">
      <option>자치구</option>
    </select>
    <select v-model="dong">
      <option>행정동</option>
    </select>
  </div>
</template>
<script>
export default {
  name: "SpaceDetail",
  data() {
    return {
      gu: "자치구",
      dong: "행정동",
    }
  },
};
</script>
<style scoped>
.space-detail {
  display: flex;
}
</style>
