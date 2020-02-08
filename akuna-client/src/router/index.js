import Vue from 'vue';
import VueRouter from 'vue-router';
import Dashboard from '../views/Dashboard.vue';
import Login from '../views/Login.vue';
import Signup from '../views/Signup.vue';
import Adc from '../views/Adc.vue';
import Genealogy from "../components/genealogy/Genealogy";
import Members from "../components/members/Members";
import NProgress from 'nprogress';
import 'nprogress/nprogress.css';

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

export default router
