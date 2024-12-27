 template
<script setup>
import { Table } from "view-ui-plus";

// 定义表格的列配置
const columns = [
  { title: "序号", type: "index", width: 80 },
  {
    title: '商品编码',
    key: 'productCode'
  }, {
    title: '商品名称',
    slot: 'name',
    minWidth: 240
  }, {
    title: '商品价格',
    key: 'price'
  }, {
    title: '税率',
    key: 'taxRate'
  }, {
    title: '单位',
    key: 'unitName'
  }, {
    title: '单位符号',
    key: 'unitSymbol'
  }, {
    title: '分类编码',
    key: 'categoryCode'
  }, {
    title: '分类名称',
    key: 'categoryName'
  }, {
    title: '操作',
    slot: 'action',
    width: 150,
    align: 'center'
  }
];

// 定义组件的props
defineProps({
  productList: Array,
  loading: Boolean
})
</script>

<template>
  <Table :loading="loading" stripe :columns="columns" :data="productList">
    <template #name="{ row }">
      <strong>{{ row.name }}</strong>
    </template>
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>
