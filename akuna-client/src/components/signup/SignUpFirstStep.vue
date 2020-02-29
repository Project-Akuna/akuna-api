<template lang="pug">
  v-stepper-content(step="1")
    v-container
      v-form(ref="signupFirstStepForm" v-model="valid" lazy-validation)
        v-row
          //HEADING - ADC Info
          v-col.pa-0(cols="12")
            h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 ADC Information
          // Region Select
          v-col.signup__input-container(cols="12")
            v-select.signup__region-select(
              v-model="selectedRegion"
              :items="regionsList"
              item-text="description"
              item-value="id"
              label="Region"
              dense
              @change="findCityFromRegion()"
              )

          // City Select
          v-col.signup__input-container(cols="8" )
            v-select.signup__city-select(
              v-model="selectedCity"
              :items="citiesList"
              item-text="name"
              item-value="id"
              label="City"
              dense
              )

          // Zipcode Textfield
          v-col.signup__input-container(cols="4" )
            v-text-field.signup__zipcode-textfield(
              dense
              v-model="zipcode"
              disabled
              label="Zipcode" 
            )

          // ADC Select
          v-col.signup__input-container(cols="12" )
            v-select.signup__adc-select(
              v-model="selectedAdc"
              :items="adcList"
              item-text="name"
              item-value="id"
              label="ADC"
              dense
              :rules="customRules('ADC',{ required: true })"
              )

          // Owner Textfield
          v-col.signup__input-container(cols="12" )
            v-text-field.signup__owner-textfield(
              dense
              v-model="owner"
              disabled
              label="Owner's Name"
            )

          // Owner Contact Textfield
          v-col.signup__input-container(cols="12" )
            v-text-field.signup__owner-contact-textfield(
              dense
              v-model="ownerContact"
              disabled
              label="Contact Number"
            )

          // Member Information Buttons
          v-col.signup__akuna-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
            v-btn.signup__btn.signup__info-submit-btn.ml-3(@click="nextSignupStep") Next
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'
import BaseRadioGroup from '../baseComponents/BaseRadioGroup'

import SignupMixin from '../../mixins/signupMixin'
import FormMixin from '../../mixins/formMixin'
import { mapState } from 'vuex'

export default {
  mixins: [SignupMixin, FormMixin],
  components: {
    BaseSelect,
    BaseTextField,
    BaseBirthdayPicker,
    BaseRadioGroup
  },
  computed: mapState({
    axiosURL: 'axiosURL',
    signupStep: 'signupStep',
    signupAccount: 'signupAccount',
    regionsList: 'regionsList'
  })
  ,
  data() {
    return {
      citiesList: [],
      adcList: [],
      selectedRegion: '',
      selectedCity: '',
      selectedAdc: '',
      owner: '',
      ownerContact: '',
      zipcode: '',
      valid: true,
    }
  },
  methods: {
    findCityFromRegion() {
      if(this.selectedRegion != '') {
        var self = this;

        // Axios call for getting city
        this.axios.get(self.axiosURL+'api/city/get-all-city/' + self.selectedRegion, {
          auth: {
            username: 'asd',
            password: 'asd'
          }
        })
        .then(function (response) {
          self.citiesList = response.data.payload;
        })
        .catch(function (error) {
          console.log(error);
        })
        .then(async function () {
        });
      }
    },
    nextSignupStep() {
      
      if (this.$refs.signupFirstStepForm.validate()) {
        this.updateAccount({ adc: {id: this.selectedAdc }});
        this.nextStep()
      }
    }
  },
  created(){
    var self = this;

    // Axios call for adc
    this.axios.get(self.axiosURL+'api/adc/get-all-adc', {
      auth: {
        username: 'asd',
        password: 'asd'
      }
    })
    .then(function (response) {
      self.adcList = response.data.payload;
    })
    .catch(function (error) {
      console.log(error);
    })
    .then(async function () {
    });
  },
  watch: {
    selectedAdc: function(newAdc, oldAdc) {
      let adc = this.adcList.filter((el, index, array) => {
        return (el.id == newAdc);
      });

      this.owner = adc[0].name;
      this.ownerContact = adc[0].mobileNumber1; 

    },
    selectedCity: function(newCity, oldCity) {
      let city = this.citiesList.filter((el, index, array) => {
        return (el.id == newCity);
      });

      this.zipcode = city[0].zipCode;
    }
  }
}
</script>
<style lang="scss">

</style>