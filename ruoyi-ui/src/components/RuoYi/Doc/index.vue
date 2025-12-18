<template>

</template>

<script setup>
const url = ref('http://doc.ruoyi.vip/ruoyi-vue')

function goto() {
  window.open(url.value)
}
</script>