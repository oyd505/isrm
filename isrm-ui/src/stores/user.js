import {defineStore} from 'pinia'
import {ref} from "vue";

export const useUserStore = defineStore("user", () => {
    const supplier = ref({})
    const token = ref()
    return {supplier, token}
}, {
    persist: {
        storage: sessionStorage
    }
})