<script setup>
import {Option, Select} from "view-ui-plus";

const model = defineModel()
defineProps({
  compType: {type: String, default: 'select'}
});
</script>

<template>
  <Select v-if="compType === 'select'" v-model="model">
    <Option value="EMPLOYEE">内部员工</Option>
    <Option value="SUPPLIER">供应商</Option>
    <Option value="ADMIN">管理员</Option>
  </Select>
  <div v-else>
    <span v-if="model === 'EMPLOYEE'">内部员工</span>
    <span v-else-if="model === 'SUPPLIER'">供应商</span>
    <span v-else-if="model === 'ADMIN'">管理员</span>
  </div>
</template>

<style scoped>

</style>