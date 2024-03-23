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
        <MenuItem name="2" to="/main/inquiry/request/list">
          <Icon type="ios-people"/>
          询价
        </MenuItem>
        <MenuItem name="3" to="/main/inquiry/quote/list/inquiry">
          <Icon type="ios-people"/>
          报价
        </MenuItem>
        <MenuItem name="4" to="/">
          <Icon type="ios-people"/>
          登出
        </MenuItem>
      </Menu>
    </Sider>
    <Content :style="{margin: '5px', background: '#fff', minHeight: '520px'}">
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
