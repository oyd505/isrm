 template
<script setup>
// 导入 Vue 的 ref 函数和 view-ui-plus 的 Select 组件
import {ref} from "vue";
import {Select} from "view-ui-plus";

// 定义响应式变量 code 和 name，用于绑定表单控件的值
const code = defineModel('code');
const name = defineModel('name');

// 定义部门列表，包含多个部门对象，每个对象有 code 和 name 属性
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

/**
 * 选择部门时触发的函数
 * @param {Object} selection - 选中的部门对象，包含选中的部门代码和名称
 * 该函数的作用是将选中的部门名称赋值给 name 变量，以显示在界面上
 */
function selectDepartment(selection) {
  name.value = selection.label;
}
</script>

<template>
  <!-- 部门选择下拉框 -->
  <Select v-model="code" @on-select="selectDepartment" label-in-value filterable>
    <!-- 循环生成每个部门的选项 -->
    <Option v-for="item in departmentList" :value="item.code" :key="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>
/* 本组件的样式 */
</style>
