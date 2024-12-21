<script setup>
import {Col, Form, FormItem, Input, Option, Row, Select, Switch} from "view-ui-plus";
import UserRoleSelect from "@/components/user/UserRoleSelect.vue";
import {onMounted, ref} from "vue";
import {getThinEmployeeList, getThinSupplierList} from "@/http/api";

const props = defineProps({
  user: {type: Object, required: true},
  disabled: {type: Boolean}
});

const employeeList = ref([])
const supplierList = ref([])

onMounted(() => {
  getThinEmployeeList().then(data => {
    employeeList.value = data
  })
  getThinSupplierList().then(data => {
    supplierList.value = data;
  })
});

function selectEmployee(selection) {
  props.user.employeeName = selection.label;
  console.log(selection)
}

function selectSupplier(selection) {
  props.user.supplierName = selection.label;
}
</script>

<template>
  <Form :model="user" label-position="top" :disabled="disabled">
    <Row>
      <Col span="8">
        <FormItem label="用户名">
          <Input v-model="user.userName" disabled/>
        </FormItem>
      </Col>
      <Col span="16">
        <FormItem label="昵称">
          <Input v-model="user.nickname"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="用户类型">
          <Select v-model="user.userType">
            <Option value="EMPLOYEE">内部员工</Option>
            <Option value="SUPPLIER">供应商</Option>
            <Option value="ADMIN">管理员</Option>
          </Select>
        </FormItem>
      </Col>
      <Col span="16">
        <FormItem label="用户角色">
          <UserRoleSelect :roles="user.roles" @update-event="user.roles=$event" disabled/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="关联员工">
          <Select v-model="user.employeeCode" @on-select="selectEmployee" label-in-value filterable>
            <Option v-for="item in employeeList" :value="item.code" :key="item.code">{{ item.name }}</Option>
          </Select>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="所属供应商">
          <Select v-model="user.supplierCode" @on-select="selectSupplier" label-in-value filterable>
            <Option v-for="item in supplierList" :value="item.code" :key="item.code">{{ item.name }}</Option>
          </Select>
        </FormItem>
      </Col>
      <Col span="4">
        <FormItem label="禁用">
          <Switch v-model="user.disabled" disabled/>
        </FormItem>
      </Col>
      <Col span="4">
        <FormItem label="已锁">
          <Switch v-model="user.accountLocked" disabled/>
        </FormItem>
      </Col>
    </Row>
    <slot></slot>
  </Form>
</template>

<style scoped>

</style>