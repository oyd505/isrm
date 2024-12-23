<script setup>
import {Description, DescriptionList, PageHeader} from "view-ui-plus";
import SupplierStatusSelect from "@/components/supplier/SupplierStatusSelect.vue";
import {useRouter} from "vue-router";

defineProps({
  supplierCode: {type: String, required: true},
  supplier: {type: Object, required: true}
});

const router = useRouter();
</script>

<template>
  <PageHeader title="供应商" back @on-back="router.push('/main/supplier/edit/'+supplierCode)">
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