<script setup>
import {onMounted, ref} from "vue";
import {delEmployee, getEmployeeList} from "@/http/api";
import {useRouter} from "vue-router";
import {Button, Message, Page, PageHeader, Poptip} from "view-ui-plus";
import EmployeeTable from "@/components/employee/EmployeeTable.vue";

const loading = ref(true);
const employeeList = ref([]);
const pageTotal = ref(0);
const pageSize = ref(20);

onMounted(() => {
  initEmployeeList(1);
});

const initEmployeeList = (pageNumber) => {
  getEmployeeList(pageNumber, pageSize.value).then(data => {
    employeeList.value = data.content;
    loading.value = false;
    pageTotal.value = data.totalElements;
  })
}

function onPageSizeChange(size) {
  pageSize.value = size;
  initEmployeeList(1);
}

const router = useRouter();

function show(employeeCode) {
  router.push("/main/employee/edit/" + employeeCode);
}

function remove(employeeCode) {
  delEmployee(employeeCode).then(() => {
    Message.success("删除成功,员工号: " + employeeCode);
    initEmployeeList(1);
  });
}
</script>

<template>
  <PageHeader>
    <template #title>员工管理</template>
    <template #action>
      <Button type="primary" to="/main/employee/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <EmployeeTable :employee-list="employeeList" :loading="loading" v-slot="{row, index}">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.employeeCode)">查看</Button>
    <Poptip confirm transfer @on-ok="remove(row.employeeCode)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定删除{{ row.name }}?</span>
      </template>
    </Poptip>
  </EmployeeTable>
  <Page :total="pageTotal" :page-size="pageSize" @on-change="initEmployeeList"
        @on-page-size-change="onPageSizeChange" show-sizer show-total/>
</template>

<style scoped>

</style>