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
                <!-- User List Select -->
                <v-col cols="12" md="6" class="pb-0">
                    <v-select
                        v-if="userRole != 'ROLE_ADC'"
                        dense
                        :label="userListLabel"
                        item-text="name"
                        :items="userList"
                        item-value="id"
                        required
                        v-model="regCodeDetails.userId"
                    ></v-select>
                    <v-select
                        v-if="userRole == 'ROLE_ADC'"
                        dense
                        :label="userListLabel"
                        :items="userList"
                        item-text="username"
                        required
                        v-model="regCodeDetails.username"
                    ></v-select>
                </v-col>
                <!-- Code Quantity -->
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
                <!-- Product List -->
                <v-col cols="12" md="6" class="pt-0">
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
                <v-col cols="12" md="6" class="pt-0">
                    <v-btn class="primary" @click="generateRegCode">Submit</v-btn>
                </v-col>
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
                userList: [],
                products: [],
                sellDetails: {},
                userRole: '',
                userListLabel: '',
            }
        },

        mounted() {
            // set current user role
            this.userRole = this.$session.get('account').roles[0].name;
            if (this.userRole == 'ROLE_SYSADMIN') this.userListLabel = 'Select Depot'
            else if (this.userRole == 'ROLE_DEPOT') this.userListLabel = 'Select ADC'
            else if (this.userRole == 'ROLE_ADC' || this.userRole == 'ROLE_CASHIER') this.userListLabel = 'Select Member'

            this.getUsers();
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
                let url;
                if (this.userRole == 'ROLE_SYSADMIN' || this.userRole  == 'ROLE_DEPOT') {
                    self.regCodeDetails.addedBy = self.$session.get('account').username;
                    self.regCodeDetails.soldBy = self.$session.get('account').username;
                    self.regCodeDetails.depotId = self.regCodeDetails.userId;

                    if (this.userRole == 'ROLE_SYSADMIN') {
                        url = 'api/registration/generate-codes-depot';
                        self.regCodeDetails.amount = parseInt(self.regCodeDetails.quantity) * 1700;
                    } else if (this.userRole  == 'ROLE_DEPOT') {
                        url = 'api/registration/generate-codes-adc';
                        self.regCodeDetails.amount = parseInt(self.regCodeDetails.quantity) * 1800;
                    }
                } else if (this.userRole  == 'ROLE_ADC') {
                    url = 'api/registration/sell-codes-to-user';
                    self.regCodeDetails.amount = parseInt(self.regCodeDetails.quantity) * 1900;
                    self.regCodeDetails.adc = self.$session.get('account').username;
                } 

                // remove userId Property
                delete self.regCodeDetails.userId

                // Axios call for selling registration code
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
                        self.$swal({
                            title: 'Success',
                            text: 'Successfully sold Registration Codes',
                            icon: 'success',
                            onClose: () => {
                                self.$router.push('/sell-registration-code')
                            }
                        })
                    })
                    .catch(function (error) {
                        self.$swal('Something Went Wrong', 'Contact your System Administrators', 'error');
                    })
            },
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
                }).catch(response => {
                    self.$swal('Something Went Wrong', 'Contact your System Administrators', 'error');
                })
            },
        }
    }
</script>
<style lang="scss">

</style>