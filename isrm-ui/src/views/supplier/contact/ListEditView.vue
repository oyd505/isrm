<script setup>
import SupplierPageHeader from "@/components/supplier/SupplierPageHeader.vue";
import {Button, Card, Col, Icon, Message, Poptip, Row} from "view-ui-plus";
import SupplierContactForm from "@/components/supplier/contact/SupplierContactForm.vue";
import SupplierContactTable from "@/components/supplier/contact/SupplierContactTable.vue";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {
  delSupplierContact,
  getSupplier,
  getSupplierContact,
  getSupplierContactList,
  saveSupplierContact,
  updateSupplierContact
} from "@/http/api";

const loading = ref(true);
const contactList = ref([]);
const contact = ref({});
const route = useRoute();
const supplierCode = route.params.supplierCode;
const supplier = ref({});

onMounted(() => {
  initSupplier();
  initSupplierContactList();
});

function initSupplier() {
  getSupplier(supplierCode).then((data) => {
    supplier.value = data;
  });
}

function initSupplierContactList() {
  getSupplierContactList(supplierCode).then((data) => {
    contactList.value = data;
    loading.value = false;
    contact.value = {};
  });
}

function add() {
  contact.value = {};
}

function updateContact(contact) {
  if (!!contact.id) {
    updateSupplierContact(supplierCode, contact).then(() => {
      Message.info("更新成功,ID: " + contact.id);
      initSupplierContactList();
    });
  } else {
    saveSupplierContact(supplierCode, contact).then((id) => {
      Message.info("新增成功,ID: " + id);
      initSupplierContactList();
    });
  }
}

function select(row) {
  getSupplierContact(supplierCode, row.id).then((data) => {
    contact.value = data;
  });
}

function remove(id) {
  delSupplierContact(supplierCode, id).then(() => {
    Message.info("删除成功,ID: " + id);
    initSupplierContactList();
  })
}
</script>

<template>
  <SupplierPageHeader :supplier="supplier" :supplier-code="supplierCode"/>
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          供应商联系人详情
        </template>
        <SupplierContactForm :contact="contact" @update-event="updateContact" :disabled="false"/>
      </Card>
    </Col>
  </Row>
  <Card>
    <template #title>
      <Icon type="md-list"/>
      供应商联系人列表
    </template>
    <template #extra>
      <Button type="primary" size="small" @click="add()">新增</Button>
    </template>
    <SupplierContactTable :loading="loading" :contact-list="contactList" @row-click-event="select">
      <template #tableAction="{row, index}">
        <Poptip confirm transfer @on-ok="remove(row.id)">
          <Button type="error" size="small">删除</Button>
          <template #title>
            <span>确定要删除{{ row.name }}?</span>
          </template>
        </Poptip>
      </template>
    </SupplierContactTable>
  </Card>
</template>

<style scoped>

</style>