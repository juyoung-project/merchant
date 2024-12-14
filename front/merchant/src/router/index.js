import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../components/LoginPage.vue';
import SignUpPage from '../components/SignUpPage.vue';
import AdminMainPage from '../components/admin/AdminMainPage.vue';
import DashboardAdminPage from '../components/admin/DashboardAdminPage.vue';
import MerchantAdminPage from '../components/admin/MerchantAdminPage.vue';
// import roleUtils from '@/utils/role_util';

const routes = [
  {
    path: '/',
    name: 'LoginPage',
    component: LoginPage,
  },
  {
    path: '/sign-up',
    name: 'SignUpPage',
    component: SignUpPage,
  },
  {
    path: '/main',
    name: 'AdminMainPage',
    component: AdminMainPage,
    meta: { requiresAuth: true }, // 인증 필요
  },
  {
    path: '/dashboard',
    name: 'DashboardAdminPage',
    component: DashboardAdminPage,
    meta: { requiresAuth: true, requiredRole: 'ADMIN' }, // 인증 및 권한 필요
  },
  {
    path: '/merchant',
    name: 'MerchantAdminPage',
    component: MerchantAdminPage,
    meta: { requiresAuth: true, requiredRole: 'ADMIN' }, // 인증 및 권한 필요
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});


export default router;
