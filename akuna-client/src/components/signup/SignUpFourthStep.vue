<template lang="pug">
  v-stepper-content(:step="getRouteName() == 'addCashier' ? '3' : '4'")
    v-container.pt-0
      v-form(ref="signupFourthStepForm" v-model="valid" lazy-validation)
        v-row
          //HEADING - Account Info
          v-col.pa-0(cols="12")
            h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3 Account Information

          // Username TextField
          v-col.signup__input-container(cols="12")
            v-text-field.signup__username-textfield(
                v-model="accountInfo.username"
                dense
                required
                clearable
                label="Username"
                counter="100"
                :rules="customRules('Username',{ required: true, counter: 100 })"
                )

          // Password TextField
          v-col.signup__input-container(cols="12")
            v-text-field.signup__password-textfield(
                v-model="accountInfo.password"
                dense
                required
                clearable
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" 
                @click:append="showPassword = !showPassword"
                :type="showPassword ? 'text' : 'password'"
                label="Password",
                counter="100",
                :rules="customRules('Password',{ required: true, counter: 100 })"
                )

          // Confirm Password TextField
          v-col.signup__input-container(cols="12")
            v-text-field.signup__confirm-password-textfield(
                v-model="accountInfo.confirmPassword"
                dense
                required
                clearable
                :append-icon="showConfirmPassword ? 'mdi-eye' : 'mdi-eye-off'" 
                @click:append="showConfirmPassword = !showConfirmPassword"
                :type="showConfirmPassword ? 'text' : 'password'"
                label="Confirm Password"
                counter="100"
                :rules="customRules('Confirm Password',{ required: true, counter: 100, match: accountInfo.password })"
                )

          // Upline Information Buttons
          v-col.signup__account-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
            v-btn(@click="changeStep(3)" depressed background-color="white") Back
            v-btn.signup__btn.ml-3(@click="submitAccount") Submit
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'
import BaseRadioGroup from '../baseComponents/BaseRadioGroup'

import SignupMixin from '../../mixins/signupMixin'
import FormMixin from '../../mixins/formMixin'
import { mapState } from 'vuex';

export default {
  mixins: [SignupMixin, FormMixin],
  data() {
    return {
      showPassword: false,
      showConfirmPassword: false,
      valid: true,
      accountInfo: {}

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

      if (this.$refs.signupFourthStepForm.validate()) {

        if (typeof this.$route.params.regID !== 'undefined') { // Reg ID is present
          self.updateAccount({ regCode: this.$route.params.regID })
        } 

        self.updateAccount({isEnabled: 1});
        self.updateAccount({roles: [{id: self.getRouteName() == 'addCashier' ? 3 : 2}]});

        delete self.accountInfo.confirmPassword;
        self.updateAccount(self.accountInfo)

        console.log(self.signupAccount);

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
      }
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
          this.$session.set('account', response.data.payload);
          this.$session.set('auth', {
            username: self.signupAccount.username,
            password: self.signupAccount.password
          });

          self.clearAccount();

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