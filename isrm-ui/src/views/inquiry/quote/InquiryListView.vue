<script setup>
import {onMounted, ref} from "vue";
import InquiryRequestTable from "@/components/inquiry/InquiryRequestTable.vue";
import {getInquiryRequestListForQuote} from "@/http/api";
import {useRouter} from "vue-router";
import {useUserStore} from "@/stores/user";
import {Button, PageHeader} from "view-ui-plus";

const loading = ref(true);
const requestList = ref([]);
const store = useUserStore();
onMounted(() => {
  initInquiryRequestList();
});

function initInquiryRequestList() {
  if (!!store.supplier) {
    getInquiryRequestListForQuote(store.supplier.code).then((data) => {
      requestList.value = data;
      loading.value = false;
    });
  } else {
    loading.value = false;
  }
}

const router = useRouter();

function show(inquiryCode) {
  router.push(`/main/inquiry/quote/request/list/${inquiryCode}`);
}
</script>

<template>
  <PageHeader>
    <template #title>报价管理</template>
  </PageHeader>
  <InquiryRequestTable
      :loading="loading"
      :requestList="requestList"
      v-slot="{row, index}">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.inquiryCode)">查看</Button>
  </InquiryRequestTable>
</template>
