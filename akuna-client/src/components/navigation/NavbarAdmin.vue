<template>
  <section class="navigation navigation-admin">
    // Plain List item
        v-list-item(v-for="item in navItems" link dense :to="item.link" v-if="checkRole(item.rolesVisible)")
          v-list-item-action
            v-icon {{item.icon}}
          v-list-item-content
            v-list-item-title {{item.title}}
  </section>
</template>

<script>
  export default {
    
  }
</script>

<style lang="scss">

</style>