<style>
html,body{height:100%}
</style>
<template>
    <div id="main" class="app-main" :style='st'>
        <router-view></router-view>
    </div>
</template>

<script>
export default {
  data () {
    return {
      st: {
        height: document.body.clientHeight + 'px'
      }
    }
  }
}
</script>
