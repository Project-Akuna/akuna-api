<template>
    <section class="members">
        <h3 class="d-block">Members</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card class="views-container ma-0 mt-5">
            <v-data-table
                :headers="headers"
                :items="users"
                multi-sort
            >
                <template v-slot:item="row">
                    <tr>
                        <td>{{row.item.username}}</td>
                        <td>{{row.item.id}}</td>
                        <td>
                            <v-btn fab depressed small color="transparent" @click="onButtonClick(row.item)">
                                <v-icon color="grey darken-2">mdi-dots-vertical</v-icon>
                            </v-btn>
                        </td>
                    </tr>
                </template>
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
    name: "Members",
    data() {
        return {
            headers: [
                {
                    text: 'ID',
                    value: 'id'
                },
                {
                    text: 'Username',
                    value: 'username',
                },
                {
                    text: 'Action',
                    value: '',
                }
            ],
            users: [],
            breadcrumbItems: [
                {
                text: "Member's Files",
                disabled: true,
                href: '',
                },
                {
                text: 'Members',
                disabled: false,
                href: '',
                },
            ],
        }
    },
    methods: {
        getUsers() {
            this.axios.post(this.axiosURL + 'api/user/get-users', {
                auth: {
                    username: 'asd',
                    password: 'asd'
                }
            }, {
                data: {
                    "pageIndex": 0,
                    "pageSize": 10,
                    "sortKey": "id",
                    "sortValue": "ASC"
                }
            })
                .then(response => {
                    this.users = response.data.payload;
                })
                .catch(response => {
                    this.$swal('Opssss! Something went wrong', response.data, 'error');
                })
        },
        onButtonClick(item) {
            this.$router.push("/genealogy/" + item.username);
        }
    },
    mounted: function () {
        this.getUsers();
    }
}
</script>

<style scoped>

</style>