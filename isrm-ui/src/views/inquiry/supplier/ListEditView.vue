<script setup>
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {Button, Card, Col, Icon, Message, Row} from "view-ui-plus";
import InquirySupplierTable from "@/components/inquiry/supplier/InquirySupplierTable.vue";
import InquirySupplierForm from "@/components/inquiry/supplier/InquirySupplierForm.vue";
import {
  auditInquirySupplier,
  delInquirySupplier,
  getInquiryRequest,
  getInquirySupplier,
  getInquirySupplierList,
  inviteInquirySupplier,
  updateInquirySupplier,
} from "@/http/api";
import InquiryPageHeader from "@/components/inquiry/InquiryPageHeader.vue";

const loading = ref(true);
const supplierList = ref([]);
const supplier = ref({});
const route = useRoute();
const inquiryCode = route.params.inquiryCode;
const inquiry = ref({});
onMounted(() => {
  initInquirySupplierList();
  initInquiryRequest();
});

function initInquiryRequest() {
  getInquiryRequest(inquiryCode).then((data) => {
    inquiry.value = data;
  });
}

function initInquirySupplierList() {
  getInquirySupplierList(inquiryCode).then((data) => {
    supplierList.value = data;
    loading.value = false;
    add();
  });
}

function add() {
  supplier.value = {confirmed: 'N', audited: 'N'};
}

function remove(id) {
  delInquirySupplier(inquiryCode, id).then(() => {
    Message.info("删除成功,ID: " + id);
    initInquirySupplierList();
  });
}

function audit(id) {
  auditInquirySupplier(inquiryCode, id).then(() => {
    Message.info("审核成功,ID: " + id);
    initInquirySupplierList();
  });
}

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

function select(row) {
  getInquirySupplier(inquiryCode, row.id).then((data) => {
    supplier.value = data;
  });
}
</script>

<template>
  <InquiryPageHeader :inquiry="inquiry" :inquiry-code="inquiryCode"/>
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
  <InquirySupplierTable :loading="loading" :supplierList="supplierList" @removeEvent="remove" @rowClickEvent="select"
                        @auditEvent="audit">
    <template #cardExtra>
      <Button type="primary" size="small" @click="add()" v-if="inquiry.status === 'PENDING'">邀请</Button>
    </template>
  </InquirySupplierTable>
</template>
