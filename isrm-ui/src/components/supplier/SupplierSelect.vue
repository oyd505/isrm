<script setup>
import {Option, Select} from "view-ui-plus";
import {onMounted, ref} from "vue";
import {getThinSupplierList} from "@/http/api";

const code = defineModel('code');
const name = defineModel('name');

const supplierList = ref([])

onMounted(() => {
  getThinSupplierList().then(data => {
    supplierList.value = data;
  })
})

function selectSupplier(selection) {
  name.value = selection.label;
}
</script>

<template>
  <Select v-model="code" @on-select="selectSupplier" label-in-value filterable>
    <Option v-for="item in supplierList" :key="item.code" :value="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>

</style>