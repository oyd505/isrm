<script setup>
import {Table} from "view-ui-plus";
import InquiryStatusSpan from "@/components/inquiry/InquiryStatusSpan.vue";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "编码",
    key: "inquiryCode",
  },
  {
    title: "标题",
    slot: "title",
  },
  {
    title: "采购员",
    key: "buyerName",
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
    <template #title="{ row }">
      <strong>{{ row.title }}</strong>
    </template>
    <template #status="{ row }">
      <InquiryStatusSpan :status="row.status"/>
    </template>
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>
