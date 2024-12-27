 template
<script setup>
// 导入 Vue 的响应式库和 View UI Plus 的选择组件
import { ref } from "vue";
import { Select } from "view-ui-plus";

// 定义两个模型，用于绑定单位符号和单位名称
const unitSymbol = defineModel('unitSymbol');
const unitName = defineModel('unitName');

// 创建一个响应式数组，包含单位的代码和名称
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

/**
 * 当选择单位时触发的函数
 * @param {Object} selection - 包含选择项的代码和名称的对象
 */
function selectUnit(selection) {
  // 更新单位名称的值为用户选择的单位名称
  unitName.value = selection.label;
}
</script>

<template>
  <!-- 使用 Select 组件，绑定单位符号模型和选择事件 -->
  <Select v-model="unitSymbol" @on-select="selectUnit" label-in-value filterable>
    <!-- 遍历单位列表，生成下拉选项 -->
    <Option v-for="item in unitList" :value="item.code" :key="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>
/* 此处添加组件的样式，仅影响当前组件 */
</style>
