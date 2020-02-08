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
    ],
    isNavShown: false,
    axiosURL: 'http://localhost:3000/'
  },
  mutations: {
    signupChangeStep(state, nextStep) {
      state.signupStep = nextStep;
    },
    toggleNav(state, visibility) {
      state.isNavShown = visibility;
    }
  },
  actions: {

  },
  modules: {

  }
})
