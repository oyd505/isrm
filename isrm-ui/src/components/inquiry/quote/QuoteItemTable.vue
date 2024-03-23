<script setup>
import {DatePicker, InputNumber, Table} from "view-ui-plus";

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
defineProps({
  loading: Boolean,
  itemList: {type: Object, required: true},
  disabled: {type: Boolean}
});
</script>

<template>
  <Table max-height="220" :loading="loading" stripe :columns="columns" :data="itemList">
    <template #quoteQuantity="{ row, index }">
      <InputNumber v-model="row.quoteQuantity" :disabled="disabled"/>
    </template>
    <template #price="{ row, index }">
      <InputNumber v-model="row.price" :disabled="disabled"/>
    </template>
    <template #taxRate="{ row, index }">
      <InputNumber v-model="row.taxRate" :disabled="disabled"/>
    </template>
    <template #deliveryDeadline="{ row, index }">
      <DatePicker v-model="row.deliveryDeadline" type="date" :disabled="disabled" transfer/>
    </template>
    <template #action="{ row, index }">
      <slot name="tableAction" :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>