<script setup>
import {Button, ButtonGroup, Card, Col, FormItem, Icon, Message, PageHeader, Row} from "view-ui-plus";
import UserForm from "@/components/user/UserForm.vue";
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getUser, saveUser, updateUser} from "@/http/api";

const route = useRoute();
const router = useRouter();
const userName = ref(route.params.userName);
const user = ref({});

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
</script>

<template>
  <PageHeader>
    <template #title>用户</template>
    <template #action>
      <ButtonGroup>
        <Button type="primary" to="/main/user/list">返回</Button>
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
</template>

<style scoped>

</style>