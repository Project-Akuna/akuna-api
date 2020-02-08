<template>
    <div>
        <v-row>
            <v-col cols="12">
                <h2>Members</h2>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="12">
                <v-data-table
                        :headers="headers"
                        :items="users"
                        multi-sort
                        class="elevation-1">
                    <template v-slot:item.action="{ item }">
                        <v-icon
                                small
                                class="mr-2"
                        >
                            edit
                        </v-icon>
                        <v-icon
                                small
                        >
                            delete
                        </v-icon>
                    </template>
                </v-data-table>
            </v-col>
        </v-row>
    </div>
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
                    },
                    { text: 'Actions', value: 'action', sortable: false }
                ],
                users: [],
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
                        this.$swal('Opssss! Something went wrong', response.data.message, 'error');
                    })
            }
        },
        mounted: function () {
            this.desserts = this.getUsers();
        }
    }
</script>

<style scoped>

</style>