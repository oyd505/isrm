<script setup>
// 导入Vue的响应式和生命周期钩子，以及必要的组件和API
import { onMounted, ref } from "vue";
import InquiryRequestTable from "@/components/inquiry/InquiryRequestTable.vue";
import { getInquiryRequestListForQuote } from "@/http/api";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";
import { Button, PageHeader } from "view-ui-plus";

// 初始化加载状态和询价请求列表的响应式变量
const loading = ref(true);
const requestList = ref([]);
const store = useUserStore();

// 组件挂载后执行初始化函数
onMounted(() => {
  initInquiryRequestList();
});

/**
 * 初始化询价请求列表
 * 如果供应商信息存在，则获取其询价请求列表，否则直接结束加载状态
 */
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

// 获取路由实例
const router = useRouter();

/**
 * 跳转到指定询价请求的详情页面
 * @param {string} inquiryCode - 询价请求的唯一代码
 */
function show(inquiryCode) {
  router.push(`/main/inquiry/quote/request/list/${inquiryCode}`);
}
</script>

<template>
  <!-- 页面头部组件，显示报价管理标题 -->
  <PageHeader>
    <template #title>报价管理</template>
  </PageHeader>
  
  <!-- 询价请求表格组件，传递加载状态和请求列表数据 -->
  <InquiryRequestTable
      :loading="loading"
      :requestList="requestList"
      v-slot="{ row, index }">
    <!-- 表格操作列，提供查看详细信息的按钮 -->
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.inquiryCode)">查看</Button>
  </InquiryRequestTable>
</template>
