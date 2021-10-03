<template>
  <div class="crime">
    <div class="main">
      <img :src="require(`@/assets/${selectGu}.png`)" class="map" />
      <div class="main-content">
        <div style="width: 1500px; margin: 0 auto">
          <!-- 검색 -->
          <div>
            검색 :
            <select class="select" @change="changeGu" v-model="selectGu">
              <option selected value="전체">전체</option>
              <option v-for="gu in guList" :key="gu.id">
                {{ gu.gu }}
              </option>
            </select>
            <select class="select" v-show="isGu" v-model="selectYear">
              <option value="2020">2020년</option>
              <option value="2019">2019년</option>
              <option value="2018">2018년</option>
            </select>
          </div>
          <!-- 전체 범죄 현황 차트 및 내용 -->
          <total-crime-modal v-show="isMain" />
          <!-- 구 선택시 모달 창-->
          <gu-crime-modal
            v-show="isGu"
            :gu="selectGu"
            :year="selectYear"
            @initYear="initYear"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script src="../../node_modules/vue-svg-gauge/dist/vue-svg-gauge.min.js"></script>
<script>
import TotalCrimeModal from "./components/TotalCrimeModal.vue";
import GuCrimeModal from "./components/GuCrimeModal.vue";
import $axios from "axios";

export default {
  name: "Crime",
  components: {
    TotalCrimeModal,
    GuCrimeModal,
  },
  data() {
    return {
      guList: [],
      selectGu: "전체",
      isMain: true,
      isGu: false,
      selectYear: "2020",
    };
  },
  created(){
    $axios
      .get("/main/sigungu")
      .then((response) =>{
        this.guList=response.data.guList;
      })
      .catch(() => {
        console.log("오류가 발생했습니다.");
      });
  },
  methods: {
    changeGu() {
      const guSelect = this.selectGu;
      if (guSelect === "전체") {
        this.isMain = true;
        this.isGu = false;
      } else {
        this.isMain = false;
        this.isGu = true;
      }
    },
    initYear() {
      this.selectYear = "2020";
    },
  },
};
</script>
<style scoped>
.crime {
  width: 100%;
  height: 1000px;
  background-color: #454d5e;
}
.main {
  min-width: 1500px;
  height: 100%;
  position: relative;
  background-color: #454d5e;
}
.map {
  padding-top: 90px;
  width: 100%;
}
.main-content {
  position: absolute;
  top: 52px;
  left: 0;
  z-index: 888;
  width: 100%;
  height: calc(100% - 52px);
  margin: 0 auto;
}
.select {
  background-color: #454d5e;
  border-radius: 5px;
  border: 1px solid white;
  font-size: 16px;
  margin-left: 8px;
  padding: 8px 16px;
}
</style>
