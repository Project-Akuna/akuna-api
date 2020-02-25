<template lang="pug">
  section.navigation
    v-navigation-drawer(
      :clipped="$vuetify.breakpoint.lgAndUp"
      v-model="drawer" 
      app
      )
      v-list(dense nav)

        // Plain List item
        v-list-item(v-for="item in navItems" link dense :to="item.link" v-if="checkRole(item.rolesVisible)")
          v-list-item-action
            v-icon {{item.icon}}
          v-list-item-content
            v-list-item-title {{item.title}}

        
        // Master Files List Group
        v-list-group(no-action v-for="mainItem in navGroupItems" v-if="checkRole(mainItem.rolesVisible)")
          template(v-slot:activator)
            v-list-item-action
              v-icon {{mainItem.icon}}
            v-list-item-content
              v-list-item-title {{mainItem.title}}

          // List item link only
          v-list-item(
            link 
            dense 
            v-for="groupItem in mainItem.groupItems" 
            :to="groupItem.link" 
            v-if="checkRole(groupItem.rolesVisible) && !groupItem.hasOwnProperty('subItems')")
            v-list-item-content
              v-list-item-title {{groupItem.title}}

          // List item with dropdown
          v-list-item(
            link 
            dense
            v-for="groupItem in mainItem.groupItems" 
            :to="groupItem.link" 
            v-if="checkRole(groupItem.rolesVisible) && groupItem.hasOwnProperty('subItems')")

            v-list-item-content
              v-menu(top offset-x open-on-hover v-if="groupItem.hasOwnProperty('subItems')")
                template(v-slot:activator="{ on }")
                  v-list-item-title(v-on="on") {{groupItem.title}}
                    v-icon.float-right mdi-menu-right
                
                v-list
                  v-list-item(
                    dense 
                    v-for="subItem in groupItem.subItems" 
                    :to="subItem.link"
                    v-if="checkRole(subItem.rolesVisible)"
                    )
                    v-list-item-title {{subItem.title}}

    v-app-bar(
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      dark
      color="primary"
      )
      v-app-bar-nav-icon(@click.stop="drawer = !drawer")
      v-toolbar-title Wellness Connection
      v-spacer
      v-btn.mr-2(icon large @click="logout")
        v-avatar(size="32px" item)
          v-img(src="https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png" alt="Account")
