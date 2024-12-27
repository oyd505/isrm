<script setup>
// 导入Vue的钩子和组件
import {onMounted, ref} from "vue";
import InquiryRequestTable from "@/components/inquiry/InquiryRequestTable.vue";
import {delInquiryRequest, getInquiryRequestList} from "@/http/api";
import {Button, PageHeader, Poptip} from "view-ui-plus";
import {useRouter} from "vue-router";

// 初始化数据
const loading = ref(true);
const requestList = ref([]);
const pageTotal = ref(0);
const pageSize = ref(20);

// 组件挂载时获取询价申请列表
onMounted(() => {
  initInquiryRequestList(1);
});

/**
 * 获取询价申请列表
 * @param pageNumber 页码
 */
function initInquiryRequestList(pageNumber) {
  getInquiryRequestList(pageNumber, pageSize.value).then((data) => {
    requestList.value = data.content;
    loading.value = false;
    pageTotal.value = data.totalElements;
  });
}

/**
 * 修改每页显示数量时重新获取列表
 * @param size 每页显示数量
 */
function onPageSizeChange(size) {
  pageSize.value = size;
  initInquiryRequestList(1);
}

// 使用Vue Router进行路由导航
const router = useRouter();

/**
 * 跳转到询价申请详情页面
 * @param inquiryCode 询价申请代码
 */
function show(inquiryCode) {
  router.push(`/main/inquiry/request/edit/${inquiryCode}`);
}

/**
 * 删除询价申请后重新获取列表
 * @param inquiryCode 询价申请代码
 */
function remove(inquiryCode) {
  delInquiryRequest(inquiryCode).then(() => {
    initInquiryRequestList(1);
  });
}
</script>

<template>
  <!-- 页眉组件，包含标题和操作按钮 -->
  <PageHeader>
    <template #title>询价申请管理</template>
    <template #action>
      <Button type="primary" to="/main/inquiry/request/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <!-- 询价申请表格组件，传递加载状态和列表数据 -->
  <InquiryRequestTable :loading="loading" :requestList="requestList" v-slot="{row, index}">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.inquiryCode)">查看</Button>
    <!-- 删除操作使用二次确认弹窗 -->
    <Poptip confirm transfer @on-ok="remove(row.inquiryCode)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定要删除{{ row.title }}?</span>
      </template>
    </Poptip>
  </InquiryRequestTable>
  <!-- 分页组件，包含总数据数和每页显示数量 -->
  <Page :total="pageTotal" :page-size="pageSize" @on-change="initInquiryRequestList"
        @on-page-size-change="onPageSizeChange" show-sizer show-total/>
</template>
