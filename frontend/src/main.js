import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import HighchartsVue from "highcharts-vue";
import VueSvgGauge from "vue-svg-gauge";
import axios from "./common/axios";

Vue.config.productionTip = false;
Vue.use(VueSvgGauge);

new Vue({
  HighchartsVue,
  router,
  store,
  axios,
  render: (h) => h(App),
}).$mount("#app");
