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
                let self = this;
                this.chart = new OrgChart(domEl, {
                    template : 'polina',
                    nodes: x,
                    nodeBinding: {
                        field_0: "name",
                        field_1: "username",
                        field_2: "date",
                        field_3: '<button class="genealogy-add-member-btn">Add Member</button>'
                    },
                    tags:{
                        Available:{
                            nodeMenu:{
                                add: {
                                    text: "<text style='font-family: Century Gothic;' fill='#ffffff'>Register Here</text>",
                                    icon: "<span class='fa fa-plus'></span>"
                                }
                            }
                        }
                    }
                });

                this.chart.on('click', function (sender, node) {
                    setTimeout(function() {
                        document.getElementsByClassName('genealogy-add-member-btn').item(0).addEventListener("click", function() {
                            self.$router.replace('/signup')
                        })
                    }, 500)
                    
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
            },
            addMember() {
                console.log("Add")
            }
        },
        mounted() {
            this.getGenealogy();
            // console.log(document.getElementsByClassName('genealogy-add-member-btn'))
        }
    }
</script>

<style lang="scss">
.genealogy-add-member-btn {
    color: white !important;
    border-radius: 8px;
    padding: 8px 20px;
    text-decoration: none;
    background-color: $c-primary;

}
</style>