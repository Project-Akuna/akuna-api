<template lang="pug">
  v-menu(
    ref="menu"
    v-model="menu"
    :close-on-content-click="false"
    transition="scale-transition"
    offset-y
    full-width
    min-width="290px")

    template(v-slot:activator="{ on }")
      v-text-field.base-birthday-picker-textfield(
        v-model="date"
        label="Birthday"
        readonly
        v-on="on"
        clearable
      )
    
    v-date-picker(
      ref="picker"
      v-model="date" 
      :max="new Date().toISOString().substr(0, 10)"
      min="1920-01-01"
      @change="save")
</template>
<script>
export default {
  data() {
    return {
      menu: false,
      date: null,
    }
  },
  watch: {
    menu (val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
    }
  },
  methods: {
    save (date) {
      this.$refs.menu.save(date)
      this.$emit('onSave', date)
    },
  },
}
</script>
<style lang="scss" scoped>
.base-birthday-picker-textfield {
  padding-top: 0;
}
</style>