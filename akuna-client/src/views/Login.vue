<template lang="pug">
  section.login
    v-container.login__card.white.pa-8.mx-5
      v-form(ref="loginForm" v-model="valid" lazy-validation)
        v-row
          v-col.pb-0(cols="12")
            h3.font-weight-black.grey--text.text--darken-2 Sign in your account
          v-col(cols="12")
            v-form
              v-text-field(
                required
                clearable
                v-model="username" 
                label="Username"
                prepend-inner-icon="mdi-account"
                :rules="customRules('Username',{ required: true, counter: 100 })"
                )

              v-text-field(
                required
                clearable
                v-model="password" 
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'" 
                @click:append="showPassword = !showPassword"
                prepend-inner-icon="mdi-lock"
                :type="showPassword ? 'text' : 'password'"
                label="Password"
                :rules="customRules('Password',{ required: true, counter: 100 })"
                @keyup.enter="signin"
                )
          v-col.pa-0.pr-3.login__forgot-container(cols="12")
            a.link Forgot password? 

        v-btn.mt-4.login__btn(block large @click="signin") SIGN IN

        div.login__register-container.text-center.mt-7
          span.grey--text.text--darken-1 Dont have an account? 
          sign-up-registration-dialog
</template>
<script>
import SignUpRegistrationDialog from '../components/signup/SignUpRegistrationDialog';

import FormMixin from '../mixins/formMixin'
import { mapState } from 'vuex'

export default {
  components: {
    SignUpRegistrationDialog
  },
  mixins: [ FormMixin ],
  data(){
    return {
      username:'',
      password:'',
      showPassword: false,
      valid: false
    }
  },
  computed: mapState({
    axiosURL: 'axiosURL'
  }),
  methods: {
    signin() {
      let self = this;

      if (this.$refs.loginForm.validate()) {
        this.axios({
          method: 'post',
          url: self.axiosURL+ 'api/login',
          auth: {
            username: self.username ,
            password: self.password
          }
        })
        .then(response => {
          if (response.status === 200) {
            this.$session.start()
            this.$session.set('auth', {
              username: self.username,
              password: self.password
            });
            this.$session.set('account', response.data.payload);

            this.$router.replace('/dashboard');
          }
        })
        .catch(response => {
          this.$swal('Opssss! Something went wrong', response.data, 'error');
        });
      }
    }
  }
}
</script>
<style lang="scss">
.login {
  height: 100vh;
  @include flex-center;
  background: $c-lighter-blue-gray;

  .login__card {
    @include card-shadow-sm;
    max-width: 400px;
    
  }

  .login__btn {
    background: $c-primary !important;
    @include shadow-sm;
    border-radius: 50px;
    
    span {
      color: white;
      font-weight: 300;
      letter-spacing: 0px;
    }
  }

  .login__forgot-container {
    font-size: 12px;
    text-align: right;
    margin-top: -25px;
    z-index: 2;
  }

  .login__register-container {
    font-size: 12px;
  }
}
</style>