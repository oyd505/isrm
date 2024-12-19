<script setup>
import {Button, Message, PageHeader, Poptip} from "view-ui-plus";
import SupplierTable from "@/components/supplier/SupplierTable.vue";
import {onMounted, ref} from "vue";
import {delSupplier, getSupplierList} from "@/http/api";
import {useRouter} from "vue-router";

const loading = ref(true);
const supplierList = ref([]);
const pageTotal = ref(0);
const pageSize = ref(20);

onMounted(() => {
  initSupplierList(1);
});

function initSupplierList(pageNumber) {
  getSupplierList(pageNumber, pageSize.value).then((data) => {
    supplierList.value = data.content;
    loading.value = false;
    pageTotal.value = data.totalElements;
  });
}

function onPageSizeChange(size) {
  pageSize.value = size;
  initSupplierList(1);
}

const router = useRouter();

function show(supplierCode) {
  router.push("/main/supplier/edit/" + supplierCode);
}

function remove(supplierCode) {
  delSupplier(supplierCode).then(() => {
    Message.info("删除成功,编号: " + supplierCode);
    initSupplierList(1);
  })
}
</script>

<template>
  <PageHeader>
    <template #title>供应商管理</template>
    <template #action>
      <Button type="primary" to="/main/supplier/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <SupplierTable :loading="loading" :supplier-list="supplierList" v-slot="{row, index}">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.supplierCode)">查看</Button>
    <Poptip confirm transfer @on-ok="remove(row.supplierCode)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定要删除{{ row.name }}?</span>
      </template>
    </Poptip>
  </SupplierTable>
  <Page :total="pageTotal" :page-size="pageSize" @on-change="initSupplierList"
        @on-page-size-change="onPageSizeChange" show-sizer show-total/>
</template>

<style scoped>

</style>