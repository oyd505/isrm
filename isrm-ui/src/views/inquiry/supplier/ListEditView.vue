 template
<script setup>
// 导入 Vue 的钩子和 API
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
// 导入 View UI Plus 组件
import {Button, Card, Col, Icon, Message, Row} from "view-ui-plus";
// 导入自定义组件
import InquirySupplierTable from "@/components/inquiry/supplier/InquirySupplierTable.vue";
import InquirySupplierForm from "@/components/inquiry/supplier/InquirySupplierForm.vue";
// 导入 HTTP API
import {
  auditInquirySupplier,
  delInquirySupplier,
  getInquiryRequest,
  getInquirySupplier,
  getInquirySupplierList,
  inviteInquirySupplier,
  updateInquirySupplier,
} from "@/http/api";
// 导入公共组件
import InquiryPageHeader from "@/components/inquiry/InquiryPageHeader.vue";

// 定义响应式数据
const loading = ref(true);
const supplierList = ref([]);
const supplier = ref({});
const route = useRoute();
const inquiryCode = route.params.inquiryCode;
const inquiry = ref({});

// 在组件挂载时执行初始化函数
onMounted(() => {
  initInquirySupplierList();
  initInquiryRequest();
});

/**
 * 初始化询价请求数据
 */
function initInquiryRequest() {
  getInquiryRequest(inquiryCode).then((data) => {
    inquiry.value = data;
  });
}

/**
 * 初始化询价供应商列表
 */
function initInquirySupplierList() {
  getInquirySupplierList(inquiryCode).then((data) => {
    supplierList.value = data;
    loading.value = false;
    add();
  });
}

/**
 * 添加新的供应商
 */
function add() {
  supplier.value = {confirmed: 'N', audited: 'N'};
}

/**
 * 删除供应商
 * @param {number} id - 供应商 ID
 */
function remove(id) {
  delInquirySupplier(inquiryCode, id).then(() => {
    Message.info("删除成功,ID: " + id);
    initInquirySupplierList();
  });
}

/**
 * 审核供应商
 * @param {number} id - 供应商 ID
 */
function audit(id) {
  auditInquirySupplier(inquiryCode, id).then(() => {
    Message.info("审核成功,ID: " + id);
    initInquirySupplierList();
  });
}

/**
 * 更新供应商信息
 * @param {Object} supplier - 供应商对象
 */
function update(supplier) {
  if (!!supplier.id) {
    updateInquirySupplier(inquiryCode, supplier).then(() => {
      Message.info("更新成功,ID: " + supplier.id);
      initInquirySupplierList();
    });
  } else {
    inviteInquirySupplier(inquiryCode, supplier).then((id) => {
      Message.info("新增成功,ID: " + id);
      initInquirySupplierList();
    });
  }
}

/**
 * 选择供应商
 * @param {Object} row - 表格行数据
 */
function select(row) {
  getInquirySupplier(inquiryCode, row.id).then((data) => {
    supplier.value = data;
  });
}
</script>

<template>
  <!-- 询价页面头部 -->
  <InquiryPageHeader :inquiry="inquiry" :inquiry-code="inquiryCode"/>
  <!-- 供应商详情表单 -->
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          询价供应商详情
        </template>
        <InquirySupplierForm :supplier="supplier" @updateEvent="update" :disabled="inquiry.status !== 'PENDING'"/>
      </Card>
    </Col>
  </Row>
  <!-- 询价供应商列表 -->
  <InquirySupplierTable :loading="loading" :supplierList="supplierList" @removeEvent="remove" @rowClickEvent="select"
                        @auditEvent="audit">
    <template #cardExtra>
      <!-- 如果询价状态为待处理，则显示邀请按钮 -->
      <Button type="primary" size="small" @click="add()" v-if="inquiry.status === 'PENDING'">邀请</Button>
    </template>
  </InquirySupplierTable>
</template>
