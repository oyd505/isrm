<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getEmployee, saveEmployee, updateEmployee} from "@/http/api";
import {Card, FormItem, Icon, Message, PageHeader, Row} from "view-ui-plus";
import EmployeeForm from "@/components/employee/EmployeeForm.vue";

const route = useRoute();
const router = useRouter();
const employeeCode = ref(route.params.employeeCode);
const employee = ref({});

onMounted(() => {
  initEmployee();
});

function initEmployee() {
  getEmployee(employeeCode.value).then(data => {
    employee.value = data;
  });
}

function update() {
  if (!!employee.value.employeeCode) {
    updateEmployee(employee.value).then(data => {
      Message.success("更新成功,员工号: " + employeeCode.value);
    });
  } else {
    saveEmployee(employee.value).then(id => {
      employeeCode.value = id;
      Message.success("保存成功,员工号: " + employeeCode.value);
      initEmployee();
      router.push(`/main/employee/edit/${employeeCode.value}`);
    });
  }
}

function handleReset() {
  employee.value.name = null;
  employee.value.dempartmentCode = null;
  employee.value.dempartmentName = null;
  employee.value.jobTitle = null;
  employee.value.phone = null;
  employee.value.email = null;
}
</script>

<template>
  <PageHeader title="员工信息" back @on-back="router.push('/main/employee/list')">
  </PageHeader>
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          员工详情
        </template>
        <EmployeeForm :employee="employee">
          <FormItem>
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </EmployeeForm>
      </Card>
    </Col>
  </Row>
</template>

<style scoped>

</style>