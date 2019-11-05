<template>
  <div
    class="left-menu"
    :class="{'is-active':toggleSider}"
  >
    <div class="menu_page_top rflex">
      <div style="display: flex;margin-top: 16px;">
        <img src="../../assets/logo5g.png" class="logo5g"/>
        <div class="logotitle">
          <div>5G Resource</div>
          <div>Sharing Platform</div>
        </div>
      </div>
      <!--<span class='title'><i></i></span>-->
      <!--<span class='title'><i style="color: white;font-style: inherit;margin-bottom: 30px;display: inline-block;margin-left: 10px;margin-top: 10px;">中国联通·5G共建协作系统</i></span>-->
    </div>
    <el-menu
      :default-active="onRoutes"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      @select="handleSelect"
      background-color="#333333"
      text-color="#fff"
      router
      unique-opened
      :collapse="toggleSider"
      active-text-color="#409EFF">

      <el-menu-item index="/Index">
        <i class="el-icon-menu"></i>
        <span slot="title">Resource Management</span>
      </el-menu-item>
      <el-menu-item index="/Index/ResourcesStatistics">
        <i class="el-icon-document"></i>
        <span slot="title">Resource Statistics</span>
      </el-menu-item>
      <el-menu-item index="/Index/Todo">
        <i class="el-icon-setting"></i>
        <span slot="title">Application & Approval</span>
        <span class="btn-bell-badge" v-if="isHasApproval"></span>
      </el-menu-item>
    </el-menu>



  </div>
</template>


<script>
  import { mapState } from 'vuex';
  export default {
    name: "left-menu",
    data() {
      return {
        isCollapse: false
      };
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    },
    computed: {
      ...mapState(['toggleSider', 'routerPath', 'isHasApproval']),
      onRoutes() {
        if (this.routerPath.indexOf('/Index/Todo') > -1) {
          return '/Index/Todo';
        }
        return this.$route.path;
      },
    },
  }
</script>

<style lang="less" scoped>
  .left-menu {
    width: 253px;
    /*height: 100%;*/
    display: block;
    position: absolute;
    left: 0;
    top: 0;
    bottom: 0;
    overflow-y: scroll;
    background-color: rgba(51, 51, 51, 1);
    background:rgba(0,33,64,1)!important;
    box-shadow:2px 0px 6px 0px rgba(0,21,41,0.35);
  }
  .left-menu > ul {
    /*height: 100%;*/
  }

  .el-menu {
    background-color: rgba(51, 51, 51, 1);
    border-right: none;
  }

  .el-menu-item:focus, .el-menu-item:hover {
    outline: 0;
    background-color: rgba(154, 1, 29, 1);
  }

  .el-menu-item {
    color: white;
  }

  .el-menu-item, .el-submenu__title {
    height: 40px;
    line-height: 40px;
  }
  .logo5g{
    width: 38px;
    height: 34px;
    /*background: #0065FF;*/
    margin-left: 12px;
    margin-right: 15px;
    margin-top: 5px;
  }
  .logotitle{
    font-size:16px;
    font-weight:normal;
    color:rgba(255,255,255,1);
    margin-right: 42px;
    /*margin-top: 3%;*/
    /*margin-top: 8%;*/
  }
  .el-menu-vertical-demo{
    margin-top: 24px;
  }
  .el-menu-item{
    /*background:rgba(0,21,41,1)!important;*/
    background:rgba(0,33,64,1)!important;
  }

  .btn-bell-badge {
    position: absolute;
    left: 208px;
    top: 13px;
    width: 6px;
    height: 6px;
    border-radius: 4px;
    background: #f56c6c;
    color: #fff;
  }
</style>


