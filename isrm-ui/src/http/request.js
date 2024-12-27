import axios from "axios";
import { useUserStore } from "@/stores/user";
import { Message } from "view-ui-plus";

// 创建一个用户存储实例，用于访问用户相关数据，如token
const store = useUserStore();

// 创建一个axios实例，用于发送HTTP请求
// 配置基础URL和请求超时时间
const service = axios.create({
    baseURL: "http://localhost:8888",
    timeout: 5000,
});

/**
 * 请求拦截器
 * 在请求发送之前做一些处理，例如添加认证token
 * @param {Object} config - 请求配置对象
 * @returns {Object} - 处理后的请求配置对象
 */
service.interceptors.request.use(
    (config) => {
        // 如果请求url不是/login且store中有token，则在请求头中添加Authorization字段
        if (config.url !== '/login' && !!store.token) {
            config.headers.Authorization = `Bearer ${store.token}`;
        }
        return config;
    },
    (error) => {
        // 请求错误处理
        console.log("Request Error:", error);
        return Promise.reject(error);
    }
);

/**
 * 响应拦截器
 * 在收到响应数据后做一些处理，例如根据响应状态显示错误消息
 * @param {Object} response - 响应数据对象
 * @returns {Promise} - 处理后的响应数据或错误处理结果
 */
service.interceptors.response.use(
    (response) => {
        // 响应后处理
        // 如果响应状态码为200且业务代码为"200"，则返回业务数据
        if (response.status === 200 && response.data.code === "200") {
            return Promise.resolve(response.data.data);
        }
        // 如果响应不符合预期，显示错误消息并返回错误数据
        Message.error(response.data.message);
        return Promise.reject(response.data);
    },
    (error) => {
        // 根据错误响应数据的可用性，显示相应的错误消息
        if (!!error.response.data && !!error.response.data.message) {
            Message.error(error.response.data.message);
        } else {
            Message.error(error.message);
        }
        return Promise.reject(error);
    }
);

// 导出配置好的axios实例
export default service;
