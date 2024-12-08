import {defineStore} from 'pinia'
import {ref} from "vue";

export const useUserStore = defineStore("user", () => {
    const supplier = ref({})
    const token = ref()
    const userName = ref()
    return {supplier, token, userName}
}, {
    persist: {
        storage: sessionStorage
    }
})