<template lang="pug">

  v-card.views-container.sell-registration-code.ma-5
    v-breadcrumbs.d-inline-block.pa-0.float-right(:items="breadcrumbItems")
        template(v-slot:divider)
          v-icon mdi-chevron-right
    h3.pb-4.d-inline-block Sell Registration Code
    
    v-data-table.elevation-1(
      :headers="headers"
      :items="regCodeList"
      multi-sort
    )
</template>
<script>
import {mapState} from 'vuex'
export default {
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
        console.log(response.data)
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