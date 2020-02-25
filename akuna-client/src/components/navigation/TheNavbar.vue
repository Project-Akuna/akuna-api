<template lang="pug">
  section.navigation
    v-navigation-drawer(
      :clipped="$vuetify.breakpoint.lgAndUp"
      v-model="drawer" 
      app
      )

      navbar-admin(v-if="checkRoleExists([1])")
      navbar-member(v-if="checkRoleExists([2])")
      navbar-cashier(v-if="checkRoleExists([3])")
      navbar-adc(v-if="checkRoleExists([4])")
      navbar-depot(v-if="checkRoleExists([5])")

    v-app-bar(
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      dark
      color="primary"
      )
      v-app-bar-nav-icon(@click.stop="drawer = !drawer")
      v-toolbar-title Wellness Connection
      v-spacer
      v-btn.mr-2(color="transparent" depressed large @click="logout")
        v-avatar(size="32px" item)
          v-img(src="https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png" alt="Account")
        div.nav-user-info
          h3 {{$session.get('account').username}}
          p {{ parseRole() }}
      
</template>
<script>
import NavbarAdmin from './NavbarAdmin'
import NavbarMember from './NavbarMember'
import NavbarCashier from './NavbarCashier'
import NavbarAdc from './NavbarAdc'
import NavbarDepot from './NavbarDepot'

import rolesMixin from '../../mixins/rolesMixin'

export default {
  mixins: [rolesMixin],
  components: {
    NavbarAdmin,
    NavbarMember,
    NavbarCashier,
    NavbarAdc,
    NavbarDepot
  },
  data() {
    return {
      drawer: null,
    }
  },
  computed: {
    items() {
      return this.$store.state.items;
    },
  },
  methods: {
    logout() {
      this.$session.destroy();
      this.$router.push('/login');
    },
    parseRole() {
      let role = this.$session.get('account').roles[0].id
      if (role == 1 ) return 'System Admin'
      if (role == 2 ) return 'Member'
      if (role == 3 ) return 'Cashier'
      if (role == 4 ) return 'ADC'
      if (role == 5 ) return 'Depot'
    }
  }
}
</script>
<style lang="scss">
// .d-flex.align-middle.flex-column.h-100.text-left.pl-5.nav-user-info
.nav-user-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: left;
  padding-left: 20px;
  

  h3 {
    text-transform: none;
    font-size: 14px;
    font-weight: 500;
  }

  p {
    margin: 4px 0 0 !important;
    font-size: 11px;
    font-weight: 400;
    text-transform: capitalize;
  }
}
</style>