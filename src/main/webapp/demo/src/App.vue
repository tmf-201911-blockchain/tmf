<template>
  <div id="app">
    <!--<router-view/>-->
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  provide() { // 注册一个方法
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false
      this.$nextTick(function() {
        this.isRouterAlive = true
        console.log('reload')
      })
    }
  },

}
</script>

<style lang="less">
  @import './style/elemennt-ui.less';
  @import './style/common.less';
  @font-face {
    font-family: 'iconfont';  /* project id 1453141 */
    src: url('//at.alicdn.com/t/font_1453141_8p0m77og4mi.eot');
    src: url('//at.alicdn.com/t/font_1453141_8p0m77og4mi.eot?#iefix') format('embedded-opentype'),
    url('//at.alicdn.com/t/font_1453141_8p0m77og4mi.woff2') format('woff2'),
    url('//at.alicdn.com/t/font_1453141_8p0m77og4mi.woff') format('woff'),
    url('//at.alicdn.com/t/font_1453141_8p0m77og4mi.ttf') format('truetype'),
    url('//at.alicdn.com/t/font_1453141_8p0m77og4mi.svg#iconfont') format('svg');
  }

  .iconfont{
    font-family:"iconfont" !important;
    font-size:18px;font-style:normal;
    -webkit-font-smoothing: antialiased;
    -webkit-text-stroke-width: 0.2px;
    -moz-osx-font-smoothing: grayscale;}

</style>
