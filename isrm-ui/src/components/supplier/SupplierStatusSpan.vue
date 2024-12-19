<script setup>
defineProps({
  status: {type: String}
});
</script>

<template>
  <span v-if="status === 'POTENTIAL'">潜在</span>
  <span v-else-if="status === 'INTRODUCING'">引入中</span>
  <span v-else-if="status === 'QUALIFIED'">合格</span>
  <span v-else-if="status === 'EXITED'">已退出</span>
</template>

<style scoped>

</style>