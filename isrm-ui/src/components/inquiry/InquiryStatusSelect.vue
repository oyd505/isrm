 template
<script setup>
// 导入 View UI Plus 组件库中的 Select 组件
import { Select } from "view-ui-plus";

// 定义模型，用于绑定表单控件的值
const model = defineModel();

// 定义组件属性，用于确定显示哪种类型的组件
defineProps({
  compType: { type: String, default: 'select' }
});
</script>

<template>
  <!-- 根据 compType 属性的值决定是否渲染 Select 组件 -->
  <Select v-if="compType === 'select'" v-model="model">
    <!-- 定义下拉列表的各个选项，表示不同的状态 -->
    <Option value="PENDING">未提交状态</Option>
    <Option value="SUBMITTED">已提交状态</Option>
    <Option value="PUBLISHED">已发布状态</Option>
    <Option value="CONFIRMED">已确认状态</Option>
    <Option value="PAID_PENDING">已确认待付款状态</Option>
    <Option value="PAID">已付款状态</Option>
    <Option value="CANCELED">已取消状态</Option>
  </Select>
  <!-- 当 compType 不是 'select' 时，根据 model 的值显示相应的状态文本 -->
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
/* 此处添加组件的样式， scoped 属性确保样式仅在当前组件生效 */
</style>
