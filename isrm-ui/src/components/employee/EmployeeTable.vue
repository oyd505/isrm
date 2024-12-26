 template
<script setup>
// 导入 View UI Plus 的 Table 组件
import { Table } from "view-ui-plus";

// 定义表格的列配置
const columns = [
  // 序号列，使用索引类型，宽度为 80 像素
  { title: "序号", type: "index", width: 80 },
  // 员工工号列，数据键为 'employeeCode'
  { title: '员工工号', key: 'employeeCode' },
  // 员工姓名列，使用插槽自定义内容
  { title: '员工姓名', slot: 'name' },
  // 所属部门列，数据键为 'departmentName'
  { title: '所属部门', key: 'departmentName' },
  // 职位列，数据键为 'jobTitle'
  { title: '职位', key: 'jobTitle' },
  // 手机号码列，数据键为 'phone'
  { title: '手机号码', key: 'phone' },
  // 邮箱列，数据键为 'email'
  { title: '邮箱', key: 'email' },
  // 操作列，使用插槽自定义内容，宽度为 150 像素，居中对齐
  { title: '操作', slot: 'action', width: 150, align: 'center' }
];

// 定义组件的属性，包括员工列表和加载状态
defineProps({
  employeeList: Array,
  loading: Boolean
})
</script>

<template>
  <!-- 使用 Table 组件显示数据 -->
  <Table :loading="loading" stripe :columns="columns" :data="employeeList">
    <!-- 使用插槽自定义员工姓名列的内容 -->
    <template #name="{row}">
      <strong>{{ row.name }}</strong>
    </template>
    <!-- 使用插槽自定义操作列的内容 -->
    <template #action="{row, index}">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>
/* 样式部分留空，可根据需要添加自定义样式 */
</style>
