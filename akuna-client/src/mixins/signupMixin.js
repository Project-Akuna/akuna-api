import { mapState } from 'vuex'
export default {
  computed: mapState({
    signupStep: 'signupStep'
  }),
  methods: {
    nextStep() {
      this.$store.commit('signupChangeStep', this.signupStep + 1)
    },
    prevStep() {
      this.$store.commit('signupChangeStep', this.signupStep - 1)
    },
    resetSignupStep() {
      this.$store.commit('resetSignupStep')
    },
    updateAccount(item) {
      this.$store.commit('updateAccount',item)
    },
    clearAccount() {
      this.$store.commit('clearAccount')
    },
    getRouteName() {
      return this.$route.name;
    },
    removeSignupProperty(property) {
      this.$store.commit('removeSignupProperty', property);
    }
  },
  computed: {
    signupStep() {
      return this.$store.state.signupStep;
    },
  }
}