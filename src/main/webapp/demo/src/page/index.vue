<template>
  <div class="home">
    <left-menu></left-menu>
    <div class="menu_right" ref="menu_right" :class="{'is-active':toggleSider}">
      <head-nav></head-nav>
      <div class="menu_content" ref="menu_content">
        <router-view></router-view><!--页面渲染入口-->
      </div>
      <footer-nav></footer-nav>
    </div>
  </div>
</template>
<script>

  import HeadNav from './Menu/headNav';
  import LeftMenu from './Menu/leftMenu';
  import FooterNav from './Menu/footerNav';
  import { mapState, mapMutations } from 'vuex';




  export default {
    name: 'Index',
    data(){
      return {
      }
    },
    computed:{
      ...mapState(['toggleSider']),
    },
    components:{
      HeadNav,
      LeftMenu,
      FooterNav,
    },
    created() {
    },
    methods: {
      getUnicomUnfinished() {
        TodoApi.getUnicomUnfinished(params).then(result => {
          const {list = [] } = result;
          if (list.length > 0) {
            this.$store.commit('IS_HAS_APPROVAL', true);
          } else {
            this.$store.commit('IS_HAS_APPROVAL', false);
          }
        }).catch(error => {
          console.log(error);
        })
      },

    },
    mounted (){

    },
    watch:{

    }
  }
</script>
<style scoped lang='less'>
  .home {
    height: 100%;
    .menu_right {
      position: absolute;
      left: 253px;
      right: 0;
      top: 0px;
      bottom: 0;
    }
    .menu_right.is-active {
      left: 64px;
    }
    .menu_content {
      width: 100%;
      height: calc(100% - 64px);
      overflow: hidden;
      overflow-y: scroll;
    }
  }
</style>
