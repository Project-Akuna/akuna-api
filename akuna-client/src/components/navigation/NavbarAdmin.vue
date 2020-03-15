<template>
  <v-list dense nav>
    <!-- Dashboard -->
    <v-list-item link to="/dashboard" color="primary">
      <v-list-item-action>
        <v-icon>mdi-view-dashboard</v-icon>
      </v-list-item-action>
      <v-list-item-content>
        <v-list-item-title>Dashboard</v-list-item-title>
      </v-list-item-content>
    </v-list-item>

    <!-- Admin Profile -->
    <!--<v-list-item link to="">-->
      <!--<v-list-item-action>-->
        <!--<v-icon>mdi-account</v-icon>-->
      <!--</v-list-item-action>-->
      <!--<v-list-item-content>-->
        <!--<v-list-item-title>Admin Account</v-list-item-title>-->
      <!--</v-list-item-content>-->
    <!--</v-list-item>-->

    <!-- Master Files -->
    <!--<v-list-group no-action>-->
      <!--<template v-slot:activator>-->
        <!--<v-list-item-action>-->
          <!--<v-icon>mdi-folder-text</v-icon>-->
        <!--</v-list-item-action>-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Master Files</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</template>-->
      
      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Paylite Member Type</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Member Package</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Product Information</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Alveo Inventory</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->
    <!--</v-list-group>-->

    <!-- Member's File -->
    <v-list-group no-action>
      <template v-slot:activator>
        <v-list-item-action>
          <v-icon>mdi-account-group</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>Member's File</v-list-item-title>
        </v-list-item-content>
      </template>

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Admin Accounts</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <v-list-item link to="/members/add">
        <v-list-item-content>
          <v-list-item-title>Add Member</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="/depot">
        <v-list-item-content>
          <v-list-item-title>Depot</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="/adc">
        <v-list-item-content>
          <v-list-item-title>ADC</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="/cashiers">
        <v-list-item-content>
          <v-list-item-title>Cashier</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="/members">
        <v-list-item-content>
          <v-list-item-title>Member</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list-group>

    <!-- Transaction -->
    <v-list-group no-action>
      <template v-slot:activator>
        <v-list-item-action>
          <v-icon>mdi-newspaper-variant-multiple</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>Transaction</v-list-item-title>
        </v-list-item-content>
      </template>

      <v-list-item link to="/warehousing">
        <v-list-item-content>
          <v-list-item-title>Warehousing</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <v-list-item link to="/sell-registration-code">
        <v-list-item-content>
          <v-list-item-title>Sell Registration Code</v-list-item-title>
        </v-list-item-content>
      </v-list-item>

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>ADC Free Bottles</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Replenish</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Qualifier / PLA</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link>-->
        <!--<v-list-item-content>-->
          <!--&lt;!&ndash;<v-menu top offset-x>&ndash;&gt;-->
            <!--&lt;!&ndash;<template v-slot:activator="{ on }">&ndash;&gt;-->
                <!--&lt;!&ndash;<v-list-item-title v-on="on">&ndash;&gt;-->
                  <!--&lt;!&ndash;Run Incentives&ndash;&gt;-->
                  <!--&lt;!&ndash;<v-icon class="float-right">mdi-menu-right</v-icon>&ndash;&gt;-->
                <!--&lt;!&ndash;</v-list-item-title>&ndash;&gt;-->
            <!--&lt;!&ndash;</template>&ndash;&gt;-->

            <!--&lt;!&ndash;<v-list-item dense link to="">&ndash;&gt;-->
              <!--&lt;!&ndash;<v-list-item-content>&ndash;&gt;-->
                <!--&lt;!&ndash;<v-list-item-title>Free Bottles Incentives</v-list-item-title>&ndash;&gt;-->
              <!--&lt;!&ndash;</v-list-item-content>&ndash;&gt;-->
            <!--&lt;!&ndash;</v-list-item>&ndash;&gt;-->

            <!--&lt;!&ndash;<v-list-item dense link to="">&ndash;&gt;-->
              <!--&lt;!&ndash;<v-list-item-content>&ndash;&gt;-->
                <!--&lt;!&ndash;<v-list-item-title>Free Bedcodes</v-list-item-title>&ndash;&gt;-->
              <!--&lt;!&ndash;</v-list-item-content>&ndash;&gt;-->
            <!--&lt;!&ndash;</v-list-item>&ndash;&gt;-->

            <!--&lt;!&ndash;<v-list-item dense link to="">&ndash;&gt;-->
              <!--&lt;!&ndash;<v-list-item-content>&ndash;&gt;-->
                <!--&lt;!&ndash;<v-list-item-title>DRI</v-list-item-title>&ndash;&gt;-->
              <!--&lt;!&ndash;</v-list-item-content>&ndash;&gt;-->
            <!--&lt;!&ndash;</v-list-item>&ndash;&gt;-->

            <!--&lt;!&ndash;<v-list-item dense link to="">&ndash;&gt;-->
              <!--&lt;!&ndash;<v-list-item-content>&ndash;&gt;-->
                <!--&lt;!&ndash;<v-list-item-title>PLA</v-list-item-title>&ndash;&gt;-->
              <!--&lt;!&ndash;</v-list-item-content>&ndash;&gt;-->
            <!--&lt;!&ndash;</v-list-item>&ndash;&gt;-->
          <!--&lt;!&ndash;</v-menu>&ndash;&gt;-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->
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

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Transactions</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>DRI Daily Incentives</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>PLA Incentives</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>ADC Free Bottles Inventory</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>ADC Free Bedcode</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Free Bottles Incentive</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

      <!--<v-list-item link to="">-->
        <!--<v-list-item-content>-->
          <!--<v-list-item-title>Daily Payout Report</v-list-item-title>-->
        <!--</v-list-item-content>-->
      <!--</v-list-item>-->

    </v-list-group>
  </v-list>
</template>

<script>
  export default {
    
  }
</script>

<style lang="scss">

</style>