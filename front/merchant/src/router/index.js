import { createRouter, createWebHistory } from 'vue-router';
import LoginPage from '../components/LoginPage.vue';
import SignUpPage from '../components/SignUpPage.vue';
import AdminMainPage from '../components/admin/AdminMainPage.vue';
import DashboardAdminPage from '../components/admin/DashboardAdminPage.vue';
import MerchantAdminPage from '../components/admin/MerchantAdminPage.vue';
import Cookies from 'js-cookie';
import roleUtils from '@/utils/role_util';

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

// 라우터 가드 설정
router.beforeEach((to, from, next) => {
  const token = Cookies.get('JWT-TOKEN'); // 토큰 확인
  const userRole = roleUtils.getRole(); // 현재 사용자 권한 확인

  if (to.matched.some(record => record.meta.requiresAuth)) { // 인증이 필요한 라우트
    if (!token) {
      // 토큰이 없으면 로그인 페이지로 리다이렉트
      next({ name: 'LoginPage' });
    } else {
      // 토큰이 있고 권한이 필요한 라우트에 접근할 때
      if (to.meta.requiredRole && to.meta.requiredRole !== userRole) {
        // 권한이 맞지 않으면 메인 페이지로 리다이렉트
        next({ name: 'AdminMainPage' });
      } else {
        next(); // 권한이 맞으면 해당 라우트로 이동
      }
    }
  } else {
    // 인증이 필요 없는 라우트로 접근할 때
    if ((to.name === 'LoginPage' || to.name === 'SignUpPage') && token) {
      // 로그인 또는 회원가입 페이지로 가려고 하면, 메인 페이지로 리다이렉트
      next({ name: 'AdminMainPage' });
    } else {
      next(); // 그대로 진행
    }
  }
});

export default router;
