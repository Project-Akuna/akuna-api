<template>
    <section class="adc-view">
        <v-btn class="float-right primary" to="/adc/add"><v-icon class="pr-1" small>mdi-plus</v-icon>Add ADC</v-btn>
        <h3 class="d-block">ADC</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card class="views-container ma-0 mt-5">
            <v-data-table
                :headers="headers"
                :items="adcList"
                multi-sort
            >
                <template v-slot:item.isSelling="{ item } ">
                    <span>{{ checkIfSelling(item.isSelling) }}</span>
                </template>
            </v-data-table>
        </v-card>
    </section>
</template>
<script>
    import {mapState} from 'vuex'

    export default {
        data() {
            return {
                isSelling : false,
                adcList: [],
                headers: [
                    {
                        text: 'ID',
                        value: 'id'
                    },
                    {
                        text: 'Code',
                        value: 'code'
                    },
                    {
                        text: 'Name',
                        value: 'name'
                    },
                    {
                        text: 'Address',
                        value: 'address'
                    },
                    {
                        text: 'City',
                        value: 'city.name'
                    },
                    {
                        text: 'Landline 1',
                        value: 'landline1'
                    },
                    {
                        text: 'Mobile 1',
                        value: 'mobile1'
                    },
                    {
                        text: 'Email',
                        value: 'email'
                    },
                    {
                        text: 'Status',
                        value: 'isSelling'
                    }
                ],
                breadcrumbItems: [
                    {
                        text: "Member's Files",
                        disabled: true,
                        href: '',
                    },
                    {
                        text: 'ADC',
                        disabled: false,
                        href: '',
                    },
                ],
            }
        },
        computed: mapState({
            axiosURL: 'axiosURL'
        }),
        methods: {
            getAdcList() {
                this.axios.get(this.axiosURL + 'api/adc/get-all-adc', {
                    auth: this.$session.get('auth')
                })
                    .then(response => {
                        this.adcList = response.data.payload;
                        console.log(this.adcList);
                    })
                    .catch(response => {
                        this.$swal('Opssss! Something went wrong', response.data.message, 'error');
                    })
            },
            checkIfSelling(isSelling) {
                return isSelling ? 'Active' : 'Inactive'
            }
        },
        mounted() {
            this.getAdcList();
        }
    }
</script>
<style lang="scss">

</style>