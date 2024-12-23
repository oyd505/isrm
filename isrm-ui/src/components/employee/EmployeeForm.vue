<script setup>
import DepartmentSelect from "@/components/employee/DepartmentSelect.vue";
import {FormItem, Row} from "view-ui-plus";

const props = defineProps({
  employee: {type: Object, required: true}
});
</script>

<template>
  <Form :model="employee" label-position="top">
    <Row>
      <Col span="8">
        <FormItem label="员工工号" prop="employeeCode">
          <Input v-model="employee.employeeCode" disabled/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="员工姓名" prop="name">
          <Input v-model="employee.name" placeholder="请填写员工姓名"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="员工部门" prop="departmentCode">
          <DepartmentSelect v-model:code="employee.departmentCode" v-model:name="employee.departmentName"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="员工手机号" prop="phone">
          <Input v-model="employee.phone" placeholder="请填写员工手机号"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="员工邮箱" prop="email">
          <Input v-model="employee.email" placeholder="请填写员工邮箱"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="员工职务" prop="jobTitle">
          <Input v-model="employee.jobTitle" placeholder="请填写员工职务"/>
        </FormItem>
      </Col>
    </Row>
    <slot/>
  </Form>
</template>

<style scoped>

</style>