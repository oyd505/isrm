 template
<script setup>
// 导入 View UI Plus 组件库中的 Button 和 Result 组件
import {Button, Result} from "view-ui-plus";
// 导入用户存储管理器，用于管理用户状态
import {useUserStore} from "@/stores/user";

// 初始化用户存储管理器实例
const store = useUserStore();
</script>
<template>
  <Result type="success" title="登陆成功" desc="ISRM" :extra="`欢迎 ${store.nickname} 回家！`">
    <template #actions>
      <Button type="primary" to="/main/inquiry/request/list">询价</Button>
      <Button type="primary" to="/main/inquiry/quote/list/inquiry">报价</Button>
    </template>
  </Result>
</template>
