<script setup>
import {Button, Checkbox, Col, Form, FormItem, Option, Row, Select} from "view-ui-plus";
import {onMounted, ref, watch} from "vue";
import {getThinContactList, getThinSupplierList} from "@/http/api";

const props = defineProps({
  supplier: {type: Object, required: true},
  disabled: {type: Boolean}
});
defineEmits(["updateEvent"]);
const thinSupplierList = ref([])
const contactList = ref([])
watch(() => props.supplier.code, (newCode) => {
  if (newCode) {
    const nn = props.supplier.contactName
    getThinContactList(newCode).then(data => {
      contactList.value = data;
      props.supplier.contactName = nn;
    })
  }
})
onMounted(() => {
  getThinSupplierList().then(data => {
    thinSupplierList.value = data;
  })
})

function selectSupplier(selection) {
  props.supplier.code = selection.tag;
  props.supplier.contactName = null;
  props.supplier.contactPhone = null;
}

function selectContact(selection) {
  props.supplier.contactPhone = selection.tag;
}

function handleReset() {
  props.supplier.code = null;
  props.supplier.name = null;
  props.supplier.contactName = null;
  props.supplier.contactPhone = null;
}
</script>

<template>
  <Form :model="supplier" label-position="top" :disabled="disabled">
    <Row>
      <Col span="18">
        <FormItem label="名称">
          <Select v-model="supplier.name" @on-select="selectSupplier" filterable>
            <Option v-for="item in thinSupplierList" :value="item.name" :tag="item.code" :key="item.code">{{
                item.name
              }}
            </Option>
          </Select>
        </FormItem>
      </Col>
      <Col span="5" offset="1">
        <FormItem label="审核">
          <Checkbox v-model="supplier.audited" true-value="Y" false-value="N">已审核</Checkbox>
        </FormItem>
      </Col>
      <Col span="6">
        <FormItem label="编码">
          <Input v-model="supplier.code" disabled/>
        </FormItem>
      </Col>
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
      <Col span="6">
        <FormItem label="联系电话">
          <Input v-model="supplier.contactPhone"/>
        </FormItem>
      </Col>
      <Col span="5" offset="1">
        <FormItem label="确认">
          <Checkbox v-model="supplier.confirmed" true-value="Y" false-value="N" disabled>已确认</Checkbox>
        </FormItem>
      </Col>
    </Row>
    <FormItem>
      <Button type="primary" @click="$emit('updateEvent', props.supplier)">保存</Button>
      <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
    </FormItem>
  </Form>
</template>

