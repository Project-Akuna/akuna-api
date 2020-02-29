<template lang="pug">
  section.signup
    v-container.signup__container.pa-0.ma-5
      h3.font-weight-black.grey--text.text--darken-2.text-center.pb-5 Get started with a free account.
      signup-stepper.signup__stepper
      div.signup__login-container.text-center.mt-7
        span.grey--text.text--darken-1.pr-1 Already have an account?
        router-link.link(to="/login") Login here.
</template>
<script>
import SignupStepper from '../components/signup/SignupStepper'

import { mapState } from 'vuex'
export default {
  components: {
    SignupStepper
  },
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  created() {
  }
}
</script>
<style lang="scss">
.signup {
  min-height: 100vh;
  @include flex-center;
  background: $c-lighter-blue-gray;

  .signup__container {
    max-width: 600px;
  }

  .signup__stepper {
    @include card-shadow-sm;
  }

  .signup__login-container {
    font-size: 12px;

    a {
      text-decoration: none;
    }
  }
}
</style>