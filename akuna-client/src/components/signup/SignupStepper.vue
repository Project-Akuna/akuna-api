<template lang="pug">
  v-stepper(v-model="signupStep" alt-labels transition="scroll-x-transition")

    // Stepper Headers
    v-stepper-header
      v-stepper-step(:complete="signupStep > 1" step="1") Akuna Information
      v-divider
      v-stepper-step(:complete="signupStep > 2" step="2") Member Information
      v-divider
      v-stepper-step(:complete="signupStep > 3" step="3") Upline Information
      v-divider
      v-stepper-step(step="4") Account Information
     
    // Stepper Contents
    v-stepper-items
      v-stepper-content(step="1")
        h4.font-weight-medium.grey--text.text--darken-2.pb-1.pl-3 Akuna Information
        v-container
          v-row
            // Region Select
            v-col.signup__input-container(cols="12" )
              base-select.signup__region-select(:selectItems="items" selectLabel="Region")

            // City Select
            v-col.signup__input-container(cols="8" )
              base-select.signup__city-select(:selectItems="items" selectLabel="City")

            // Zipcode Textfield
            v-col.signup__input-container(cols="4" )
              base-text-field.signup__zipcode-textfield(textFieldLabel="Zipcode" disabled)

            // ADC Select
            v-col.signup__input-container(cols="12" )
              base-select.signup__adc-select(:selectItems="items" selectLabel="ADC")

            // Owner Textfield
            v-col.signup__input-container(cols="12" )
              base-text-field.signup__owner-textfield(textFieldLabel="Owner's Name" disabled)

            // Owner Contact Textfield
            v-col.signup__input-container(cols="12" )
              base-text-field.signup__owner-contact-textfield(textFieldLabel="Contact Number" disabled)

            v-btn.signup__btn.signup__info-submit-btn(@click="signupStep = 2") Next

      v-stepper-content(step="2")
        v-container.pt-0
          v-row
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
            v-col.signup__personal-info-btn-container.d-flex.justify-end.pb-0(cols="12")
              v-btn(@click="signupStep = 1" depressed background-color="white") Back
              v-btn.signup__btn.ml-3(@click="signupStep = 3") Next

      
      v-stepper-content(step="3")
        h1 step 3
        h1 step 1
        h1 step 1
        h1 step 1
        h1 step 1
        h1 step 1
        h1 step 1
        h1 step 1
        v-btn(@click="signupStep = 2") Back
        v-btn(@click="signupStep = 4") Next
      
      v-stepper-content(step="4")
        h1 step 4
        h1 step 1h1 step 1
        h1 step 1
        v-btn.signup__btn(@click="signupStep = 4") Submit
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'

export default {
  components: {
    BaseSelect,
    BaseTextField,
    BaseBirthdayPicker
  },
  data() {
    return {
      signupStep: 2,
      items: [
        "One",
        "Two",
        "Three"
      ]
    }
  }
}
</script>
<style lang="scss">
.signup__stepper {
  .signup__btn {
    background: $c-accent !important;
    color: white !important;
    @include shadow-sm-primary;
  }

  .signup__info-submit-btn {
    margin-right: 12px;
    margin-left: auto;
  } 

  .signup__input-container {
    padding: 0 12px;
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