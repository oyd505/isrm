 template
<script setup>
// 导入 Vue 的钩子和 API
import {onMounted, ref} from "vue";
// 导入获取组织列表的 API 函数
import {getThinComOrganizationList, getThinFtyOrganizationList, getThinPurOrganizationList} from "@/http/api";
// 导入 View UI Plus 的组件
import {Option, Select} from "view-ui-plus";

// 定义组件的属性
const props = defineProps({
  orgType: {type: String, default: 'po'}
})
// 定义双向绑定的代码和名称模型
const code = defineModel('code');
const name = defineModel('name');

// 定义组织列表的响应式引用
const organizationList = ref([])

// 在组件挂载时获取相应的组织列表
onMounted(() => {
  if (props.orgType === 'po') {
    getThinPurOrganizationList().then(data => {
      organizationList.value = data;
    });
  } else if (props.orgType === 'fo') {
    getThinFtyOrganizationList().then(data => {
      organizationList.value = data;
    })
  } else {
    getThinComOrganizationList().then(data => {
      organizationList.value = data;
    })
  }
});

// 选择组织时更新名称模型
function selectOrganization(selection) {
  name.value = selection.label;
}
</script>

<template>
  <!-- 渲染选择框组件，绑定代码模型和选择事件 -->
  <Select v-model="code" @on-select="selectOrganization" label-in-value filterable>
    <!-- 渲染组织列表项 -->
    <Option v-for="item in organizationList" :key="item.code" :value="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>
/* 本地样式 */
</style>
