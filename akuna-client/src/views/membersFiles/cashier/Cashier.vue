<template>
    <section class="cashier-view">
        <v-btn class="float-right primary" to="/cashiers/add"><v-icon class="pr-1" small>mdi-plus</v-icon>Add Cashier</v-btn>
        <h3 class="d-block">Cashier</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card class="views-container ma-0 mt-5">
            <v-data-table
                :headers="headers"
                :items="cashiers"
                multi-sort
                :search="search"
            ></v-data-table>
        </v-card>
        
    </section>
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