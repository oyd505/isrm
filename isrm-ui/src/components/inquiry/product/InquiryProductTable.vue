<script setup>
import { Table } from "view-ui-plus";

// 定义表格的列配置
const columns = [
  { title: "序号", type: "index", width: 80 },
  {
    title: "编码",
    key: "code",
    width: 120
  },
  {
    title: "名称",
    key: "name",
    tooltip: true,
    align: "center"
  },
  {
    title: "数量",
    key: "quantity",
    width: 120
  },
  {
    title: "单位",
    key: "units",
    width: 100
  },
  {
    title: "工厂",
    key: "factoryName",
    width: 150
  },
  {
    title: "交付期",
    key: "deliveryDate",
    width: 120
  },
  {
    title: "操作",
    slot: "action",
    width: 150,
    align: "center",
  },
];

// 定义组件的props
const { loading, productList } = defineProps({
  loading: Boolean,
  productList: { type: Object, required: true },
});

// 定义组件的emit事件
const emit = defineEmits(["rowClickEvent"]);
</script>

<template>
  <!-- 渲染表格组件 -->
  <Table
    max-height="220"
    :loading="loading"
    stripe
    :columns="columns"
    :data="productList"
    @on-row-dblclick="$emit('rowClickEvent', $event)"
  >
    <!-- 定义操作列的模板 -->
    <template #action="{ row, index }">
      <!-- 插槽用于自定义操作列的内容 -->
      <slot name="tableAction" :row="row" :index="index"></slot>
    </template>
  </Table>
</template>
