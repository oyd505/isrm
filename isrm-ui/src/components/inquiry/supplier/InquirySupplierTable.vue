<script setup>
import {Button, Card, Icon, Poptip, Table} from "view-ui-plus";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "编码",
    key: "code",
    width: 160
  },
  {
    title: "名称",
    slot: "name",
    tooltip: true,
    align: "center"
  },
  {
    title: "联系人",
    key: "contactName",
    width: 140
  },
  {
    title: "联系电话",
    key: "contactPhone",
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
    title: "操作",
    slot: "action",
    width: 150,
    align: "center",
  },
];
defineProps({
  loading: Boolean,
  supplierList: {type: Object, required: true},
});
defineEmits(["removeEvent", "rowClickEvent", "auditEvent"]);
</script>

<template>
  <Card>
    <template #title>
      <Icon type="md-list"/>
      询价供应商列表
    </template>
    <template #extra>
      <slot name="cardExtra"></slot>
    </template>
    <Table max-height="220" :loading="loading" stripe :columns="columns" :data="supplierList"
           @on-row-dblclick="$emit('rowClickEvent', $event)">
      <template #name="{ row }">
        <strong>{{ row.name }}</strong>
      </template>
      <template #action="{ row, index }">
        <Button type="primary" size="small" @click="$emit('auditEvent', row.id)">审核</Button>
        <Poptip confirm transfer @on-ok="$emit('removeEvent', row.id)">
          <Button type="error" size="small">删除</Button>
          <template #title>
            <span>确定要删除{{ row.name }}?</span>
          </template>
        </Poptip>
      </template>
    </Table>
  </Card>
</template>
