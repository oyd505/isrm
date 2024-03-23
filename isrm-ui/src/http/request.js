import axios from "axios";
import {useUserStore} from "@/stores/user"
import {Message} from "view-ui-plus";

const store = useUserStore();
const service = axios.create({
    baseURL: "http://localhost:8888",
    timeout: 5000,
});
// 请求拦截器
service.interceptors.request.use(
    (config) => {
        if (config.url !== '/login' && !!store.token) {
            config.headers.Authorization = `Bearer ${store.token}`
        }
        return config;
    },
    (error) => {
        // 请求错误处理
        console.log("Request Error:", error);
        return Promise.reject(error);
    }
);
// 响应拦截器
service.interceptors.response.use(
    (response) => {
        // 响应后处理
        if (response.status === 200 && response.data.code === "200") {
            return Promise.resolve(response.data.data);
        }
        Message.error(response.data.message);
        return Promise.reject(response.data);
    },
    (error) => {
        if (!!error.response.data && !!error.response.data.message) {
            Message.error(error.response.data.message);
        } else {
            Message.error(error.message);
        }
        return Promise.reject(error);
    }
);
export default service;