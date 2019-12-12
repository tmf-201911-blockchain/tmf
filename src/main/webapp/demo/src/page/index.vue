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

  import TodoApi from './Todo/api/TodoApi';
  import Api from './OpsAndMaintenance/api/index'


  export default {
    name: 'Index',
    data(){
      return {
        pageConfig: {
          pageNum: 1,
          pageSize: 10,
        },
      }
    },
    computed:{
      ...mapState(['toggleSider', 'userInfo']),
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
        if (this.userInfo.Operator) {
          TodoApi.getUnicomUnfinished({ ...this.pageConfig }).then(result => {
            const {list = [] } = result;
            if (list.length > 0) {
              this.$store.commit('IS_HAS_APPROVAL', true);
            } else {
              this.$store.commit('IS_HAS_APPROVAL', false);
            }
          }).catch(error => {
            console.log(error);
          })
        }
      },
      fetchList() {
        if (this.userInfo.Operator) {
          Api.fetchList({ ...this.pageConfig }).then(result => {
            const { data = {} } = result;
            const { list = [], pages = 0, total = 0 } = data;
            if (list.length > 0) {
              this.$store.commit('IS_HAS_INPROGRESS', true);
            } else {
              this.$store.commit('IS_HAS_INPROGRESS', false);
            }
          }).catch(error => {
            console.log(error);
          })
        }
      },

    },
    mounted (){
      this.getUnicomUnfinished();
      this.fetchList();
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
      background: rgb(243, 246, 254);
    }
    .menu_right.is-active {
      left: 64px;
    }
    .menu_content {
      width: 100%;
      height: 87%;
      /*height: calc(100% - 64px);*/
      overflow: hidden;
      /*overflow-y: scroll;*/
    }
  }
</style>
