 template
<script setup>
// 导入 View UI Plus 组件库中的各个组件
import {Button, Card, Col, FormItem, Icon, Message, PageHeader, Row} from "view-ui-plus";
// 导入报价请求表单组件
import QuoteRequestForm from "@/components/inquiry/quote/QuoteRequestForm.vue";
// 导入 Vue 的响应式和生命周期钩子
import {onMounted, ref} from "vue";
// 导入 HTTP API 请求函数
import {getQuoteItemList, getQuoteRequest, submitQuoteRequest, updateQuoteItem, updateQuoteRequest} from "@/http/api";
// 导入 Vue Router 的钩子
import {useRoute, useRouter} from "vue-router";
// 导入报价项表格组件
import QuoteItemTable from "@/components/inquiry/quote/QuoteItemTable.vue";

// 初始化路由和路由器
const route = useRoute();
const router = useRouter();
// 从路由参数中获取报价代码
const quoteCode = route.params.quoteCode;
// 初始化报价信息和加载状态
const quote = ref({});
const loading = ref(true);
// 初始化报价项列表
const itemList = ref([]);

// 在组件挂载时执行初始化函数
onMounted(() => {
  initQuoteRequest();
  initQuoteItemList();
});

/**
 * 初始化报价请求数据
 * 通过报价代码获取报价请求的详细信息
 */
function initQuoteRequest() {
  getQuoteRequest(quoteCode).then((data) => {
    quote.value = data;
  });
}

/**
 * 初始化报价项列表数据
 * 通过报价代码获取报价项列表，并设置加载状态
 */
function initQuoteItemList() {
  getQuoteItemList(quoteCode).then((data) => {
    itemList.value = data;
    loading.value = false;
  })
}

/**
 * 更新报价请求
 * 保存对报价请求的修改，并显示成功消息
 */
function update() {
  updateQuoteRequest(quote.value).then(() => {
    Message.info("更新成功,编号: " + quoteCode);
  });
}

/**
 * 更新报价项
 * @param {Object} item - 需要更新的报价项
 * 保存对报价项的修改，并显示成功消息
 */
function updateItem(item) {
  updateQuoteItem(quoteCode, item).then(() => {
    Message.info("更新成功,ID: " + item.id);
  });
}

/**
 * 提交报价请求
 * 提交报价请求，并在成功后重新加载报价信息
 */
function submitQuote() {
  submitQuoteRequest(quoteCode).then(() => {
    Message.info("提交成功,编号: " + quoteCode);
    initQuoteRequest();
  });
}

/**
 * 重置报价请求的货币信息
 */
function handleReset() {
  quote.value.currency = null;
}
</script>

<template>
  <!-- 报价信息页面头部 -->
  <PageHeader title="报价信息" back @on-back="router.push('/main/inquiry/quote/request/list/'+quote.inquiryCode)">
    <template #action>
      <!-- 在待处理状态下显示提交按钮 -->
      <Button type="primary" @click="submitQuote" v-if="quote.status === 'PENDING'">提交</Button>
    </template>
  </PageHeader>
  <!-- 报价详情卡片 -->
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          报价详情
        </template>
        <!-- 报价请求表单，根据状态控制是否禁用 -->
        <QuoteRequestForm :quote="quote" :disabled="quote.status !== 'PENDING'">
          <FormItem>
            <!-- 保存和重置按钮 -->
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </QuoteRequestForm>
      </Card>
    </Col>
  </Row>
  <!-- 报价项表格，根据状态控制是否禁用 -->
  <QuoteItemTable :loading="loading" :item-list="itemList" :disabled="quote.status !== 'PENDING'">
    <template #tableAction="{row, index}">
      <!-- 表格操作列：保存按钮 -->
      <Button type="primary" @click="updateItem(row)" :disabled="quote.status !== 'PENDING'" size="small">保存</Button>
    </template>
  </QuoteItemTable>
</template>

<style scoped>

</style>
