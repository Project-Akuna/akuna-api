<template lang="pug">
  v-radio-group.mt-0
    v-radio(
      v-for="item in radioGroupItems"
      :key="item"
      :label="item"
      :value="item"
      color="primary"
    )
</template>
<script>
export default {
  props: ['radioGroupItems']
}
</script>
<style lang="scss">
.v-input--radio-group {
  .v-label{
    font-size: 14px;
  }
}
</style>