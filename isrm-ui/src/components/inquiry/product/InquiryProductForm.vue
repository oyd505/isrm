<script setup>
// 导入 view-ui-plus 组件库中的组件
import {Button, Col, DatePicker, Form, FormItem, Input, InputNumber, Row} from "view-ui-plus";
// 导入 Vue 的生命周期钩子和 ref 函数
// 导入获取产品列表的 API 函数
// 导入自定义的组织选择组件
import OrganizationSelect from "@/components/common/OrganizationSelect.vue";
import ProductSelect from "@/components/product/ProductSelect.vue";
import ProductUnitSelect from "@/components/product/ProductUnitSelect.vue";

/**
 * 定义组件的 props
 * product: 一个对象，包含产品相关信息，是必传参数
 */
const props = defineProps({
  product: {type: Object, required: true}
});
// 定义组件的 emits
defineEmits(["updateEvent"]);

/**
 * 重置表单的处理函数
 */
function handleReset() {
  // 重置 product 的相关属性
  props.product.name = null;
  props.product.quantity = null;
  props.product.deliveryDate = null;
}
</script>

<template>
  <!-- 使用 view-ui-plus 的 Form 组件，绑定 model 为 product -->
  <Form :model="product" label-position="top">
    <Row>
      <Col span="16">
        <!-- 产品名称表单项 -->
        <FormItem label="名称">
          <ProductSelect v-model:name="product.name" v-model:code="product.code"/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 工厂选择表单项 -->
        <FormItem label="工厂">
          <OrganizationSelect orgType="fo" v-model:code="product.factoryCode" v-model:name="product.factoryName"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <!-- 产品编码表单项 -->
        <FormItem label="编码">
          <Input v-model="product.code" disabled/>
        </FormItem>
      </Col>
      <Col span="4">
        <!-- 产品数量表单项 -->
        <FormItem label="数量">
          <InputNumber v-model="product.quantity"/>
        </FormItem>
      </Col>
      <Col span="4">
        <!-- 产品单位表单项 -->
        <FormItem label="单位">
          <ProductUnitSelect v-model:unitSymbol="product.units"/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 产品交付期表单项 -->
        <FormItem label="交付期">
          <DatePicker :model-value="product.deliveryDate" type="date" @on-change="product.deliveryDate = $event"/>
        </FormItem>
      </Col>
    </Row>
    <FormItem>
      <!-- 保存和重置按钮 -->
      <Button type="primary" @click="$emit('updateEvent', props.product)">保存</Button>
      <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
    </FormItem>
  </Form>
</template>
