<script setup>
import {Table} from "view-ui-plus";
import SupplierStatusSpan from "@/components/supplier/SupplierStatusSpan.vue";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "编码",
    key: "supplierCode",
  },
  {
    title: "名称",
    slot: "name",
  },
  {
    title: "状态",
    slot: "supplierStatus"
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
  supplierList: {type: Object, required: true},
});
</script>

<template>
  <Table :loading="loading" stripe :columns="columns" :data="supplierList">
    <template #name="{ row }">
      <strong>{{ row.name }}</strong>
    </template>
    <template #supplierStatus="{ row }">
      <SupplierStatusSpan :status="row.supplierStatus"/>
    </template>
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>