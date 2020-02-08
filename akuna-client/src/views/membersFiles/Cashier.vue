<template>
    <v-card>
        <h3 class="pb-4 d-inline-block">Cashiers</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-inline-block pa-0 float-right">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-spacer></v-spacer>
        <v-data-table
                :headers="headers"
                :items="cashiers"
                multi-sort
                class="elevation-1"
                :search="search"
        ></v-data-table>
    </v-card>
</template>

<script>
    import { mapState } from 'vuex';
    export default {
        name: "Cashier",
        data() {
            return {
                search: '',
                headers: [
                    {
                        text: 'ID',
                        value: 'id'
                    },
                    {
                        text: 'Username',
                        value: 'username',
                    }
                ],
                cashiers: [],
                breadcrumbItems: [
                    {
                        text: "Member's Files",
                        disabled: true,
                        href: '',
                    },
                    {
                        text: 'Cashiers',
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
            getAllCashier() {
                let self = this;
                this.axios.get(self.axiosURL+ 'api/cashier/get-all-cashier', {
                    auth: this.$session.get('auth')
                })
                    .then(response => {
                        this.cashiers = response.data.payload;
                    })
                    .catch(response => {
                        this.$swal('Opssss! Something went wrong', response.message, 'error');
                    })
            }
        },
        mounted() {
            this.getAllCashier();
        }
    }
</script>

<style scoped>

</style>