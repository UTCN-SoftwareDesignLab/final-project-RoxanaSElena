import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import "material-design-icons-iconfont/dist/material-design-icons.css";
import "./api";
import store from "./store";
import VueGeolocation from "vue-browser-geolocation";

Vue.config.productionTip = false;
Vue.use(VueGeolocation);

// import * as VueGoogleMaps from "vue2-google-maps";
// Vue.use(VueGoogleMaps, {
//   load: {
//     key: ";"
//   },
// });
new Vue({
  router,
  vuetify,
  store,
  render: (h) => h(App),
}).$mount("#app");
