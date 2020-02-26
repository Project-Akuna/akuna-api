<template>
  <section class="warehousing-add-delivery">
    <h3 class="d-block">Add Warehousing Delivery</h3>
    <v-breadcrumbs class="d-block pa-0" :items="breadcrumbItems">
      <template v-slot:divider>
        <v-icon>mdi-chevron-right</v-icon>
      </template>
    </v-breadcrumbs>
    <v-card class="views-container ma-0 mt-5">
      <h3 class="table-title">Enter Warehousing Delivery Details</h3>
      <v-form ref="warehousingAddDeliveryForm" v-model="valid" lazy-validation>
        <v-row class="py-6" >
          <!-- Delivery Date Time Picker -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-dialog
              ref="deliveryDateModalRef"
              v-model="deliveryDateModal"
              :return-value.sync="deliveryDetails.deliveryDate"
              persistent
              width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  dense
                  v-model="deliveryDetails.deliveryDate"
                  label="Delivery Date"
                  readonly
                  v-on="on"
                  :rules="customRules('Delivery Date',{ required: true })"
                ></v-text-field>
              </template>
              <v-date-picker v-model="deliveryDetails.deliveryDate" scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="deliveryDateModal = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.deliveryDateModalRef.save(deliveryDetails.deliveryDate)">OK</v-btn>
              </v-date-picker>
            </v-dialog>
          </v-col>

          <!-- Delivery Code -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-text-field
              dense
              v-model="deliveryDetails.deliveryCode"
              clearable
              :counter="100"
              label="Delivery Code"
              :rules="customRules('Delivery Code',{ required: true, counter: 100 })"
            ></v-text-field>
          </v-col>

          <!-- Inventory Type -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-select
              v-model="deliveryDetails.inventoryType"
              dense
              label="Inventory Type"
              :items="inventoryTypeList"
              item-text="name"
              item-value="value"
              :rules="customRules('Inventory Type',{ required: true })"
            ></v-select>
          </v-col>

          <!-- Beginning -->
          <!-- <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-text-field
              dense
              v-model="deliveryDetails.beginning"
              disabled
              label="Beginning"
            ></v-text-field>
          </v-col> -->

          <!-- Ending -->
          <!-- <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-text-field
              dense
              v-model="deliveryDetails.ending"
              disabled
              label="Ending"
            ></v-text-field>
          </v-col> -->

          <!-- Products heading -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <h4 class="d-inline font-weight-medium grey--text text--darken-3 pb-3 pt-5">Products</h4>
            <v-btn class="d-inline ml-5 primary" @click="productsCounter.push(0)">Add Product</v-btn>
          </v-col>

          <!-- Products -->
          <v-col class="delivery-form__input-container pt-5" cols="12" md="6">
            <v-card 
              class="products-card"
              v-for="(item,i) in productsCounter.length"
              :key="i"
            >
              <v-btn v-if="false" class="delete-product-btn" @click="deleteProduct(i)" color="red lighten-1" fab x-small dark>
                {{i}}<v-icon>mdi-delete</v-icon>
              </v-btn>

              <v-select
                class="py-1"
                ref="productName"
                dense
                label="Product"
                :items="productsList"
                item-text="flavor"
                item-value="id"
                :rules="customRules('Product',{ required: true })"
              ></v-select>

              <!-- Price -->
              <v-text-field
                class="py-1"
                ref="productPrice"
                type="number"
                value=""
                dense
                clearable
                label="Price"
                :rules="customRules('Price',{ required: true })"
              ></v-text-field>

              <!-- Quantity -->
              <v-text-field
                class="py-1"
                ref="productQuantity"
                type="number"
                value=""
                dense
                clearable
                label="Quantity"
                :rules="customRules('Quantity',{ required: true })"
              ></v-text-field>
            </v-card>
          </v-col>

          <!-- Form Buttons -->
          <v-col class="delivery-form__input-container pt-5 text-right" cols="12" md="6">
            <v-btn class="mr-4" depressed color="white">Cancel</v-btn>
            <v-btn class="primary" @click="addDelivery">Add Delivery</v-btn>
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
      breadcrumbItems: [
        {
          text: "Transaction",
          disabled: true,
          href: '',
        },
        {
          text: 'Warehousing',
          disabled: false,
          href: '/warehousing',
        },
        {
          text: 'Add Warehousing Delivery',
          disabled: false,
          href: '',
        },
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
      productsList: [],
      deliveryDetails: {
        deliveryDate: new Date().toISOString().substr(0, 10),
      },
      valid: false,
      deliveryDateModal: false,
      productsCounter: [0]

    }
  },
  methods: {
    async addDelivery() {
      let self = this;
      if (this.$refs.warehousingAddDeliveryForm.validate()) {
        // Convert date to ISO date format
        let selectedDate = new Date(self.deliveryDetails.deliveryDate)
        selectedDate.setHours(new Date().getHours())
        selectedDate.setMinutes(new Date().getMinutes())
        selectedDate.setSeconds(new Date().getSeconds())

        let index = 0

        for (let productName of this.$refs.productName) {
          let tempDetails = { ...self.deliveryDetails };

          tempDetails.deliveryDate = selectedDate.toISOString();
          tempDetails.soldTo = self.$session.get('account').username;

          // Add other details to temporary variable
          Object.assign(tempDetails, {
            sellingPrice: this.$refs.productPrice[index].lazyValue,
            deliveryQuantity: this.$refs.productQuantity[index].lazyValue,
            product: [{id: productName.selectedItems[0].id}]
          });

          // Push object to array
          await self.uploadDeliveryDetails(tempDetails)

          index++;
        }

        self.$router.push('/warehousing')
      }
    },
    uploadDeliveryDetails(details) {
      let self = this;

      this.axios.post(this.axiosURL + 'api/inventory/update-sysadmin', {
        auth: self.$session.get('auth')
      }, 
      {
        data: details
      })
      .then(response => {
        console.log(response);
      })
      .catch(function (error) { 
        self.$swal('Opssss! Something went wrong', error.data, 'error');
      });
    },
    computeEnding() {
      let ending = 0;
      this.$refs.productName.forEach( (productName, index) => {
        ending = ending + parseInt(this.$refs.productQuantity[index].lazyValue);
      });
    },
    getProducts() {
      let self = this;
      this.axios.get(this.axiosURL + 'api/product/get-all-products', {
        auth: self.$session.get('auth')
      })
      .then(response => {
        self.productsList = response.data.payload;
      })
      .catch(function (error) { });
    }
  },
  created() {
    this.getProducts();
  }
}
</script>
<style lang="scss">
.warehousing-add-delivery {
  .delivery-form__input-container {
    padding: 8px 32px 0 !important;
    margin: 0 30px 0 0;
  }

  .products-card {
    box-shadow: 0 2px 8px 0 rgba(0,0,0,0.18);
    padding: 14px 18px 10px;
    margin-top: 18px;

    &:first-of-type {
      margin-top: 0;
    }

    .delete-product-btn {
      position: absolute;
      top: -15px;
      right: -15px;
    }
  }
}

</style>