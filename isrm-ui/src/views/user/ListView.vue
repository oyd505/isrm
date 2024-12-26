<script setup>
import {Button, Message, PageHeader, Poptip} from "view-ui-plus";
import {onMounted, ref} from "vue";
import {delUser, getUserList} from "@/http/api";
import {useRouter} from "vue-router";
import UserTable from "@/components/user/UserTable.vue";

const loading = ref(true);
const userList = ref([]);
const pageTotal = ref(0);
const pageSize = ref(20);

onMounted(() => {
  initUserList(1);
})

function initUserList(pageNumber) {
  getUserList(pageNumber, pageSize.value).then((data) => {
    userList.value = data.content;
    loading.value = false;
    pageTotal.value = data.totalElements;
  });
}

function onPageSizeChange(size) {
  pageSize.value = size;
  initUserList(1);
}

const router = useRouter();

function show(userName) {
  router.push("/main/user/edit/" + userName);
}

function remove(userName) {
  delUser(userName).then(() => {
    Message.success("删除成功,用户名: " + userName);
    initUserList(1);
  });
}
</script>

<template>
  <PageHeader>
    <template #title>用户管理</template>
    <template #action>
      <Button type="primary" to="/main/user/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <UserTable :loading="loading" :user-list="userList" v-slot="{row, index}">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.userName)">查看</Button>
    <Poptip confirm transfer @on-ok="remove(row.userName)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定要删除{{ row.userName }}?</span>
      </template>
    </Poptip>
  </UserTable>
  <Page :total="pageTotal" :page-size="pageSize" @on-change="initUserList"
        @on-page-size-change="onPageSizeChange" show-sizer show-total/>
</template>

<style scoped>

</style>