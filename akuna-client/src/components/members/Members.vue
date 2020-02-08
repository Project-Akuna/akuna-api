<template>
    <v-data-table
            :headers="headers"
            :items="users"
            multi-sort
            class="elevation-1"
    ></v-data-table>
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