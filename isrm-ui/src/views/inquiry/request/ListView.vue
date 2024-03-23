<script setup>
import {onMounted, ref} from "vue";
import InquiryRequestTable from "@/components/inquiry/InquiryRequestTable.vue";
import {delInquiryRequest, getInquiryRequestList} from "@/http/api";
import {Button, PageHeader, Poptip} from "view-ui-plus";
import {useRouter} from "vue-router";

const loading = ref(true);
const requestList = ref([]);
onMounted(() => {
  initInquiryRequestList();
});

function initInquiryRequestList() {
  getInquiryRequestList().then((data) => {
    requestList.value = data;
    loading.value = false;
  });
}

const router = useRouter();

function show(inquiryCode) {
  router.push(`/main/inquiry/request/edit/${inquiryCode}`);
}

function remove(inquiryCode) {
  delInquiryRequest(inquiryCode).then(() => {
    initInquiryRequestList();
  });
}
</script>

<template>
  <PageHeader>
    <template #title>询价申请管理</template>
    <template #action>
      <Button type="primary" to="/main/inquiry/request/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <InquiryRequestTable :loading="loading" :requestList="requestList" v-slot="{row, index}">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.inquiryCode)">查看</Button>
    <Poptip confirm transfer @on-ok="remove(row.inquiryCode)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定要删除{{ row.title }}?</span>
      </template>
    </Poptip>
  </InquiryRequestTable>
</template>
