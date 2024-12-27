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
// 导入 InquiryRequestForm 组件用于显示询价请求的详细信息
import InquiryRequestForm from "@/components/inquiry/InquiryRequestForm.vue";
// 导入 InquiryProductTable 组件用于显示询价商品列表
import InquiryProductTable from "@/components/inquiry/product/InquiryProductTable.vue";
// 导入 view-ui-plus 的各种组件用于页面布局和功能实现
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
// 导入用户存储以获取当前供应商信息
import {useUserStore} from "@/stores/user";
// 导入 QuoteRequestTable 组件用于显示报价请求列表
import QuoteRequestTable from "@/components/inquiry/quote/QuoteRequestTable.vue";

// 获取路由参数中的询价编码
const route = useRoute();
const inquiryCode = route.params.inquiryCode;
// 定义加载状态变量
const loading = ref(true);
// 定义报价列表变量
const quoteList = ref([])
// 定义询价请求变量
const inquiry = ref({});
// 定义商品抽屉的显示状态变量
const productDrawer = ref(false);
// 定义商品列表的加载状态变量
const productLoading = ref(true);
// 定义商品列表变量
const productList = ref([]);
// 获取当前用户存储
const store = useUserStore();
// 定义申请模态框的显示状态变量
const applyModal = ref(false);
// 定义供应商信息变量，并初始化为当前供应商的编码和名称
const supplier = ref({inquiryCode: inquiryCode, code: store.supplier.code, name: store.supplier.name});
// 在组件挂载时初始化数据
onMounted(() => {
  initInquirySupplier();
  initQuoteRequestList();
  initInquiryRequest();
});

// 初始化报价请求列表
function initQuoteRequestList() {
  getQuoteRequestList(inquiryCode, store.supplier.code).then((data) => {
    quoteList.value = data;
    loading.value = false;
  });
}

// 初始化询价供应商信息
function initInquirySupplier() {
  getInquirySupplierByCode(inquiryCode, store.supplier.code).then((data) => {
    supplier.value = data;
    supplier.value.code = store.supplier.code;
    supplier.value.name = store.supplier.name;
  });
}

// 初始化询价请求信息
function initInquiryRequest() {
  getInquiryRequest(inquiryCode).then((data) => {
    inquiry.value = data;
  });
}

// 确认询价邀请
function confirm() {
  confirmInquiryInvitation(inquiryCode, supplier.value.code).then(() => {
    Message.info("响应邀请成功")
    initInquirySupplier();
  })
}

// 创建报价
function quote() {
  createQuoteByInquiry(inquiryCode, supplier.value.code).then(() => {
    Message.info("创建报价成功")
    initQuoteRequestList();
  })
}

// 路由导航
const router = useRouter();

// 显示报价详情页面
function show(quoteCode) {
  router.push(`/main/inquiry/quote/request/edit/${quoteCode}`);
}

// 删除报价请求
function remove(quoteCode) {
  delQuoteRequest(quoteCode).then(() => {
    initQuoteRequestList();
  });
}

// 申请成为询价供应商
function apply() {
  applyModal.value = true;
}

// 提交申请
function ok() {
  applyInquirySupplier(inquiryCode, supplier.value).then((id) => {
    Message.info("申请成功,ID: " + id);
    initInquirySupplier();
  })
}

// 取消申请
function cancel() {
  supplier.value.contactName = null;
  supplier.value.contactPhone = null;
}

// 显示询价商品列表
function showInquiryProductList() {
  productDrawer.value = true;
  if (productList.value.length < 1) {
    initInquiryProductList();
  }
}

// 初始化询价商品列表
function initInquiryProductList() {
  getInquiryProductList(inquiryCode).then((data) => {
    productList.value = data;
    productLoading.value = false;
  });
}
</script>
<template>
  <!-- 页面头部，包含标题、返回按钮和操作按钮 -->
  <PageHeader title="报价申请" back @on-back="router.push('/main/inquiry/quote/list/inquiry')">
    <template #action>
      <ButtonGroup>
        <Button type="primary" @click="apply()" v-if="!supplier.id">申请</Button>
        <Button type="primary" @click="confirm()" v-if="!!supplier.id && supplier.confirmed !== 'Y'">确认</Button>
        <Button type="primary" @click="quote()">报价</Button>
      </ButtonGroup>
    </template>
    <!-- 显示询价请求的基本信息 -->
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
    <!-- 显示供应商的审核和确认状态 -->
    <template #extra>
      <DescriptionList :col="1">
        <Description term="审核状态：">{{ supplier.audited }}</Description>
        <Description term="确认状态：">{{ supplier.confirmed }}</Description>
      </DescriptionList>
    </template>
  </PageHeader>
  <!-- 显示报价申请列表 -->
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
  <!-- 显示询价商品列表的抽屉 -->
  <Drawer title="询价商品列表" placement="bottom" :closable="false" v-model="productDrawer">
    <InquiryProductTable :loading="productLoading" :productList="productList"/>
  </Drawer>

  <!-- 申请询价供应商的模态框 -->
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
