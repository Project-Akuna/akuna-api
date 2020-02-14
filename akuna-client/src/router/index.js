import Vue from 'vue';
// Import session
import VueSession from 'vue-session'
import VueRouter from 'vue-router';

// Base Views
import Dashboard from '../views/Dashboard.vue';
import Login from '../views/Login.vue';
import Signup from '../views/Signup.vue';

// Member's Files Views
import Adc from '../views/membersFiles/Adc.vue';
import Members from "../views/membersFiles/Members";

// Transaction Views
import SellRegistrationCode from '../views/transaction/SellRegistrationCode.vue'

import NProgress from 'nprogress';
import 'nprogress/nprogress.css';

import Cashier from "../views/membersFiles/Cashier";

import Genealogy from "../views/genealogy/Genealogy";

Vue.use(VueSession);
Vue.use(VueRouter);


const routes = [
  {
    path: '/',
    name: 'Dashboard',
    component: Dashboard
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/signup/:regID',
    name: 'signupWithRegID',
    component: Signup
  },
  {
    path: '/adc',
    name: 'Adc',
    component: Adc
  },
  {
    path: '/genealogy',
    name: 'Genealogy',
    component: Genealogy
  },
  {
    path: '/members',
    name: 'Members',
    component: Members
  },
  {
    path: '/cashiers',
    name: 'cashiers',
    component: Cashier
  },
  {
    path: '/sell-registration-code',
    name: 'sellRegistrationCode',
    component: SellRegistrationCode
  }
];

NProgress.configure({ easing: 'ease', speed: 500 });

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

router.beforeResolve((to, from, next) => {
  // If this isn't an initial page load.
  if (to.name) {
    // Start the route progress bar.
    NProgress.start()
  }
  next()
});

router.afterEach((to, from) => {
  // Complete the animation of the route progress bar.
  NProgress.done()
});

router.beforeEach((to, from, next) => {
  let session = Vue.prototype.$session;

  if (!session.exists() && to.name !== 'Login') {
    if (to.name !== 'Signup' && to.name !== 'signupWithRegID') next('/login')
    else next()
  } else if(session.exists() && to.name === 'Login' || to.name === 'Signup' || to.name === 'signupWithRegID'){
    router.replace('/dashboard');
  } else {
    next();
  }
})

export default router
