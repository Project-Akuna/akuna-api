<template>
  <section class="warehousing-add-delivery">
    <h3 class="d-block">Add Warehousing Delivery</h3>
    <v-breadcrumbs class="d-block pa-0" :items="breadcrumbItems">
      <template v-slot:divider>
        <v-icon>mdi-chevron-right</v-icon>
      </template>
    </v-breadcrumbs>
    <v-card class="views-container ma-0 mt-5">
      <h3 class="table-title">Enter Warehousing Delivery Details</h3>
      <v-form ref="warehousingAddDeliveryForm" v-model="valid" lazy-validation>
        <v-row>
          <v-col cols="12" md="6">
            
          </v-col>
        </v-row>
      </v-form>
    </v-card>
  </section>
</template>
<script>
export default {
  data() {
    return {
      breadcrumbItems: [
        {
          text: "Transaction",
          disabled: true,
          href: '',
        },
        {
          text: 'Warehousing',
          disabled: false,
          href: '/warehousing',
        },
        {
          text: 'Add Warehousing Delivery',
          disabled: false,
          href: '',
        },
      ],
      valid: false
    }
  }
}
</script>
<style lang="scss">

</style>