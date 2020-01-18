<template lang="pug">
  v-stepper-content(step="3")
    v-container.pt-0
      v-row
        //HEADING - MyAkuna Information
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 MyAkuna Information

        // Registration Code TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__registration-code-textfield(textFieldLabel="Registration Code" counter="10")

        v-col.signup__input-container(cols="12" )
          p.signup__radio-group-label Number of Accounts
          base-radio-group.signup__accounts-radio-group(:radioGroupItems="items")

        //HEADING - Upline Information
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3.pt-3 Upline Information

        // Direct Sponsor Select
        v-col.signup__input-container(cols="12" )
          base-select.signup__direct-sponsor-select(:selectItems="items" selectLabel="Direct Sponsor")

        // Direct Sponsor Reg ID TextField
        v-col.signup__input-container(cols="12")
          base-text-field.signup__direct-reg-id-textfield(textFieldLabel="Direct Sponsor Registration ID" counter="10")

        // Immediate Upline Select
        v-col.signup__input-container(cols="12" )
          base-select.signup__immediate-upline-select(:selectItems="items" selectLabel="Immediate Upline")

        // Immediate Upline Reg ID TextField
        v-col.signup__input-container(cols="12")
          base-text-field.signup__direct-reg-id-textfield(textFieldLabel="Immediate Upline Registration ID" counter="10")

        // Upline Information Buttons
        v-col.signup__upline-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
          v-btn(@click="changeStep(2)" depressed background-color="white") Back
          v-btn.signup__btn.ml-3(@click="changeStep(4)") Next
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