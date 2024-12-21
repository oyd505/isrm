<script setup>
import {Col, Form, FormItem, Input, Row} from "view-ui-plus";
import SupplierStatusSelect from "@/components/supplier/SupplierStatusSelect.vue";

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
          <SupplierStatusSelect v-model="supplier.supplierStatus" disabled/>
        </FormItem>
      </Col>
    </Row>
    <slot></slot>
  </Form>
</template>

<style scoped>

</style>