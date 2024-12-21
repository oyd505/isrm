<script setup>
import {Col, DatePicker, Form, FormItem, Input, Row, Select} from "view-ui-plus";
import CurrencySelect from "@/components/common/CurrencySelect.vue";
import EmployeeSelect from "@/components/common/EmployeeSelect.vue";
import OrganizationSelect from "@/components/common/OrganizationSelect.vue";

const props = defineProps({
  inquiry: {type: Object, required: true}
});
</script>

<template>
  <Form :model="inquiry" label-position="top">
    <Row>
      <Col span="8">
        <FormItem label="编码">
          <Input v-model="inquiry.inquiryCode" disabled/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="状态">
          <Select v-model="inquiry.status" disabled>
            <Option value="PENDING">未提交状态</Option>
            <Option value="SUBMITTED">已提交状态</Option>
            <Option value="PUBLISHED">已发布状态</Option>
            <Option value="CONFIRMED">已确认状态</Option>
            <Option value="PAID_PENDING">已确认待付款状态</Option>
            <Option value="PAID">已付款状态</Option>
            <Option value="CANCELED">已取消状态</Option>
          </Select>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="参与方式">
          <Select v-model="inquiry.participation">
            <Option value="INVITED">受邀的</Option>
            <Option value="OPEN">开放的</Option>
          </Select>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="16">
        <FormItem label="标题">
          <Input v-model="inquiry.title"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="采购组织">
          <OrganizationSelect orgType="po" v-model:code="inquiry.purOrgCode" v-model:name="inquiry.purOrgName"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <FormItem label="报价开始时间">
          <DatePicker type="datetime" v-model="inquiry.startTime" @on-change="inquiry.startTime = $event"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="报价结束时间">
          <DatePicker type="datetime" v-model="inquiry.endTime" @on-change="inquiry.endTime = $event"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="采购员">
          <EmployeeSelect v-model:code="inquiry.buyerCode" v-model:name="inquiry.buyerName"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="16">
        <FormItem label="付款条件">
          <Input v-model="inquiry.paymentTerms"/>
        </FormItem>
      </Col>
      <Col span="8">
        <FormItem label="币种">
          <CurrencySelect v-model="inquiry.currency"/>
        </FormItem>
      </Col>
    </Row>
    <FormItem label="说明">
      <Input v-model="inquiry.reason" type="textarea"/>
    </FormItem>
    <slot></slot>
  </Form>
</template>
