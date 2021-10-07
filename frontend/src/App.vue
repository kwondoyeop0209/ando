<template>
  <div id="app">
    <Main />
  </div>
</template>

<script>
import Main from "./views/Main/Main.vue";
export default {
  name: "App",
  components: {
    Main,
  },
};
</script>

<style>
@font-face {
  font-family: "KoPubDotumMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/KoPubDotumMedium.woff") format("woff");
  font-weight: normal;
  font-style: normal;
}
#app {
  color: #2c3e50;
  background-color: #454d5e;
}
* {
  margin: 0;
  padding: 0;
  color: white;
  font-family: "KoPubDotumMedium";
}
/* 링크줄 없애기 */
.deco-none {
  text-decoration: none;
  color: white;
}
/* input 클릭시 outline 없애기 */
input:focus {
  outline: none;
}

/* dark-blue */
.dark-blue {
  color: #454d5e;
}
/* light-blue */
.light-blue {
  color: #6a7daf;
}
.white-blue {
  color: #a4b5e2;
}
/* dark-blue */
.light-red {
  color: #f57272;
}
/* white */
.dark-blue {
  color: #ffffff;
}
/* light-gray */
.light-gray {
  color: #eeeeee;
}
/* gray */
.gray {
  color: #dedede;
}
</style>
