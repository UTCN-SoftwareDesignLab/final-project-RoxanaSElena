<template>
   <v-app>
    <v-main>
      <v-container fluid>
        <TopBar v-if="isLoggedIn"></TopBar>
        <router-view></router-view>
      </v-container>
    </v-main>
  </v-app> 
   <!-- <div id="app">
    <Map/>
  </div>  -->
</template>

<script>
import TopBar from "./components/TopBar";
//import Map from "./views/Map";
export default {
  name: "App",
  //components: { Map },
  components: { TopBar }, //Map },
  data: () => ({
    //
  }),
  computed: {
    isLoggedIn: function () {
      return this.$store.state.auth.status.loggedIn;
    },
  },
};
</script>
