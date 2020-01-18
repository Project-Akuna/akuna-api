<template lang="pug">
  v-stepper-content(step="4")
    v-container.pt-0
      v-row
        //HEADING - Account Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 Account Information

        // Username TextField
        v-col.signup__input-container(cols="12")
          base-text-field.signup__username-textfield(textFieldLabel="Username" counter="10")

        // Password TextField
        v-col.signup__input-container(cols="12")
          base-text-field.signup__password-textfield(textFieldLabel="Password" counter="10")

        // Confirm Password TextField
        v-col.signup__input-container(cols="12")
          base-text-field.signup__confirm-password-textfield(textFieldLabel="Confirm Password" counter="10")

        // Upline Information Buttons
        v-col.signup__account-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
          v-btn(@click="changeStep(3)" depressed background-color="white") Back
          v-btn.signup__btn.ml-3() Submit
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'
import BaseRadioGroup from '../baseComponents/BaseRadioGroup'
import SignupMixin from '../../mixins/signupMixin'

export default {
  mixins: [SignupMixin],
  components: {
    BaseSelect,
    BaseTextField,
    BaseBirthdayPicker,
    BaseRadioGroup
  },
  computed: {
    items() {
      return this.$store.state.items;
    }
  },
}
</script>
<style lang="scss">

</style>