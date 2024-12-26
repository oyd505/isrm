 template
<script setup>
// 导入 View UI Plus 组件库中的 Option 和 Select 组件
import {Option, Select} from "view-ui-plus";
// 导入 Vue 的生命周期钩子 onMounted 和响应式对象 ref
import {onMounted, ref} from "vue";
// 导入获取供应商列表的 API 函数
import {getThinSupplierList} from "@/http/api";

// 使用 defineModel 定义 'code' 模型，用于绑定 Select 组件的 v-model
const code = defineModel('code');
// 使用 defineModel 定义 'name' 模型，用于存储选中供应商的名称
const name = defineModel('name');

// 使用 ref 创建响应式数组 supplierList，用于存储从 API 获取的供应商列表数据
const supplierList = ref([])

// 在组件挂载时获取供应商列表数据
onMounted(() => {
  getThinSupplierList().then(data => {
    supplierList.value = data;
  })
})

/**
 * 当用户在 Select 组件中选择供应商时调用此函数
 * @param {Object} selection - 包含选中项的值和标签的对象
 */
function selectSupplier(selection) {
  name.value = selection.label;
}
</script>

<template>
  <!-- 渲染 Select 组件，绑定 code 模型，选择时调用 selectSupplier 函数 -->
  <Select v-model="code" @on-select="selectSupplier" label-in-value filterable>
    <!-- 遍历 supplierList，为每个供应商渲染一个 Option 组件 -->
    <Option v-for="item in supplierList" :key="item.code" :value="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>
/* 此处添加组件的私有样式 */
</style>
