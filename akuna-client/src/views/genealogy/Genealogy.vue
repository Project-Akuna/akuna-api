<template>
    <section>
        <h3 class="pb-4 d-inline-block">Genealogy</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-inline-block pa-0 float-right">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card>
            <div id="tree" ref="tree"></div>
        </v-card>
    </section>
</template>

<script>
    import OrgChart from '@balkangraph/orgchart.js/orgchart';

    export default {
        name: "Genealogy",
        data () {
            return {
                breadcrumbItems: [
                    {
                        text: "Member's Files",
                        disabled: true,
                        href: '',
                    },
                    {
                        text: 'Members',
                        disabled: false,
                        to: '/members',
                    },
                    {
                        text: 'Genealogy',
                        disabled: false,
                        href: '',
                    },
                ]
            }
        },
        methods: {
            oc: function (domEl, x) {
                this.chart = new OrgChart(domEl, {
                    template : 'polina',
                    nodes: x,
                    nodeBinding: {
                        field_0: "name",
                        field_1: "username",
                        field_2: "date"
                    }
                });

            },
            getGenealogy() {
                let username = this.$route.params.username;
                this.axios.get('http://localhost:3000/api/genealogy/get-genealogy/' + username, {
                    auth: {
                        username: 'asd',
                        password: 'asd'
                    }
                })
                    .then(response => {
                        this.oc(this.$refs.tree, response.data.payload)
                    })
                    .catch(function (error) {

                    })
            }
        },
        mounted() {
            this.getGenealogy();
        }
    }
</script>

<style scoped>

</style>