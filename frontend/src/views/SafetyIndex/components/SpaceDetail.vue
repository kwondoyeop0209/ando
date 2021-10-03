<template>
  <div class="space-detail">
    <div>
      <select class="select" @change="changeGu" v-model="selectGu">
              <option selected value="자치구">자치구</option>
              <option v-for="(gu, idx) in GuList" :key="idx" :value="gu">
                {{ gu.gu }}
              </option>
            </select>&nbsp;

       <select class="select" v-show="isGu" v-model="selectDong">
         <option selected value="행정동">행정동</option>
              <option v-for="(dong, idx) in DongList" :key="idx" :value="dong">
                {{ dong }}
              </option>
       </select>  
    </div>
       <div > CCTV 버튼나오는거(아직 이쪽부분 수정중) {{cctv}} </div>
  </div>
  
</template>
<script>

import axios from "axios";
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
      selectGuID : "",
      selectDong: "행정동",
      isMain: true,
      isGu: false,
      isDong: false,
      GuList: [],
      GuIDList: [],
      DongList: [],
    
    }
  },

  mounted() {
    //처음에 구 가져오기 위한 코드
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/main/sigungu")
      .then(respon => {
        this.guList = respon.data.guList
        for(const idx in this.guList) {
          const guName = this.guList[idx];
          this.GuList.push(guName)
        }
      })
      .catch(e => {
          console.log('error : ', e)
          })
  },

  methods: {
    changeGu() {
       const guSelect = this.selectGu;
      if (guSelect === "자치구") {
        this.isMain = true;
        this.isGu = false;
      } else {
        this.isMain = false;
        this.isGu = true;
      }

      //선택한 구랑 구 아이디 저장
      console.log(this.selectGu.gu)
      //console.log(this.GuList)
      for (let i=0; i<this.GuList.length; i++) {
        if(this.GuList[i].gu == guSelect.gu) {
          const GuID =  this.GuList[i].id;
          this.selectGuID = GuID;
          console.log(this.selectGuID)
        }
      }
    },
  },

  watch: {
      selectGuID: function (val) {
        // 해당 구의 행정동 리스트 가져오기
        this.DongList = []
        console.log(val)
        console.log(this.DongList)
      axios
      .get("http://j5a305.p.ssafy.io:8080/api/v1/main/dong/" + val)
      .then(respond => {
        this.dongList = respond.data.getDongListDtoList
        //console.log(this.dongList)
        for(const idx in this.dongList) {
          const dongName = this.dongList[idx].dong;
          this.DongList.push(dongName)
        }
        //console.log(this.DongList)
      })

      .catch(e => {
          console.log('error : ', e)
          })
      },

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
