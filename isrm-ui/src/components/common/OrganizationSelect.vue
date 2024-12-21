<script setup>
import {onMounted, ref} from "vue";
import {getThinComOrganizationList, getThinFtyOrganizationList, getThinPurOrganizationList} from "@/http/api";
import {Option, Select} from "view-ui-plus";

const props = defineProps({
  orgType: {type: String, default: 'po'}
})
const code = defineModel('code');
const name = defineModel('name');

const organizationList = ref([])

onMounted(() => {
  if (props.orgType === 'po') {
    getThinPurOrganizationList().then(data => {
      organizationList.value = data;
    });
  } else if (props.orgType === 'fo') {
    getThinFtyOrganizationList().then(data => {
      organizationList.value = data;
    })
  } else {
    getThinComOrganizationList().then(data => {
      organizationList.value = data;
    })
  }
});

function selectOrganization(selection) {
  name.value = selection.label;
}
</script>

<template>
  <Select v-model="code" @on-select="selectOrganization" label-in-value filterable>
    <Option v-for="item in organizationList" :key="item.code" :value="item.code">{{ item.name }}</Option>
  </Select>
</template>

<style scoped>

</style>