<script setup>
import {Select} from "view-ui-plus";
import {computed} from "vue";

const props = defineProps(['modelValue'])
const emit = defineEmits(['update:modelValue'])
const value = computed({
  get() {
    return props.modelValue
  },
  set(value) {
    emit('update:modelValue', value)
  }
})
</script>

<template>
  <Select v-model="value">
    <Option value="CNY">人民币</Option>
    <Option value="USD">美元</Option>
    <Option value="EUR">欧元</Option>
    <Option value="JPY">日元</Option>
    <Option value="GBP">英镑</Option>
    <Option value="AUD">澳元</Option>
    <Option value="CAD">加元</Option>
    <Option value="NZD">新西兰元</Option>
  </Select>
</template>

<style scoped>

</style>