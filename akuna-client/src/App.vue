<template lang="pug">
  v-app
    navbar(v-if="isNavShown")
    v-content
       router-view.pa-5
</template>

<script>
import Navbar from './components/navigation/TheNavbar'
export default {
  name: 'App',

  components: {
    Navbar
  },

  data() {
    return {

    }
  },
  mounted() {
    this.checkRoute();
  },
  watch: {
   $route: function() {
    this.checkRoute();
   }
  },
  computed: {
    isNavShown() {
      return this.$store.state.isNavShown;
    }
  },
  methods: {
    checkRoute() {
      if (this.$route.path.includes("signup") || this.$route.path.includes("login")) {
        this.$store.commit('toggleNav', false);
      } else  {
        this.$store.commit('toggleNav', true);
      }
    }
  }
}
</script>
<style lang="scss">
@import './assets/scss/_global.scss','./assets/scss/_vuetify.scss';
// 640px+
@media screen and (min-width: 40em) {
  .v-stepper {
    .v-stepper__wrapper {
      padding: 24px;
    }
  }
}
.swal2-container {
  font-family: Roboto;
}
</style>
