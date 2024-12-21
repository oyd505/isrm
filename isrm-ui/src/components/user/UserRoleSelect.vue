<script setup>
import {computed} from "vue";

const props = defineProps({
  roles: {type: String},
  disabled: {type: Boolean}
});

const emit = defineEmits(["updateEvent"]);

const roleList = [
  {value: 'BUYER', label: '采购员'},
  {value: 'MANAGER', label: '经理'},
  {value: 'QUALITY', label: '品质管理员'},
  {value: 'VENDOR', label: '供应商'},
  {value: 'ADMIN', label: '管理员'},
  {value: 'GUEST', label: '访客'}
];

const roleArray = computed({
  get() {
    if (!props.roles) return [];
    return props.roles.split(',').map(s => s.trim());
  },
  set(newValue) {
    emit('updateEvent', newValue.join(','))
  }
});
</script>

<template>
  <Select v-model="roleArray" :disabled="disabled" multiple>
    <Option v-for="item in roleList" :value="item.value" :key="item.value">{{ item.label }}</Option>
  </Select>
</template>

<style scoped>

</style>