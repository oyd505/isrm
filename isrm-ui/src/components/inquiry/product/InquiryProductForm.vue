<script setup>
import {Button, Col, DatePicker, Form, FormItem, Input, InputNumber, Option, Row, Select} from "view-ui-plus";
import {onMounted, ref} from "vue";
import {getThinProductList} from "@/http/api";
import OrganizationSelect from "@/components/common/OrganizationSelect.vue";

const props = defineProps({
  product: {type: Object, required: true}
});
defineEmits(["updateEvent"]);
const thinProductList = ref([])
onMounted(() => {
  getThinProductList().then(data => {
    thinProductList.value = data;
  })
})

function selectProduct(selection) {
  props.product.code = selection.tag;
}

function handleReset() {
  props.product.name = null;
  props.product.quantity = null;
  props.product.deliveryDate = null;
}
</script>

<template>
  <Form :model="product" label-position="top">
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
        <FormItem label="工厂">
          <OrganizationSelect orgType="fo" v-model:code="product.factoryCode" v-model:name="product.factoryName"/>
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
