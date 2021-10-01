import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import HighchartsVue from "highcharts-vue";
import VueSvgGauge from "vue-svg-gauge";

Vue.config.productionTip = false;
Vue.use(VueSvgGauge);

new Vue({
  HighchartsVue,
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
