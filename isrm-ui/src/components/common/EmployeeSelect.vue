 template
<script setup>
// 导入 Vue 的钩子和 API
import {onMounted, ref} from "vue";
import {getThinEmployeeList} from "@/http/api";
import {Option, Select} from "view-ui-plus";

// 定义模型以获取父组件传递的值
const code = defineModel('code');
const name = defineModel('name');

// 初始化员工列表
const employeeList = ref([])

// 在组件挂载时获取员工列表数据
onMounted(() => {
  getThinEmployeeList().then(data => {
    employeeList.value = data
  })
});

/**
 * 当选择员工时更新名称值
 * @param {Object} selection - 包含所选员工信息的对象
 */
function selectEmployee(selection) {
  name.value = selection.label
}
</script>

<template>
  <!-- 使用 Select 组件显示员工列表，并在选择时触发 selectEmployee 函数 -->
  <Select v-model="code" @on-select="selectEmployee" label-in-value filterable>
    <!-- 遍历员工列表，生成 Option 组件 -->
    <Option v-for="item in employeeList" :key="item.code" :value="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>
/* 此处添加组件样式 */
</style>
