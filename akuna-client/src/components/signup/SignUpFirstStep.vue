<template lang="pug">
  v-stepper-content(step="1")
    v-container
      v-row
        //HEADING - ADC Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 ADC Information
        // Region Select
        v-col.signup__input-container(cols="12")
          <!--base-select.signup__region-select(:selectItems="regionsList" selectLabel="Region" )-->
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
          <!--base-select.signup__city-select(:selectItems="items" selectLabel="City")-->
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
      regionsList: [],
      selectedRegion: '',
      selectedCity: '',
      citiesList: []
    }
  },
  computed: {
    items() {
      return this.$store.state.items;
    },
  },
  methods: {
    findCityFromRegion() {
      if(this.selectedRegion != '') {
        var self = this;
        this.axios.get('http://localhost:3000/api/city/get-all-city/' + self.selectedRegion, {
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
    }
  },
  created(){
    var self = this;
    this.axios.get('http://localhost:3000/api/region/get-all-regions', {
      auth: {
        username: 'asd',
        password: 'asd'
      }
    })
    .then(function (response) {
      self.regionsList = response.data.payload;
    })
    .catch(function (error) {
      console.log(error);
    })
    .then(async function () {
    });
  }

}
</script>
<style lang="scss">

</style>