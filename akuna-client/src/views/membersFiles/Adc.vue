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
          text: 'ADC Name',
          value: 'adc_name'
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
        auth: {
          username: 'asd',
          password: 'asd'
        }
      })
      .then(response => {
        this.adcList = response.data.payload;
      })
      .catch(response => {
        this.$swal('Opssss! Something went wrong', response.data.message, 'error');
      })
    }
  },
  mounted() {
    this.getAdcList();
  }
}
</script>
<style lang="scss">

</style>