<template>
  <div class="stores-container">
    <!-- 헤더 -->
    <header class="header">
      <h1>가맹점 관리</h1>
      <button class="create-button" @click="createMerchant">가맹점 추가</button>
    </header>

    <!-- 검색 필터 -->
    <div class="filter-bar">
      <input 
        v-model="filters.storeName" 
        type="text" 
        placeholder="가맹점명" 
        class="filter-input"
      />
      <input 
        v-model="filters.ownerName" 
        type="text" 
        placeholder="사업주" 
        class="filter-input"
      />
      <input 
        v-model="filters.businessNumber" 
        type="text" 
        placeholder="사업자번호" 
        class="filter-input"
      />
      <select v-model="filters.city" class="filter-select">
        <option value="">시/도 선택</option>
        <option value="서울시">서울시</option>
        <option value="경기도">경기도</option>
      </select>
      <select v-model="filters.district" class="filter-select">
        <option value="">구/군 선택</option>
        <option v-for="district in availableDistricts" :key="district" :value="district">
          {{ district }}
        </option>
      </select>
      <button @click="searchStores" class="search-button">검색</button>
    </div>

    <!-- 가맹점 리스트 -->
    <div class="store-list">
      <table>
        <thead>
          <tr>
            <th>가맹점명</th>
            <th>사업주</th>
            <th>사업자번호</th>
            <th>시/도</th>
            <th>구/군</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="store in filteredStores" :key="store.id" @click="goToDetail(store.id)">
            <td>{{ store.storeName }}</td>
            <td>{{ store.ownerName }}</td>
            <td>{{ store.businessNumber }}</td>
            <td>{{ store.city }}</td>
            <td>{{ store.district }}</td>
          </tr>
          <tr v-if="filteredStores.length === 0">
            <td colspan="5" class="no-data">검색 결과가 없습니다.</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'MerchantAdminPage',
  data() {
    return {
      filters: {
        storeName: '',
        ownerName: '',
        businessNumber: '',
        city: '',
        district: '',
      },
      districts: {
        '서울시': ['송파구', '강남구', '종로구'],
        '경기도': ['수원시', '성남시', '용인시'],
      },
      stores: [
        { id: 1, storeName: '가맹점 A', ownerName: '홍길동', businessNumber: '123-45-67890', city: '서울시', district: '강남구' },
        { id: 2, storeName: '가맹점 B', ownerName: '김철수', businessNumber: '987-65-43210', city: '경기도', district: '성남시' },
      ],
    };
  },
  computed: {
    availableDistricts() {
      return this.districts[this.filters.city] || [];
    },
    filteredStores() {
      return this.stores.filter(store => {
        return (
          (!this.filters.storeName || store.storeName.includes(this.filters.storeName)) &&
          (!this.filters.ownerName || store.ownerName.includes(this.filters.ownerName)) &&
          (!this.filters.businessNumber || store.businessNumber.includes(this.filters.businessNumber)) &&
          (!this.filters.city || store.city === this.filters.city) &&
          (!this.filters.district || store.district === this.filters.district)
        );
      });
    },
  },
  methods: {
    searchStores() {
      console.log('검색 실행');
    },
    goToDetail(storeId) {
      this.$router.push({ name: 'MerchantDetailPage', params: { id: storeId } });
    },
    async createMerchant() {
      let data ={
        "address": "123 Main Street, Business District",
        "contactNumber": "010-1234-5678",
        "addressGu": "Gangnam",
        "addressSi": "Seoul",
        "name" :"테스트가맹점",
        "owner": "John Doe",
        "businessNumber": "123-45-67890",
        "contractEndDate": "2025-12-31",
        "openDate": "2024-01-01"
      }

      await axios.post('/api/create-merchant', data);

    }
  },
};
</script>

<style scoped>
/* 전체 컨테이너 */
.stores-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
  background: #f9f9f9;
  font-family: 'Roboto', sans-serif;
  padding: 20px;
  box-sizing: border-box;
}

/* 헤더 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.header h1 {
  font-size: 24px;
  color: #007bff;
  margin: 0;
}

.create-button {
  padding: 10px 15px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.3s;
}

.create-button:hover {
  background: #0056b3;
}

/* 필터 바 */
.filter-bar {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-bottom: 20px;
}

.filter-input,
.filter-select {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 14px;
  flex: 1;
  max-width: calc(20% - 10px);
  box-sizing: border-box;
}

.filter-input:focus,
.filter-select:focus {
  border-color: #007bff;
}

.search-button {
  padding: 10px 15px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  transition: background 0.3s;
  max-width: 100px;
}

.search-button:hover {
  background: #0056b3;
}

/* 리스트 스타일 */
.store-list table {
  width: 100%;
  border-collapse: collapse;
  background: white;
  border: 1px solid #ddd;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.store-list th,
.store-list td {
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #eee;
}

.store-list th {
  background: #007bff;
  color: white;
  font-weight: bold;
}

.store-list td {
  color: #333;
}

.store-list tbody tr:hover {
  background-color: #f0f8ff;
  cursor: pointer;
}

.no-data {
  text-align: center;
  color: #888;
  font-size: 14px;
}
</style>
