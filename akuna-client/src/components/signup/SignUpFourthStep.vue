<template lang="pug">
  v-stepper-content(step="3")
    v-container.pt-0
      v-row
        //HEADING - Account Info
        v-col.pa-0(cols="12")
          h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 Account Information

        // Username TextField
        v-col.signup__input-container(cols="12")
          v-text-field.signup__username-textfield(
              dense
              required
              clearable
              label="Username"
              counter="100"
              @input="updateAccount({ username: $event})")

        // Password TextField
        v-col.signup__input-container(cols="12")
          v-text-field.signup__password-textfield(
              dense
              required
              clearable
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" 
              @click:append="showPassword = !showPassword"
              :type="showPassword ? 'text' : 'password'"
              label="Password",
              counter="100"
              @input="updateAccount({ password: $event})"
              )

        // Confirm Password TextField
        v-col.signup__input-container(cols="12")
          v-text-field.signup__confirm-password-textfield(
              dense
              required
              clearable
              :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" 
              @click:append="showPassword = !showPassword"
              :type="showPassword ? 'text' : 'password'"
              label="Confirm Password"
              counter="100")

        // Upline Information Buttons
        v-col.signup__account-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
          v-btn(@click="changeStep(2)" depressed background-color="white") Back
          //- v-btn.signup__btn.ml-3(@click="$router.push('/dashboard')") Submit
          v-btn.signup__btn.ml-3(@click="submitAccount") Submit
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'
import BaseRadioGroup from '../baseComponents/BaseRadioGroup'

import SignupMixin from '../../mixins/signupMixin'
import { mapState } from 'vuex';

export default {
  mixins: [SignupMixin],
  data() {
    return {
      showPassword: false
    }
  },
  components: {
    BaseSelect,
    BaseTextField,
    BaseBirthdayPicker,
    BaseRadioGroup
  },
  computed: mapState({
    signupAccount: 'signupAccount',
    axiosURL: 'axiosURL'
    
  }),
  methods: {
    submitAccount() {
      let self = this;

      if (typeof this.$route.params.regID === 'undefined') { // Reg ID is present
        self.updateAccount({ regCode: this.$route.params.regID })
      } 

      self.updateAccount({isEnabled: 1});
      self.updateAccount({roles: [{id: 3}]});

      // Axios post request for saving account
      this.axios.post(self.axiosURL+'api/user/save', {
        auth: {
          username: 'asd',
          password: 'asd'
        },
      },
      {
        data : self.signupAccount
      })
      .then( response => {
        self.loginAccount();
      })
      .catch( response => {
        this.$swal('Opssss! Something went wrong', response.data, 'error');
        console.log(response)
      })
    },
    loginAccount() {
      let self = this;
      this.axios({
        method: 'post',
        url: self.axiosURL+ 'api/login',
        auth: {
          username: self.signupAccount.username ,
          password: self.signupAccount.password
        }
      })
      .then(response => {
        if (response.status === 200) {
          this.$session.start()
          this.$session.set('session', response.payload);
          this.$session.set('auth', {
            username: self.signupAccount.username,
            password: self.signupAccount.password
          });

          this.$router.replace('/dashboard');
        }
      })
      .catch(response => {
        this.$swal('Opssss! Sorry you can\'t login', response.data, 'error');
      });
    }
  },
}
</script>
<style lang="scss">

</style>