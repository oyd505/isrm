 template
<script setup>
import {computed} from "vue";

/**
 * 定义组件的props
 * roles: 一个字符串，表示用户的多个角色，角色之间用逗号分隔
 */
const props = defineProps({
  roles: {type: String}
});

/**
 * 定义组件的emit事件
 * updateEvent: 当角色选择发生变化时触发的事件
 */
const emit = defineEmits(["updateEvent"]);

/**
 * 角色列表，包含一系列角色对象，每个对象包括value和label两个属性
 * value: 角色的值，用于内部标识角色
 * label: 角色的标签，用于在界面上显示角色名称
 */
const roleList = [
  {value: 'BUYER', label: '采购员'},
  {value: 'MANAGER', label: '经理'},
  {value: 'QUALITY', label: '品质管理员'},
  {value: 'VENDOR', label: '供应商'},
  {value: 'ADMIN', label: '管理员'},
  {value: 'GUEST', label: '访客'}
];

/**
 * 计算属性，用于处理角色数组
 * get: 根据传入的roles字符串返回一个角色数组
 *      如果roles为空，则返回空数组
 *      否则，将roles字符串按逗号分割，并去除每个角色字符串两端的空白
 * set: 角色数组发生变化时，触发updateEvent事件，将新的角色数组作为参数传递
 */
const roleArray = computed({
  get() {
    if (!props.roles) return [];
    return props.roles.split(',').map(s => s.trim());
  },
  set(newValue) {
    emit('updateEvent', newValue)
  }
});
</script>

<template>
  <!-- 使用Select组件，绑定roleArray计算属性，实现角色的多选 -->
  <Select v-model="roleArray" multiple>
    <!-- 使用Option组件，遍历roleList，为每个角色创建一个选项 -->
    <Option v-for="item in roleList" :value="item.value" :key="item.value">{{ item.label }}</Option>
  </Select>
</template>

<style scoped>
/* 此处添加组件的私有样式 */
</style>
