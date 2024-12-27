 template
<script setup>
// 导入 View UI Plus 组件库中的相关组件
import {Col, Form, FormItem, Input, Row, Switch} from "view-ui-plus";
// 导入自定义组件：用户角色选择
import UserRoleSelect from "@/components/user/UserRoleSelect.vue";
// 导入自定义组件：供应商选择
import SupplierSelect from "@/components/supplier/SupplierSelect.vue";
// 导入自定义组件：员工选择
import EmployeeSelect from "@/components/common/EmployeeSelect.vue";
// 导入自定义组件：用户类型选择
import UserTypeSelect from "@/components/user/UserTypeSelect.vue";

// 定义组件的属性，这里要求 user 属性为对象类型，并且是必须的
const props = defineProps({
  user: {type: Object, required: true}
});

/**
 * 处理用户类型选择的函数
 * 当用户类型发生变化时，重置与用户类型相关的字段
 */
function selectUserType() {
  props.user.employeeCode = "";
  props.user.employeeName = "";
  props.user.supplierCode = "";
  props.user.supplierName = "";
}

/**
 * 处理选择并设置昵称的函数
 * 当选择员工或供应商时，将其名称作为昵称
 * @param {Object} selection - 选择的对象，包含 label 属性
 */
function selectAndSetNickname(selection) {
  props.user.nickname = selection.label;
}
</script>

<template>
  <!-- 使用 View UI Plus 的 Form 组件来创建用户信息编辑表单 -->
  <Form :model="user" label-position="top">
    <!-- 使用 Row 和 Col 组件来布局表单项 -->
    <Row>
      <Col span="8">
        <!-- 用户名表单项，禁用编辑 -->
        <FormItem label="用户名">
          <Input v-model="user.userName" disabled/>
        </FormItem>
      </Col>
      <Col span="16">
        <!-- 昵称表单项，根据用户类型禁用编辑 -->
        <FormItem label="昵称">
          <Input v-model="user.nickname" :disabled="user.userType !== 'ADMIN'"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <!-- 用户类型表单项，使用自定义的用户类型选择组件 -->
        <FormItem label="用户类型">
          <UserTypeSelect v-model="user.userType" @on-select="selectUserType"/>
        </FormItem>
      </Col>
      <Col span="16">
        <!-- 用户角色表单项，使用自定义的用户角色选择组件，禁用编辑 -->
        <FormItem label="用户角色">
          <UserRoleSelect :roles="user.roles" disabled/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <!-- 关联员工表单项，使用自定义的员工选择组件 -->
        <FormItem label="关联员工">
          <EmployeeSelect v-model:code="user.employeeCode" v-model:name="user.employeeName"
                          @on-select="selectAndSetNickname" :disabled="user.userType === 'SUPPLIER'"/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 所属供应商表单项，使用自定义的供应商选择组件 -->
        <FormItem label="所属供应商">
          <SupplierSelect v-model:code="user.supplierCode" v-model:name="user.supplierName"
                          @on-select="selectAndSetNickname" :disabled="user.userType === 'EMPLOYEE'"/>
        </FormItem>
      </Col>
      <Col span="4">
        <!-- 禁用用户表单项，使用 Switch 组件，禁用编辑 -->
        <FormItem label="禁用">
          <Switch v-model="user.disabled" disabled/>
        </FormItem>
      </Col>
      <Col span="4">
        <!-- 用户账户锁定表单项，使用 Switch 组件，禁用编辑 -->
        <FormItem label="已锁">
          <Switch v-model="user.accountLocked" disabled/>
        </FormItem>
      </Col>
    </Row>
    <!-- 插槽，用于扩展表单内容 -->
    <slot></slot>
  </Form>
</template>

<style scoped>
  /* scoped 样式，仅影响当前组件 */
</style>
