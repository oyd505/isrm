<script setup>
// 导入 View UI Plus 组件库中的 Table 组件
import { Table } from "view-ui-plus";
// 导入 InquiryStatusSelect 组件，用于显示采购状态
import InquiryStatusSelect from "@/components/inquiry/InquiryStatusSelect.vue";

// 定义表格的列配置
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

// 定义组件接收的属性
defineProps({
  loading: Boolean,
  requestList: {type: Object, required: true},
});
</script>

<template>
  <!-- 使用 Table 组件显示数据 -->
  <Table :loading="loading" stripe :columns="columns" :data="requestList">
    <!-- 自定义标题列的显示 -->
    <template #title="{ row }">
      <strong>{{ row.title }}</strong>
    </template>
    <!-- 自定义状态列的显示，使用 InquiryStatusSelect 组件 -->
    <template #status="{ row }">
      <InquiryStatusSelect v-model="row.status" comp-type="span" />
    </template>
    <!-- 自定义操作列的显示，提供一个插槽供外部定义操作按钮 -->
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>
