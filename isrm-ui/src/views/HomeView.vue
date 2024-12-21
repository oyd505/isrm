<script setup>
import {Button, Result} from "view-ui-plus";
import {useUserStore} from "@/stores/user";

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