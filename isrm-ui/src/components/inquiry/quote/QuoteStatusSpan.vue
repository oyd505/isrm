<script setup>
defineProps({
  status: {type: String}
});
</script>

<template>
  <span v-if="status === 'PENDING'">未提交状态</span>
  <span v-else-if="status === 'SUBMITTED'">已提交状态</span>
  <span v-else-if="status === 'CANCELED'">已取消状态</span>
</template>

<style scoped>

</style>