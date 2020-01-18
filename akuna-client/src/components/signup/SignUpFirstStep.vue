<template lang="pug">
  v-stepper-content(step="1")
    v-container
      v-row
        //HEADING - ADC Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 ADC Information
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

        // Member Information Buttons
        v-col.signup__akuna-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
          v-btn.signup__btn.signup__info-submit-btn.ml-3(@click="changeStep(2)") Next
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
  data() {
    return {
      
    }
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