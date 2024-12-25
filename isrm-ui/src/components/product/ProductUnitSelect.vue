<script setup>
import { ref } from "vue";
import { Select } from "view-ui-plus";

const unitSymbol = defineModel('unitSymbol');
const unitName = defineModel('unitName');

const unitList = ref([{
  code: 'kg',
  name: '千克'
}, {
  code: 'g',
  name: '克'
}, {
  code: 'l',
  name: '升'
}, {
  code: 'ml',
  name: '毫升'
}]);

function selectUnit(selection) {
  unitName.value = selection.label;
}
</script>

<template>
  <Select v-model="unitSymbol" @on-select="selectUnit" label-in-value filterable>
    <Option v-for="item in unitList" :value="item.code" :key="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>

</style>
