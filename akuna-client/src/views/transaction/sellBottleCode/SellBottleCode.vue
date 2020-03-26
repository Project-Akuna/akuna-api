<template>
  <section class="warehousing-add-delivery">
    <h3 class="d-block">Sell Bottle Codes</h3>
    <v-breadcrumbs class="d-block pa-0" :items="breadcrumbItems">
      <template v-slot:divider>
        <v-icon>mdi-chevron-right</v-icon>
      </template>
    </v-breadcrumbs>
    <v-card class="views-container ma-0 mt-5">
      <h3 class="table-title">Enter Bottle Codes Details</h3>
      <v-form ref="sellBottleCodesForm" v-model="valid" lazy-validation>
        <v-row class="pa-5">
          <v-col cols="12" md="6" class="pb-0 mr-2">
            <v-select
              dense
              :label="userListLabel"
              item-text="name"
              :items="userList"
              item-value="id"
              v-model="bottleCodeDetails.userId"
              :rules="customRules(userListLabel,{ required: true })"
            ></v-select>
          </v-col>

          <!-- Bottle Quantity -->
          <v-col cols="12" md="6" class="mr-2">
            <v-text-field
              dense
              type="number"
              clearable
              :counter="7"
              label="Bottle Quantity"
              v-model="bottleCodeDetails.quantity"
              :rules="customRules('Bottle Quantity',{ required: true })"
            ></v-text-field>
          </v-col>

          <!-- Bottle Type -->
          <v-col cols="12" md="6" class="pt-0 mr-2">
            <v-select
              v-model="bottleCodeDetails.bottleType"
              dense
              label="Bottle Type"
              :items="inventoryTypeList"
              item-text="name"
              item-value="value"
              :rules="customRules('Bottle Type',{ required: true })"
            ></v-select>
          </v-col>

          <!-- Flavor -->
          <v-col cols="12" md="6" class="pt-0 mr-2">
            <v-select
              v-model="bottleCodeDetails.product"
              dense
              label="Product"
              :items="productsList"
              item-text="flavor"
              item-value="id"
              :rules="customRules('Product',{ required: true })"
            ></v-select>
          </v-col>

          <!-- Form Buttons -->
          <v-col class="pt-0 text-right" cols="12" md="6">
            <v-btn class="mr-4" depressed color="white">Cancel</v-btn>
            <v-btn class="primary" @click="sellBottleCodes">Sell Bottle Codes</v-btn>
          </v-col>
        </v-row>
      </v-form>
    </v-card>
  </section>
</template>

<script>
import FormMixin from '../../../mixins/formMixin'

import { mapState } from 'vuex'

export default {
  mixins: [ FormMixin ],
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  data() {
    return {
      valid: false,
      userRole: '',
      breadcrumbItems: [
        {
          text: "Transaction",
          disabled: true
        },
        {
          text: 'Sell Bottle Codes',
          disabled: true,
          to: '/sell-bottle-code'
        }
      ],
      inventoryTypeList: [
        {
          name: 'Kit',
          value: 'KIT' 
        },
        {
          name: 'Incentive',
          value: 'INCENTIVE' 
        },
        {
          name: 'Repeat Purchase',
          value: 'REPEAT_PURCHASE' 
        },
      ],
      bottleCodeDetails: {},
      userListLabel: '',
      userList: [],
      productsList: []
    }
  },
  mounted() {
    // set current user role
    this.userRole = this.$session.get('account').roles[0].name;
    if (this.userRole == 'ROLE_SYSADMIN') this.userListLabel = 'Select Depot'
    else if (this.userRole == 'ROLE_DEPOT') this.userListLabel = 'Select ADC'

    this.getUsers();
    this.getProducts();
  },
  methods: {
    getUsers() {
      let self = this;
      let url;

      if (this.userRole  == 'ROLE_SYSADMIN') {
          url = 'api/depot/get-all-depot';
      } else if (this.userRole  == 'ROLE_DEPOT') {
          url = 'api/adc/get-all-adc'
      } else if (this.userRole  == 'ROLE_ADC') {
          url = 'api/user/get-all-visible'
      }

      self.axios.get(this.axiosURL + url, {
          auth: this.$session.get('auth')
      }).then(response => {
          self.userList = response.data.payload
          console.log(self.userList);
      }).catch(response => {
          self.$swal('Something Went Wrong', 'Contact your System Administrators', 'error');
      })
    },
    getProducts() {
      let self = this;
      this.axios.get(this.axiosURL + 'api/product/get-all-products', {
        auth: self.$session.get('auth')
      })
      .then(response => {
        self.productsList = response.data.payload;
      })
      .catch(function (error) { self.$swal('Opssss! Something went wrong', error.data, 'error'); });
    },
    sellBottleCodes() {
      let self = this;
      if (this.$refs.sellBottleCodesForm.validate()) {
        console.log(self.bottleCodeDetails)
      }
    }
  }
}
</script>

<style lang="scss">

</style>