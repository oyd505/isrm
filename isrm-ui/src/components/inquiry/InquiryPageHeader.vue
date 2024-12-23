<script setup>

import {Description, DescriptionList, PageHeader} from "view-ui-plus";
import InquiryStatusSelect from "@/components/inquiry/InquiryStatusSelect.vue";
import {useRouter} from "vue-router";

defineProps({
  inquiryCode: {type: String, required: true},
  inquiry: {type: Object, required: true}
});

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