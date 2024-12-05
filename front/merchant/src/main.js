import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import utilsPlugin from './utilsPlugin'
import axiosPlugin from './axiosPlugin'

createApp(App)
.use(router)
.use(utilsPlugin)
.use(axiosPlugin)
.mount('#app')
