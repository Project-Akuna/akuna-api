import Vue from 'vue';
// Import session
import VueSession from 'vue-session'
import VueRouter from 'vue-router';

// Base Views
import Dashboard from '../views/Dashboard.vue';
import Login from '../views/Login.vue';
import Signup from '../views/Signup.vue';

// Member's Files Views
import Adc from '../views/membersFiles/adc/Adc.vue';
import AddAdc from '../views/membersFiles/adc/AddAdc.vue'
import Members from "../views/membersFiles/members/Members";
import Cashier from "../views/membersFiles/cashier/Cashier";
import AddCashier from '../views/membersFiles/cashier/AddCashier.vue'
import Depot from "../views/membersFiles/depot/Depot";
import AddDepot from '../views/membersFiles/depot/AddDepot.vue'

// Transaction Views
import SellRegistrationCode from '../views/transaction/sellRegistrationCode/SellRegistrationCode.vue'
import SellRegistrationCodeDialog from '../views/transaction/sellRegistrationCode/SellRegistrationDialog.vue'
import Warehousing from '../views/transaction/warehousing/Warehousing.vue'
import WarehousingAddDelivery from '../views/transaction/warehousing/WarehousingAddDelivery.vue'

import NProgress from 'nprogress';
import 'nprogress/nprogress.css';

// Member Profile
import Genealogy from "../views/genealogy/Genealogy";
import AddMember from "../views/genealogy/AddMember.vue"


Vue.use(VueSession);
Vue.use(VueRouter);


const routes = [
    {
        path: '/dashboard',
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
        path: '/adc/add',
        name: 'addAdc',
        component: AddAdc
    },
    {
        path: '/genealogy',
        name: 'Genealogy',
        component: Genealogy
    },
    {
        path: '/genealogy/:username',
        name: 'GenealogyWithUsername',
        component: Genealogy
    },
    {
        path: '/genealogy/add/:id',
        name: 'AddMemberFromGenealogy',
        component: AddMember
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
        path: '/cashiers/add',
        name: 'addCashier',
        component: AddCashier
    },
    {
        path: '/sell-registration-code',
        name: 'sellRegistrationCode',
        component: SellRegistrationCode
    },
    {
        path: '/sell-registration-code/add',
        name: 'sellRegistrationCodeAdd',
        component: SellRegistrationCodeDialog
    },
    {
        path: '/depot',
        name: 'depot',
        component: Depot
    },
    {
        path: '/depot/add',
        name: 'AddDepot',
        component: AddDepot
    },
    {
        path: '/warehousing',
        name: 'warehousing',
        component: Warehousing
    },
    {
        path: '/warehousing/add',
        name: 'addWarehousingDelivery',
        component: WarehousingAddDelivery
    },
];

NProgress.configure({easing: 'ease', speed: 500});

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
    } else if (session.exists() && to.name === 'Login' || to.name === 'Signup' || to.name === 'signupWithRegID') {
        router.replace('/dashboard');
    } else {
        next();
    }
})

export default router
