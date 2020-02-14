<template>
    <v-card class="views-container members">
        <h3 class="pb-4 d-inline-block">Members</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-inline-block pa-0 float-right">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-data-table
                :headers="headers"
                :items="users"
                multi-sort
                class="elevation-1"
        ></v-data-table>
    </v-card>
</template>

<script>
    export default {
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
                this.axios.post('http://localhost:3000/api/user/get-users', {
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
            }
        },
        mounted: function () {
            this.getUsers();
        }
    }
</script>

<style scoped>

</style>