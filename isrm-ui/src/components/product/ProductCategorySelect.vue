 template
<script setup>
// 导入 Vue 的响应式引用和 View UI Plus 的选择组件
import { ref } from "vue";
import { Select } from "view-ui-plus";

// 定义模型以获取和设置 categoryCode 和 categoryName 的值
const categoryCode = defineModel('categoryCode');
const categoryName = defineModel('categoryName');

// 初始化一个响应式数组，用于存储类别列表
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

/**
 * 当选择类别时调用此函数
 * @param {Object} selection - 包含所选类别信息的对象
 */
function selectCategory(selection) {
  // 将所选类别的名称赋值给 categoryName
  categoryName.value = selection.label;
}
</script>

<template>
  <!-- 使用 View UI Plus 的 Select 组件来渲染类别选择框 -->
  <Select v-model="categoryCode" @on-select="selectCategory" label-in-value filterable>
    <!-- 遍历 categoryList，为每个类别生成一个 Option 元素 -->
    <Option v-for="item in categoryList" :value="item.code" :key="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>
  /* 此处添加组件的样式 */
</style>
