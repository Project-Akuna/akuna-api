<template lang="pug">
  v-select(
    :items="selectItems" 
    :label="selectLabel"
    dense
    color="green darken-2"
    )
</template>
<script>
export default {
  props: ['selectItems','selectLabel']
}
</script>
<style lang="scss">

</style>