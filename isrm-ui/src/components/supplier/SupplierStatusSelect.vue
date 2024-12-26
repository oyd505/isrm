 template
<script setup>
/**
 * 导入View UI Plus组件库中的Option和Select组件
 * 用于在模板中创建选择框和选项
 */
import { Option, Select } from "view-ui-plus";

/**
 * 定义模型，用于绑定选择框的选中值
 * 该值会根据用户选择动态更新
 */
const model = defineModel();

/**
 * 定义组件属性，用于确定渲染哪种类型的组件
 * compType: 组件类型，默认为'select'
 */
defineProps({
  compType: { type: String, default: 'select' }
});
</script>

<template>
  <!-- 根据compType属性值决定是否渲染Select组件 -->
  <Select v-if="compType === 'select'" v-model="model">
    <!-- 定义Select组件中的Option组件，表示不同的选择项 -->
    <Option value="POTENTIAL">潜在</Option>
    <Option value="INTRODUCING">引入中</Option>
    <Option value="QUALIFIED">合格</Option>
    <Option value="EXITED">已退出</Option>
  </Select>
  <!-- 当compType不是'select'时，根据model值显示对应文本 -->
  <div v-else>
    <span v-if="model === 'POTENTIAL'">潜在</span>
    <span v-else-if="model === 'INTRODUCING'">引入中</span>
    <span v-else-if="model === 'QUALIFIED'">合格</span>
    <span v-else-if="model === 'EXITED'">已退出</span>
  </div>
</template>

<style scoped>
  /* 此处添加组件的CSS样式，scoped属性确保样式仅在本组件生效 */
</style>
