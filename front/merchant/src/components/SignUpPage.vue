<template>
  <div class="signup-container">
    <div class="signup-box">
      <h1>회원가입</h1>
      <form @submit.prevent="signup">
        <div class="input-container">
          <input v-model="email" type="email" placeholder="이메일" required />
        </div>
        <div class="input-container">
          <input v-model="name" type="text" placeholder="이름" required />
        </div>
        <div class="input-container">
          <input v-model="password" type="password" placeholder="비밀번호" required />
        </div>
        <div class="input-container">
          <input v-model="contact" type="text" placeholder="연락처 (010-1234-5678)" required />
        </div>
        <button type="submit">회원가입</button>
      </form>
      <div class="additional-links">
        <a href="#" @click.prevent="$router.push('/login')">이미 계정이 있으신가요? 로그인</a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "SignUpPage",
  data() {
    return {
      email: '',
      name: '',
      password: '',
      contact: '',
    };
  },
  methods: {
    async signup() {
      try {
        await axios.post('/api/sign-up', {
          email: this.email,
          username: this.name,
          password: this.password,
          contact: this.contact,
          role: "STAFF"
        });
        alert('회원가입 성공!');
        this.$router.push('/login'); // 회원가입 후 로그인 페이지로 이동
      } catch (error) {
        alert('회원가입 실패!');
      }
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

.signup-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(120deg, #ffffff 0%, #f0f2f5 100%);
}

.signup-box {
  background: white;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

.signup-box h1 {
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
  border-color: #007bff; /* 로그인 페이지와 동일한 파란색 테마 */
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
  text-align: center;
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