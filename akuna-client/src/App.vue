<template lang="pug">
  v-app
    navbar(v-if="isNavShown")
    v-content
      router-view
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

</style>
