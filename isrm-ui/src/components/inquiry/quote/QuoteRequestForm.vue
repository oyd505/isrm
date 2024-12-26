 template
<script setup>
// 导入view-ui-plus组件库中的组件
import {Col, Form, FormItem, Input, InputNumber, Row} from "view-ui-plus";
// 导入自定义的货币选择组件
import CurrencySelect from "@/components/common/CurrencySelect.vue";
// 导入自定义的报价状态选择组件
import QuoteStatusSelect from "@/components/inquiry/quote/QuoteStatusSelect.vue";

// 定义组件的props，quote属性为一个对象，包含报价的相关信息
const props = defineProps({
  quote: {type: Object, required: true}
});
</script>

<template>
  <!-- 使用Form组件包裹整个表单，设置label位置为top -->
  <Form :model="quote" label-position="top">
    <!-- 使用Row和Col组件进行网格布局，将表单分为多个部分 -->
    <Row>
      <!-- 编码输入框，绑定quote对象的quoteCode属性，禁用状态 -->
      <Col span="8">
        <FormItem label="编码">
          <Input v-model="quote.quoteCode" disabled/>
        </FormItem>
      </Col>
      <!-- 状态选择框，绑定quote对象的status属性，禁用状态 -->
      <Col span="8">
        <FormItem label="状态">
          <QuoteStatusSelect v-model="quote.status" disabled/>
        </FormItem>
      </Col>
      <!-- 询价编码输入框，绑定quote对象的inquiryCode属性，禁用状态 -->
      <FormItem label="询价编码">
        <Input v-model="quote.inquiryCode" disabled/>
      </FormItem>
    </Row>
    <!-- 第二行表单元素 -->
    <Row>
      <!-- 币种选择框，绑定quote对象的currency属性 -->
      <Col span="8">
        <FormItem label="币种">
          <CurrencySelect v-model="quote.currency"/>
        </FormItem>
      </Col>
      <!-- 金额输入框，绑定quote对象的amount属性，禁用状态 -->
      <Col span="8">
        <FormItem label="金额">
          <InputNumber v-model="quote.amount" disabled/>
        </FormItem>
      </Col>
    </Row>
    <!-- 第三行表单元素 -->
    <Row>
      <!-- 供应商编码输入框，绑定quote对象的supplierCode属性，禁用状态 -->
      <Col span="8">
        <FormItem label="供应商编码">
          <Input v-model="quote.supplierCode" disabled/>
        </FormItem>
      </Col>
      <!-- 供应商名称输入框，绑定quote对象的supplierName属性，禁用状态 -->
      <Col span="16">
        <FormItem label="供应商名称">
          <Input v-model="quote.supplierName" disabled/>
        </FormItem>
      </Col>
    </Row>
    <!-- 插槽，用于父组件插入额外内容 -->
    <slot></slot>
  </Form>
</template>

<style scoped>
/* 此处添加组件的样式 */
</style>
