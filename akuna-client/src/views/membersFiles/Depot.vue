<template>
    <section>
        <h3 class="pb-4 d-inline-block">Depot</h3>
        <v-breadcrumbs :items="breadcrumbItems" class="d-inline-block pa-0 float-right">
            <template v-slot:divider>
                <v-icon>mdi-chevron-right</v-icon>
            </template>
        </v-breadcrumbs>
        <v-card>
            
        </v-card>
    </section>
</template>

<script>
    export default {
        name: "Depot",
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
                ]
            }
        }
    }
</script>

<style scoped>

</style>