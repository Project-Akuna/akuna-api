export default {
  methods: {
    changeStep(step) {
      this.$store.commit('signupChangeStep', step)
    },
    updateAccount(item) {
      this.$store.commit('updateAccount',item)
    },
  },
  computed: {
    signupStep() {
      return this.$store.state.signupStep;
    },
  }
}