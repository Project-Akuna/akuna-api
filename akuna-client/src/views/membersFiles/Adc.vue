<template lang="pug">
  section.adc
    h3.pb-4.d-inline-block ADC
    v-breadcrumbs.d-inline-block.pa-0.float-right(:items="breadcrumbItems")
      template(v-slot:divider)
        v-icon mdi-chevron-right
    v-data-table.elevation-1(
      :headers="headers"
      :items="adcList"
      multi-sort
    )
      template(v-slot:item.isSelling="{ item }")
        span {{ checkIfSelling(item.isSelling) }}


</template>
<script>
import { mapState } from 'vuex'
export default {
  data() {
    return {
      adcList: [],
      headers: [
        {
          text: 'ID',
          value: 'id'
        },
        {
          text: 'Code',
          value: 'code'
        },
        {
          text: 'Name',
          value: 'name'
        },
        {
          text: 'Address',
          value: 'address'
        },
        {
          text: 'City',
          value: 'city'
        },
        {
          text: 'Landline 1',
          value: 'landline1'
        },
        {
          text: 'Landline 2',
          value: 'landline2'
        },
        {
          text: 'Mobile 1',
          value: 'mobile1'
        },
        {
          text: 'Mobile 2',
          value: 'mobile2'
        },
        {
          text: 'Email',
          value: 'email'
        },
        {
          text: 'Status',
          value: 'isSelling'
        }
      ],
      breadcrumbItems: [
        {
          text: "Member's Files",
          disabled: true,
          href: '',
        },
        {
          text: 'ADC',
          disabled: false,
          href: '',
        },
      ],
    }
  },
  computed: mapState({ 
    axiosURL: 'axiosURL'
  }),
  methods: {
    getAdcList() {
      this.axios.get(this.axiosURL+ 'api/adc/get-all-adc', {
        auth: this.$session.get('auth')
      })
      .then(response => {
        this.adcList = response.data.payload;
        console.log(this.adcList);
      })
      .catch(response => {
        this.$swal('Opssss! Something went wrong', response.data.message, 'error');
      })
    },
    checkIfSelling(isSelling) {
      return isSelling ? 'Active' : 'Inactive'
    }
  },
  mounted() {
    this.getAdcList();
  }
}
</script>
<style lang="scss">

</style>