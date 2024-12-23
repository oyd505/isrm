<script setup>
import {Table} from "view-ui-plus";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: '员工工号',
    key: 'employeeCode'
  }, {
    title: '员工姓名',
    slot: 'name'
  }, {
    title: '所属部门',
    key: 'departmentName'
  }, {
    title: '职位',
    key: 'jobTitle'
  }, {
    title: '手机号码',
    key: 'phone'
  }, {
    title: '邮箱',
    key: 'email'
  }, {
    title: '操作',
    slot: 'action',
    width: 150,
    align: 'center'
  }
];

defineProps({
  employeeList: Array,
  loading: Boolean
})
</script>

<template>
  <Table :loading="loading" stripe :columns="columns" :data="employeeList">
    <template #name="{row}">
      <strong>{{ row.name }}</strong>
    </template>
    <template #action="{row, index}">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>