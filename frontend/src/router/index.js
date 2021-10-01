import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home/Home.vue";
import Crime from "../views/Crime/Crime.vue";
import SafetyIndex from "../views/SafetyIndex/SafetyIndex.vue";
import SpaceElement from "../views/SafetyIndex/SpaceElement";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/crime",
    name: "Crime",
    component: Crime,
  },
  {
    path: "/safety",
    name: "SafetyIndex",
    component: SafetyIndex,
  },
  {
    path: "/space",
    name: "SpaceElement",
    component: SpaceElement,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
