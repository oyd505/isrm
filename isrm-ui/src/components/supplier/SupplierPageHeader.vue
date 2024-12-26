 template
<script setup>
// 导入 View UI Plus 组件库中的 Description、DescriptionList 和 PageHeader 组件
import {Description, DescriptionList, PageHeader} from "view-ui-plus";
// 导入自定义的 SupplierStatusSelect 组件，用于选择供应商状态
import SupplierStatusSelect from "@/components/supplier/SupplierStatusSelect.vue";
// 导入 Vue Router 的 useRouter 函数，用于页面跳转
import {useRouter} from "vue-router";

// 定义组件接收的属性，包括供应商编码和供应商对象
defineProps({
  supplierCode: {type: String, required: true},
  supplier: {type: Object, required: true}
});

// 获取 Vue Router 的 router 实例，用于导航到其他页面
const router = useRouter();
</script>

<template>
  <!-- 使用 PageHeader 组件显示页面头部，包含供应商信息和状态 -->
  <PageHeader title="供应商" back @on-back="router.push('/main/supplier/edit/'+supplierCode)">
    <template #content>
      <!-- 使用 DescriptionList 组件列出供应商的基本信息 -->
      <DescriptionList :col="1">
        <Description term="编码：">{{ supplier.supplierCode }}</Description>
        <Description term="名称：">{{ supplier.name }}</Description>
      </DescriptionList>
    </template>
    <template #extra>
      <!-- 显示供应商的状态 -->
      <p style="color: #808695">状态</p>
      <p style="font-size: 18px">
        <SupplierStatusSelect v-model="supplier.supplierStatus" comp-type="span"/>
      </p>
    </template>
  </PageHeader>
</template>

<style scoped>
  /* 此处添加组件的样式，由于代码中未提供样式内容，此处留空 */
</style>
