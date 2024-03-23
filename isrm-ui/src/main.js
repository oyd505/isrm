import {createApp} from "vue";
import {createPinia} from "pinia";
import createPersistedState from 'pinia-plugin-persistedstate'
import ViewUIPlus from "view-ui-plus";
import App from "./App.vue";
import router from "./router";
import "view-ui-plus/dist/styles/viewuiplus.css";

const app = createApp(App);

const pinia = createPinia()
pinia.use(createPersistedState)
app.use(pinia);
app.use(router);
app.use(ViewUIPlus);
app.mount("#app");

