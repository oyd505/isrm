<script setup>
import {Col, Form, FormItem, Input, Row, Select} from "view-ui-plus";

const props = defineProps({
  supplier: {type: Object, required: true}
});
</script>

<template>
  <Form :model="supplier" label-position="top">
    <Row>
      <Col span="8">
        <FormItem label="编码">
          <Input v-model="supplier.supplierCode" disabled/>
        </FormItem>
      </Col>
      <Col span="16">
        <FormItem label="名称">
          <Input v-model="supplier.name"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="状态">
          <Select v-model="supplier.supplierStatus" disabled>
            <Option value="POTENTIAL">潜在</Option>
            <Option value="INTRODUCING">引入中</Option>
            <Option value="QUALIFIED">合格</Option>
            <Option value="EXITED">已退出</Option>
          </Select>
        </FormItem>
      </Col>
    </Row>
    <slot></slot>
  </Form>
</template>

<style scoped>

</style>