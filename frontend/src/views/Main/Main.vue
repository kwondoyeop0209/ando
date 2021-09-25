<template>
  <div class="main">
    <main-header />
    <router-view />
  </div>
</template>

<script>
import MainHeader from "./components/MainHeader.vue";

export default {
  name: "Main",
  components: {
    MainHeader,
  },
};
</script>
