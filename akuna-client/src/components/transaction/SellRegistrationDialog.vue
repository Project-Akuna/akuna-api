<template lang="pug">
  v-dialog(v-model="dialog" persistent max-width="500px")
    template(v-slot:activator="{ on }")
      v-btn.float-right.primary(v-on="on") 
        v-icon.pr-1(small) mdi-plus
        | Generate Registration Code
    v-card.pa-5.pt-5
      h3.font-weight-black.grey--text.text--darken-2.pb-4 Sell Registration Code
      v-row
        v-col.pa-0.px-4(cols="12" )
          v-select(
            :items="adcList"
            item-text="name"
            item-value="id"
            label="ADC"
            dense
            @input="regCodeDetails.adcId = $event"
            )

        v-col.pa-0.px-4(cols="12" )
          v-select(
            :items="quantityList"
            label="Number of Codes"
            dense
            @input="regCodeDetails.quantity = $event"
            )
        v-col.pa-0.px-4.pt-4.text-right(cols="12")
          v-btn.mr-4(depressed @click="dialog=false") Cancel
          v-btn.signup__btn(color="primary" @click="generateRegCode") Continue
</template>
<script>
import { mapState } from 'vuex'
export default {
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  data () {
    return {
      dialog: false,
      regCodeDetails: {},
      adcList: [],
      quantityList: ['1','4','13']
    }
  },

  created() {
    let self = this;

    // Axios call for adc
    this.axios.get(self.axiosURL+'api/adc/get-all-adc', {
      auth: this.$session.get('auth')
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
  methods: {
    generateRegCode() {
      let self = this;
      self.regCodeDetails.addedBy = self.$session.get('account').username;
      self.regCodeDetails.amount = parseInt(self.regCodeDetails.quantity) * 1900;

      console.log(this.$session.get('auth'))

      // Axios call for adc
      self.axios.post(self.axiosURL+'api/registration/generate-codes', {
        auth: {
          username: 'asd',
          password: 'asd'
        }
      }, 
      {
        data: self.regCodeDetails
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      })
      .then(async function () {
        // location.reload()
      });
    }
  }
}
</script>
<style lang="scss">

</style>