<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getProduct, saveProduct, updateProduct} from "@/http/api"; // 更新为商品相关的 API
import {Card, FormItem, Icon, Message, Modal, PageHeader, Row} from "view-ui-plus";
import ProductForm from "@/components/product/ProductForm.vue"; // 更新为商品表单组件

const route = useRoute();
const router = useRouter();
const productCode = ref(route.params.productCode); // 更新为商品编码
const product = ref({}); // 更新为商品对象

onMounted(() => {
  initProduct(); // 初始化商品
});

function initProduct() {
  getProduct(productCode.value).then(data => {
    product.value = data; // 更新为商品数据
  });
}

function update() {
  if (!!product.value.productCode) { // 更新为商品编码
    updateProduct(product.value).then(data => {
      Message.success("更新成功,商品编码: " + productCode.value); // 更新成功消息
    });
  } else {
    saveProduct(product.value).then(id => {
      productCode.value = id; // 更新为商品 ID
      Modal.confirm({
        title: '成功',
        content: "保存成功,商品编码: " + productCode.value,
        okText: '查看详情',
        cancelText: '继续新增',
        onOk: () => {
          initProduct(); // 重新初始化商品
          router.push(`/main/product/edit/${productCode.value}`); // 更新路由为商品编辑
        },
        onCancel: () => {
          product.value = {};
          productCode.value = 'undefined';
          router.push('/main/product/edit/undefined');
        }
      });
    });
  }
}

function handleReset() {
  product.value.name = null; // 更新为商品名称
  product.value.categoryCode = null; // 更新为商品类别编码
  product.value.categoryName = null; // 更新为商品类别名称
  product.value.price = null; // 更新为商品价格
  product.value.taxRate = null; // 更新为税率
  product.value.unitSymbol = null; // 更新为商品单位
  product.value.unitName = null; // 更新为商品单位
  product.value.categoryCode = null; // 更新为商品类别
  product.value.categoryName = null; // 更新为商品类别
}
</script>

<template>
  <PageHeader title="商品信息" back @on-back="router.push('/main/product/list')"> <!-- 更新为商品信息 -->
  </PageHeader>
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          商品详情 <!-- 更新为商品详情 -->
        </template>
        <ProductForm :product="product"> <!-- 更新为商品表单 -->
          <FormItem>
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </ProductForm>
      </Card>
    </Col>
  </Row>
</template>

<style scoped>

</style> 