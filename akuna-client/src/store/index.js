import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    signupStep: 1,
    isNavShown: false,
    axiosURL: 'http://localhost:8080/',
      // axiosURL: 'http://148.72.210.244:80/',
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
    resetSignupStep(state) {
      state.signupStep = 1;
    },
    toggleNav(state, visibility) {
      state.isNavShown = visibility;
    },
    updateAccount(state, account) {
      state.signupAccount = Object.assign({}, state.signupAccount, account)
    },
    updateRegions(state, regionsList) {
      state.regionsList = regionsList
    },
    clearAccount(state) {
      state.signupAccount = {}
    },
    removeSignupProperty(state, property) {
      if (property == 'noOfAccount') delete state.signupAccount.noOfAccount
    }
  },
  actions: {

  },
  modules: {

  }
})