</template>
<script>
export default {
  data() {
    return {
      drawer: null,
      navGroupItems: [
        {
          icon: 'mdi-account',
          title: 'Member Profile',
          rolesVisible: [2],
          groupItems: [
            {
              title: 'Genealogy',
              link: '/genealogy',
              rolesVisible: [2]
            }
          ]
        },
        {
          icon: 'mdi-folder-text',
          title: 'Master Files',
          rolesVisible: [1],
          groupItems: [
            {
              title: 'Paylite Member Type',
              link: '',
              rolesVisible: [1],
            },
            {
              title: 'Member Package',
              link: '',
              rolesVisible: [1],
            },
            {
              title: 'Product Information',
              link: '',
              rolesVisible: [1],
            },
            {
              title: 'Alveo Inventory',
              link: '',
              rolesVisible: [1],
            },
          ]
        },
        {
          icon: 'mdi-account-group',
          title: "Member's File",
          rolesVisible: [1,3,4],
          groupItems: [
            {
              title: 'Admin Accounts',
              link: '',
              rolesVisible: [1],
            },
            {
              title: 'ADC Admin Accounts',
              link: '',
              rolesVisible: [4],
            },
            {
              title: 'Depot',
              link: '/depot',
              rolesVisible: [1],
            },
            {
              title: 'ADC',
              link: '/adc',
              rolesVisible: [1],
            },
            {
              title: 'Cashier',
              link: '/cashiers',
              rolesVisible: [1,3,4],
            },
            {
              title: 'Member',
              link: '/members',
              rolesVisible: [1,3,4],
            },
          ]
        },
        {
          icon: 'mdi-newspaper-variant-multiple',
          title: 'Transaction',
          rolesVisible: [1,3,4],
          groupItems: [
            {
              title: 'Warehousing',
              link: '/warehousing',
              rolesVisible: [1]
            },
            {
              title: 'Sell Registration Code',
              link: '/sell-registration-code',
              rolesVisible: [1,3,4],
            },
            {
              title: 'ADC Free Bottles',
              link: '',
              rolesVisible: [1,4],
            },
            {
              title: 'Replenish',
              link: '',
              rolesVisible: [1],
            },
            {
              title: 'Qualifier / PLA',
              link: '',
              rolesVisible: [1],
            },
            {
              title: 'Claim Incentives',
              rolesVisible: [3,4],
              subItems: [
                {
                  title: 'Claim Free Bottles',
                  link: '',
                  rolesVisible: [3,4]
                },
                {
                  title: 'Claim Bed Session',
                  link: '',
                  rolesVisible: [3,4]
                },
              ]
            },
            {
              title: 'Run Incentive',
              rolesVisible: [1],
              subItems: [
                {
                  title: 'Free Bottles Incentives',
                  link: '',
                  rolesVisible: [1]
                },
                {
                  title: 'Free Bedcodes',
                  link: '',
                  rolesVisible: [1]
                },
                {
                  title: 'DRI',
                  link: '',
                  rolesVisible: [1]
                },
                {
                  title: 'PLA',
                  link: '',
                  rolesVisible: [1]
                }
              ]
            },
          ]
        },
        {
          icon: 'mdi-file-chart',
          title: 'Reports',
          rolesVisible: [1,2,3,4],
          groupItems: [
            {
              title: 'Acknowledgement Receipts',
              link: '',
              rolesVisible: [1,2,3,4],
            },
            {
              title: 'Transactions',
              link: '',
              rolesVisible: [1,4],
            },
            {
              title: 'DRI Daily Incentives',
              link: '',
              rolesVisible: [1,2,3],
            },
            {
              title: 'PLA Incentives',
              link: '',
              rolesVisible: [1,2,3],
            },
            {
              title: 'ADC Free Bottles Inventory',
              link: '',
              rolesVisible: [1,2,3,4],
            },
            {
              title: 'ADC Free Bedcode',
              link: '',
              rolesVisible: [1,2,3,4],
            },
            {
              title: 'Free Bottles Incentive',
              link: '',
              rolesVisible: [1,2,3,4],
            },
            {
              title: 'Bedcode Inventory',
              link: '',
              rolesVisible: [1,2,3,4],
            },
            {
              title: 'Daily Payout Report',
              link: '',
              rolesVisible: [1],
            },
            {
              title: 'Wallets',
              rolesVisible: [2],
              subItems: [
                {
                  title: 'Incentive Wallets',
                  link: '',
                  rolesVisible: [2]
                }
              ]
            }
          ]
        },
      ],
      navItems: [
        {
          icon: 'mdi-view-dashboard',
          title: 'Dashboard',
          link: '/dashboard',
          rolesVisible: [1,2,3,4,5]
        },
        {
          icon: 'mdi-account',
          title: 'Admin Profile',
          link: '',
          rolesVisible: [1]
        },
        {
          icon: 'mdi-account',
          title: 'ADC Profile',
          link: '',
          rolesVisible: [4]
        },
        {
          icon: 'mdi-account',
          title: 'Cashier Profile',
          link: '',
          rolesVisible: [3]
        },
      ]
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
    checkRole(navItemRole) {
      if (navItemRole) {
        let isVisible = false
        this.$session.get('account').roles.forEach( (accountRole) => {
          // 1
          if (navItemRole.includes(accountRole.id)) isVisible = true
        })
        return isVisible
      }
    }
  }
}
</script>
<style lang="scss">

</style>