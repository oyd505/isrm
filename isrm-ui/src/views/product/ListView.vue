 template
<script setup>
import { onMounted, ref } from "vue";
import { delProduct, getProductList } from "@/http/api"; // 导入删除商品和获取商品列表的API
import { useRouter } from "vue-router";
import { Button, Message, Page, PageHeader, Poptip } from "view-ui-plus";
import ProductTable from "@/components/product/ProductTable.vue"; // 导入商品表格组件

// 初始化状态
const loading = ref(true);
const productList = ref([]);
const pageTotal = ref(0);
const pageSize = ref(20);

// 组件挂载时获取商品列表
onMounted(() => {
  initProductList(1);
});

// 获取商品列表的函数
const initProductList = (pageNumber) => {
  getProductList(pageNumber, pageSize.value).then(data => {
    productList.value = data.content;
    loading.value = false;
    pageTotal.value = data.totalElements;
  });
}

// 分页大小变化时的处理函数
function onPageSizeChange(size) {
  pageSize.value = size;
  initProductList(1);
}

// 路由实例
const router = useRouter();

// 跳转到商品编辑页面的函数
function show(productCode) {
  router.push("/main/product/edit/" + productCode);
}

// 删除商品的函数
function remove(productCode) {
  delProduct(productCode).then(() => {
    Message.success("删除成功, 商品编码: " + productCode);
    initProductList(1);
  });
}
</script>

<template>
  <PageHeader>
    <template #title>商品管理</template>
    <template #action>
      <Button type="primary" to="/main/product/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <ProductTable :product-list="productList" :loading="loading" v-slot="{ row }">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.productCode)">查看</Button>
    <Poptip confirm transfer @on-ok="remove(row.productCode)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定删除{{ row.name }}?</span>
      </template>
    </Poptip>
  </ProductTable>
  <Page :total="pageTotal" :page-size="pageSize" @on-change="initProductList"
        @on-page-size-change="onPageSizeChange" show-sizer show-total/>
</template>

<style scoped>
/* 添加样式 */
</style>
