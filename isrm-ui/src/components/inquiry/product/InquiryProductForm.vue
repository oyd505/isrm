<script setup>
import {Button, Col, DatePicker, Form, FormItem, Input, InputNumber, Option, Row, Select} from "view-ui-plus";
import {onMounted, ref} from "vue";
import {getThinFtyOrganizationList, getThinProductList} from "@/http/api";

const props = defineProps({
  product: {type: Object, required: true},
  disabled: {type: Boolean}
});
defineEmits(["updateEvent"]);
const thinProductList = ref([])
const organizationList = ref([])
onMounted(() => {
  getThinProductList().then(data => {
    thinProductList.value = data;
  })
  getThinFtyOrganizationList().then(data => {
    organizationList.value = data;
  })
})

function selectProduct(selection) {
  props.product.code = selection.tag;
}

function selectOrganization(selection) {
  props.product.factoryName = selection.label;
}

function handleReset() {
  props.product.name = null;
  props.product.quantity = null;
  props.product.deliveryDate = null;
}
</script>

<template>
  <Form :model="product" label-position="top" :disabled="disabled">
    <Row>
      <Col span="16">
        <FormItem label="名称">
          <Select v-model="product.name" @on-select="selectProduct" filterable>
            <Option v-for="item in thinProductList" :value="item.name" :tag="item.code" :key="item.code">{{
                item.name
              }}
            </Option>
          </Select>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="采购组织">
          <Select v-model="product.factoryCode" @on-select="selectOrganization" filterable>
            <Option v-for="item in organizationList" :value="item.code" :key="item.code">{{ item.name }}</Option>
          </Select>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="编码">
          <Input v-model="product.code" disabled/>
        </FormItem>
      </Col>
      <Col span="4">
        <FormItem label="数量">
          <InputNumber v-model="product.quantity"/>
        </FormItem>
      </Col>
      <Col span="4">
        <FormItem label="单位">
          <Input v-model="product.units"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="交付期">
          <DatePicker v-model="product.deliveryDate" type="date" @on-change="product.deliveryDate = $event"/>
        </FormItem>
      </Col>
    </Row>
    <FormItem>
      <Button type="primary" @click="$emit('updateEvent', props.product)">保存</Button>
      <Button @click="handleReset()" style="margin-left: 8px">重置</Button>
    </FormItem>
  </Form>
</template>
