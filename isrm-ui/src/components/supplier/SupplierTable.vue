 template
<script setup>
// 导入 View UI Plus 组件库中的 Table 组件
import {Table} from "view-ui-plus";
// 导入 SupplierStatusSelect 组件，用于显示供应商状态
import SupplierStatusSelect from "@/components/supplier/SupplierStatusSelect.vue";

// 定义表格的列配置
const columns = [
  // 序号列，使用索引类型，宽度为 80 像素
  {title: "序号", type: "index", width: 80},
  // 供应商编码列，直接显示数据中的 supplierCode 字段
  {
    title: "编码",
    key: "supplierCode",
  },
  // 供应商名称列，使用 name slot 自定义显示方式
  {
    title: "名称",
    slot: "name",
  },
  // 供应商状态列，使用 supplierStatus slot 自定义显示方式
  {
    title: "状态",
    slot: "supplierStatus"
  },
  // 操作列，居中对齐，宽度为 150 像素，使用 action slot 自定义显示方式
  {
    title: "操作",
    slot: "action",
    width: 150,
    align: "center",
  },
];

// 定义组件接收的属性
defineProps({
  // 加载状态，布尔类型
  loading: Boolean,
  // 供应商列表数据，数组类型，必须提供
  supplierList: {type: Array, required: true},
});
</script>

<template>
  <!-- 使用 Table 组件显示数据 -->
  <Table :loading="loading" stripe :columns="columns" :data="supplierList">
    <!-- 定义 name slot，用于显示供应商名称 -->
    <template #name="{ row }">
      <strong>{{ row.name }}</strong>
    </template>
    <!-- 定义 supplierStatus slot，用于显示供应商状态 -->
    <template #supplierStatus="{ row }">
      <SupplierStatusSelect v-model="row.supplierStatus" comp-type="span"/>
    </template>
    <!-- 定义 action slot，用于显示操作按钮 -->
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>
/* 此处添加组件的样式 */
</style>
