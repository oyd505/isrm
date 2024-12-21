<template>
  <div class="demo-login">
    <Login @on-submit="handleSubmit">
      <UserName name="username"/>
      <Password name="password"/>
      <div class="demo-auto-login">
        <Checkbox v-model="autoLogin" size="large">自动登录</Checkbox>
        <a>忘记密码</a>
      </div>
      <Submit/>
    </Login>
  </div>
</template>
<script setup>
import {useRouter} from "vue-router";
import {useUserStore} from "@/stores/user"
import {Checkbox, Login, Password, Submit, UserName} from "view-ui-plus";
import {login} from "@/http/api";

const autoLogin = true;
const router = useRouter();
const store = useUserStore();

function handleSubmit(valid, {username, password}) {
  if (valid) {
    login(username, password).then((data) => {
      store.$patch({
        supplier: data.userSupplier,
        token: data.token,
        userName: data.userName,
        nickname: data.nickname
      });
      router.push('/main/home');
    })
  }
}
</script>
<style>
.demo-login {
  width: 400px;
  margin: 0 auto !important;
}

.demo-auto-login {
  margin-bottom: 24px;
  text-align: left;
}

.demo-auto-login a {
  float: right;
}
</style>
