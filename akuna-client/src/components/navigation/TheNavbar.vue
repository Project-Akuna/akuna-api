<template lang="pug">
  section.navigation
    v-navigation-drawer(
      :clipped="$vuetify.breakpoint.lgAndUp"
      v-model="drawer" 
      app
      )
      v-list(dense nav)
        
        // Home (Dashboard)
        v-list-item(link dense to="dashboard") 
          v-list-item-action
            v-icon mdi-view-dashboard
          v-list-item-content
            v-list-item-title Dashboard

        // Admin Profile
        v-list-item(link dense) 
          v-list-item-action
            v-icon mdi-account
          v-list-item-content
            v-list-item-title Admin Account

        // Master Files List Group
        v-list-group(no-action)
          template(v-slot:activator)
            v-list-item-action
              v-icon mdi-folder-text
            v-list-item-content
              v-list-item-title Master Files

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Paylite Member Type

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Member Package

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Product Information

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Alveo Inventory

        // Member's Files List Group
        v-list-group(no-action)
          template(v-slot:activator)
            v-list-item-action
              v-icon mdi-account-group
            v-list-item-content
              v-list-item-title Member's File

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Admin Accounts

          v-list-item(link dense to="adc")
            v-list-item-content
              v-list-item-title ADC

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Cashier

          v-list-item(link dense to="/members")
            v-list-item-content
              v-list-item-title Member

        // Transaction List Group
        v-list-group(no-action)
          template(v-slot:activator)
            v-list-item-action
              v-icon mdi-newspaper-variant-multiple
            v-list-item-content
              v-list-item-title Transaction

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Sell Registration Code

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title ADC Free Bottles

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Replenish

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Qualifier / PLA

          //- Run Income Incentives Dropdown
          v-list-item(link dense)
            v-list-item-content
              //- v-list-item-title Run Income / Incentives
              v-menu(top offset-x open-on-hover)
                template(v-slot:activator="{ on }")
                  v-list-item-title(v-on="on") Run Income/Incentives
                    v-icon.float-right mdi-menu-right

                  //- v-btn(v-on="on" text) Run Income / Incentives
                
                v-list(dense)
                  v-list-item
                    v-list-item-title Free Bottles Incentives
                  
                  v-list-item
                    v-list-item-title Free Bedcodes

                  v-list-item
                    v-list-item-title DRI

                  v-list-item
                    v-list-item-title PLA

        // View / Listing / Reports List Group
        v-list-group(no-action)
          template(v-slot:activator)
            v-list-item-action
              v-icon mdi-file-chart
            v-list-item-content
              v-list-item-title Reports

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Acknowledgement Receipts

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Transactions

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title DRI Daily Incentives

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title PLA Incentives

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title ADC Free Bottles Inventory

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title ADC Free Bedcode

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Free Bottles Incentive

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Bedcode Inventory

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Daily Payout Report

    v-app-bar(
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      dark
      color="green darken-2"
      )
      v-app-bar-nav-icon(@click.stop="drawer = !drawer")
      v-toolbar-title Wellness Connection
</template>
<script>
export default {
  data() {
    return {
      drawer: null
    }
  },
  computed: {
    items() {
      return this.$store.state.items;
    },
  }
}
</script>
<style lang="scss">

</style>