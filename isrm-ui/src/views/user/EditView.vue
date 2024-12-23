<script setup>
import {Button, ButtonGroup, Card, Col, FormItem, Icon, Message, Modal, PageHeader, Row} from "view-ui-plus";
import UserForm from "@/components/user/UserForm.vue";
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {disableUser, enableUser, getUser, saveUser, updateRoles, updateUser} from "@/http/api";
import UserRoleSelect from "@/components/user/UserRoleSelect.vue";

const route = useRoute();
const router = useRouter();
const userName = ref(route.params.userName);
const user = ref({});
const userRolesModel = ref(false);
const userRoles = ref([]);

onMounted(() => {
  initUser();
});

function initUser() {
  getUser(userName.value).then(data => {
    user.value = data;
  });
}

function update() {
  if (!!user.value.userName) {
    updateUser(user.value).then(() => {
      Message.info("更新成功,用户名: " + userName.value);
    });
  } else {
    saveUser(user.value).then((id) => {
      userName.value = id;
      Message.info("保存成功,用户名: " + id);
      initUser();
      router.push(`/main/user/edit/${userName.value}`);
    });
  }
}

function handleReset() {
  user.value.nickname = null;
  user.value.userType = null;
  user.value.employeeCode = null;
  user.value.employeeName = null;
  user.value.supplierCode = null;
  user.value.supplierName = null;
}

function disable() {
  disableUser(userName.value).then(() => {
    Message.info("禁用成功");
    initUser();
  });
}

function enable() {
  enableUser(userName.value).then(() => {
    Message.info("启用成功");
    initUser();
  });
}

function updateUserRoles() {
  updateRoles(userName.value, userRoles.value).then(() => {
    Message.info("设置角色成功");
    userRolesModel.value = false;
    initUser();
  })
}
</script>

<template>
  <PageHeader title="用户信息" back @on-back="router.push('/main/user/list')">
    <template #action>
      <ButtonGroup v-if="userName !== 'undefined'">
        <Button type="info" v-show="user.disabled" @click="enable">启用</Button>
        <Button type="info" v-show="!user.disabled" @click="disable">禁用</Button>
        <Button type="info" v-show="!user.disabled" @click="userRolesModel = true">角色</Button>
      </ButtonGroup>
    </template>
  </PageHeader>
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          用户详情
        </template>
        <UserForm :user="user">
          <FormItem>
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </UserForm>
      </Card>
    </Col>
  </Row>

  <Modal v-model="userRolesModel" title="用户角色配置" @on-ok="updateUserRoles">
    <UserRoleSelect :roles="user.roles" @update-event="userRoles=$event"/>
  </Modal>
</template>

<style scoped>

</style>