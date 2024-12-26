<script setup>
import {onMounted, ref} from "vue";
import {useRoute, useRouter} from "vue-router";
import {Button, ButtonGroup, Card, Col, Drawer, FormItem, Icon, Message, PageHeader, Row} from "view-ui-plus";
import InquiryRequestForm from "@/components/inquiry/InquiryRequestForm.vue";
import {
  getInquiryRequest,
  getInquirySupplierQuoteList,
  publishInquiryRequest,
  saveInquiryRequest,
  submitInquiryRequest,
  updateInquiryRequest
} from "@/http/api";
import InquirySupplierQuoteTable from "@/components/inquiry/supplier/InquirySupplierQuoteTable.vue";

const route = useRoute();
const router = useRouter();
const inquiryCode = ref(route.params.inquiryCode);
const inquiry = ref({});
const supplierQuoteDrawer = ref(false);
const supplierQuoteLoading = ref(true);
const supplierQuoteList = ref([]);
onMounted(() => {
  initInquiryRequest();
});

function initInquiryRequest() {
  getInquiryRequest(inquiryCode.value).then((data) => {
    inquiry.value = data;
  });
}

function submit() {
  submitInquiryRequest(inquiryCode.value).then(() => {
    Message.info("提交成功,编号: " + inquiryCode.value);
    initInquiryRequest();
  });
}

function publish() {
  publishInquiryRequest(inquiryCode.value).then(() => {
    Message.info("提交成功,编号: " + inquiryCode.value);
    initInquiryRequest();
  });
}

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

function showInquirySupplierQuoteList() {
  supplierQuoteDrawer.value = true;
  if (supplierQuoteList.value.length < 1) {
    initInquirySupplierQuoteList();
  }
}

function initInquirySupplierQuoteList() {
  supplierQuoteLoading.value = true;
  getInquirySupplierQuoteList(inquiryCode.value).then((data) => {
    supplierQuoteList.value = data;
    supplierQuoteLoading.value = false;
  });
}
</script>

<template>
  <PageHeader title="询价信息" back @on-back="router.push('/main/inquiry/request/list')">
    <template #action>
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
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          询价详情
        </template>
        <InquiryRequestForm :inquiry="inquiry" :disabled="inquiry.status !== 'PENDING' && inquiryCode !== 'undefined'">
          <FormItem>
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </InquiryRequestForm>
      </Card>
    </Col>
  </Row>

  <Drawer title="询价供应商报价监控" placement="bottom" :closable="false" v-model="supplierQuoteDrawer" height="60">
    <InquirySupplierQuoteTable :loading="supplierQuoteLoading" :supplierQuoteList="supplierQuoteList"/>
  </Drawer>
</template>
