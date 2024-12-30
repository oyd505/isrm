template
<script setup>
import {Option, Select} from "view-ui-plus";
import {onMounted, ref} from "vue";
import {getThinProductList} from "@/http/api";

const code = defineModel('code');
const name = defineModel('name');

const productList = ref([])

onMounted(() => {
  getThinProductList().then(data => {
    productList.value = data;
  })
})

function selectProduct(selection) {
  name.value = selection.label;
}
</script>

<template>
  <Select v-model="code" @on-select="selectProduct" label-in-value filterable>
    <Option v-for="item in productList" :key="item.code" :value="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>
</style>
