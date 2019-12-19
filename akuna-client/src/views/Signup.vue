<template lang="pug">
  section.signup
    v-container.signup__container.pa-0.ma-5
      signup-stepper.signup__stepper
</template>
<script>
import SignupStepper from '../components/signup/SignupStepper'
export default {
  components: {
    SignupStepper
  }
}
</script>
<style lang="scss">
.signup {
  min-height: 100vh;
  @include flex-center;
  background: $c-accent;

  .signup__container {
    max-width: 600px;
  }

  .signup__stepper {
    @include card-shadow-sm;
  }
}
</style>