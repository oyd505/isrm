<script setup>

import {Button, ButtonGroup, Description, DescriptionList, PageHeader} from "view-ui-plus";
import InquiryStatusSelect from "@/components/inquiry/InquiryStatusSelect.vue";

defineProps({
  inquiryCode: {type: String, required: true},
  inquiry: {type: Object, required: true}
})
</script>

<template>
  <PageHeader>
    <template #title>询价</template>
    <template #action>
      <ButtonGroup>
        <Button type="primary" :to="`/main/inquiry/request/edit/${inquiryCode}`">返回</Button>
      </ButtonGroup>
    </template>
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