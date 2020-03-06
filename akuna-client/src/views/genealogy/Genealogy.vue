<template>
    <section>
        <h3 class="d-block">Genealogy</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-block pa-0">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card class="views-container ma-0 mt-5">
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
                        if(node.level > 0) {
                            let level = node.parent.level;
                            let parentArray = Object.keys(sender.nodes).filter( node => ( sender.nodes[node].level == level ))
                            
                            // check if node has less than 3 children and check parent level if every node has less than 3 children
                            if (node.children.length < 3 && !parentArray.some( id => ( sender.nodes[id].children.length < 3 ))) {
                                console.log("pumasok")
                                document.getElementsByClassName('genealogy-add-member-btn').item(0).style.display = 'block'
                                document.getElementsByClassName('genealogy-add-member-btn').item(0).addEventListener("click", function() {
                                    self.$router.replace("/genealogy/add/"+node.id)
                                })
                            } 
                        }
                    }, 100)
                    
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
                        this.$swal('Opssss! Something went wrong', error.data, 'error');
                    })
            },
            addMember() {
                console.log("Add")
            }
        },
        mounted() {
            this.getGenealogy();
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
    display: none;
}
</style>