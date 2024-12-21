<script setup>
import {ref} from "vue";
import {RouterView} from "vue-router";
import {Content, Icon, Layout, Menu, MenuItem, Sider} from "view-ui-plus";

const theme = ref("light");
const isCollapsed = ref(false);
</script>

<template>
  <Layout>
    <Sider breakpoint="md" collapsible :collapsed-width="68" v-model="isCollapsed">
      <Menu :theme="theme" active-name="1" width="auto">
        <MenuItem name="1" to="/main/home">
          <Icon type="ios-paper"/>
          主页
        </MenuItem>
        <MenuItem name="2" to="/main/user/list">
          <Icon type="ios-people"/>
          用户
        </MenuItem>
        <MenuItem name="2" to="/main/supplier/list">
          <Icon type="ios-business"/>
          供应商
        </MenuItem>
        <MenuItem name="3" to="/main/inquiry/request/list">
          <Icon type="ios-chatbubbles"/>
          询价
        </MenuItem>
        <MenuItem name="4" to="/main/inquiry/quote/list/inquiry">
          <Icon type="ios-pricetags"/>
          报价
        </MenuItem>
        <MenuItem name="5" to="/">
          <Icon type="ios-log-out"/>
          登出
        </MenuItem>
      </Menu>
    </Sider>
    <Content style="background: #fff">
      <RouterView/>
    </Content>
  </Layout>
</template>

<style>
.ivu-layout-sider {
  background: #fff;
}

.ivu-layout-sider-trigger {
  background: #2d8cf0;
}
</style>
