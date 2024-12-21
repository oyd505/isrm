<script setup>
import {onMounted, ref} from "vue";
import {getThinEmployeeList} from "@/http/api";
import {Option, Select} from "view-ui-plus";

const code = defineModel('code');
const name = defineModel('name');

const employeeList = ref([])

onMounted(() => {
  getThinEmployeeList().then(data => {
    employeeList.value = data
  })
});

function selectEmployee(selection) {
  name.value = selection.label
}
</script>

<template>
  <Select v-model="code" @on-select="selectEmployee" label-in-value filterable>
    <Option v-for="item in employeeList" :key="item.code" :value="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>

</style>