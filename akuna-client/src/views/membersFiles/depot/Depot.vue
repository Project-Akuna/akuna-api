<template>
    <section class="depot-view">
        <v-btn class="float-right primary" to=""><v-icon class="pr-1" small>mdi-plus</v-icon>Add Depot</v-btn>
        <h3 class="d-block">Depot</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card class="views-container ma-0 mt-5">
            <v-data-table
                :headers="headers"
                :items="depotList"
                multi-sort
            >
            </v-data-table>
        </v-card>
    </section>
</template>

<script>
import {mapState} from 'vuex'

export default {
    computed: mapState({
        axiosURL: 'axiosURL'
    }),
    data () {
        return {
            breadcrumbItems: [
                {
                    text: "Member's Files",
                    disabled: true,
                    href: '',
                },
                {
                    text: 'Depot',
                    disabled: false,
                    href: '',
                },
            ],
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
                    value: 'officeAddress'
                },
                {
                    text: 'City',
                    value: 'city.id'
                },
                {
                    text: 'Landline 1',
                    value: 'landLineNumber1'
                },
                {
                    text: 'Mobile 1',
                    value: 'mobileNumber1'
                },
                {
                    text: 'Email',
                    value: 'email'
                },
            ],
            depotList: []
        }
    },
    methods: {
        getDepotList() {
            this.axios.get(this.axiosURL + 'api/depot/get-all-depot', {
                auth: this.$session.get('auth')
            })
                .then(response => {
                    this.depotList = response.data.payload;
                    console.log(response.data.payload)
                })
                .catch(response => {
                    this.$swal('Opssss! Something went wrong', response.data.message, 'error');
                })
        },
    },
    mounted() {
        this.getDepotList();
    }
}
</script>

<style scoped>

</style>