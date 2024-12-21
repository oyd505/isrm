<script setup>
import {Select} from "view-ui-plus";

const model = defineModel()
defineProps({
  compType: {type: String, default: 'select'}
});
</script>

<template>
  <Select v-if="compType === 'select'" v-model="model">
    <Option value="PENDING">未提交状态</Option>
    <Option value="SUBMITTED">已提交状态</Option>
    <Option value="CANCELED">已取消状态</Option>
  </Select>
  <div v-else>
    <span v-if="model === 'PENDING'">未提交状态</span>
    <span v-else-if="model === 'SUBMITTED'">已提交状态</span>
    <span v-else-if="model === 'CANCELED'">已取消状态</span>
  </div>
</template>

<style scoped>

</style>