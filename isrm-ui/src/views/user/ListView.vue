 template
<script setup>
// 导入 view-ui-plus 组件库中的组件
import {Button, Message, PageHeader, Poptip} from "view-ui-plus";
// 导入 Vue 的生命周期钩子和响应式对象
import {onMounted, ref} from "vue";
// 导入用户相关的 API 接口
import {delUser, getUserList} from "@/http/api";
// 导入 Vue Router 的钩子
import {useRouter} from "vue-router";
// 导入用户表格组件
import UserTable from "@/components/user/UserTable.vue";

// 定义一个响应式对象 loading，用于控制数据加载状态
const loading = ref(true);
// 定义一个响应式对象 userList，用于存储用户列表数据
const userList = ref([]);
// 定义一个响应式对象 pageTotal，用于存储总页数
const pageTotal = ref(0);
// 定义一个响应式对象 pageSize，用于控制每页显示的数量
const pageSize = ref(20);

// 在组件挂载时获取用户列表数据
onMounted(() => {
  initUserList(1);
})

/**
 * 初始化用户列表数据
 * @param pageNumber 当前页码
 */
function initUserList(pageNumber) {
  getUserList(pageNumber, pageSize.value).then((data) => {
    userList.value = data.content;
    loading.value = false;
    pageTotal.value = data.totalElements;
  });
}

/**
 * 当每页显示数量改变时的处理函数
 * @param size 新的每页显示数量
 */
function onPageSizeChange(size) {
  pageSize.value = size;
  initUserList(1);
}

// 使用 Vue Router 的钩子获取 router 实例
const router = useRouter();

/**
 * 跳转到用户编辑页面
 * @param userName 用户名
 */
function show(userName) {
  router.push("/main/user/edit/" + userName);
}

/**
 * 删除用户
 * @param userName 用户名
 */
function remove(userName) {
  delUser(userName).then(() => {
    Message.success("删除成功,用户名: " + userName);
    initUserList(1);
  });
}
</script>

<template>
  <!-- 页面头部组件，包含标题和操作按钮 -->
  <PageHeader>
    <template #title>用户管理</template>
    <template #action>
      <Button type="primary" to="/main/user/edit/undefined">新增</Button>
    </template>
  </PageHeader>
  <!-- 用户表格组件，传递 loading、userList 参数，并定义操作按钮 -->
  <UserTable :loading="loading" :user-list="userList" v-slot="{row, index}">
    <Button type="primary" size="small" style="margin-right: 5px" @click="show(row.userName)">查看</Button>
    <!-- 弹出确认框的删除按钮 -->
    <Poptip confirm transfer @on-ok="remove(row.userName)">
      <Button type="error" size="small">删除</Button>
      <template #title>
        <span>确定要删除{{ row.userName }}?</span>
      </template>
    </Poptip>
  </UserTable>
  <!-- 分页组件，传递总页数、每页显示数量，并绑定分页事件处理函数 -->
  <Page :total="pageTotal" :page-size="pageSize" @on-change="initUserList"
        @on-page-size-change="onPageSizeChange" show-sizer show-total/>
</template>

<style scoped>

</style>
