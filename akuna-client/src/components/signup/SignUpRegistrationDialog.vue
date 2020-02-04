<template lang="pug">
  div.d-inline-block
    a(@click.stop="signupDialog = true") Register here.
    v-dialog(v-model="signupDialog" max-width="500")
      v-card.pa-7
        h3.font-weight-black.grey--text.text--darken-2 Do you have Registration ID?
        v-radio-group.signup__registration-dialog-radiogroup(v-model="signupRadioGroup")
          v-radio(label="Yes" value="yes" color="green darken-2")
          v-text-field.signup__registration-dialog-textfield(
            clearable
            required
            label="Registration ID" 
            color="green darken-2" 
            v-if="signupRadioGroup == 'yes'"
            v-model="registrationID"
            )
          v-radio(label="No" value="no" color="green darken-2")
        div.signup__registration-dialog-btn-container
          v-btn.mr-4(depressed @click="signupDialog=false") Cancel
          v-btn.signup__btn(@click="continueToSignup") Continue

</template>
<script>
export default {
  data() {
    return {
      signupDialog: false,
      signupRadioGroup: "yes",
      registrationID: ''
    }
  },
  methods: {
    continueToSignup() {
      if(this.registrationID == '' && this.signupRadioGroup == 'yes') {
        this.$swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Please enter your Registration ID'
        });
      } else {
        this.$router.push('signup/'+this.registrationID);
        
      }
    }
  }
}
</script>
<style lang="scss">
.signup__registration-dialog-radiogroup .v-input__control {
  flex-grow: 1 !important;
  width: 100% !important;
}

.signup__registration-dialog-textfield label {
  top: 5px !important;

}

.signup__registration-dialog-btn-container {
  text-align: right;
  .signup__btn {
    background: $c-primary !important;
    color: white !important;
    @include shadow-sm-primary;
  }
}
</style>