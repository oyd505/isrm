 template
<script setup>
// 导入 Vue 的钩子和 View UI 组件
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {Button, Card, Col, Icon, Message, Poptip, Row} from "view-ui-plus";
// 导入自定义组件
import InquiryProductTable from "@/components/inquiry/product/InquiryProductTable.vue";
import InquiryProductForm from "@/components/inquiry/product/InquiryProductForm.vue";
// 导入与后端交互的 API 函数
import {
  delInquiryProduct,
  getInquiryProduct,
  getInquiryProductList,
  getInquiryRequest,
  saveInquiryProduct,
  updateInquiryProduct,
} from "@/http/api";
// 导入公共组件
import InquiryPageHeader from "@/components/inquiry/InquiryPageHeader.vue";

// 定义响应式数据
const loading = ref(true);
const productList = ref([]);
const product = ref({});
const route = useRoute();
const inquiryCode = route.params.inquiryCode;
const inquiry = ref({});

// 在组件挂载时执行的钩子函数
onMounted(() => {
  initInquiryRequest();
  initInquiryProductList();
});

/**
 * 初始化询价请求数据
 */
function initInquiryRequest() {
  getInquiryRequest(inquiryCode).then((data) => {
    inquiry.value = data;
  });
}

/**
 * 初始化询价商品列表
 */
function initInquiryProductList() {
  getInquiryProductList(inquiryCode).then((data) => {
    productList.value = data;
    loading.value = false;
    product.value = {};
  });
}

/**
 * 新增商品时清空商品表单数据
 */
function add() {
  product.value = {};
}

/**
 * 更新或保存商品数据
 * @param {Object} product - 商品对象
 */
function update(product) {
  if (!!product.id) {
    updateInquiryProduct(inquiryCode, product).then(() => {
      Message.info("更新成功,ID: " + product.id);
      initInquiryProductList();
    });
  } else {
    saveInquiryProduct(inquiryCode, product).then((id) => {
      Message.info("新增成功,ID: " + id);
      initInquiryProductList();
    });
  }
}

/**
 * 选择商品，根据商品 ID 获取商品详情
 * @param {Object} row - 商品行数据
 */
function select(row) {
  getInquiryProduct(inquiryCode, row.id).then((data) => {
    product.value = data;
  });
}

/**
 * 删除商品
 * @param {String} id - 商品 ID
 */
function remove(id) {
  delInquiryProduct(inquiryCode, id).then(() => {
    initInquiryProductList();
  });
}
</script>

<template>
  <!-- 询价页面头部组件 -->
  <InquiryPageHeader :inquiry="inquiry" :inquiry-code="inquiryCode"/>
  <!-- 新增或编辑商品的表单 -->
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          询价商品详情
        </template>
        <InquiryProductForm :product="product" @updateEvent="update" :disabled="inquiry.status !== 'PENDING'"/>
      </Card>
    </Col>
  </Row>
  <!-- 询价商品列表 -->
  <Card>
    <template #title>
      <Icon type="md-list"/>
      询价商品列表
    </template>
    <template #extra>
      <!-- 在待处理状态下显示新增按钮 -->
      <Button type="primary" size="small" @click="add()" v-if="inquiry.status === 'PENDING'">新增</Button>
    </template>
    <InquiryProductTable :loading="loading" :productList="productList" @rowClickEvent="select">
      <template #tableAction="{row, index}">
        <!-- 在待处理状态下显示删除按钮 -->
        <Poptip confirm transfer @on-ok="remove(row.id)">
          <Button type="error" size="small" v-if="inquiry.status === 'PENDING'">删除</Button>
          <template #title>
            <span>确定要删除{{ row.name }}?</span>
          </template>
        </Poptip>
      </template>
    </InquiryProductTable>
  </Card>
</template>

