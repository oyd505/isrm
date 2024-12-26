<script setup>
// 导入 View UI Plus 组件库中的 Table 组件
import { Table } from "view-ui-plus";

// 定义表格的列配置
const columns = [
  // 定义序号列，使用索引类型，宽度为 80 像素
  { title: "序号", type: "index", width: 80 },
  // 定义供应商名称列，键名为 name，居中对齐
  {
    title: "供应商名称",
    key: "name",
    align: "center"
  },
  // 定义联系人列，键名为 contactName，宽度为 140 像素
  {
    title: "联系人",
    key: "contactName",
    width: 140
  },
  // 定义已审核列，键名为 audited，宽度为 80 像素
  {
    title: "已审核",
    key: "audited",
    width: 80
  },
  // 定义已确认列，键名为 confirmed，宽度为 80 像素
  {
    title: "已确认",
    key: "confirmed",
    width: 80
  },
  // 定义报价次数列，键名为 quoteCount，宽度为 140 像素
  {
    title: "报价次数",
    key: "quoteCount",
    width: 140
  },
  // 定义报价编码列，键名为 quoteCode，宽度为 140 像素
  {
    title: "报价编码",
    key: "quoteCode",
    width: 140
  },
];

// 定义组件的属性，包括 loading 状态和供应商报价列表 supplierQuoteList
defineProps({
  loading: Boolean,
  supplierQuoteList: { type: Object, required: true },
});
</script>

<template>
  <!-- 渲染表格组件，设置最大高度为 320 像素，启用加载状态和斑马纹样式 -->
  <Table max-height="320" :loading="loading" stripe :columns="columns" :data="supplierQuoteList">
  </Table>
</template>
