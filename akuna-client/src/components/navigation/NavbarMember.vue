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

    <!-- Member Profile -->
    <v-list-group no-action color="primary">
      <template v-slot:activator>
        <v-list-item-action>
          <v-icon>mdi-account</v-icon>
        </v-list-item-action>
        <v-list-item-content>
          <v-list-item-title>Member Profile</v-list-item-title>
        </v-list-item-content>
      </template>

      
      <v-list-item link :to="'/genealogy/'+$session.get('account').username">
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

    </v-list-group>
  </v-list>
</template>

<script>
  export default {
    
  }
</script>

<style lang="scss">

</style>