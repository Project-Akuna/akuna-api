<template lang="pug">
  v-stepper-content(step="2")
    v-container.pt-1
      v-form(ref="signupSecondStepForm" v-model="valid" lazy-validation)
        v-row
          //HEADING - Personal Info
          v-col.pa-0(cols="12")
            h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 Personal Information
          // First Name TextField
          v-col.signup__input-container(cols="12")
            v-text-field(
              v-model="localSignupAccount.firstName"
              clearable
              :counter="100"
              label="First Name"
              :rules="customRules('First Name',{ required: true, counter: 100 })"
              @input="localSignupAccount.firstName = capitalize(localSignupAccount.firstName)"
            )

          // Middle Name TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.middleName"
              clearable
              dense
              counter="100"
              label="Middle Name"
              :rules="customRules('Middle Name',{ counter: 100 })"
              @input="localSignupAccount.middleName = capitalize(localSignupAccount.middleName)"
            )

          // Last Name TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.lastName"
              clearable
              dense
              counter="100"
              label="Last Name"
              :rules="customRules('Last Name',{ required: true, counter: 100 })"
              @input="localSignupAccount.lastName = capitalize(localSignupAccount.lastName)"
            )

          // Birthday Select
          v-col.signup__input-container(cols="12" )
            base-birthday-picker.signup__birthday-date-picker(
              @onSave=" localSignupAccount.birthday = $event" 
              :customRules="customRules('Birthday',{ required: true })")

          // Status Select
          v-col.signup__input-container(cols="12" )
            v-select(
              v-model="localSignupAccount.maritalStatus"
              dense
              label="Marital Status"
              :items="statusList"
              :rules="customRules('Marital Status',{ required: true })"
            )

          // Spouse Full Name TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.spouse"
              clearable
              dense
              counter="100"
              label="Spouse Full Name"
              :rules="customRules('Spouse Full Name',{ counter: 100 })"
              @input="localSignupAccount.spouse = capitalize(localSignupAccount.spouse)"
            )
            
          // Successor TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.successor"
              clearable
              dense
              counter="100"
              label="Successor"
              :rules="customRules('Successor',{ counter: 100 })"
              @input="localSignupAccount.successor = capitalize(localSignupAccount.successor)"
            )

          // Relationship TextField
          v-col.signup__input-container(cols="12" )
            v-text-field.capitalize-input(
              v-model="localSignupAccount.relationship"
              clearable
              dense
              counter="100"
              label="Relationship to Member"
              :rules="customRules('Relationship to Member',{ counter: 100 })"
            )

          // TIN Number TextField
          //- v-col.signup__input-container(cols="12" )
          //-   v-text-field(
          //-     clearable
          //-     dense
          //-     type="number"
          //-     counter="15"
          //-     label="TIN Number"
          //-   )
            //- @input="updateAccount({ tinNumber: $event })"


          // HEADING - Contact Info
          v-col.pa-0(cols="12")
            h4.font-weight-medium.grey--text.text--darken-3.pt-5.pb-3.pl-3 Contact Information
          
          // Address TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.address"
              clearable
              dense
              counter="100"
              label="Complete Address"
              :rules="customRules('Adress',{ required: true, counter: 100 })"
            )

          // Region Select
          v-col.signup__input-container(cols="12" )
            v-select(
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
            v-select(
              v-model="selectedCity"
              :items="citiesList"
              item-text="name"
              item-value="id"
              label="City"
              dense
              :rules="customRules('City',{ required: true })"
              )

          // Zipcode Textfield
          v-col.signup__input-container(cols="4" )
            v-text-field(
              dense
              v-model="zipcode"
              disabled
              label="Zipcode" 
            )

          // Primary Contact Number TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.primaryContact"
              clearable
              dense
              type="number"
              counter="11"
              label="Primary Contact Number"
              :rules="customRules('Primary Contact Number',{ counter: 11 })"
            )
            //- @input="updateAccount({ primaryContactNumber: $event })"

          // Secondary Contact Number TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.secondaryContact"
              clearable
              dense
              type="number"
              counter="11"
              label="Secondary Contact Number"
              :rules="customRules('Secondary Contact Number',{ counter: 100 })"
            )
            //- @input="updateAccount({ secondaryContactNumber: $event })"

          // Email TextField
          v-col.signup__input-container(cols="12" )
            v-text-field(
              v-model="localSignupAccount.email"
              clearable
              dense
              type="email"
              counter="50"
              label="Email"
              :rules="customRules('Email',{ email: true, required: true, counter: 50 })"
            )

          // Member Information Buttons
          v-col.signup__personal-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
            v-btn(@click="prevStep()" depressed background="red") Back
            v-btn.signup__btn.ml-3(@click="nextSignupStep") Next
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'
import BaseRadioGroup from '../baseComponents/BaseRadioGroup'

import SignupMixin from '../../mixins/signupMixin'
import FormMixin from '../../mixins/formMixin'
import { mapState } from 'vuex';

export default {
  mixins: [SignupMixin, FormMixin],
  components: {
    BaseSelect,
    BaseTextField,
    BaseBirthdayPicker,
    BaseRadioGroup
  },
  data() {
    return {
      statusList: ['Single', 'Married'],
      citiesList: [],
      selectedRegion: '',
      selectedCity: '',
      zipcode: '',
      localSignupAccount: {},
      valid: true,
    }
  },
  computed: mapState({
    signupAccount: 'signupAccount',
    regionsList: 'regionsList',
    axiosURL: 'axiosURL'
  }),
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
      if (this.$refs.signupSecondStepForm.validate()) {
        this.localSignupAccount.city = { id: this.selectedCity }
        this.localSignupAccount.maritalStatus = this.localSignupAccount.maritalStatus.toUpperCase();

        // Check relationship
        if (this.localSignupAccount.relationship == '') delete this.localSignupAccount.relationship;
        else if (this.localSignupAccount.hasOwnProperty('relationship')) this.localSignupAccount.relationship = this.localSignupAccount.relationship.toUpperCase()
        
        
        this.updateAccount( this.localSignupAccount )

        this.nextStep()
      }
    },
  },
  watch: {
    selectedCity: function (newCity, oldCity) {
      let city = this.citiesList.filter((el, index, array) => {
        return (el.id == newCity);
      });

      this.zipcode = city[0].zipCode;

      this.updateAccount({ city: { id: city[0].id } })
    }
  }
}
</script>
<style lang="scss">

</style>