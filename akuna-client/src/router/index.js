import Vue from 'vue';

// Import session
import VueSession from 'vue-session'

import VueRouter from 'vue-router';
import Dashboard from '../views/Dashboard.vue';
import Login from '../views/Login.vue';
import Signup from '../views/Signup.vue';
import Adc from '../views/membersFiles/Adc.vue';
import Genealogy from "../components/genealogy/Genealogy";
import Members from "../views/membersFiles/Members";
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';

Vue.use(VueSession);
Vue.use(VueRouter);


const routes = [
  {
    path: '/',
    name: 'dashboard',
    component: Dashboard
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/signup',
    name: 'signup',
    component: Signup
  },
  {
    path: '/signup/:regID',
    name: 'signupWithRegID',
    component: Signup
  },
  {
    path: '/adc',
    name: 'adc',
    component: Adc
  },
  {
    path: '/genealogy',
    name: 'genealogy',
    component: Genealogy
  },
  {
    path: '/members',
    name: 'members',
    component: Members
  }
];

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

  if (!session.exists() && to.name != 'login' ) {
    next('/login')
  } else if(session.exists() && to.name == 'login'){
    router.push('/dashboard');
  } else {
    next();
  }
})

export default router
