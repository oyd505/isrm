 template
<script setup>
// 导入 view-ui-plus 组件库中的相关组件
import {Button, Col, Form, FormItem, Input, Row} from "view-ui-plus";

// 定义组件的 props，这里指定了 contact 属性为 Object 类型，并且是必须的
const props = defineProps({
  contact: {type: Object, required: true}
});

// 定义 emit，用于触发自定义事件
const emit = defineEmits(["updateEvent"]);

/**
 * 重置联系人信息
 * 这个函数将 contact 对象的 name 和 phone 属性重置为 null
 */
function handleReset() {
  props.contact.name = null;
  props.contact.phone = null;
}
</script>

<template>
  <!-- 使用 Form 组件来布局联系人信息输入表单 -->
  <Form :model="contact" label-position="top">
    <Row>
      <Col span="12">
        <!-- 名称输入框 -->
        <FormItem label="名称">
          <Input v-model="contact.name"/>
        </FormItem>
      </Col>
      <Col span="12">
        <!-- 电话输入框 -->
        <FormItem label="电话">
          <Input v-model="contact.phone"/>
        </FormItem>
      </Col>
    </Row>
    <FormItem>
      <!-- 保存按钮，点击时触发 updateEvent 自定义事件，传递 contact 对象 -->
      <Button type="primary" @click="$emit('updateEvent', props.contact)">保存</Button>
      <!-- 重置按钮，点击时调用 handleReset 函数 -->
      <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
    </FormItem>
  </Form>
</template>

<style scoped>
/* 这里可以添加 scoped 的样式来定制组件的外观 */
</style>
