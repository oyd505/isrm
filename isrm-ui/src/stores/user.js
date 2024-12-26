import {defineStore} from 'pinia'
import {ref} from "vue";

export const useUserStore = defineStore("user", () => {
    const supplier = ref({})
    const token = ref()
    const userName = ref()
    const nickname = ref()
    return {supplier, token, userName, nickname}
}, {
    persist: {
        storage: sessionStorage
    }
})