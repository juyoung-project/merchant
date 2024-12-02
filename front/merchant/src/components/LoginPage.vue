<template>
  <div class="login-container">
    <div class="login-box">
      <h1>로그인</h1>
      <form @submit.prevent="login">
        <div class="input-container">
          <input v-model="email" type="email" placeholder="이메일" required />
        </div>
        <div class="input-container">
          <input v-model="password" type="password" placeholder="비밀번호" required />
        </div>
        <button type="submit">로그인</button>
      </form>
      <div class="additional-links">
        <a href="#">비밀번호를 잊으셨나요?</a>
        <a href="#" @click="goSignUp">회원가입</a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "LoginPage",
  data() {
    return {
      email: '',
      password: '',
    };
  },
  methods: {
    async login() {
      try {
        await axios.post('/api/sign-in', {
          email: this.email,
          password: this.password,
        });

        if ( this.$roleUtils.isAdmin()) {
          this.$router.push('/main');
        } else {
          alert("일치하지 않음")
        }
       
      } catch (error) {
        console.log(error)
        alert('로그인 실패!');
      }
    },
    goSignUp() {
      this.$router.push('/sign-up');
    }
  },
};
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

body {
  margin: 0;
  font-family: 'Roboto', sans-serif;
  background-color: #f0f2f5;
}

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(120deg, #ffffff 0%, #f0f2f5 100%);
}

.login-box {
  background: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.login-box h1 {
  margin-bottom: 30px;
  font-size: 24px;
  text-align: center;
  color: #333;
}

.input-container {
  margin: 15px 0;
}

form input {
  width: 100%;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 16px;
  outline: none;
  transition: border-color 0.3s, padding 0.3s;
  box-sizing: border-box;
}

form input:focus {
  border-color: #007bff;
}

form button {
  width: 100%;
  padding: 15px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background 0.3s;
  margin-top: 20px;
}

form button:hover {
  background: #0056b3;
}

.additional-links {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.additional-links a {
  color: #007bff;
  text-decoration: none;
  font-size: 14px;
}

.additional-links a:hover {
  text-decoration: underline;
}
</style>
