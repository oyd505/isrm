<script setup>
// 导入Vue的钩子和API
import {onMounted, ref} from "vue";
// 导入Vue Router的功能
import {useRoute, useRouter} from "vue-router";
// 导入View UI Plus的组件
import {Button, ButtonGroup, Card, Col, Drawer, FormItem, Icon, Message, PageHeader, Row} from "view-ui-plus";
// 导入询价请求表单组件
import InquiryRequestForm from "@/components/inquiry/InquiryRequestForm.vue";
// 导入与后端交互的API
import {
  getInquiryRequest,
  getInquirySupplierQuoteList,
  publishInquiryRequest,
  saveInquiryRequest,
  submitInquiryRequest,
  updateInquiryRequest
} from "@/http/api";
// 导入供应商报价表格组件
import InquirySupplierQuoteTable from "@/components/inquiry/supplier/InquirySupplierQuoteTable.vue";

// 使用Vue Router的路由信息和路由导航功能
const route = useRoute();
const router = useRouter();
// 定义一个响应式的变量来存储询价代码
const inquiryCode = ref(route.params.inquiryCode);
// 定义一个响应式的变量来存储询价请求的数据
const inquiry = ref({});
// 定义一个响应式的变量来控制供应商报价抽屉的显示与隐藏
const supplierQuoteDrawer = ref(false);
// 定义一个响应式的变量来控制供应商报价数据的加载状态
const supplierQuoteLoading = ref(true);
// 定义一个响应式的变量来存储供应商报价的数据列表
const supplierQuoteList = ref([]);

// 在组件挂载时获取询价请求数据
onMounted(() => {
  initInquiryRequest();
});

/**
 * 初始化询价请求数据
 */
function initInquiryRequest() {
  getInquiryRequest(inquiryCode.value).then((data) => {
    inquiry.value = data;
  });
}

/**
 * 提交询价请求
 */
function submit() {
  submitInquiryRequest(inquiryCode.value).then(() => {
    Message.info("提交成功,编号: " + inquiryCode.value);
    initInquiryRequest();
  });
}

/**
 * 发布询价请求
 */
function publish() {
  publishInquiryRequest(inquiryCode.value).then(() => {
    Message.info("提交成功,编号: " + inquiryCode.value);
    initInquiryRequest();
  });
}

/**
 * 更新或保存询价请求
 */
function update() {
  if (!!inquiry.value.inquiryCode) {
    updateInquiryRequest(inquiry.value).then(() => {
      Message.info("更新成功,编号: " + inquiryCode.value);
    });
  } else {
    saveInquiryRequest(inquiry.value).then((id) => {
      inquiryCode.value = id.value;
      Message.info("新增成功,编号: " + inquiryCode.value);
      initInquiryRequest();
      // 刷新URL不刷新数据
      router.push(`/main/inquiry/request/edit/${inquiryCode.value}`);
    });
  }
}

/**
 * 重置询价请求表单
 */
function handleReset() {
  inquiry.value.title = null;
  inquiry.value.participation = null;
  inquiry.value.buyerCode = null;
  inquiry.value.reason = null;
  inquiry.value.startTime = null;
  inquiry.value.endTime = null;
  inquiry.value.currency = null;
  inquiry.value.purOrgCode = null;
}

/**
 * 显示供应商报价列表
 */
function showInquirySupplierQuoteList() {
  supplierQuoteDrawer.value = true;
  if (supplierQuoteList.value.length < 1) {
    initInquirySupplierQuoteList();
  }
}

/**
 * 初始化供应商报价数据
 */
function initInquirySupplierQuoteList() {
  supplierQuoteLoading.value = true;
  getInquirySupplierQuoteList(inquiryCode.value).then((data) => {
    supplierQuoteList.value = data;
    supplierQuoteLoading.value = false;
  });
}
</script>

<template>
  <!-- 询价信息页面头部 -->
  <PageHeader title="询价信息" back @on-back="router.push('/main/inquiry/request/list')">
    <template #action>
      <!-- 根据询价代码显示不同的按钮组 -->
      <ButtonGroup v-if="inquiryCode !== 'undefined'">
        <Button type="primary" :to="`/main/inquiry/product/list/${inquiryCode}`">商品</Button>
        <Button type="primary" :to="`/main/inquiry/supplier/list/${inquiryCode}`">供应商</Button>
        <Button type="primary">协作小组</Button>
      </ButtonGroup>
      <ButtonGroup v-if="inquiryCode !== 'undefined'">
        <Button type="info" v-show="inquiry.status === 'PENDING'" @click="submit">提交</Button>
        <Button type="info" v-show="inquiry.status === 'SUBMITTED'" @click="publish">发布</Button>
        <Button type="info" @click="showInquirySupplierQuoteList">监控报价</Button>
      </ButtonGroup>
    </template>
  </PageHeader>
  <!-- 询价详情卡片 -->
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          询价详情
        </template>
        <!-- 询价请求表单 -->
        <InquiryRequestForm :inquiry="inquiry" :disabled="inquiry.status !== 'PENDING' && inquiryCode !== 'undefined'">
          <FormItem>
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </InquiryRequestForm>
      </Card>
    </Col>
  </Row>

  <!-- 供应商报价监控抽屉 -->
  <Drawer title="询价供应商报价监控" placement="bottom" :closable="false" v-model="supplierQuoteDrawer" height="60">
    <InquirySupplierQuoteTable :loading="supplierQuoteLoading" :supplierQuoteList="supplierQuoteList"/>
  </Drawer>
</template>
