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
      if (this.$route.path === "/login" || this.$route.path === "/signup") {
        this.$store.commit('toggleNav', false);
      } else  {
        this.$store.commit('toggleNav', true);
      }
    }
  }
}
</script>
<style lang="scss">
.v-text-field {
  .v-input__prepend-inner i {
    padding-left: 10px;
    transform: scale(0.8);
  }
  .v-input__append-inner i {
    transform: scale(0.8);
    padding-right: 10px;
  }
  input {
    font-size: 14px;
  }
  label {
    font-size: 14px;
    padding-right: 10px;
  }
  &.v-text-field--enclosed .v-text-field__details {
    margin-bottom: 0 !important;
  }
}

.v-stepper {
  .v-stepper__header {
    @include shadow-sm;
  }
  .v-stepper__label {
    font-size: 12px;
    text-align: center;
  }
  .v-stepper__content {
    padding: 0;
  }
  .v-stepper__wrapper {
    padding: 24px 12px 12px;
  }
}

.v-select {
  .v-label {
    background: white;
  }

  &.v-text-field.v-text-field--enclosed .v-text-field__details {
    margin-bottom: 0;
  }
}

// 640px+
@media screen and (min-width: 40em) {
  .v-stepper {
    .v-stepper__wrapper {
      padding: 24px;
    }
  }
}
</style>
