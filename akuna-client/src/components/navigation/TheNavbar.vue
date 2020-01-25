<template lang="pug">
  section.navigation
    v-navigation-drawer(
      v-model="drawer" 
      app)
      v-list(dense nav)
        
        v-list-item(link dense) 
          v-list-item-action
            v-icon mdi-home
          v-list-item-content
            v-list-item-title Dashboard

        v-list-item(link dense)
          v-list-item-action
            v-icon mdi-contact-mail
          v-list-item-content
            v-list-item-title Contact

        v-list-group(no-action)
          template(v-slot:activator)
            v-list-item-action
              v-icon mdi-home
            v-list-item-content
              v-list-item-title Transactions

          v-list-item(link dense)
            v-list-item-content
              v-list-item-title Current
    v-app-bar(
      app 
      color="white")
      v-app-bar-nav-icon(@click.stop="drawer = !drawer")
      v-toolbar-title Akuna
</template>
<script>
export default {
  data() {
    return {
      drawer: null
    }
  }
}
</script>
<style lang="scss">

</style>