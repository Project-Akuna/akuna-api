<template lang="pug">
  v-app
    //- navbar
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

  data: () => ({
    //
  }),
};
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
    margin-left: 10px;
  }
  label {
    margin-left: 10px;
    font-size: 14px;
    background: white;
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
    padding: 24px;
  }
}

.v-select {
  .v-label {
    left: -10px !important;
    background: white;
    padding: 0 .5rem;
  }

  &.v-text-field.v-text-field--enclosed .v-text-field__details {
    margin-bottom: 0;
  }
<<<<<<< HEAD
=======

>>>>>>> 689a07bc868c727c8b9b3ee3bb72c731d1e7ce80
}
</style>
