<script setup>
import { ref } from "vue";
import { Select } from "view-ui-plus";

const categoryCode = defineModel('categoryCode');
const categoryName = defineModel('categoryName');

const categoryList = ref([{
  code: 'electronics',
  name: '电子产品'
}, {
  code: 'clothing',
  name: '服装'
}, {
  code: 'food',
  name: '食品'
}, {
  code: 'furniture',
  name: '家具'
}]);

function selectCategory(selection) {
  categoryName.value = selection.label;
}
</script>

<template>
  <Select v-model="categoryCode" @on-select="selectCategory" label-in-value filterable>
    <Option v-for="item in categoryList" :value="item.code" :key="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>

</style> 