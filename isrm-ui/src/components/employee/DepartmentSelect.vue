<script setup>
import {ref} from "vue";
import {Select} from "view-ui-plus";

const code = defineModel('code');
const name = defineModel('name');

const departmentList = ref([{
  code: '1',
  name: '技术部'
}, {
  code: '2',
  name: '采购部'
}, {
  code: '3',
  name: '运营部'
}, {
  code: '4',
  name: '销售部'
}]);

function selectDepartment(selection) {
  name.value = selection.label;
}
</script>

<template>
  <Select v-model="code" @on-select="selectDepartment" label-in-value filterable>
    <Option v-for="item in departmentList" :value="item.code" :key="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>

</style>