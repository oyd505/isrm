<script setup>
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {Button, Card, Col, Icon, Message, Poptip, Row} from "view-ui-plus";
import InquiryProductTable from "@/components/inquiry/product/InquiryProductTable.vue";
import InquiryProductForm from "@/components/inquiry/product/InquiryProductForm.vue";
import {
  delInquiryProduct,
  getInquiryProduct,
  getInquiryProductList,
  getInquiryRequest,
  saveInquiryProduct,
  updateInquiryProduct,
} from "@/http/api";
import InquiryPageHeader from "@/components/inquiry/InquiryPageHeader.vue";

const loading = ref(true);
const productList = ref([]);
const product = ref({});
const route = useRoute();
const inquiryCode = route.params.inquiryCode;
const inquiry = ref({});
onMounted(() => {
  initInquiryRequest();
  initInquiryProductList();
});

function initInquiryRequest() {
  getInquiryRequest(inquiryCode).then((data) => {
    inquiry.value = data;
  });
}

function initInquiryProductList() {
  getInquiryProductList(inquiryCode).then((data) => {
    productList.value = data;
    loading.value = false;
    product.value = {};
  });
}

function add() {
  product.value = {};
}

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

function select(row) {
  getInquiryProduct(inquiryCode, row.id).then((data) => {
    product.value = data;
  });
}

function remove(id) {
  delInquiryProduct(inquiryCode, id).then(() => {
    initInquiryProductList();
  });
}
</script>

<template>
  <InquiryPageHeader :inquiry="inquiry" :inquiry-code="inquiryCode"/>
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
  <Card>
    <template #title>
      <Icon type="md-list"/>
      询价商品列表
    </template>
    <template #extra>
      <Button type="primary" size="small" @click="add()" v-if="inquiry.status === 'PENDING'">新增</Button>
    </template>
    <InquiryProductTable :loading="loading" :productList="productList" @rowClickEvent="select">
      <template #tableAction="{row, index}">
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
