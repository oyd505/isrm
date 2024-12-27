 template
<template>
  <!-- 登录组件主体 -->
  <div class="demo-login">
    <!-- 使用Login组件，监听on-submit事件，事件处理函数为handleSubmit -->
    <Login @on-submit="handleSubmit">
      <!-- 用户名输入框 -->
      <UserName name="username"/>
      <!-- 密码输入框 -->
      <Password name="password"/>
      <!-- 自动登录选项和忘记密码链接 -->
      <div class="demo-auto-login">
        <!-- Checkbox绑定autoLogin变量，大小为large -->
        <Checkbox v-model="autoLogin" size="large">自动登录</Checkbox>
        <!-- 忘记密码链接 -->
        <a>忘记密码</a>
      </div>
      <!-- 登录按钮 -->
      <Submit/>
    </Login>
  </div>
</template>
<script setup>
// 导入vue-router的useRouter函数
import {useRouter} from "vue-router";
// 导入用户状态管理的store
import {useUserStore} from "@/stores/user"
// 导入view-ui-plus的组件
import {Checkbox, Login, Password, Submit, UserName} from "view-ui-plus";
// 导入登录API
import {login} from "@/http/api";

// 自动登录的布尔变量，默认值为true
const autoLogin = true;
// 获取路由器实例
const router = useRouter();
// 获取用户状态管理实例
const store = useUserStore();

/**
 * 登录表单提交事件处理函数
 * @param {boolean} valid 表单验证是否通过
 * @param {object} formData 表单数据，包含username和password
 */
function handleSubmit(valid, {username, password}) {
  if (valid) {
    // 调用登录API进行登录
    login(username, password).then((data) => {
      // 更新用户状态
      store.$patch({
        supplier: data.userSupplier,
        token: data.token,
        userName: data.userName,
        nickname: data.nickname
      });
      // 登录成功后跳转到主页
      router.push('/main/home');
    })
  }
}
</script>
<style>
/* 登录组件样式 */
.demo-login {
  width: 400px;
  margin: 0 auto !important;
}

/* 自动登录和忘记密码区域样式 */
.demo-auto-login {
  margin-bottom: 24px;
  text-align: left;
}

/* 忘记密码链接样式 */
.demo-auto-login a {
  float: right;
}
</style>
