import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    signupStep: 1,
    items: [
      "One",
      "Two",
      "Three"
    ]
  },
  mutations: {
    signupChangeStep(state, nextStep) {
      state.signupStep = nextStep;
    }
  },
  actions: {

  },
  modules: {

  }
})
