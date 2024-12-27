// 导入 Vue 核心库
import {createApp} from "vue";
// 导入 Pinia 状态管理库
import {createPinia} from "pinia";
// 导入 Pinia 插件，用于持久化状态
import createPersistedState from 'pinia-plugin-persistedstate'
// 导入 ViewUIPlus 组件库
import ViewUIPlus from "view-ui-plus";
import App from "./App.vue";
import router from "./router";
// 导入 ViewUIPlus 样式
import "view-ui-plus/dist/styles/viewuiplus.css";

// 创建 Vue 应用实例
const app = createApp(App);

// 创建 Pinia 实例
const pinia = createPinia()
// 使用持久化状态插件扩展 Pinia
pinia.use(createPersistedState)
// 将 Pinia 集成到应用中
app.use(pinia);
// 集成路由配置
app.use(router);
// 集成 ViewUIPlus 组件库
app.use(ViewUIPlus);
// 将应用挂载到指定的 HTML 元素上
app.mount("#app");
