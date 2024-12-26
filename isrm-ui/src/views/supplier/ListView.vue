 template
<script setup>
// 导入 view-ui-plus 组件库中的相关组件
import {Button, Message, PageHeader, Poptip} from "view-ui-plus";
// 导入自定义的供应商表格组件
import SupplierTable from "@/components/supplier/SupplierTable.vue";
// 导入 Vue 的响应式 API 和生命周期钩子
import {onMounted, ref} from "vue";
// 导入 HTTP API 中的删除供应商和获取供应商列表的函数
import {delSupplier, getSupplierList} from "@/http/api";
// 导入 Vue Router 的钩子
import {useRouter} from "vue-router";

// 定义一个响应式的 loading 变量，用于控制加载状态
const loading = ref(true);
// 定义一个响应式的 supplierList 变量，用于存储供应商列表数据
const supplierList = ref([]);
// 定义一个响应式的 pageTotal 变量，用于存储总页数
const pageTotal = ref(0);
// 定义一个响应式的 pageSize 变量，用于控制每页显示的数量
const pageSize = ref(20);

// 在组件挂载时执行的钩子函数
onMounted(() => {
  initSupplierList(1);
});

/**
 * 初始化供应商列表
 * @param pageNumber 当前页码
 */
function initSupplierList(pageNumber) {
  getSupplierList(pageNumber, pageSize.value).then((data) => {
    supplierList.value = data.content;
    loading.value = false;
    pageTotal.value = data.totalElements;
  });
}

/**
 * 当每页显示数量改变时的处理函数
 * @param size 新的每页显示数量
 */
function onPageSizeChange(size) {
  pageSize.value = size;
  initSupplierList(1);
}

// 获取 Vue Router 的 router 实例
const router = useRouter();

/**
 * 跳转到供应商编辑页面
 * @param supplierCode 供应商编码
 */
function show(supplierCode) {
  router.push("/main/supplier/edit/" + supplierCode);
}

/**
 * 删除供应商
 * @param supplierCode 供应商编码
 */
function remove(supplierCode) {
  delSupplier(supplierCode).then(() => {
    Message.info("删除成功,编号: " + supplierCode);
    initSupplierList(1);
  });
}
</script>

<template>
  <!-- 供应商管理页面的头部 -->
  <PageHeader>
    <template #title>供应商管理</template>
    <template #action>
      <!-- 新增供应商的按钮 -->
      <Button type="primary" to="/main/supplier/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <!-- 供应商列表表格 -->
  <SupplierTable :loading="loading" :supplier-list="supplierList" v-slot="{row, index}">
    <!-- 查看供应商详情的按钮 -->
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.supplierCode)">查看</Button>
    <!-- 删除供应商的按钮和确认对话框 -->
    <Poptip confirm transfer @on-ok="remove(row.supplierCode)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定要删除{{ row.name }}?</span>
      </template>
    </Poptip>
  </SupplierTable>
  <!-- 分页组件 -->
  <Page :total="pageTotal" :page-size="pageSize" @on-change="initSupplierList"
        @on-page-size-change="onPageSizeChange" show-sizer show-total/>
</template>

<style scoped>

</style>
