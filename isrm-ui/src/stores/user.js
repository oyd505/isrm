// 导入 Pinia 的 defineStore 函数和 Vue 的 ref 函数
import {defineStore} from 'pinia'
import {ref} from "vue"

/**
 * 定义并导出一个用于存储用户相关数据的 Pinia store
 * 这个 store 被命名为 "user"，并包含以下状态：
 * - supplier: 供应商信息对象
 * - token: 用户认证令牌
 * - userName: 用户名
 * - nickname: 用户昵称
 * 所有状态都将被持久化到 sessionStorage 中，以保持用户数据在页面刷新后仍然可用
 */
export const useUserStore = defineStore("user", () => {
    // 初始化供应商信息对象
    const supplier = ref({})
    // 初始化用户认证令牌
    const token = ref()
    // 初始化用户名
    const userName = ref()
    // 初始化用户昵称
    const nickname = ref()
    // 返回所有状态对象
    return {supplier, token, userName, nickname}
}, {
    // 配置持久化选项，将所有状态持久化到 sessionStorage
    persist: {
        storage: sessionStorage
    }
})
