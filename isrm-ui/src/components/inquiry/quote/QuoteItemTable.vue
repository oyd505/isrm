 template
<script setup>
// 导入所需的组件：DatePicker、InputNumber和Table，这些组件用于创建和操作表格
import {DatePicker, InputNumber, Table} from "view-ui-plus";

// 定义表格的列配置，包括序号、商品编码、商品名称等列
const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "商品编码",
    key: "productCode",
    width: 120
  },
  {
    title: "商品名称",
    key: "productName",
    tooltip: true,
    align: "center"
  },
  {
    title: "报价数量",
    slot: "quoteQuantity",
    width: 150
  },
  {
    title: "单位",
    key: "units",
    width: 100
  },
  {
    title: "含税单价",
    slot: "price",
    width: 150
  },
  {
    title: "税率",
    slot: "taxRate",
    width: 100
  },
  {
    title: "交付限期",
    slot: "deliveryDeadline",
    width: 150
  },
  {
    title: "操作",
    slot: "action",
    width: 150,
    align: "center",
  },
];

// 定义组件接收的属性，包括loading状态、itemList数据和disabled状态
defineProps({
  loading: Boolean,
  itemList: {type: Object, required: true},
  disabled: {type: Boolean}
});
</script>

<template>
  // 渲染表格组件，设置最大高度、加载状态、条纹样式、列配置和数据源
  <Table max-height="220" :loading="loading" stripe :columns="columns" :data="itemList">
    // 使用模板插槽自定义“报价数量”列的渲染方式
    <template #quoteQuantity="{ row, index }">
      <InputNumber v-model="row.quoteQuantity" :disabled="disabled"/>
    </template>
    // 使用模板插槽自定义“含税单价”列的渲染方式
    <template #price="{ row, index }">
      <InputNumber v-model="row.price" :disabled="disabled"/>
    </template>
    // 使用模板插槽自定义“税率”列的渲染方式
    <template #taxRate="{ row, index }">
      <InputNumber v-model="row.taxRate" :disabled="disabled"/>
    </template>
    // 使用模板插槽自定义“交付限期”列的渲染方式
    <template #deliveryDeadline="{ row, index }">
      <DatePicker v-model="row.deliveryDeadline" type="date" :disabled="disabled" transfer/>
    </template>
    // 使用模板插槽自定义“操作”列的渲染方式，提供行数据和索引给父组件
    <template #action="{ row, index }">
      <slot name="tableAction" :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>
