<template lang="pug">
  v-stepper-content(step="2")
    v-container.pt-0
      v-row
        //HEADING - Personal Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 Personal Information
        // First Name TextField
        v-col.signup__input-container(cols="12")
          v-text-field(
            clearable
            dense
            counter="100"
            label="First Name"
            @input="updateAccount({ firstName: $event })"
          )

        // Middle Name TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            counter="100"
            label="Middle Name"
            @input="updateAccount({ middleName: $event })"
          )

        // Last Name TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            counter="100"
            label="Last Name"
            @input="updateAccount({ lastName: $event })"
          )

        // Birthday Select
        v-col.signup__input-container(cols="12" )
          base-birthday-picker.signup__birthday-date-picker(@onSave="updateAccount({ birthday: $event })")

        // Status Select
        v-col.signup__input-container(cols="12" )
          v-select(
            dense
            label="Marital Status"
            :items="statusList"
            @change="updateAccount({ maritalStatus: $event.toUpperCase() })"
          )

        // Spouse Full Name TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            counter="100"
            label="Spouse Full Name"
          )
          //- @input="updateAccount({ firstname: $event })"

        // Successor TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            counter="100"
            label="Successor"
          )
          //- @input="updateAccount({ successor: $event })"
          

        // Relationship TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            counter="100"
            label="Relationship to Member"
          )
          //- @input="updateAccount({ relationship: $event })"

        // TIN Number TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            type="number"
            counter="15"
            label="TIN Number"
          )
          //- @input="updateAccount({ tinNumber: $event })"


        // HEADING - Contact Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pt-5.pb-3.pl-3 Contact Information
        
        // Address TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            counter="100"
            label="Complete Address"
            @input="updateAccount({ address: $event })"
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
            clearable
            dense
            type="number"
            counter="11"
            label="Primary Contact Number"
          )
          //- @input="updateAccount({ primaryContactNumber: $event })"

        // Secondary Contact Number TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            type="number"
            counter="11"
            label="Secondary Contact Number"
          )
          //- @input="updateAccount({ secondaryContactNumber: $event })"

        // Email TextField
        v-col.signup__input-container(cols="12" )
          v-text-field(
            clearable
            dense
            type="email"
            counter="50"
            label="Email"
            @input="updateAccount({ email: $event })"
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
import { mapState } from 'vuex';

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
      statusList: ['Single', 'Married'],
      citiesList: [],
      selectedRegion: '',
      selectedCity: '',
      zipcode: ''
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