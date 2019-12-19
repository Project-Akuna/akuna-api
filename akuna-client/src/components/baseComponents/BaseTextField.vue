<template lang="pug">
  v-text-field(
    :label="textFieldLabel"
    dense
    :disabled="disabled"
    )
</template>
<script>
export default {
  props: ['textFieldLabel','disabled'],
}
</script>
<style lang="scss">

</style>