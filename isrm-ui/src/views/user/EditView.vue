 template
<script setup>
// 导入 view-ui-plus 组件库中的各个组件
import {Button, ButtonGroup, Card, Col, FormItem, Icon, Message, Modal, PageHeader, Row} from "view-ui-plus";
// 导入用户表单组件
import UserForm from "@/components/user/UserForm.vue";
// 导入 Vue Router 的钩子
import {useRoute, useRouter} from "vue-router";
// 导入 Vue 的生命周期钩子和 ref 函数
import {onMounted, ref} from "vue";
// 导入与用户相关的 API 函数
import {disableUser, enableUser, getUser, saveUser, updateRoles, updateUser} from "@/http/api";
// 导入用户角色选择组件
import UserRoleSelect from "@/components/user/UserRoleSelect.vue";

// 使用 Vue Router 的钩子获取路由信息
const route = useRoute();
const router = useRouter();
// 使用 ref 函数创建响应式变量
const userName = ref(route.params.userName);
const user = ref({});
const userRolesModel = ref(false);
const userRoles = ref([]);

// 在组件挂载时执行的函数
onMounted(() => {
  initUser();
});

/**
 * 初始化用户信息
 */
function initUser() {
  getUser(userName.value).then(data => {
    user.value = data;
  });
}

/**
 * 更新用户信息或保存新用户
 */
function update() {
  if (!!user.value.userName) {
    updateUser(user.value).then(() => {
      Message.info("更新成功,用户名: " + userName.value);
    });
  } else {
    saveUser(user.value).then((id) => {
      userName.value = id;
      Modal.confirm({
        title: '成功',
        content: `保存成功,用户名: ${userName.value}`,
        okText: '查看详情',
        cancelText: '继续新增',
        onOk: () => {
          initUser(); // 重新初始化用户
          router.push(`/main/user/edit/${userName.value}`); // 更新路由为用户编辑
        },
        onCancel: () => {
          user.value = {};
          userName.value = 'undefined';
          router.push('/main/user/edit/undefined');
        }
      });
    });
  }
}

/**
 * 重置用户表单
 */
function handleReset() {
  user.value.nickname = null;
  user.value.userType = null;
  user.value.employeeCode = null;
  user.value.employeeName = null;
  user.value.supplierCode = null;
  user.value.supplierName = null;
}

/**
 * 禁用用户
 */
function disable() {
  disableUser(userName.value).then(() => {
    Message.info("禁用成功");
    initUser();
  });
}

/**
 * 启用用户
 */
function enable() {
  enableUser(userName.value).then(() => {
    Message.info("启用成功");
    initUser();
  });
}

/**
 * 更新用户角色
 */
function updateUserRoles() {
  updateRoles(userName.value, userRoles.value).then(() => {
    Message.info("设置角色成功");
    userRolesModel.value = false;
    initUser();
  })
}
</script>

<template>
  <!-- 页面头部组件 -->
  <PageHeader title="用户信息" back @on-back="router.push('/main/user/list')">
    <template #action>
      <!-- 按钮组，根据用户名是否为 'undefined' 来决定是否显示 -->
      <ButtonGroup v-if="userName !== 'undefined'">
        <!-- 根据用户是否被禁用来决定是否显示启用和禁用按钮 -->
        <Button type="info" v-show="user.disabled" @click="enable">启用</Button>
        <Button type="info" v-show="!user.disabled" @click="disable">禁用</Button>
        <!-- 显示角色配置按钮 -->
        <Button type="info" v-show="!user.disabled" @click="userRolesModel = true">角色</Button>
      </ButtonGroup>
    </template>
  </PageHeader>
  <!-- 布局组件 -->
  <Row>
    <Col span="12" offset="6">
      <!-- 卡片组件 -->
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          用户详情
        </template>
        <!-- 用户表单组件 -->
        <UserForm :user="user">
          <FormItem>
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </UserForm>
      </Card>
    </Col>
  </Row>

  <!-- 模态框组件，用于配置用户角色 -->
  <Modal v-model="userRolesModel" title="用户角色配置" @on-ok="updateUserRoles">
    <UserRoleSelect :roles="user.roles" @update-event="userRoles=$event"/>
  </Modal>
</template>

<style scoped>

</style>
