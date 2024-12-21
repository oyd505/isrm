<script setup>
import {Col, Form, FormItem, Input, Row, Switch} from "view-ui-plus";
import UserRoleSelect from "@/components/user/UserRoleSelect.vue";
import SupplierSelect from "@/components/supplier/SupplierSelect.vue";
import EmployeeSelect from "@/components/common/EmployeeSelect.vue";
import UserTypeSelect from "@/components/user/UserTypeSelect.vue";

const props = defineProps({
  user: {type: Object, required: true}
});

function selectUserType() {
  props.user.employeeCode = "";
  props.user.employeeName = "";
  props.user.supplierCode = "";
  props.user.supplierName = "";
}

function selectAndSetNickname(selection) {
  props.user.nickname = selection.label;
}
</script>

<template>
  <Form :model="user" label-position="top">
    <Row>
      <Col span="8">
        <FormItem label="用户名">
          <Input v-model="user.userName" disabled/>
        </FormItem>
      </Col>
      <Col span="16">
        <FormItem label="昵称">
          <Input v-model="user.nickname" :disabled="user.userType !== 'ADMIN'"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="用户类型">
          <UserTypeSelect v-model="user.userType" @on-select="selectUserType"/>
        </FormItem>
      </Col>
      <Col span="16">
        <FormItem label="用户角色">
          <UserRoleSelect :roles="user.roles" disabled/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="关联员工">
          <EmployeeSelect v-model:code="user.employeeCode" v-model:name="user.employeeName"
                          @on-select="selectAndSetNickname" :disabled="user.userType === 'SUPPLIER'"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="所属供应商">
          <SupplierSelect v-model:code="user.supplierCode" v-model:name="user.supplierName"
                          @on-select="selectAndSetNickname" :disabled="user.userType === 'EMPLOYEE'"/>
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