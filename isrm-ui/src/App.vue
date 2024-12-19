<script setup>
import {RouterView} from "vue-router";
import {Content, Footer, Layout} from "view-ui-plus";

const COPYRIGHT = "Copyright © 2023 island.com All Rights Reserved";
</script>

<template>
  <Layout>
    <Content class="main-content">
      <RouterView/>
    </Content>
    <Footer class="fixed-footer">{{ COPYRIGHT }}</Footer>
  </Layout>
</template>

<style scoped>
.main-content {
  z-index: 2; /* 确保 overlay-content 在顶层 */
  min-height: calc(100vh - 10px); /* 确保内容区域有足够的空间 */
  padding-bottom: 10px; /* 避免内容被 fixed-footer 覆盖 */
}

.fixed-footer {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 10px;
  text-align: center;
  z-index: 1; /* 确保 GlobalFooter 在底层 */
}
</style>
