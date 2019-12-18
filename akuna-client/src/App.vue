<template lang="pug">
  v-app
    //- navbar
    v-content
      router-view
</template>

<script>
import Navbar from './components/navigation/navbar'
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
  }

}
</style>
