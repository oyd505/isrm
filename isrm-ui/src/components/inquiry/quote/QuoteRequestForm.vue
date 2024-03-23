<script setup>
import {Col, Form, FormItem, Input, InputNumber, Row, Select} from "view-ui-plus";
import CurrencySelect from "@/components/common/CurrencySelect.vue";

const props = defineProps({
  quote: {type: Object, required: true},
  disabled: {type: Boolean}
});
</script>

<template>
  <Form :model="quote" label-position="top" :disabled="disabled">
    <Row>
      <Col span="8">
        <FormItem label="编码">
          <Input v-model="quote.quoteCode" disabled/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="状态">
          <Select v-model="quote.status" disabled>
            <Option value="PENDING">未提交状态</Option>
            <Option value="SUBMITTED">已提交状态</Option>
            <Option value="CANCELED">已取消状态</Option>
          </Select>
        </FormItem>
      </Col>
      <FormItem label="询价编码">
        <Input v-model="quote.inquiryCode" disabled/>
      </FormItem>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="币种">
          <CurrencySelect v-model="quote.currency"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="金额">
          <InputNumber v-model="quote.amount" disabled/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="供应商编码">
          <Input v-model="quote.supplierCode" disabled/>
        </FormItem>
      </Col>
      <Col span="16">
        <FormItem label="供应商名称">
          <Input v-model="quote.supplierName" disabled/>
        </FormItem>
      </Col>
    </Row>
    <slot></slot>
  </Form>
</template>

<style scoped>

</style>