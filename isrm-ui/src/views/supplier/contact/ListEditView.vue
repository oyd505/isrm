 template
<script setup>
// 导入供应商页面头部组件
import SupplierPageHeader from "@/components/supplier/SupplierPageHeader.vue";
// 导入View UI Plus的组件
import {Button, Card, Col, Icon, Message, Poptip, Row} from "view-ui-plus";
// 导入供应商联系人表单组件
import SupplierContactForm from "@/components/supplier/contact/SupplierContactForm.vue";
// 导入供应商联系人表格组件
import SupplierContactTable from "@/components/supplier/contact/SupplierContactTable.vue";
// 导入Vue的钩子和响应式引用
import {onMounted, ref} from "vue";
// 导入Vue Router的路由使用钩子
import {useRoute} from "vue-router";
// 导入与供应商联系人相关的API函数
import {
  delSupplierContact,
  getSupplier,
  getSupplierContact,
  getSupplierContactList,
  saveSupplierContact,
  updateSupplierContact
} from "@/http/api";

// 定义页面加载状态
const loading = ref(true);
// 定义联系人列表数据源
const contactList = ref([]);
// 定义当前操作的联系人数据源
const contact = ref({});
// 使用Vue Router获取当前路由
const route = useRoute();
// 提取路由参数中的供应商编码
const supplierCode = route.params.supplierCode;
// 定义供应商信息数据源
const supplier = ref({});

// 在组件挂载时执行初始化函数
onMounted(() => {
  initSupplier();
  initSupplierContactList();
});

/**
 * 初始化供应商信息
 */
function initSupplier() {
  getSupplier(supplierCode).then((data) => {
    supplier.value = data;
  });
}

/**
 * 初始化供应商联系人列表
 */
function initSupplierContactList() {
  getSupplierContactList(supplierCode).then((data) => {
    contactList.value = data;
    loading.value = false;
    contact.value = {};
  });
}

/**
 * 新增联系人
 */
function add() {
  contact.value = {};
}

/**
 * 更新或保存联系人信息
 * @param contact 联系人信息
 */
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

/**
 * 选择联系人
 * @param row 表格行数据
 */
function select(row) {
  getSupplierContact(supplierCode, row.id).then((data) => {
    contact.value = data;
  });
}

/**
 * 删除联系人
 * @param id 联系人ID
 */
function remove(id) {
  delSupplierContact(supplierCode, id).then(() => {
    Message.info("删除成功,ID: " + id);
    initSupplierContactList();
  })
}
</script>

<template>
  <!-- 供应商页面头部 -->
  <SupplierPageHeader :supplier="supplier" :supplier-code="supplierCode"/>
  <!-- 供应商联系人详情卡片 -->
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          供应商联系人详情
        </template>
        <!-- 供应商联系人表单 -->
        <SupplierContactForm :contact="contact" @update-event="updateContact" :disabled="false"/>
      </Card>
    </Col>
  </Row>
  <!-- 供应商联系人列表卡片 -->
  <Card>
    <template #title>
      <Icon type="md-list"/>
      供应商联系人列表
    </template>
    <template #extra>
      <!-- 新增联系人按钮 -->
      <Button type="primary" size="small" @click="add()">新增</Button>
    </template>
    <!-- 供应商联系人表格 -->
    <SupplierContactTable :loading="loading" :contact-list="contactList" @row-click-event="select">
      <template #tableAction="{row, index}">
        <!-- 删除联系人按钮 -->
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
