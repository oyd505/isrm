 template
<script setup>
// 导入 View UI Plus 组件库中的 Select 组件
import { Select } from "view-ui-plus";

// 定义模型，用于绑定表单控件的值
const model = defineModel();
// 定义组件属性，用于确定要渲染的表单控件类型
defineProps({
  compType: { type: String, default: 'select' }
});
</script>

<template>
  <!-- 根据 compType 属性值决定是否渲染 Select 组件 -->
  <Select v-if="compType === 'select'" v-model="model">
    <!-- 定义 Select 组件中的 Option 项，表示不同的货币类型 -->
    <Option value="CNY">人民币</Option>
    <Option value="USD">美元</Option>
    <Option value="EUR">欧元</Option>
    <Option value="JPY">日元</Option>
    <Option value="GBP">英镑</Option>
    <Option value="AUD">澳元</Option>
    <Option value="CAD">加元</Option>
    <Option value="NZD">新西兰元</Option>
  </Select>
  <!-- 当 compType 不是 'select' 时，根据 model 值显示对应的货币名称 -->
  <div v-else>
    <span v-if="model === 'CNY'">人民币</span>
    <span v-else-if="model === 'USD'">美元</span>
    <span v-else-if="model === 'EUR'">欧元</span>
    <span v-else-if="model === 'JPY'">日元</span>
    <span v-else-if="model === 'GBP'">英镑</span>
    <span v-else-if="model === 'AUD'">澳元</span>
    <span v-else-if="model === 'CAD'">加元</span>
    <span v-else-if="model === 'NZD'">新西兰元</span>
  </div>
</template>

<style scoped>
  /* 此处添加组件的私有样式 */
</style>
