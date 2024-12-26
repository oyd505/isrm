 template
<script setup>
// 导入 view-ui-plus 组件库中的各个组件
import {Button, ButtonGroup, Card, Col, FormItem, Icon, Message, Modal, PageHeader, Row} from "view-ui-plus";
// 导入自定义的 SupplierForm 组件
import SupplierForm from "@/components/supplier/SupplierForm.vue";
// 导入 Vue Router 的钩子
import {useRoute, useRouter} from "vue-router";
// 导入 Vue 的生命周期钩子和 ref 函数
import {onMounted, ref} from "vue";
// 导入与供应商相关的 API 函数
import {getSupplier, saveSupplier, updateSupplier} from "@/http/api";

// 使用 useRoute 和 useRouter 获取当前路由信息和路由器实例
const route = useRoute();
const router = useRouter();
// 使用 ref 创建响应式的供应商编码和供应商对象
const supplierCode = ref(route.params.supplierCode);
const supplier = ref({});

// 在组件挂载时初始化供应商信息
onMounted(() => {
  initSupplier();
});

/**
 * 初始化供应商信息
 * 通过供应商编码获取供应商详情，并赋值给 supplier
 */
function initSupplier() {
  getSupplier(supplierCode.value).then((data) => {
    supplier.value = data;
  });
}

/**
 * 更新或保存供应商信息
 * 根据 supplierCode 是否存在决定是更新还是保存操作
 */
function update() {
  if (!!supplier.value.supplierCode) {
    // 如果 supplierCode 存在，则更新供应商信息
    updateSupplier(supplier.value).then(() => {
      Message.info("更新成功,编号: " + supplierCode.value);
    });
  } else {
    // 如果 supplierCode 不存在，则保存新的供应商信息
    saveSupplier(supplier.value).then((id) => {
      supplierCode.value = id;
      Modal.confirm({
        title: '成功',
        content: `保存成功,编号: ${supplierCode.value}`,
        okText: '查看详情',
        cancelText: '继续新增',
        // 点击确定按钮后的操作
        onOk: () => {
          initSupplier();
          router.push(`/main/supplier/edit/${supplierCode.value}`);
        },
        // 点击取消按钮后的操作
        onCancel: () => {
          supplier.value = {};
          supplierCode.value = 'undefined';
          router.push('/main/supplier/edit/undefined');
        }
      })
    });
  }
}

/**
 * 重置供应商名称
 * 将供应商名称设置为空，用于表单重置
 */
function handleReset() {
  supplier.value.name = null;
}
</script>

<template>
  <!-- 页面头部组件，包含页面标题和返回按钮 -->
  <PageHeader title="供应商" back @on-back="router.push('/main/supplier/list')">
    <template #action>
      <!-- 如果 supplierCode 不是 'undefined'，显示联系人按钮 -->
      <ButtonGroup v-if="supplierCode !== 'undefined'">
        <Button type="primary" :to="`/main/supplier/contact/list/${supplierCode}`">联系人</Button>
      </ButtonGroup>
    </template>
  </PageHeader>
  <!-- 主要内容区域，包含供应商表单 -->
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          询价详情
        </template>
        <!-- SupplierForm 组件，用于编辑供应商信息 -->
        <SupplierForm :supplier="supplier"
                      :disabled="supplier.supplierStatus !== 'POTENTIAL' && supplierCode !== 'undefined'">
          <FormItem>
            <!-- 保存和重置按钮 -->
            <Button type="primary" @click="update">保存</Button>
            <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
          </FormItem>
        </SupplierForm>
      </Card>
    </Col>
  </Row>
</template>

<style scoped>

</style>
