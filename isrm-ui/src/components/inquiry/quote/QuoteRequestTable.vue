<script setup>
import {Table} from "view-ui-plus";
import QuoteStatusSpan from "@/components/inquiry/quote/QuoteStatusSpan.vue";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "编码",
    key: "quoteCode",
  },
  {
    title: "供应商",
    key: "supplierName",
  },
  {
    title: "询价编码",
    key: "inquiryCode",
  },
  {
    title: "状态",
    slot: "status",
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
  requestList: {type: Object, required: true},
});
</script>

<template>
  <Table :loading="loading" stripe :columns="columns" :data="requestList">
    <template #status="{ row }">
      <QuoteStatusSpan :status="row.status"/>
    </template>
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>