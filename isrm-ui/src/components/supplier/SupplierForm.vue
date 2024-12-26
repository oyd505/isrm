 template
<script setup>
// 导入 View UI Plus 组件库中的组件
import {Col, Form, FormItem, Input, Row} from "view-ui-plus";
// 导入供应商状态选择组件
import SupplierStatusSelect from "@/components/supplier/SupplierStatusSelect.vue";

// 定义组件的 props
// supplier: 一个对象，包含供应商的信息，是必须的属性
const props = defineProps({
  supplier: {type: Object, required: true}
});
</script>

<template>
  <!-- 使用 Form 组件来布局供应商信息表单 -->
  <Form :model="supplier" label-position="top">
    <!-- 使用 Row 和 Col 组件来布局表单中的列 -->
    <Row>
      <Col span="8">
        <!-- 供应商编码输入框，禁止编辑 -->
        <FormItem label="编码">
          <Input v-model="supplier.supplierCode" disabled/>
        </FormItem>
      </Col>
      <Col span="16">
        <!-- 供应商名称输入框 -->
        <FormItem label="名称">
          <Input v-model="supplier.name"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <!-- 供应商状态选择框，禁止编辑 -->
        <FormItem label="状态">
          <SupplierStatusSelect v-model="supplier.supplierStatus" disabled/>
        </FormItem>
      </Col>
    </Row>
    <!-- 插槽，用于在组件使用时插入自定义内容 -->
    <slot></slot>
  </Form>
</template>

<style scoped>
/* scoped 样式，仅影响当前组件 */
</style>
