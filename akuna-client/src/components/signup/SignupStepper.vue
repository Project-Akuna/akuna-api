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
      v-stepper-content.pr-5(step="1")
        h4.font-weight-medium.grey--text.text--darken-2.pb-2 Akuna Information
        v-container
          v-row
            // Region Select
            v-col.py-0.pl-0(cols="12" sm="3")
              p.signup__label-text Region:

            v-col.signup__input-container(cols="12" sm="9")
              base-select.signup__region-select(:selectItems="items" selectLabel="Select Region")

            // City Select
            v-col.py-0.pl-0(cols="12" sm="3")
              p.signup__label-text City:

            v-col.signup__input-container(cols="12" sm="9")
              base-select.signup__city-select(:selectItems="items" selectLabel="Select City")

            // Zipcode Textfield
            v-col.py-0.pl-0(cols="12" sm="3")
              p.signup__label-text Zipcode:

            v-col.signup__input-container(cols="12" sm="9")
              base-text-field.signup__zipcode-textfield(textFieldLabel="Zipcode" disabled)

            // ADC Select
            v-col.py-0.pl-0(cols="12" sm="3")
              p.signup__label-text ADC Name:

            v-col.signup__input-container(cols="12" sm="9")
              base-select.signup__adc-select(:selectItems="items" selectLabel="Select ADC")

            // Owner Textfield
            v-col.py-0.pl-0(cols="12" sm="3")
              p.signup__label-text Owner's Name:

            v-col.signup__input-container(cols="12" sm="9")
              base-text-field.signup__owner-textfield(textFieldLabel="Owner's Name" disabled)

            // Owner Contact Textfield
            v-col.py-0.pl-0(cols="12" sm="3")
              p.signup__label-text Contact Number:

            v-col.signup__input-container(cols="12" sm="9")
              base-text-field.signup__owner-contact-textfield(textFieldLabel="Contact Number" disabled)

            v-btn.signup__btn.signup__info-submit-btn(@click="signupStep = 2") Next

      v-stepper-content(step="2")
        v-container.pt-0
          v-row
            v-col.pa-0(cols="12")
              h4.font-weight-medium.grey--text.text--darken-3.pb-5.pl-3 Personal Information
            // First Name TextField
            v-col.signup__input-container(cols="12")
              base-text-field.signup__first-name-textfield(textFieldLabel="Enter First Name")

            // Middle Name TextField
            v-col.signup__input-container(cols="12" )
              base-text-field.signup__middl-name-textfield(textFieldLabel="Enter Middle Name")

            // Last Name TextField
            v-col.signup__input-container(cols="12" )
              base-text-field.signup__last-name-textfield(textFieldLabel="Enter Last Name")

            // Status Select
            v-col.signup__input-container(cols="12" )
              base-select.signup__status-select(:selectItems="items" selectLabel="Select Status" )

            // Contact Info Heading
            v-col.pa-0(cols="12")
              h4.font-weight-medium.grey--text.text--darken-3.py-5 Contact Information
            
            // Address TextField
            v-col.signup__input-container(cols="12" )
              base-text-field.signup__address-textfield(textFieldLabel="Enter Complete Address")

            // City Select
            v-col.signup__input-container(cols="12" )
              base-select.signup__personal-city-select(:selectItems="items" selectLabel="Select City" )

            // Zipcode Textfield
            v-col.signup__input-container(cols="12" )
              base-text-field.signup__personal-zipcode-textfield(textFieldLabel="Zipcode" disabled)

            

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
export default {
  components: {
    BaseSelect,
    BaseTextField
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
  .signup__label-text {
    text-align: left;
    padding-top: .6rem;
    font-size: 14px;
    font-weight: bold;
    color: $c-dark-gray;
    margin-bottom: 5px;
  }

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
    padding: 0;
  }
}

@media screen and (min-width: 600px) {
  .signup__stepper {
    .signup__label-text {
      text-align: right;
    }

    .signup__input-container {
      padding: 0 12px;
    }
  }
}

</style>