<template lang="pug">
  v-stepper-content(:step="getRouteName() == 'addCashier' ? '3' : '4'")
    v-container.pt-0.signup-stepper-fourth-step
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
          v-col.signup__input-container(cols="12")
            v-switch.account-visible-switch(
              v-if="checkRoleExists([1])"
              v-model="isVisible"
              label="Is Account Visible"
              color="primary"
            )
          // Upline Information Buttons
          v-col.signup__account-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
            v-btn(@click="prevStep()" depressed background-color="white") Back
            v-btn.signup__btn.ml-3(@click="submitAccount") Submit
</template>
<script>
import BaseSelect from '../baseComponents/BaseSelect'
import BaseTextField from '../baseComponents/BaseTextField'
import BaseBirthdayPicker from '../baseComponents/BaseBirthdayPicker'
import BaseRadioGroup from '../baseComponents/BaseRadioGroup'

import SignupMixin from '../../mixins/signupMixin'
import FormMixin from '../../mixins/formMixin'
import RolesMixin from '../../mixins/rolesMixin'
import { mapState } from 'vuex';

export default {
  mixins: [SignupMixin, FormMixin, RolesMixin],
  data() {
    return {
      showPassword: false,
      showConfirmPassword: false,
      valid: true,
      accountInfo: {},
        isVisible : "true"
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
        // Check Reg ID
        if (typeof this.$route.params.regID !== 'undefined') { // Reg ID is present
          self.updateAccount({ regCode: this.$route.params.regID })
        } 

        // Update is enabled,roles, and isVisible
        self.updateAccount({isEnabled: 1});
        self.updateAccount({isVisible: self.isVisible});
        self.updateAccount({roles: [{id: self.getRouteName() == 'addCashier' ? 3 : 2}]});

        // Remove confirm password and update signup Account
        delete self.accountInfo.confirmPassword;
        self.updateAccount(self.accountInfo)

        // Axios post request for saving account
        this.axios.post(self.axiosURL+'api/user/save', {
        },
        {
          data : self.signupAccount
        })
        .then( response => {
          if (self.$route.name == 'AddMemberFromGenealogy') self.$router.replace('/genealogy/'+self.$session.get('account').username)
          else if (self.$route.name == 'AddMemberAdmin') self.$router.replace('/members')
          else self.loginAccount()
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
        url: self.axiosURL+ 'api/entry/login',
        auth: {
          username: self.signupAccount.username ,
          password: self.signupAccount.password
        }
      })
      .then(response => {
        if (response.status === 200) {
          self.$session.start()
          self.$session.set('account', response.data.payload);
          self.$session.set('auth', {
            username: self.signupAccount.username,
            password: self.signupAccount.password
          });

          self.clearAccount();

          self.$router.replace('/dashboard');
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
.signup-stepper-fourth-step {
  .v-input--switch.account-visible-switch .v-label {
    font-size: 14px;
    padding-left: 5px;
  }
}
</style>