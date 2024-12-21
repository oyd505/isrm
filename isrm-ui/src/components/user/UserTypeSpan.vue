<script setup>
defineProps({
  userType: {type: String}
});
</script>

<template>
  <span v-if="userType === 'EMPLOYEE'">内部员工</span>
  <span v-else-if="userType === 'SUPPLIER'">供应商</span>
  <span v-else-if="userType === 'ADMIN'">管理员</span>
</template>

<style scoped>

</style>