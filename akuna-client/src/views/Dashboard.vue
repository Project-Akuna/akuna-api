<template>
  <section class="dashboard">
    <v-row align="center" justify="center" v-if="checkRoleExists([1])">
      <v-col cols="12" md="4">
        <v-card  class="views-container dashboard-wallet">
          <v-card dark class="views-container dashboard-wallet-inner">
            <span>₱</span>16,349.00
          </v-card>

          Wallet Balance
        </v-card>
      </v-col>
    </v-row>
  </section>
</template>

<script>
import {mapState} from 'vuex'
export default {
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  components: {
  },
  methods: {
    checkRoleExists(roles) {
      let hasRole = false
      roles.forEach( (role) => {
        if (this.$session.get('account').roles.some((v)=>{ return v.id == role })) hasRole = true
      })
      return hasRole
    },
  },
  mounted() {
    // this.axios.get(this.axiosURL + 'api/product/get-all-products', {
    //     auth: self.$session.get('auth')
    //   })
    //   .then(response => {
    //     self.productsList = response.data.payload;
    //   })
    //   .catch(function (error) { });
  }
}
</script>
<style lang="scss">
.dashboard {
  .dashboard-wallet {
    text-align: center;
    padding: 0 16px 20px;

    &-inner {
      
      background-color: $c-primary !important;
      padding: 16px 20px;
      font-size: 40px;
      margin: 0;
      top: -16px;
      font-weight: 900;

      span {
        font-size: 20px;
        font-weight: 300;
        padding-right: 8px;
      }
    }
  }
}
</style>
