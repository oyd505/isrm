 template
<script setup>
// 导入 view-ui-plus 组件库中的相关组件
import {Col, DatePicker, Form, FormItem, Input, Row, Select} from "view-ui-plus";
// 导入自定义的货币选择组件
import CurrencySelect from "@/components/common/CurrencySelect.vue";
// 导入自定义的员工选择组件
import EmployeeSelect from "@/components/common/EmployeeSelect.vue";
// 导入自定义的组织选择组件
import OrganizationSelect from "@/components/common/OrganizationSelect.vue";
// 导入自定义的询价状态选择组件
import InquiryStatusSelect from "@/components/inquiry/InquiryStatusSelect.vue";

// 定义组件的 props，inquiry 是一个必需的对象
const props = defineProps({
  inquiry: {type: Object, required: true}
});
</script>

<template>
  <!-- 使用 Form 组件包裹整个表单，绑定 inquiry 对象 -->
  <Form :model="inquiry" label-position="top">
    <!-- 使用 Row 和 Col 组件进行网格布局 -->
    <Row>
      <Col span="8">
        <!-- 询价编码字段，只读 -->
        <FormItem label="编码">
          <Input v-model="inquiry.inquiryCode" disabled/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 询价状态字段，只读 -->
        <FormItem label="状态">
          <InquiryStatusSelect v-model="inquiry.status" disabled/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 询价参与方式字段 -->
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
        <!-- 询价标题字段 -->
        <FormItem label="标题">
          <Input v-model="inquiry.title"/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 采购组织字段 -->
        <FormItem label="采购组织">
          <OrganizationSelect orgType="po" v-model:code="inquiry.purOrgCode" v-model:name="inquiry.purOrgName"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="8">
        <!-- 报价开始时间字段 -->
        <FormItem label="报价开始时间">
          <DatePicker type="datetime" :model-value="inquiry.startTime" @on-change="inquiry.startTime = $event"/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 报价结束时间字段 -->
        <FormItem label="报价结束时间">
          <DatePicker type="datetime" :model-value="inquiry.endTime" @on-change="inquiry.endTime = $event"/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 采购员字段 -->
        <FormItem label="采购员">
          <EmployeeSelect v-model:code="inquiry.buyerCode" v-model:name="inquiry.buyerName"/>
        </FormItem>
      </Col>
    </Row>
    <Row>
      <Col span="16">
        <!-- 付款条件字段 -->
        <FormItem label="付款条件">
          <Input v-model="inquiry.paymentTerms"/>
        </FormItem>
      </Col>
      <Col span="8">
        <!-- 币种字段 -->
        <FormItem label="币种">
          <CurrencySelect v-model="inquiry.currency"/>
        </FormItem>
      </Col>
    </Row>
    <!-- 说明字段，使用文本域 -->
    <FormItem label="说明">
      <Input v-model="inquiry.reason" type="textarea"/>
    </FormItem>
    <!-- 插槽用于扩展额外内容 -->
    <slot></slot>
  </Form>
</template>
