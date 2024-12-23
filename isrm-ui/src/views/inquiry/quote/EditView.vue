<script setup>
import {Button, Card, Col, FormItem, Icon, Message, PageHeader, Row} from "view-ui-plus";
import QuoteRequestForm from "@/components/inquiry/quote/QuoteRequestForm.vue";
import {onMounted, ref} from "vue";
import {getQuoteItemList, getQuoteRequest, submitQuoteRequest, updateQuoteItem, updateQuoteRequest} from "@/http/api";
import {useRoute, useRouter} from "vue-router";
import QuoteItemTable from "@/components/inquiry/quote/QuoteItemTable.vue";

const route = useRoute();
const router = useRouter();
const quoteCode = route.params.quoteCode;
const quote = ref({});
const loading = ref(true);
const itemList = ref([]);
onMounted(() => {
  initQuoteRequest();
  initQuoteItemList();
});

function initQuoteRequest() {
  getQuoteRequest(quoteCode).then((data) => {
    quote.value = data;
  });
}

function initQuoteItemList() {
  getQuoteItemList(quoteCode).then((data) => {
    itemList.value = data;
    loading.value = false;
  })
}

function update() {
  updateQuoteRequest(quote.value).then(() => {
    Message.info("更新成功,编号: " + quoteCode);
  });
}

function updateItem(item) {
  updateQuoteItem(quoteCode, item).then(() => {
    Message.info("更新成功,ID: " + item.id);
  });
}

function submitQuote() {
  submitQuoteRequest(quoteCode).then(() => {
    Message.info("提交成功,编号: " + quoteCode);
    initQuoteRequest();
  });
}

function handleReset() {
  quote.value.currency = null;
}
</script>

<template>
  <PageHeader title="报价信息" back @on-back="router.push('/main/inquiry/quote/request/list/'+quote.inquiryCode)">
    <template #action>
      <Button type="primary" @click="submitQuote" v-if="quote.status === 'PENDING'">提交</Button>
    </template>
  </PageHeader>
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          报价详情
        </template>
        <QuoteRequestForm :quote="quote" :disabled="quote.status !== 'PENDING'">
          <FormItem>
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </QuoteRequestForm>
      </Card>
    </Col>
  </Row>
  <QuoteItemTable :loading="loading" :item-list="itemList" :disabled="quote.status !== 'PENDING'">
    <template #tableAction="{row, index}">
      <Button type="primary" @click="updateItem(row)" :disabled="quote.status !== 'PENDING'" size="small">保存</Button>
    </template>
  </QuoteItemTable>
</template>

<style scoped>

</style>