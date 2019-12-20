<template lang="pug">
  v-text-field(
    clearable
    :label="textFieldLabel"
    dense
    :disabled="disabled"
    :counter="counter"
    :type="type"
    :suffix="suffix",
    :rules="rulesList"
    )
</template>
<script>
export default {
  props: ['textFieldLabel','disabled','counter','type','suffix','rulesList'],
  data() {
    return {
      rules: {
        required: value => !!value || 'Required.',
        counter: value => value.length <= this.counter || 'Max '+this.counter+' characters.',
        email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          },
      }
    }
  }
}
</script>
<style lang="scss">

</style>