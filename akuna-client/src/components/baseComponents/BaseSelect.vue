<template lang="pug">
  v-select(
    :items="selectItems" 
    :label="selectLabel"
    dense
    )
</template>
<script>
export default {
  props: ['selectItems','selectLabel']
}
</script>
<style lang="scss">

</style>