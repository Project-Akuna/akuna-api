<template>
    <div>
        <div id="tree" ref="tree"></div>
    </div>
</template>

<script>
    import OrgChart from '@balkangraph/orgchart.js/orgchart';

    export default {
        name: "Genealogy",
        data() {
            return {
                nodesx: []
            }
        },
        methods: {
            oc: function (domEl, x) {
                this.chart = new OrgChart(domEl, {
                    template : 'olivia',
                    nodes: x,
                    nodeBinding: {
                        field_0: "name",
                        field_1: "username"
                    }
                });

            },
            getGenealogy() {
                this.axios.get('http://localhost:3000/api/genealogy/get-genealogy/zxc', {
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