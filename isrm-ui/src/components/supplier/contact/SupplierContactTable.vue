 template
<script setup>
// 导入 View UI Plus 的 Table 组件
import { Table } from "view-ui-plus";

// 定义表格的列配置
const columns = [
  // 序号列，使用索引类型，宽度为 80 像素
  { title: "序号", type: "index", width: 80 },
  // 名称列，键为 name，开启 tooltip 以支持长文本溢出时的提示，居中对齐
  {
    title: "名称",
    key: "name",
    tooltip: true,
    align: "center"
  },
  // 电话列，键为 phone，宽度为 180 像素
  {
    title: "电话",
    key: "phone",
    width: 180
  },
  // 操作列，使用 slot 方式自定义内容，宽度为 150 像素，居中对齐
  {
    title: "操作",
    slot: "action",
    width: 150,
    align: "center",
  },
];

// 定义组件的 props，包括 loading 状态和联系人列表 contactList
defineProps({
  loading: Boolean,
  contactList: { type: Object, required: true },
});
// 定义组件的 emits，用于触发行双击事件
defineEmits(["rowClickEvent"]);
</script>

<template>
  <Table max-height="220" :loading="loading" stripe :columns="columns" :data="contactList"
         @on-row-dblclick="$emit('rowClickEvent', $event)">
    <template #action="{ row, index }">
      <slot name="tableAction" :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>
/* 以下是样式代码，当前未定义任何样式 */
</style>
