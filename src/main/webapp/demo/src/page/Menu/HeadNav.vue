<template>
  <div class="header">
    <!-- 折叠按钮 -->
    <div class="collapse-btn" @click="isShow">
      <i v-if="!toggleSider" class="el-icon-s-fold"></i>
      <i v-else class="el-icon-s-unfold"></i>
    </div>
<!--    <div class="logo">后台管理系统</div>-->
    <div class="header-right">
      <div class="header-user-con">
        <!-- 消息中心 -->
<!--        <div class="btn-bell">-->
<!--          <el-tooltip-->
<!--            effect="dark"-->
<!--            :content="`消息中心`"-->
<!--            placement="bottom"-->
<!--          >-->
<!--            <router-link to="/Index/Todo">-->
<!--              <i class="el-icon-bell"></i>-->
<!--            </router-link>-->
<!--          </el-tooltip>-->
<!--          <span class="btn-bell-badge"></span>-->
<!--        </div>-->
        <!-- 用户头像 -->
        <div class="user-avator">
          <img :src="imgSrc" />
        </div>
        <!-- 用户名下拉菜单 -->
        <el-dropdown class="user-name" trigger="click" @command="handleCommand">
                    <span class="el-dropdown-link">
                        {{ userInfo.userName }}
                        <i class="el-icon-caret-bottom"></i>
                    </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="loginout">SIGN OUT</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
    <el-dialog
      title="Exit confirm"
      :visible.sync="centerDialogVisible"
      width="30%"
      center>
      <span>Are you sure you want to log out?</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="LogOut">Confirm</el-button>
        <el-button @click="centerDialogVisible = false">Cancel</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
  import { mapState } from 'vuex';
  import imgSrc1 from "../../assets/img.jpg";
  import ZTE from '../../assets/ZTE.png';
  import liantong from '../../assets/liantong.png';
  import moren from '../../assets/moren.png';
  import IBM from '../../assets/IBM.png';
  import dianxin from '../../assets/dianxin.png'
  import alibaba from '../../assets/alibaba.png';


    export default {
      name: "headNav",
      computed: {
        ...mapState(['userInfo', 'toggleSider']),
      },
      data() {
        return {
          isCollapse: false,
          centerDialogVisible: false,
          imgSrc: moren,
        };
      },
      methods: {
        LogOut() {
          this.centerDialogVisible = false;
          this.$store.commit('DELETE_USER_INFO');
          this.$router.push('/');
        },
        // 用户名下拉菜单选择事件
        handleCommand(command) {
          if (command == 'loginout') {
            // this.LogOut();
            this.centerDialogVisible = true;
          }
        },
        isShow() {
          this.$store.dispatch('toggleSideBar')
        },
        LoadHead() {
          const { userName, role } = this.userInfo;
          switch (role) {
            case 'Unicom':
              this.imgSrc = liantong;
              break;
            case 'Telecom':
              this.imgSrc = dianxin;
              break;
            case 'Investor':
              if (userName == 'IBM') {
                this.imgSrc = IBM;
              } else if (userName == 'ZTE') {
                this.imgSrc = ZTE;
              } else if (userName == 'Alibaba') {
                this.imgSrc = alibaba;
              }
              break;
            default:
              break;
          }
        },
      },
      mounted() {
        this.LoadHead();
      }
    }
</script>

<style scoped>
  .header {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    font-size: 22px;
    /*height: 45px;*/
    /*color: #fff;*/
    /*background: #9a001d;*/
    /*height:9%;*/
    height: 64px;
    color: rgba(0,0,0,0.65);
    background:rgba(255,255,255,1);
    box-shadow:0px 1px 4px 0px rgba(0,21,41,0.12);
  }
  .collapse-btn {
    float: left;
    padding: 10px 21px;
    cursor: pointer;
    line-height: 45px;
  }
  .header .logo {
    float: left;
    width: 250px;
    line-height: 64px;
  }
  .header-right {
    float: right;
    padding-right: 50px;
  }
  .header-user-con {
    display: flex;
    height: 64px;
    align-items: center;
  }
  .btn-fullscreen {
    transform: rotate(45deg);
    margin-right: 5px;
    font-size: 24px;
  }
  .btn-bell,
  .btn-fullscreen {
    position: relative;
    width: 30px;
    height: 30px;
    text-align: center;
    border-radius: 15px;
    cursor: pointer;
  }
  .btn-bell-badge {
    position: absolute;
    right: 0;
    top: -2px;
    width: 8px;
    height: 8px;
    border-radius: 4px;
    background: #f56c6c;
    color: #fff;
  }
  .btn-bell .el-icon-bell {
    /*color: #fff;*/
  }
  .user-name {
    margin-left: 10px;
  }
  .user-avator {
    margin-left: 20px;
  }
  .user-avator img {
    display: block;
    width: 33px;
    height: 33px;
    border-radius: 50%;
  }
  .el-dropdown-link {
    color: rgba(0,0,0,0.65);
    cursor: pointer;
  }
  .el-dropdown-menu__item {
    text-align: center;
  }
</style>

<style>
  .header .el-dialog__header {
    text-align: left;
  }
</style>
