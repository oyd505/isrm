<script setup>
// 导入 view-ui-plus 组件库中的相关组件
import { Button, Card, Icon, Poptip, Table } from "view-ui-plus";

// 定义表格的列配置
const columns = [
  { title: "序号", type: "index", width: 80 },
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

// 定义组件接收的属性
defineProps({
  loading: Boolean,
  supplierList: { type: Object, required: true },
});

// 定义组件发射的事件
defineEmits(["removeEvent", "rowClickEvent", "auditEvent"]);
</script>

<template>
  <!-- 使用 Card 组件包裹整个供应商列表 -->
  <Card>
    <!-- Card 组件的标题部分 -->
    <template #title>
      <Icon type="md-list"/>
      询价供应商列表
    </template>
    <!-- Card 组件的额外操作区域 -->
    <template #extra>
      <slot name="cardExtra"></slot>
    </template>
    <!-- 表格组件，用于展示供应商列表数据 -->
    <Table max-height="220" :loading="loading" stripe :columns="columns" :data="supplierList"
           @on-row-dblclick="$emit('rowClickEvent', $event)">
      <!-- 自定义名称列的展示内容 -->
      <template #name="{ row }">
        <strong>{{ row.name }}</strong>
      </template>
      <!-- 自定义操作列的展示内容 -->
      <template #action="{ row, index }">
        <!-- 审核按钮，点击时触发 auditEvent 事件 -->
        <Button type="primary" size="small" @click="$emit('auditEvent', row.id)">审核</Button>
        <!-- 删除按钮，使用 Poptip 组件进行确认提示 -->
        <Poptip confirm transfer @on-ok="$emit('removeEvent', row.id)">
          <Button type="error" size="small">删除</Button>
          <!-- Poptip 组件的标题内容 -->
          <template #title>
            <span>确定要删除{{ row.name }}?</span>
          </template>
        </Poptip>
      </template>
    </Table>
  </Card>
</template>
