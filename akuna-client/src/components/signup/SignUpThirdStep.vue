<template lang="pug">
  v-stepper-content(:step="getRouteName == 'addCashier' ? '' : '3'")
    v-container.pt-0
      v-form(ref="signupThirdStepForm" v-model="valid" lazy-validation)
        v-row
          //HEADING - Upline Information
          v-col.pa-0(cols="12")
            h4.font-weight-medium.grey--text.text--darken-3.pb-3.pl-3.pt-3 Upline Information

          // Number of accounts Select
          v-col.signup__input-container(cols="12" )
            v-select(
              v-model="uplineInfo.numberOfAccount"
              :items="accountsNumberList"
              label="Number of Accounts"
              dense
              :rules="customRules('Number of Accounts',{ required: true })"
              )

          // Direct Sponsor Select
          v-col.signup__input-container(cols="12" )
            v-select(
              :disabled="isGenealogy"
              v-model="uplineInfo.directSponsor"
              :items="usersList"
              item-text="username"
              item-value="id"
              label="Direct Sponsor"
              dense
              :rules="customRules('Direct Sponsor',{ required: true })"
              )

          //- // Direct Sponsor Reg ID TextField
          //- v-col.signup__input-container(cols="12")
          //-   base-text-field.signup__direct-reg-id-textfield(textFieldLabel="Direct Sponsor Registration ID" counter="10")

          // Immediate Upline Select
          v-col.signup__input-container(cols="12" )
            v-select(
              :disabled="isGenealogy"
              v-model="uplineInfo.upline"
              :items="usersList"
              item-text="username"
              item-value="id"
              label="Immediate Upline"
              dense
              :rules="customRules('Immediate Upline',{ required: true })"
              )

          //- // Immediate Upline Reg ID TextField
          //- v-col.signup__input-container(cols="12")
          //-   base-text-field.signup__direct-reg-id-textfield(textFieldLabel="Immediate Upline Registration ID" counter="10")

          // Upline Information Buttons
          v-col.signup__upline-info-btn-container.d-flex.justify-end.pb-0.pt-6(cols="12")
            v-btn(@click="prevStep()" depressed background-color="white") Back
            v-btn.signup__btn.ml-3(@click="nextSignupStep") Next
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
      valid: false,
      usersList: [],
      uplineInfo: {},
      accountsNumberList: [1,4,13],
      isGenealogy: false,
      genealogySponsorAccount: {}
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
    nextSignupStep () {
      if (this.$refs.signupThirdStepForm.validate()) {
        this.updateAccount({
          directSponsor: { id: this.uplineInfo.directSponsor },
          upline: { id: this.uplineInfo.upline },
          numberOfAccount: this.uplineInfo.numberOfAccount
        });
        // this.updateAccount(this.uplineInfo)

        this.nextStep()
      }
    },
    getAllUsers() {
      let self = this

      // Axios get request for getting all users
      this.axios.post(self.axiosURL+'api/user/get-users', {
        auth: {
          username: 'asd',
          password: 'asd'
        },
      })
      .then( response => {
        self.usersList = response.data.payload;
        self.checkGenealogy();
      })
      .catch( response => {
        this.$swal('Opssss! Something went wrong', response.data, 'error');
        console.log(response)
      })
    },
    getUserByUsername(callback) {
      let self = this
      // Axios get request for getting user by username
      this.axios.get(self.axiosURL+'api/user/get-user/'+self.$session.get('account').username, {
        auth: {
          username: 'asd',
          password: 'asd'
        },
      })
      .then( response => {
        self.genealogySponsorAccount = response.data.payload
        callback();
      })
      .catch( response => {
        this.$swal('Opssss! Something went wrong', response.data, 'error');
      })
    },
    checkGenealogy() {
      if (this.$route.name == 'AddMemberFromGenealogy' && typeof this.$route.params.id !== 'undefined') {
        this.isGenealogy = true;
        this.uplineInfo.upline = parseInt(this.$route.params.id)
        // this.uplineInfo.directSponsor = currentUser[0].id;
        // console.log(this.getUserByUsername())
        
        this.getUserByUsername(() => {
          this.usersList.push({
            id: this.genealogySponsorAccount.id,
            username: this.genealogySponsorAccount.username
          })
          this.uplineInfo.directSponsor = this.genealogySponsorAccount.id
        });
        
      }
    }
  },
  mounted() {
    this.getAllUsers();
    
  }
}
</script>
<style lang="scss">

</style>