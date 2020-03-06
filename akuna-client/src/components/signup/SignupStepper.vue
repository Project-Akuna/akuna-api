<template lang="pug">
  v-stepper(
    v-model="signupStep" 
    alt-labels 
    transition="scroll-x-transition")

    // Stepper Headers
    v-stepper-header
      v-stepper-step(color="green darken-2" :complete="signupStep > 1" step="1") ADC Information
      v-divider
      v-stepper-step(color="green darken-2" :complete="signupStep > 2" step="2") Member Information
      v-divider
      v-stepper-step(color="green darken-2" :complete="signupStep > 3" step="3" v-if="getRouteName() != 'addCashier'") Upline Information
      v-divider
      v-stepper-step(color="green darken-2" :step="getRouteName() == 'addCashier' ? '3': '4'") Account Information
     
    // Stepper Contents
    v-stepper-items
      sign-up-first-step
      sign-up-second-step
      sign-up-third-step(v-if="getRouteName() != 'addCashier'")
      sign-up-fourth-step
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'
import BaseRadioGroup from '../baseComponents/BaseRadioGroup'

import SignUpFirstStep from '../signup/SignUpFirstStep'
import SignUpSecondStep from '../signup/SignUpSecondStep'
import SignUpThirdStep from '../signup/SignUpThirdStep'
import SignUpFourthStep from '../signup/SignUpFourthStep'

import { mapState } from 'vuex'
import SignupMixin from '@/mixins/signupMixin.js'

export default {
  computed: mapState({
    axiosURL: 'axiosURL',
    signupStep: 'signupStep'
  }),
  mixins: [ SignupMixin],
  components: {
    BaseSelect,
    BaseTextField,
    BaseBirthdayPicker,
    BaseRadioGroup,

    SignUpFirstStep,
    SignUpSecondStep,
    SignUpThirdStep,
    SignUpFourthStep
  },
  data() {
    return {
    }
  },
  methods: {
    updateRegions() {
      var self = this;

      // Axios call for regions
      this.axios.get(self.axiosURL+'api/region/get-all-regions', {
        auth: {
          username: 'asd',
          password: 'asd'
        }
      })
      .then(function (response) {
        self.$store.commit('updateRegions', response.data.payload)
      })
      .catch(function (error) {
        console.log(error);
      })
      .then(async function () {
      });
    }
  },
  mounted() {
    this.updateRegions();
    this.resetSignupStep();
  }
}
</script>
<style lang="scss">
.signup__stepper {
  .signup__btn {
    background: $c-primary !important;
    color: white !important;
    @include shadow-sm-primary;
  }

  .signup__input-container {
    padding: 4px 12px;
  }

  .signup__radio-group-label {
    font-size: 14px;
    color: $c-text-light;
    margin-bottom: 6px;
  }
}

@media screen and (min-width: 600px) {
  .signup__stepper {

    .signup__input-container {
      // padding: 0 12px;
    }
  }
}

</style>