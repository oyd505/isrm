<script setup>
import {Option, Select} from "view-ui-plus";

const model = defineModel()
defineProps({
  compType: {type: String, default: 'select'}
});
</script>

<template>
  <Select v-if="compType === 'select'" v-model="model">
    <Option value="POTENTIAL">潜在</Option>
    <Option value="INTRODUCING">引入中</Option>
    <Option value="QUALIFIED">合格</Option>
    <Option value="EXITED">已退出</Option>
  </Select>
  <div v-else>
    <span v-if="model === 'POTENTIAL'">潜在</span>
    <span v-else-if="model === 'INTRODUCING'">引入中</span>
    <span v-else-if="model === 'QUALIFIED'">合格</span>
    <span v-else-if="model === 'EXITED'">已退出</span>
  </div>
</template>

<style scoped>

</style>