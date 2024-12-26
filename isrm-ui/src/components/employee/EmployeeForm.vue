 template
<script setup>
// 导入 DepartmentSelect 组件，用于选择员工所属部门
import DepartmentSelect from "@/components/employee/DepartmentSelect.vue";
// 导入 FormItem 和 Row 组件，用于布局和表单项的展示
import {FormItem, Row} from "view-ui-plus";

// 定义组件的 props，这里 employee 是一个对象，包含员工的相关信息
const props = defineProps({
  employee: {type: Object, required: true}
});
</script>

<template>
  <!-- 使用 Form 组件，绑定员工信息作为模型 -->
  <Form :model="employee" label-position="top">
    <Row>
      <!-- 员工工号字段，只读状态 -->
      <Col span="8">
        <FormItem label="员工工号" prop="employeeCode">
          <Input v-model="employee.employeeCode" disabled/>
        </FormItem>
      </Col>
      <!-- 员工姓名字段 -->
      <Col span="8">
        <FormItem label="员工姓名" prop="name">
          <Input v-model="employee.name" placeholder="请填写员工姓名"/>
        </FormItem>
      </Col>
      <!-- 员工部门字段，使用 DepartmentSelect 组件进行选择 -->
      <Col span="8">
        <FormItem label="员工部门" prop="departmentCode">
          <DepartmentSelect v-model:code="employee.departmentCode" v-model:name="employee.departmentName"/>
        </FormItem>
      </Col>
      <!-- 员工手机号字段 -->
      <Col span="8">
        <FormItem label="员工手机号" prop="phone">
          <Input v-model="employee.phone" placeholder="请填写员工手机号"/>
        </FormItem>
      </Col>
      <!-- 员工邮箱字段 -->
      <Col span="8">
        <FormItem label="员工邮箱" prop="email">
          <Input v-model="employee.email" placeholder="请填写员工邮箱"/>
        </FormItem>
      </Col>
      <!-- 员工职务字段 -->
      <Col span="8">
        <FormItem label="员工职务" prop="jobTitle">
          <Input v-model="employee.jobTitle" placeholder="请填写员工职务"/>
        </FormItem>
      </Col>
    </Row>
    <!-- 插槽，用于扩展额外的内容 -->
    <slot/>
  </Form>
</template>

<style scoped>
/* 这里可以添加 scoped 的 CSS 样式，以确保样式仅应用于当前组件 */
</style>
