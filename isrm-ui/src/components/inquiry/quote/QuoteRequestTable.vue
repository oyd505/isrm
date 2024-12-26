 template
<script setup>
// 导入 Table 组件和报价状态选择组件
import {Table} from "view-ui-plus";
import QuoteStatusSelect from "@/components/inquiry/quote/QuoteStatusSelect.vue";

// 定义表格的列配置
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

// 定义组件的 props
defineProps({
  loading: Boolean,
  requestList: {type: Object, required: true},
});
</script>

<template>
  <Table :loading="loading" stripe :columns="columns" :data="requestList">
    <template #status="{ row }">
      <QuoteStatusSelect v-model="row.status" comp-type="span"/>
    </template>
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>
