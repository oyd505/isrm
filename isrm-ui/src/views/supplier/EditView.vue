<script setup>
import {Button, ButtonGroup, Card, Col, FormItem, Icon, Message, Modal, PageHeader, Row} from "view-ui-plus";
import SupplierForm from "@/components/supplier/SupplierForm.vue";
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {getSupplier, saveSupplier, updateSupplier} from "@/http/api";

const route = useRoute();
const router = useRouter();
const supplierCode = ref(route.params.supplierCode);
const supplier = ref({});

onMounted(() => {
  initSupplier();
});

function initSupplier() {
  getSupplier(supplierCode.value).then((data) => {
    supplier.value = data;
  });
}

function update() {
  if (!!supplier.value.supplierCode) {
    updateSupplier(supplier.value).then(() => {
      Message.info("更新成功,编号: " + supplierCode.value);
    });
  } else {
    saveSupplier(supplier.value).then((id) => {
      supplierCode.value = id;
      Modal.confirm({
        title: '成功',
        content: `保存成功,编号: ${supplierCode.value}`,
        okText: '查看详情',
        cancelText: '继续新增',
        onOk: () => {
          initSupplier();
          router.push(`/main/supplier/edit/${supplierCode.value}`);
        },
        onCancel: () => {
          supplier.value = {};
          supplierCode.value = 'undefined';
          router.push('/main/supplier/edit/undefined');
        }
      })
    });
  }
}

function handleReset() {
  supplier.value.name = null;
}
</script>

<template>
  <PageHeader title="供应商" back @on-back="router.push('/main/supplier/list')">
    <template #action>
      <ButtonGroup v-if="supplierCode !== 'undefined'">
        <Button type="primary" :to="`/main/supplier/contact/list/${supplierCode}`">联系人</Button>
      </ButtonGroup>
    </template>
  </PageHeader>
  <Row>
    <Col span="12" offset="6">
      <Card>
        <template #title>
          <Icon type="ios-book"/>
          询价详情
        </template>
        <SupplierForm :supplier="supplier"
                      :disabled="supplier.supplierStatus !== 'POTENTIAL' && supplierCode !== 'undefined'">
          <FormItem>
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