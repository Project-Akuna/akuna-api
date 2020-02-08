import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    signupStep: 1,
    isNavShown: false,
    axiosURL: 'http://localhost:3000/',
    items: [
      "One",
      "Two",
      "Three"
    ],
    signupAccount: {},
    regionsList: [],
  },
  mutations: {
    signupChangeStep(state, nextStep) {
      state.signupStep = nextStep;
    },
    toggleNav(state, visibility) {
      state.isNavShown = visibility;
    },
    updateAccount(state, account) {
      state.signupAccount = Object.assign({}, state.signupAccount, account)
    },
    updateRegions(state, regionsList) {
      state.regionsList = regionsList
    }
  },
  actions: {

  },
  modules: {

  }
})
