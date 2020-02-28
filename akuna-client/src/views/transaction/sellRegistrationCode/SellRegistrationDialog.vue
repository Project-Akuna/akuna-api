<template>
    <section class="sell-registration-code">

        <h3 class="d-block">Sell</h3>
        <v-breadcrumbs class="d-block pa-0" :items="breadcrumbItems">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card class="views-container ma-0 mt-5" style="padding: 20px">
            <v-form ref="warehousingAddDeliveryForm" lazy-validation>
                <!-- Inventory Type -->
                <v-col cols="12" md="6">
                    <v-select
                            dense
                            label="Select"
                            item-text="name"
                            :items="depotList"
                            item-value="id"
                            required
                            v-model="regCodeDetails.depotId"
                    ></v-select>
                </v-col>
                <!-- Delivery Code -->
                <v-col cols="12" md="6">
                    <v-text-field
                            dense
                            type="number"
                            clearable
                            :counter="7"
                            label="Code Quantity"
                            v-model="regCodeDetails.quantity"
                    ></v-text-field>
                </v-col>
                <v-col cols="12" md="6">
                    <v-select
                            dense
                            label="Product"
                            :items="products"
                            item-value="id"
                            v-model="regCodeDetails.productId"
                    >
                        <template slot="selection" slot-scope="data">
                            {{ data.item.name}} - {{data.item.flavor}}
                        </template>
                        <template slot="item" slot-scope="data">
                            {{ data.item.name}} - {{data.item.flavor}}
                        </template>
                    </v-select>
                </v-col>
                <v-btn class="primary" @click="generateRegCode">Submit</v-btn>
            </v-form>
        </v-card>
    </section>
</template>
<script>
    import {mapState} from 'vuex'
    export default {
        name: "sellRegistrationCodeAdd",
        computed: mapState({
            axiosURL: 'axiosURL'
        }),
        data () {
            return {
                breadcrumbItems: [
                    {
                        text: "Transaction",
                        disabled: true
                    },
                    {
                        text: 'Sell Registration Code',
                        disabled: false,
                        to: '/sell-registration-code'
                    },
                    {
                        text: 'Sell',
                        disabled: true
                    }
                ],
                regCodeDetails: {},
                adcList: [],
                depotList: [],
                products: [],
                sellDetails: {}
            }
        },

        created() {
            this.getAllDepot();
            this.getProducts();
        },
        methods: {
            getProducts() {
                let self = this;
                self.axios.get(self.axiosURL + 'api/product/get-all-products', {
                    auth: {
                        auth: self.$session.get('auth')
                    }
                })
                    .then(function (response) {
                        self.products = response.data.payload;
                    })
                    .catch(function (error) {
                        self.$swal('Something Went Wrong', 'Contact your System Administrators', 'error');
                    })
            },
            generateRegCode() {
                let self = this;
                let auth = this.$session.get('account');
                let url;
                self.regCodeDetails.addedBy = self.$session.get('account').username;
                self.regCodeDetails.amount = parseInt(self.regCodeDetails.quantity) * 1900;
                self.regCodeDetails.soldBy = self.$session.get('account').username;

                if (auth.roles[0].name == 'ROLE_SYSADMIN') {
                    url = 'api/registration/generate-codes-depot';
                } else if (auth.roles[0].name == 'ROLE_DEPOT') {
                    url = 'api/registration/generate-codes-adc';
                }

                // Axios call for adc
                self.axios.post(self.axiosURL + url, {
                        auth: {
                            auth: self.$session.get('auth')
                        }
                    },
                    {
                        data: self.regCodeDetails
                    })
                    .then(function (response) {
                        self.$swal('Success', 'Successfully sold Registration Codes', 'success');
                    })
                    .catch(function (error) {
                        self.$swal('Something Went Wrong', 'Contact your System Administrators', 'error');
                    })
            },
            getAllDepot() {
                let self = this;
                let auth = this.$session.get('account');
                let url;

                if (auth.roles[0].name == 'ROLE_SYSADMIN') {
                    url = 'api/depot/get-all-depot';
                } else if (auth.roles[0].name == 'ROLE_DEPOT') {
                    url = 'api/adc/get-all-adc'
                }

                self.axios.get(this.axiosURL + url, {
                    auth: this.$session.get('auth')
                }).then(response => {
                    self.depotList = response.data.payload
                }).catch(response => {
                    self.$swal('Something Went Wrong', 'Contact your System Administrators', 'error');
                })
            },
        }
    }
</script>
<style lang="scss">

</style>