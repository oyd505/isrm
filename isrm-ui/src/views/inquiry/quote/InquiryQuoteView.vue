<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {
  applyInquirySupplier,
  confirmInquiryInvitation,
  createQuoteByInquiry,
  delQuoteRequest,
  getInquiryProductList,
  getInquiryRequest,
  getInquirySupplierByCode,
  getQuoteRequestList
} from "@/http/api";
import InquiryRequestForm from "@/components/inquiry/InquiryRequestForm.vue";
import InquiryProductTable from "@/components/inquiry/product/InquiryProductTable.vue";
import {
  Button,
  ButtonGroup,
  Card,
  Col,
  Description,
  DescriptionList,
  Drawer,
  Form,
  FormItem,
  Icon,
  Message,
  Modal,
  PageHeader,
  Poptip,
  Row
} from "view-ui-plus";
import {useUserStore} from "@/stores/user";
import QuoteRequestTable from "@/components/inquiry/quote/QuoteRequestTable.vue";

const route = useRoute();
const inquiryCode = route.params.inquiryCode;
const loading = ref(true);
const quoteList = ref([])
const inquiry = ref({});
const productDrawer = ref(false);
const productLoading = ref(true);
const productList = ref([]);
const store = useUserStore();
const applyModal = ref(false);
const supplier = ref({inquiryCode: inquiryCode, code: store.supplier.code, name: store.supplier.name});
onMounted(() => {
  initInquirySupplier();
  initQuoteRequestList();
  initInquiryRequest();
});

function initQuoteRequestList() {
  getQuoteRequestList(inquiryCode, store.supplier.code).then((data) => {
    quoteList.value = data;
    loading.value = false;
  });
}

function initInquirySupplier() {
  getInquirySupplierByCode(inquiryCode, store.supplier.code).then((data) => {
    supplier.value = data;
    supplier.value.code = store.supplier.code;
    supplier.value.name = store.supplier.name;
  });
}

function initInquiryRequest() {
  getInquiryRequest(inquiryCode).then((data) => {
    inquiry.value = data;
  });
}

function confirm() {
  confirmInquiryInvitation(inquiryCode, supplier.value.code).then(() => {
    Message.info("响应邀请成功")
    initInquirySupplier();
  })
}

function quote() {
  createQuoteByInquiry(inquiryCode, supplier.value.code).then(() => {
    Message.info("创建报价成功")
    initQuoteRequestList();
  })
}

const router = useRouter();

function show(quoteCode) {
  router.push(`/main/inquiry/quote/request/edit/${quoteCode}`);
}

function remove(quoteCode) {
  delQuoteRequest(quoteCode).then(() => {
    initQuoteRequestList();
  });
}

function apply() {
  applyModal.value = true;
}

function ok() {
  applyInquirySupplier(inquiryCode, supplier.value).then((id) => {
    Message.info("申请成功,ID: " + id);
    initInquirySupplier();
  })
}

function cancel() {
  supplier.value.contactName = null;
  supplier.value.contactPhone = null;
}

function showInquiryProductList() {
  productDrawer.value = true;
  if (productList.value.length < 1) {
    initInquiryProductList();
  }
}

function initInquiryProductList() {
  getInquiryProductList(inquiryCode).then((data) => {
    productList.value = data;
    productLoading.value = false;
  });
}
</script>
<template>
  <PageHeader title="报价申请" back @on-back="router.push('/main/inquiry/quote/list/inquiry')">
    <template #action>
      <ButtonGroup>
        <Button type="primary" @click="apply()" v-if="!supplier.id">申请</Button>
        <Button type="primary" @click="confirm()" v-if="!!supplier.id && supplier.confirmed !== 'Y'">确认</Button>
        <Button type="primary" @click="quote()">报价</Button>
      </ButtonGroup>
    </template>
    <template #content>
      <DescriptionList :col="1">
        <Description term="询价编码：">{{ inquiryCode }}
          <Poptip placement="bottom" trigger="hover" width="400">
            <Button type="dashed">详情</Button>
            <template #content>
              <InquiryRequestForm :inquiry="inquiry" :disabled="true"/>
            </template>
          </Poptip>
          <Button type="default" @click="showInquiryProductList">商品</Button>
        </Description>
        <Description term="供应商：">{{ supplier.name }}</Description>
      </DescriptionList>
    </template>
    <template #extra>
      <DescriptionList :col="1">
        <Description term="审核状态：">{{ supplier.audited }}</Description>
        <Description term="确认状态：">{{ supplier.confirmed }}</Description>
      </DescriptionList>
    </template>
  </PageHeader>
  <Card>
    <template #title>
      <Icon type="md-list"/>
      报价申请列表
    </template>
    <QuoteRequestTable :loading="loading" :request-list="quoteList" v-slot="{row, index}">
      <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.quoteCode)">查看</Button>
      <Poptip confirm transfer @on-ok="remove(row.quoteCode)">
        <Button type="error" size="small">删除</Button>
        <template #title>
          <span>确定要删除{{ row.title }}?</span>
        </template>
      </Poptip>
    </QuoteRequestTable>
  </Card>
  <Drawer title="询价商品列表" placement="bottom" :closable="false" v-model="productDrawer">
    <InquiryProductTable :loading="productLoading" :productList="productList"/>
  </Drawer>

  <Modal v-model="applyModal" title="申请询价供应商" @on-ok="ok" @on-cancel="cancel">
    <Form :model="supplier">
      <Row>
        <Col span="12">
          <FormItem label="名称">
            <Input v-model="supplier.name" disabled/>
          </FormItem>
        </Col>
        <Col span="12">
          <FormItem label="编码">
            <Input v-model="supplier.code" disabled/>
          </FormItem>
        </Col>
      </Row>
      <Row>
        <Col span="12">
          <FormItem label="联系人">
            <Input v-model="supplier.contactName"/>
          </FormItem>
        </Col>
        <Col span="12">
          <FormItem label="联系电话">
            <Input v-model="supplier.contactPhone"/>
          </FormItem>
        </Col>
      </Row>
    </Form>
  </Modal>
</template>