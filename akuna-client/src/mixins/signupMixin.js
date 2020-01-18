export default {
  methods: {
    changeStep(step) {
      this.$store.commit('signupChangeStep', step);
    }
  },
  computed: {
    signupStep() {
      return this.$store.state.signupStep;
    },
  }
}