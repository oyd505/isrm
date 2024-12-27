 template
<script setup>
// 导入 View UI Plus 组件库中的 Option 和 Select 组件
import {Option, Select} from "view-ui-plus";

// 定义模型，用于绑定表单控件的值
const model = defineModel()

// 定义组件属性，compType 用于决定渲染哪种类型的组件
defineProps({
  compType: {type: String, default: 'select'}
});
</script>

<template>
  <!-- 根据 compType 属性的值条件性地渲染 Select 组件或其他内容 -->
  <Select v-if="compType === 'select'" v-model="model">
    <!-- 定义 Select 组件中的 Option 项，分别代表不同的用户角色 -->
    <Option value="EMPLOYEE">内部员工</Option>
    <Option value="SUPPLIER">供应商</Option>
    <Option value="ADMIN">管理员</Option>
  </Select>
  <div v-else>
    <!-- 根据 model 的值条件性地显示用户角色 -->
    <span v-if="model === 'EMPLOYEE'">内部员工</span>
    <span v-else-if="model === 'SUPPLIER'">供应商</span>
    <span v-else-if="model === 'ADMIN'">管理员</span>
  </div>
</template>

<style scoped>
/* 此处添加组件的样式， scoped 属性确保样式仅在当前组件中生效 */
</style>
