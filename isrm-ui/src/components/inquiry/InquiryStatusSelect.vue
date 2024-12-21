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
    <Option value="PUBLISHED">已发布状态</Option>
    <Option value="CONFIRMED">已确认状态</Option>
    <Option value="PAID_PENDING">已确认待付款状态</Option>
    <Option value="PAID">已付款状态</Option>
    <Option value="CANCELED">已取消状态</Option>
  </Select>
  <div v-else>
    <span v-if="model === 'PENDING'">未提交状态</span>
    <span v-else-if="model === 'SUBMITTED'">已提交状态</span>
    <span v-else-if="model === 'PUBLISHED'">已发布状态</span>
    <span v-else-if="model === 'CONFIRMED'">已确认状态</span>
    <span v-else-if="model === 'PAID_PENDING'">已确认待付款状态</span>
    <span v-else-if="model === 'PAID'">已付款状态</span>
    <span v-else-if="model === 'CANCELED'">已取消状态</span>
  </div>
</template>

<style scoped>

</style>