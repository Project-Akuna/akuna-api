<template lang="pug">
  section.sell-registration-code
    sell-registration-dialog
    h3.d-block Sell Registration Code
    v-breadcrumbs.d-block.pa-0(:items="breadcrumbItems")
      template(v-slot:divider)
        v-icon mdi-chevron-right
    v-card.views-container.sell-registration-code.ma-0.mt-5
      h3.table-title Registration Codes
      v-data-table(
        :headers="headers"
        :items="regCodeList"
        multi-sort
      )
</template>
<script>
import SellRegistrationDialog from '../../components/transaction/SellRegistrationDialog'
import {mapState} from 'vuex'

export default {
  components: { 
    SellRegistrationDialog 
  },
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  data() {
    return {
      breadcrumbItems: [
        {
          text: "Transaction",
          disabled: true,
          href: '',
        },
        {
          text: 'Sell Registration Code',
          disabled: false,
          href: '',
        },
      ],
      regCodeList: [],
      headers: [
        {
          text: 'Code',
          value: 'code'
        },
        {
          text: 'ADC',
          value: 'adc.name'
        },
      ]
    }
  },
  methods: {
    getRegCodeList() {
      this.axios.get(this.axiosURL+ 'api/registration/all', {
        auth: this.$session.get('auth')
      })
      .then(response => {
        this.regCodeList = response.data.payload;
      })
      .catch(response => {
        this.$swal('Opssss! Something went wrong', response.data, 'error');
      })
    },
  },
  mounted() {
    this.getRegCodeList();
  }
}
</script>
<style lang="scss">

</style>