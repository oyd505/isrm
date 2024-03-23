<script setup>
defineProps({
  status: {type: String}
});
</script>

<template>
  <span v-if="status === 'PENDING'">未提交状态</span>
  <span v-else-if="status === 'SUBMITTED'">已提交状态</span>
  <span v-else-if="status === 'PUBLISHED'">已发布状态</span>
  <span v-else-if="status === 'CONFIRMED'">已确认状态</span>
  <span v-else-if="status === 'PAID_PENDING'">已确认待付款状态</span>
  <span v-else-if="status === 'PAID'">已付款状态</span>
  <span v-else-if="status === 'CANCELED'">已取消状态</span>
</template>

<style scoped>

</style>