<script setup>
import {Button, ButtonGroup, Description, DescriptionList, PageHeader} from "view-ui-plus";
import SupplierStatusSelect from "@/components/supplier/SupplierStatusSelect.vue";

defineProps({
  supplierCode: {type: String, required: true},
  supplier: {type: Object, required: true}
})
</script>

<template>
  <PageHeader>
    <template #title>供应商</template>
    <template #action>
      <ButtonGroup>
        <Button type="primary" :to="`/main/supplier/edit/${supplierCode}`">返回</Button>
      </ButtonGroup>
    </template>
    <template #content>
      <DescriptionList :col="1">
        <Description term="编码：">{{ supplier.supplierCode }}</Description>
        <Description term="名称：">{{ supplier.name }}</Description>
      </DescriptionList>
    </template>
    <template #extra>
      <p style="color: #808695">状态</p>
      <p style="font-size: 18px">
        <SupplierStatusSelect v-model="supplier.supplierStatus" comp-type="span"/>
      </p>
    </template>
  </PageHeader>
</template>

<style scoped>

</style>