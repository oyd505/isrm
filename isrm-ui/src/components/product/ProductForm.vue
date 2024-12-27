 template
<script setup>
// 导入所需的组件
import { FormItem, Row, Col, Input, Button } from 'view-ui-plus';
import ProductUnitSelect from './ProductUnitSelect.vue';
import ProductCategorySelect from './ProductCategorySelect.vue';

// 定义组件的props
const props = defineProps({
    // product属性，类型为Object，必须提供
    product: {type: Object, required: true}
});
</script>

<template>
  <Form :model="product" label-position="top">
    <Row>
      <Col span="8">
        <FormItem label="商品编码" prop="productCode">
          <Input v-model="product.productCode" placeholder="请填写商品编码" disabled/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="商品名称" prop="name">
          <Input v-model="product.name" placeholder="请填写商品名称"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="商品价格" prop="price">
          <Input v-model="product.price" placeholder="请填写商品价格" type="number"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="税率" prop="taxRate">
          <Input v-model="product.taxRate" placeholder="请填写税率" type="number"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="单位" prop="unitSymbol">
            <ProductUnitSelect v-model:unitSymbol="product.unitSymbol" v-model:unitName="product.unitName"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="商品类别" prop="categoryCode">
            <ProductCategorySelect v-model:categoryCode="product.categoryCode" v-model:categoryName="product.categoryName"/>
        </FormItem>
      </Col>
    </Row>
    <slot/>
  </Form>
</template>

<style scoped>
/* 添加样式 */
</style>
