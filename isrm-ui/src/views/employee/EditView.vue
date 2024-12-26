<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getEmployee, saveEmployee, updateEmployee} from "@/http/api";
import {Card, FormItem, Icon, Message, Modal, PageHeader, Row} from "view-ui-plus";
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
      Modal.confirm({
        title: '成功',
        content: `保存成功,员工号: ${employeeCode.value}`,
        okText: '查看详情',
        cancelText: '继续新增',
        onOk: () => {
          initEmployee(); // 重新初始化员工
          router.push(`/main/employee/edit/${employeeCode.value}`); // 更新路由为员工编辑
        },
        onCancel: () => {
          employee.value = {};
          employeeCode.value = 'undefined';
          router.push('/main/employee/edit/undefined'); // 提供一个默认路径
        }
      });
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