<template>
    <section class="warehousing">
        <v-btn class="float-right primary" to="warehousing/add"><v-icon class="pr-1" small>mdi-plus</v-icon>Add Warehousing Delivery</v-btn>
        <h3 class="d-block">Warehousing</h3>
        <v-breadcrumbs class="d-block pa-0" :items="breadcrumbItems">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-row >
            <v-col v-for="(inventory, i) in inventories"
                   :key="i" class="mb-3" cols="12" md="4">
                <v-card class="warehousing-card views-container">
                    <h4 class="warehousing-card__title"> Remaining {{inventory.inventoryType}} Bottles</h4>
                    <p class="warehousing-card__description"><v-icon class="ml-0">mdi-square-small</v-icon>75 Mint bottles</p>
                    <p class="warehousing-card__description"><v-icon>mdi-square-small</v-icon>75 Grape bottles</p>
                    <v-card class="warehousing-card__value">{{inventory.quantity}} <span>pieces</span></v-card>
                </v-card>
            </v-col>
        </v-row>
        <v-card class="views-container ma-0 mt-5">
            <h3 class="table-title">Warehousing History</h3>
            <v-data-table
                    :headers="headers"
                    :items="history"
                    multi-sort
            >
            </v-data-table>
        </v-card>
    </section>
</template>
<script>
    import { mapState } from 'vuex';
    export default {
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
                        href: '',
                    },
                ],
                inventories : [],
                history : [],
                headers : [
                    {
                        text: 'ID',
                        value: 'id'
                    },
                    {
                        text: 'Beginning Quantity',
                        value: 'beginningQuantity'
                    },
                    {
                        text: 'Delivery Quantity',
                        value: 'deliveryQuantity'
                    },
                    {
                        text: 'Ending Quantity',
                        value: 'endingQuantity'
                    },
                    {
                        text: 'Action',
                        value: ''
                    }
                ],
                warehousingList : []
            }
        },
        methods : {
            getInventory() {
                let self = this
                this.axios.get(this.axiosURL + 'api/inventory/get-user-inventory/' + this.$session.get('account').username, {
                    auth: self.$session.get('auth')
                })
                    .then(response => {
                        this.inventories = response.data.payload;
                    })
                    .catch(function (error) {

                    });
            },
            getHistory() {
                let self = this
                this.axios.get(this.axiosURL + 'api/inventory/get-history/' + this.$session.get('account').username, {
                    auth: self.$session.get('auth')
                }).then(response => {
                    this.history = response.data.payload;
                    console.log(response.data.payload)
                }).catch(function (error) {
                    console.log(error)
                });
            }
        },
        mounted () {
            this.getInventory();
            this.getHistory()
        }
    }
</script>
<style lang="scss">
    .warehousing {
    .warehousing-card {
        position: relative;
        top: 20px;
        padding: 16px 140px 16px 20px;
        margin: 00 0;
        height: 100%;

    &__title {
         font-weight: 600;
         padding: 5px 0;
         margin-bottom: 2px;
         color: $c-dark-gray;
     }

    &__description {
         position: relative;
         left: -8px;
         margin-bottom: 0;
         color: $c-text;
         font-weight: 400;
         font-size: 14px;
         line-height: 24px;
     }

    &__value {
         position: absolute;
         top: -20px;
         right: 20px;
         height: 100px;
         width: 100px;
         background: $c-primary !important;
         display: flex;
         flex-direction: column;
         align-items: center;
         justify-content: center;
         color: white !important;
         font-size: 36px;
         line-height: 36px;
         font-weight: 600;
         margin-bottom: 0;

         box-shadow: 0 2px 8px 0 rgba(0,0,0,.30) !important;

    span {
        font-size: 14px;
        line-height: 16px;
        font-weight: 300;
    }
    }
    }
    }
</style>