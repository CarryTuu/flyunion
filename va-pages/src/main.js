import 'element-plus/theme-chalk/index.css';

import 'mapbox-gl/dist/mapbox-gl.css';
import '@/assets/style.css'
import {createApp} from 'vue'
import App from './App.vue'
import ElementUI from 'element-plus'
import router from "@/utils/router.js";
import 'bootstrap/dist/css/bootstrap.min.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(router)
app.use(ElementUI)
app.mount('#app')