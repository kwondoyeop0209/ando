import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import HighchartsVue from "highcharts-vue";

Vue.config.productionTip = false;

new Vue({
  HighchartsVue,
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
