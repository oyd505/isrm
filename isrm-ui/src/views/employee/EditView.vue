 template
<script setup>
// 导入路由相关模块和 Vue 的生命周期钩子及响应式对象
import { useRoute, useRouter } from "vue-router";
import { onMounted, ref } from "vue";
// 导入与员工相关的 API 函数
import { getEmployee, saveEmployee, updateEmployee } from "@/http/api";
// 导入 UI 组件
import { Card, FormItem, Icon, Message, Modal, PageHeader, Row } from "view-ui-plus";
// 导入员工表单组件
import EmployeeForm from "@/components/employee/EmployeeForm.vue";

// 初始化路由对象
const route = useRoute();
const router = useRouter();
// 定义员工编号的响应式对象
const employeeCode = ref(route.params.employeeCode);
// 定义员工信息的响应式对象
const employee = ref({});

// 组件挂载时初始化员工信息
onMounted(() => {
  initEmployee();
});

/**
 * 初始化员工信息
 * 根据员工编号获取员工详情
 */
function initEmployee() {
  getEmployee(employeeCode.value).then(data => {
    employee.value = data;
  });
}

/**
 * 更新或保存员工信息
 * 根据员工信息中是否包含员工编号决定是更新还是保存操作
 */
function update() {
  if (!!employee.value.employeeCode) {
    // 如果员工信息中包含员工编号，则执行更新操作
    updateEmployee(employee.value).then(data => {
      Message.success("更新成功,员工号: " + employeeCode.value);
    });
  } else {
    // 如果员工信息中不包含员工编号，则执行保存操作
    saveEmployee(employee.value).then(id => {
      employeeCode.value = id;
      // 弹窗提示保存成功，并提供查看和继续新增的选项
      Modal.confirm({
        title: '成功',
        content: `保存成功,员工号: ${employeeCode.value}`,
        okText: '查看详情',
        cancelText: '继续新增',
        onOk: () => {
          // 点击确定后重新初始化员工信息，并导航到员工编辑页面
          initEmployee();
          router.push(`/main/employee/edit/${employeeCode.value}`);
        },
        onCancel: () => {
          // 点击取消后清空员工信息，并导航到默认的员工编辑页面
          employee.value = {};
          employeeCode.value = 'undefined';
          router.push('/main/employee/edit/undefined');
        }
      });
    });
  }
}

/**
 * 重置员工表单
 * 清空员工表单中的姓名、部门编号、部门名称、职位、电话和邮箱信息
 */
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
  <!-- 页面头部，显示标题并提供返回员工列表的功能 -->
  <PageHeader title="员工信息" back @on-back="router.push('/main/employee/list')">
  </PageHeader>
  <!-- 员工详情卡片 -->
  <Row>
    <Col span="12" offset="6">
      <Card>
        <!-- 卡片标题，包含图标 -->
        <template #title>
          <Icon type="ios-book"/>
          员工详情
        </template>
        <!-- 员工表单，包含员工信息和操作按钮 -->
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
  /* 样式代码省略 */
</style>
