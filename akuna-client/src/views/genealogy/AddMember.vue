<template>
  <section class="add-cashier-view">
    <h3 class="d-block">Add Member</h3>
    <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
        <template v-slot:divider>
            <v-icon>mdi-chevron-right</v-icon>
        </template>
    </v-breadcrumbs>
    <v-row>
      <v-col md="8" lg="6">
        <signup-stepper class="signup__stepper"></signup-stepper>
      </v-col>
    </v-row>
  </section>
</template>

<script>
import SignupStepper from '@/components/signup/SignupStepper'
  export default {
    components: {
      SignupStepper
    },
    data() {
      return {
        breadcrumbItems: [
          {
            text: "Member Profile",
            disabled: true,
            href: '',
          },
          {
            text: 'Genealogy',
            disabled: false,
            href: '/genealogy/'+ this.$session.get('account').username,
          },
          {
            text: 'Add Member',
            disabled: false,
            href: '',
          },
        ],
      }
    }
  }
</script>

<style lang="scss">
.add-cashier-view {
  .signup__stepper {
    @include shadow-sm;
    border-radius: 12px;
  }
}
</style>