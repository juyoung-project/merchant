<template>
  <div class="dashboard-container">
    <aside class="sidebar">
      <ul>
        <li @click="selectTab('dashboard')" :class="{ active: selectedTab === 'dashboard' }">대시보드</li>
        <li @click="selectTab('merchant')" :class="{ active: selectedTab === 'merchant' }">가맹점관리 관리</li>

      </ul>
    </aside>
    <main class="content">

      <section class="main-content">
        <component :is="selectedComponent"></component>
      </section>
    </main>
  </div>
</template>

<script>
import DashboardAdminPage from './DashboardAdminPage.vue';
import MerchantAdminPage from './MerchantAdminPage.vue';


export default {
  data() {
    return {
      selectedTab: 'dashboard',
    };
  },
  computed: {
    selectedTabTitle() {
      switch (this.selectedTab) {
        case 'dashboard':
          return '대시보드';
        case 'merchant':
          return '가맹점 관리';
        default:
          return '';
      }
    },
    selectedComponent() {
      switch (this.selectedTab) {
        case 'dashboard':
          return DashboardAdminPage;
        case 'merchant':
          return MerchantAdminPage;
        default:
          return DashboardAdminPage;
      }
    },
  },
  methods: {
    selectTab(tab) {
      this.selectedTab = tab;
    },
  },
};
</script>

<style scoped> .dashboard-container { display: flex; height: 100vh; font-family: 'Roboto', sans-serif; margin: 0; } .sidebar { width: 250px; background-color: #007bff; color: white; display: flex; flex-direction: column; align-items: start; padding: 0; } .sidebar ul { list-style: none; padding: 0; margin: 0; width: 100%; } .sidebar li { width: 100%; padding: 15px 20px; text-align: left; cursor: pointer; transition: background-color 0.3s; } .sidebar li:hover, .sidebar li.active { background-color: #0056b3; } .content { flex: 1; display: flex; flex-direction: column; margin: 0; } .header { background-color: #007bff; padding: 20px; text-align: center; color: white; border-bottom: 1px solid #0056b3; margin: 0; } .main-content { padding: 20px; background-color: #f0f2f5; flex: 1; margin: 0; } </style>