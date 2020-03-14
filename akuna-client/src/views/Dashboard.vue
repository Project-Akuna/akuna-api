<template>
  <section class="dashboard">
    <v-row align="center" justify="center" v-if="checkRoleExists([1,2])">
      <v-col cols="12" md="4">
        <v-card  class="views-container dashboard-wallet">
          <v-card dark class="views-container dashboard-wallet-inner">
            <span>₱</span>{{wallet}}
          </v-card>

          Wallet Balance
        </v-card>
      </v-col>
    </v-row>
  </section>
</template>

<script>
import {mapState} from 'vuex'

import rolesMixin from '../mixins/rolesMixin'
export default {
  mixins: [rolesMixin],
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  components: {
  },
    data () {
      return {
          wallet: 0
      }
    },
  methods: {
      getWallet() {
          let self = this;
          self.axios.get(self.axiosURL + 'api/user/get-wallet/' + self.$session.get('account').username, {})
              .then(response => {
              self.wallet = response.data.payload.amount;
          }).catch(response => {
              self.$swal('Something Went Wrong', 'Contact your System Administrators', 'error');
          })
      }
  },
  mounted() {
      this.getWallet();
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
