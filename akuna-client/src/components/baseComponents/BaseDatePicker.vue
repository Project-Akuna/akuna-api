<template lang="pug">
  v-dialog(
    ref="dialog" 
    v-model="modal"
    :return-value.sync="date"
    persistent
    width="290px")
    template(v-slot:activator="{ on }")
      v-text-field(
        v-model="date"
        label="Birthday"
        readonly
        v-on="on"
      )
    v-date-picker(v-model="date" scrollable)
      v-spacer
      v-btn(
        text
        color="red"
        @click="modal = false"
      ) Cancel
      v-btn(
        text
        color="primary"
        @click="$refs.dialog.save(date)"
      ) OK
</template>
<script>
export default {
  data() {
    return {
      modal: false,
      date: null,
    }
  }
}
</script>
<style lang="scss">

</style>