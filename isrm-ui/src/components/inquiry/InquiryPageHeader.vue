 template
<script setup>
// 导入 View UI Plus 组件库中的 Description、DescriptionList 和 PageHeader 组件
import {Description, DescriptionList, PageHeader} from "view-ui-plus";
// 导入自定义组件 InquiryStatusSelect，用于显示询价状态
import InquiryStatusSelect from "@/components/inquiry/InquiryStatusSelect.vue";
// 导入 Vue Router 的 useRouter 函数，用于页面导航
import {useRouter} from "vue-router";

// 定义组件接收的属性，包括 inquiryCode（询价代码）和 inquiry（询价对象）
defineProps({
  inquiryCode: {type: String, required: true},
  inquiry: {type: Object, required: true}
});

// 获取 Vue Router 的 router 实例，用于页面跳转
const router = useRouter();
</script>

<template>
  <PageHeader title="询价信息" back @on-back="router.push('/main/inquiry/request/edit/'+inquiryCode)">
    <template #content>
      <DescriptionList :col="1">
        <Description term="询价标题：">{{ inquiry.title }}</Description>
        <Description term="采购员：">{{ inquiry.buyerName }}</Description>
      </DescriptionList>
    </template>
    <template #extra>
      <p style="color: #808695">状态</p>
      <p style="font-size: 18px">
        <InquiryStatusSelect v-model="inquiry.status" comp-type="span"/>
      </p>
    </template>
  </PageHeader>
</template>

<style scoped>

</style>
