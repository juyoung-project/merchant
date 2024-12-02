import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import utilsPlugin from './utilsPlugin'

createApp(App)
.use(router)
.use(utilsPlugin)
.mount('#app')
