<script setup>
// 导入 view-ui-plus 组件库中的组件
import {Button, Checkbox, Col, Form, FormItem, Option, Row, Select} from "view-ui-plus";
// 导入 Vue 的响应式 API 和生命周期钩子
import {ref, watch} from "vue";
// 导入获取联系人列表的 API 函数
import {getThinContactList} from "@/http/api";
// 导入供应商选择组件
import SupplierSelect from "@/components/supplier/SupplierSelect.vue";

/**
 * 定义组件的 props
 * supplier: 一个对象，包含供应商的相关信息，为必传项
 */
const props = defineProps({
  supplier: {type: Object, required: true}
});
// 定义 emit，用于触发更新事件
defineEmits(["updateEvent"]);

// 初始化联系人列表的响应式引用
const contactList = ref([])
// 监听供应商代码的变化，当代码变化时，重新获取联系人列表
watch(() => props.supplier.code, (newCode) => {
  if (newCode) {
    const nn = props.supplier.contactName
    getThinContactList(newCode).then(data => {
      contactList.value = data;
      props.supplier.contactName = nn;
    })
  }
})

/**
 * 当选择供应商时调用此函数
 * 重置联系人名称和联系电话
 */
function selectSupplier() {
  props.supplier.contactName = null;
  props.supplier.contactPhone = null;
}

/**
 * 当选择联系人时调用此函数
 * @param {Object} selection - 包含联系人信息的对象
 * 更新供应商的联系电话为所选联系人的电话
 */
function selectContact(selection) {
  props.supplier.contactPhone = selection.tag;
}

/**
 * 重置供应商信息
 * 清空供应商的代码、名称、联系人名称和联系电话
 */
function handleReset() {
  props.supplier.code = null;
  props.supplier.name = null;
  props.supplier.contactName = null;
  props.supplier.contactPhone = null;
}
</script>

<template>
  <!-- 使用 Form 组件来布局供应商信息输入表单 -->
  <Form :model="supplier" label-position="top">
    <Row>
      <!-- 供应商名称输入框 -->
      <Col span="18">
        <FormItem label="名称">
          <SupplierSelect v-model:code="supplier.code" v-model:name="supplier.name" @on-select="selectSupplier"/>
        </FormItem>
      </Col>
      <!-- 审核复选框 -->
      <Col span="5" offset="1">
        <FormItem label="审核">
          <Checkbox v-model="supplier.audited" true-value="Y" false-value="N">已审核</Checkbox>
        </FormItem>
      </Col>
      <!-- 供应商编码输入框，禁用状态 -->
      <Col span="6">
        <FormItem label="编码">
          <Input v-model="supplier.code" disabled/>
        </FormItem>
      </Col>
      <!-- 联系人选择下拉框 -->
      <Col span="6">
        <FormItem label="联系人">
          <Select v-model="supplier.contactName" @on-select="selectContact" filterable>
            <Option v-for="item in contactList" :value="item.name" :tag="item.phone" :key="item.phone">{{
                item.name
              }}
            </Option>
          </Select>
        </FormItem>
      </Col>
      <!-- 联系电话输入框 -->
      <Col span="6">
        <FormItem label="联系电话">
          <Input v-model="supplier.contactPhone"/>
        </FormItem>
      </Col>
      <!-- 确认复选框，禁用状态 -->
      <Col span="5" offset="1">
        <FormItem label="确认">
          <Checkbox v-model="supplier.confirmed" true-value="Y" false-value="N" disabled>已确认</Checkbox>
        </FormItem>
      </Col>
    </Row>
    <!-- 保存和重置按钮 -->
    <FormItem>
      <Button type="primary" @click="$emit('updateEvent', props.supplier)">保存</Button>
      <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
    </FormItem>
  </Form>
</template>
