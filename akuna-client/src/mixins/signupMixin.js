export default {
  methods: {
    changeStep(step) {
      this.$store.commit('signupChangeStep', step)
    },
    updateAccount(item) {
      this.$store.commit('updateAccount',item)
    },
    clearAccount() {
      this.$store.commit('clearAccount')
    }
  },
  computed: {
    signupStep() {
      return this.$store.state.signupStep;
    },
  }
}