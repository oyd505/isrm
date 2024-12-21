<script setup>
import {Button, Col, Form, FormItem, Input, Row} from "view-ui-plus";

const props = defineProps({
  contact: {type: Object, required: true}
});

defineEmits(["updateEvent"]);

function handleReset() {
  props.contact.name = null;
  props.contact.phone = null;
}
</script>

<template>
  <Form :model="contact" label-position="top">
    <Row>
      <Col span="12">
        <FormItem label="名称">
          <Input v-model="contact.name"/>
        </FormItem>
      </Col>
      <Col span="12">
        <FormItem label="电话">
          <Input v-model="contact.phone"/>
        </FormItem>
      </Col>
    </Row>
    <FormItem>
      <Button type="primary" @click="$emit('updateEvent', props.contact)">保存</Button>
      <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
    </FormItem>
  </Form>
</template>

<style scoped>

</style>