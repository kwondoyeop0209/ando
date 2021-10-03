<template>
  <div class="space-detail">
    <div>
      <select class="select" @change="changeGu" v-model="selectGu">
              <option selected value="자치구">자치구</option>
              <option v-for="(gu, idx) in GuList" :key="idx" :value="gu">
                {{ gu }}
              </option>
            </select>&nbsp;  
    </div>
       <div > CCTV나오는거(아직 이쪽부분 수정중) {{cctv}} </div>
  </div>
  
</template>
<script>

import $axios from "axios";
export default {
  name: "SpaceDetail",
  props: {
    cctv: Boolean,
    dance: Boolean,
    police: Boolean,
    boan: Boolean,
    house: Boolean
  },
  data() {
    return {
      selectGu: "자치구",
      selectDong: "행정동",
      isMain: true,
      isGu: false,
      isDong: false,
      GuList: [],
      DongList: [],
    
    }
  },

  methods: {
    changeGu() {
       $axios
      .get("/sigungu")
      .then(res => {
        this.guName = res.data;
        this.GuList.push(this.guName);
        console.log(this.GuList);
      })
       .catch(e => {
        console.log('error : ', e)
      })


    },

  },

  watch: {
      cctv: function (val) {
      console.log(val);
    },
      dance: function (val) {
      console.log(val);
    },
      police: function (val) {
      console.log(val);
    },
      boan: function (val) {
      console.log(val);
    },
      house: function (val) {
      console.log(val);
    },
  }
};

</script>
<style scoped>
.space-detail {
  display: flex;
}

.select {
  padding: 10px 6px 10px 6px;
  background-color: #454d5e;
  border-radius: 5px;
  border: 1px solid white;
  width: 150px;
  font-size: 16px;
}



</style>
