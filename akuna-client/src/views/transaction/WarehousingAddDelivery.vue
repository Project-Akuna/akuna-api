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
        <v-row class="py-6">
          <!-- Text field -->
          <!-- <v-col class="px-8 mr-8" cols="12" md="6">
            <v-text-field
              v-model="deliveryDetails.date"
              clearable
              :counter="100"
              label="First Name"
            ></v-text-field>
          </v-col> -->

          <!-- Delivery Date Time Picker -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-dialog
              ref="deliveryDateModalRef"
              v-model="deliveryDateModal"
              :return-value.sync="deliveryDetails.date"
              persistent
              width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  dense
                  v-model="deliveryDetails.date"
                  label="Delivery Date"
                  readonly
                  v-on="on"
                  :rules="customRules('Delivery Date',{ required: true })"
                ></v-text-field>
              </template>
              <v-date-picker v-model="deliveryDetails.date" scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="deliveryDateModal = false">Cancel</v-btn>
                <v-btn text color="primary" @click="$refs.deliveryDateModalRef.save(deliveryDetails.date)">OK</v-btn>
              </v-date-picker>
            </v-dialog>
          </v-col>

          <!-- Delivery Code -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-text-field
              dense
              v-model="deliveryDetails.code"
              clearable
              :counter="100"
              label="Delivery Code"
              :rules="customRules('Delivery Code',{ required: true, counter: 100 })"
            ></v-text-field>
          </v-col>

          <!-- Beginning -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-text-field
              dense
              v-model="deliveryDetails.beginning"
              disabled
              label="Beginning"
            ></v-text-field>
          </v-col>

          <!-- Ending -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <v-text-field
              dense
              v-model="deliveryDetails.ending"
              disabled
              label="Ending"
            ></v-text-field>
          </v-col>

          <!-- Products heading -->
          <v-col class="delivery-form__input-container" cols="12" md="6">
            <h4 class="d-inline font-weight-medium grey--text text--darken-3 pb-3 pt-5">Products</h4>
            <v-btn class="d-inline ml-5 primary" @click="productsCounter++">Add Product</v-btn>
          </v-col>

          <!-- Products -->
          <v-col class="delivery-form__input-container pt-5" cols="12" md="6">
            <v-card 
              class="products-card"
              v-for="(item,i) in productsCounter"
              :key="i"
            >
              <v-select
                ref="productName"
                dense
                label="Product"
                :items="productsList"
                :rules="customRules('Product',{ required: true })"
              ></v-select>

              <!-- Price -->
              <v-text-field
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
                ref="productQuantity"
                type="number"
                value=""
                dense
                clearable
                label="Quantity"
                :rules="customRules('Quantity',{ required: true })"
                @input="computeEnding"
              ></v-text-field>
            </v-card>
          </v-col>

          <!-- Form Buttons -->
          <v-col class="delivery-form__input-container pt-5 text-right" cols="12" md="6">
            <v-btn class="mr-4" depressed color="white">Cancel</v-btn>
            <v-btn class="primary" @click="showProducts">Submit</v-btn>
          </v-col>

        </v-row>
      </v-form>
    </v-card>
  </section>
</template>
<script>
import FormMixin from '../../mixins/formMixin'

export default {
  mixins: [ FormMixin ],
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
      productsList: ['Grape','Mint'],
      deliveryDetails: {
        date: new Date().toISOString().substr(0, 10),
        beginning: 750,
        ending: 1000
      },
      valid: false,
      deliveryDateModal: false,
      productsCounter: 1

    }
  },
  methods: {
    showProducts() {
      let productsArray = [];

      this.$refs.productName.forEach( (productName, index) => {
        productsArray.push({ 
          id: index,
          productPrice: this.$refs.productPrice[index].lazyValue,
          productQuantity: this.$refs.productQuantity[index].lazyValue,
          productName:  productName.selectedItems[0] 
          })
      });

      console.log(productsArray);
    },
    computeEnding() {
      let ending = 0;
      this.$refs.productName.forEach( (productName, index) => {
        ending = ending + parseInt(this.$refs.productQuantity[index].lazyValue);
      });

      console.log(ending)
    }
  }
}
</script>
<style lang="scss">
.warehousing-add-delivery {
  .delivery-form__input-container {
    padding: 4px 32px 0 !important;
    margin: 0 30px 0 0;
  }

  .products-card {
     box-shadow: 0 2px 8px 0 rgba(0,0,0,0.18);
     padding: 14px 18px 10px;
     margin-top: 18px;

     &:first-of-type {
       margin-top: 0;
     }
  }
}

</style>