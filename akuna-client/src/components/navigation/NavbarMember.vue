<template>
  <v-list dense nav>
    <!-- Dashboard -->
    <v-list-item link to="/dashboard">
      <v-list-item-action>
        <v-icon>mdi-view-dashboard</v-icon>
      </v-list-item-action>
      <v-list-item-content>
        <v-list-item-title>Dashboard</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <!-- Member Profile -->
    <v-list-group no-action>
      <template v-slot:activator>
        <v-list-item-action>
          <v-icon>mdi-account</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>Member Profile</v-list-item-title>
        </v-list-item-content>
      </template>

      
      <v-list-item link :to="'genealogy/'+$session.get('account').username">
        <v-list-item-content>
          <v-list-item-title>Genealogy</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list-group>

    <!-- Reports -->
    <v-list-group no-action>
      <template v-slot:activator>
        <v-list-item-action>
          <v-icon>mdi-file-chart</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>Reports</v-list-item-title>
        </v-list-item-content>
      </template>

      <v-list-item link to="">
        <v-list-item-content>
          <v-list-item-title>Acknowledgement Receipts</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="">
        <v-list-item-content>
          <v-list-item-title>DRI Daily Incentives</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="">
        <v-list-item-content>
          <v-list-item-title>PLA Incentives</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="">
        <v-list-item-content>
          <v-list-item-title>ADC Free Bottles Inventory</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="">
        <v-list-item-content>
          <v-list-item-title>ADC Free Bedcode</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="">
        <v-list-item-content>
          <v-list-item-title>Free Bottles Incentive</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="">
        <v-list-item-content>
          <v-list-item-title>Bedcode Inventory</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link>
        <v-list-item-content>
          <v-menu top offset-x>
            <template v-slot:activator="{ on }">
                <v-list-item-title v-on="on">
                  Wallets
                  <v-icon class="float-right">mdi-menu-right</v-icon>
                </v-list-item-title>
            </template>

            <v-list-item dense link to="">
              <v-list-item-content>
                <v-list-item-title>Incentive Wallets</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-menu>
        </v-list-item-content>
      </v-list-item>

    </v-list-group>
  </v-list>
</template>

<script>
  export default {
    
  }
</script>

<style lang="scss">

</style>