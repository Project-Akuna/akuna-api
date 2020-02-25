<template>
  <section class="adc-view">
    <h3 class="d-block">Add Cashier</h3>
    <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
        <template v-slot:divider>
            <v-icon>mdi-chevron-right</v-icon>
        </template>
    </v-breadcrumbs>
    <v-card class="views-container ma-0 mt-5">
    </v-card>
  </section>
</template>

<script>
  export default {
    data() {
      return {
        breadcrumbItems: [
          {
            text: "Member's Files",
            disabled: true,
            href: '',
          },
          {
            text: 'Cashier',
            disabled: false,
            href: '/cashiers',
          },
          {
            text: 'Add',
            disabled: false,
            href: '',
          },
        ],
      }
    }
  }
</script>

<style lang="scss">

</style>