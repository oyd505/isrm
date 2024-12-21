<script setup>
import {Switch, Table} from "view-ui-plus";
import UserTypeSpan from "@/components/user/UserTypeSpan.vue";
import UserRoleSelect from "@/components/user/UserRoleSelect.vue";

const columns = [
  {title: "序号", type: "index", width: 80},
  {
    title: "用户名",
    slot: "userName",
  },
  {
    title: "昵称",
    key: "nickname",
  },
  {
    title: "用户类型",
    slot: "userType"
  },
  {
    title: "用户角色",
    slot: "roles"
  },
  {
    title: "禁用",
    slot: "disabled"
  },
  {
    title: "已锁",
    slot: "accountLocked"
  },
  {
    title: "关联员工",
    key: "employeeName",
  },
  {
    title: "所属供应商",
    key: "supplierName",
  },
  {
    title: "操作",
    slot: "action",
    width: 150,
    align: "center",
  },
];
defineProps({
  loading: Boolean,
  userList: {type: Object, required: true},
});
</script>

<template>
  <Table :loading="loading" stripe :columns="columns" :data="userList">
    <template #userName="{ row }">
      <strong>{{ row.userName }}</strong>
    </template>
    <template #userType="{ row }">
      <UserTypeSpan :userType="row.userType"/>
    </template>
    <template #roles="{ row }">
      <UserRoleSelect :roles="row.roles" :disabled="true"/>
    </template>
    <template #disabled="{ row }">
      <Switch v-model="row.disabled" disabled/>
    </template>
    <template #accountLocked="{ row }">
      <Switch v-model="row.accountLocked" disabled/>
    </template>
    <template #action="{ row, index }">
      <slot :row="row" :index="index"></slot>
    </template>
  </Table>
</template>

<style scoped>

</style>