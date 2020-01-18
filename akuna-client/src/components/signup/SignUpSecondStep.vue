<template lang="pug">
  v-stepper-content(step="2")
    v-container.pt-0
      v-row
        //HEADING - Personal Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 Personal Information
        // First Name TextField
        v-col.signup__input-container(cols="12")
          base-text-field.signup__first-name-textfield(textFieldLabel=" First Name" counter="10")

        // Middle Name TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__middl-name-textfield(textFieldLabel=" Middle Name" counter="10")

        // Last Name TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__last-name-textfield(textFieldLabel="Last Name" counter="10")

        // Birthday Select
        v-col.signup__input-container(cols="12" )
          base-birthday-picker.signup__birthday-date-picker

        // Status Select
        v-col.signup__input-container(cols="12" )
          base-select.signup__status-select(:selectItems="items" selectLabel="Status")

        // Spouse Full Name TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__spouse-textfield(textFieldLabel="Spouse Full Name" counter="10")

        // Successor TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__successor-textfield(textFieldLabel="Successor" counter="10")

        // Relationship TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__relationship-textfield(textFieldLabel="Relationship to Member" counter="10")

        // TIN Number TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__tin-number-textfield(textFieldLabel="TIN Number" counter="10" type="number")


        // HEADING - Contact Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pt-5.pb-3.pl-3 Contact Information
        
        // Address TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__address-textfield(textFieldLabel="Complete Address" counter="10")

        // City Select
        v-col.signup__input-container(cols="8" )
          base-select.signup__personal-city-select(:selectItems="items" selectLabel="City")

        // Zipcode Textfield
        v-col.signup__input-container(cols="4" )
          base-text-field.signup__personal-zipcode-textfield(textFieldLabel="Zipcode" disabled)

        // Primary Contact Number TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__primary-contact-textfield(
            textFieldLabel="Primary Contact Number" 
            counter="11"
            type="number")

        // Secondary Contact Number TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__secondary-contact-textfield(
            textFieldLabel="Secondary Contact Number" 
            counter="11"
            type="number")

        // Email TextField
        v-col.signup__input-container(cols="12" )
          base-text-field.signup__email-textfield(
            textFieldLabel="Email" 
            counter="20"
            type="email"
            )

        // Member Information Buttons
        v-col.signup__personal-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
          v-btn(@click="changeStep(1)" depressed background="red") Back
          v-btn.signup__btn.ml-3(@click="changeStep(3)") Next
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
  }
}
</script>
<style lang="scss">

</style>