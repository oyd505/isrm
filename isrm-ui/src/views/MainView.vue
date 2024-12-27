 template
<script setup>
// 导入 Vue 的响应式API和路由视图组件
import {ref} from "vue";
import {RouterView} from "vue-router";
// 导入 View UI Plus 的相关组件
import {Content, Icon, Layout, Menu, MenuItem, Sider, Submenu} from "view-ui-plus";

// 初始化主题为浅色模式
const theme = ref("light");
// 初始化侧边栏折叠状态为未折叠
const isCollapsed = ref(false);
</script>

<template>
  <Layout>
    <!-- 侧边栏，设置断点和折叠属性 -->
    <Sider breakpoint="md" collapsible :collapsed-width="68" v-model="isCollapsed">
      <!-- 菜单，绑定主题和折叠状态 -->
      <Menu :theme="theme" active-name="1" width="auto">
        <!-- 主页菜单项 -->
        <MenuItem name="1" to="/main/home">
          <Icon type="ios-paper"/>
          主页
        </MenuItem>
        <!-- 基础数据子菜单 -->
        <Submenu name="2">
          <template #title>
            <Icon type="ios-paper"/>
            基础数据
          </template>
          <!-- 员工菜单项 -->
          <MenuItem name="2-1" to="/main/employee/list">
            <Icon type="ios-person"/>
            员工
          </MenuItem>
          <!-- 用户菜单项 -->
          <MenuItem name="2-2" to="/main/user/list">
            <Icon type="ios-people"/>
            用户
          </MenuItem>
          <!-- 商品菜单项 -->
          <MenuItem name="2-3" to="/main/product/list">
            <Icon type="ios-cart"/>
            商品
          </MenuItem>
        </Submenu>
        <!-- 供应商菜单项 -->
        <MenuItem name="2" to="/main/supplier/list">
          <Icon type="ios-subway"/>
          供应商
        </MenuItem>
        <!-- 询价菜单项 -->
        <MenuItem name="3" to="/main/inquiry/request/list">
          <Icon type="ios-chatbubbles"/>
          询价
        </MenuItem>
        <!-- 报价菜单项 -->
        <MenuItem name="4" to="/main/inquiry/quote/list/inquiry">
          <Icon type="ios-pricetags"/>
          报价
        </MenuItem>
        <!-- 登出菜单项 -->
        <MenuItem name="5" to="/">
          <Icon type="ios-log-out"/>
          登出
        </MenuItem>
      </Menu>
    </Sider>
    <!-- 内容区域，使用路由视图 -->
    <Content style="background: #fff">
      <RouterView/>
    </Content>
  </Layout>
</template>

<style>
/* 设置侧边栏背景颜色 */
.ivu-layout-sider {
  background: #fff;
}

/* 设置侧边栏触发器背景颜色 */
.ivu-layout-sider-trigger {
  background: #2d8cf0;
}
</style>
