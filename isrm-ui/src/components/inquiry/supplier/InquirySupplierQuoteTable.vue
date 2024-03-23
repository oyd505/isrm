<script setup>
import {Table} from "view-ui-plus";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "供应商名称",
    key: "name",
    align: "center"
  },
  {
    title: "联系人",
    key: "contactName",
    width: 140
  },
  {
    title: "已审核",
    key: "audited",
    width: 80
  },
  {
    title: "已确认",
    key: "confirmed",
    width: 80
  },
  {
    title: "报价次数",
    key: "quoteCount",
    width: 140
  },
  {
    title: "报价编码",
    key: "quoteCode",
    width: 140
  },
];
defineProps({
  loading: Boolean,
  supplierQuoteList: {type: Object, required: true},
});
</script>

<template>
  <Table max-height="320" :loading="loading" stripe :columns="columns" :data="supplierQuoteList">
  </Table>
</template>
