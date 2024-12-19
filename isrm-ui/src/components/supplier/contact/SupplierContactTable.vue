<script setup>
import {Table} from "view-ui-plus";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "名称",
    key: "name",
    tooltip: true,
    align: "center"
  },
  {
    title: "电话",
    key: "phone",
    width: 180
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
  contactList: {type: Object, required: true},
});
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

</style>