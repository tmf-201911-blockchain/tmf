<template>
  <div class="login_page">
    <transition name="form-fade" mode="in-out">
      <div style="display: flex;" class="leftMenu">
        <div class="logoLeft">
           <!--<img src="../assets/Leftside.png" style="width: 100%;height: 100%;"/>-->
        </div>
        <div style="background: #fff;width: 55%;min-height: 700px;position: relative">
          <div class="LeftSide">
            <section class="form_contianer">
              <div class='titleArea rflex'>
                <div class='title'>Sign in to 5G</div>
                <div class='title'>Resource Sharing Platform</div>
              </div>
              <el-form :model="loginForm" :rules="rules" ref="loginForm" class="loginForm">
                <el-form-item prop="userName" class="login-item">
                  <span class="usernameStyle">Username</span>
                  <el-input class="area" type="text" placeholder="please enter user name" v-model="loginForm.userName" ></el-input>
                </el-form-item>
                <el-form-item prop="password" class="login-item">
                  <span class="PasswordStyle">Password</span>
                  <el-input class="area" type="password" placeholder="Please enter your password" v-model="loginForm.password"></el-input>
                </el-form-item>
                <el-form-item style="margin-top: 80px;">
                  <el-button type="primary"  @click="LoginnBtn" class="submit_btn">SIGN IN</el-button>
                </el-form-item>
                <!--<div class="tiparea">-->
                <!--<p class="wxtip">温馨提示：</p>-->
                <!--<p class="tip">用户名为：admin/editor<span>(可用于切换权限)</span></p>-->
                <!--<p class="tip">密码为：123456</p>-->
                <!--</div>-->
                <!--<div class="sanFangArea">-->
                <!--<p class="title">第三方账号登录</p>-->
                <!--<ul class="rflex">-->
                <!--<li @click="loginByWechat">-->
                <!--<icon-svg icon-class="iconwechat" />-->
                <!--</li>-->
                <!--<li>-->
                <!--<icon-svg icon-class="iconweibo" />-->
                <!--</li>-->
                <!--<li>-->
                <!--<icon-svg icon-class="iconGithub" />-->

                <!--</li>-->
                <!--</ul>-->
                <!--</div>-->
              </el-form>
            </section>
          </div>
        </div>
      </div>

    </transition>
  </div>
</template>

<script>
  import LoginApi from "./Login/LoginApi";
  export default {
    inject: ['reload'], // 引入方法
    data(){
      return {
        loginForm: {
          userName: 'ChinaUnicom', // ChinaTelecom
          password: '123456' // abcde
        },
        rules: {
          userName: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
        }
      }
    },
    mounted(){
    },
    methods: {
      loginByWechat(){
      },
      showMessage(type,message){
        this.$msg({
          type: type,
          message: message
        });
      },
      LoginnBtn() {
        // this.$store.dispatch('Login', this.loginForm);
          LoginApi.Login(this.loginForm).then(result => {
            this.$store.commit('USER_INFO', result);
            const { role } = result;
            if (role) {
              this.$router.push('/Index');
              // this.$msg.success("登录成功！");
              this.$msg({
                message: 'Landed successfully',
                type: 'success',
                duration: '1000',
              });
              this.reload() // 调用方法
            }
          }).catch(error => {
            console.log(error);
            this.$msg.error("Account password error!");
          });

      },
    }
  }
</script>

<style lang="less" scoped>
  .logoLeft{
    /*width:648px;*/
    /*height:874px;*/
    /*height: 100%;*/
    /*background:linear-gradient(225deg,rgba(55,82,154,0) 0%,rgba(34,50,118,0.42) 72%,rgba(4,4,67,1) 100%);*/
    /*z-index: 20;*/
    /*height: 100vh;*/
    /*background-size: cover;*/
    /*position: relative;*/
    /*background: linear-gradient(225deg, rgba(55, 82, 154, 0) 0%, rgba(34, 50, 118, 0.42) 72%, #040443 100%);*/
    z-index: 20;
    height: 100vh;
    background: url(../assets/Leftside.png) no-repeat 0;
    background-size: cover;
    position: relative;
    width: 45%;
    min-height: 700px;
    display: -webkit-flex;
    display: -moz-flex;
    display: flex;
    flex-direction: column;
  }
  .login_page{
    position: absolute;
    width: 100%;
    height: 100%;
    background-size: 100% 100%;
  }
  .form_contianer{
    /*position: absolute;*/
    /*top: 50%;*/
    /*left: 50%;*/
    /*transform: translate(-50%,-50%);*/
    /*background: #fff;*/
    /*width:370px;*/
    /*border-radius: 5px;*/
    /*padding: 25px;*/
    /*text-align: center;*/
    /*margin-top: 91px;*/
    /*margin-left: 77px;*/
    /*position: absolute;*/
    /*left: 20%;*/
    /*margin-top: 25%;*/
    padding-top: 17vh;
    width: 500px;
    margin: 0 auto;
    position: relative;
    .titleArea{
      justify-content: center;
      align-items: center;
      /*text-transform: uppercase;*/
      font-size: 35px;
      color:rgba(7,45,79,1);
      width: 100%;
      padding-bottom: 80px;
      .logo{
        width: 40px;
        margin-right: 10px;
      }
      .title{
        i{
          color: #FF6C60;
        }
      }
    }

    .loginForm{
      .submit_btn{
        /*width: 100%;*/
        /*padding:13px 0;*/
        /*font-size: 16px;*/
        width:215px;
        height:61px;
        background:rgba(80,105,228,1);
        border-radius:4px;
        font-size:22px;
        font-family:ArialMT;
        color:rgba(255,255,255,1);
      }
      .loginTips{
        position: absolute;
        left: 10px;
        z-index: 20;
        // color: #FF7C1A;
        font-size: 18px;
        top: 50%;
        transform: translateY(-50%);
      }
    }
  }

  .manage_tip{
    margin-bottom:20px;
    .title{
      font-family: cursive;
      font-weight: bold;
      font-size: 26px;
      color:#fff;
    }
    .logo{
      width:60px;
      height:60px;
    }
  }

  .tiparea{
    text-align:left;
    font-size: 12px;
    color: #4cbb15;
    padding: 10px 0;
    .tip{
      margin-left: 54px;
    }
  }

  .form-fade-enter-active, .form-fade-leave-active {
    transition: all 1s;
  }
  .form-fade-enter, .form-fade-leave-active {
    transform: translate3d(0, -50px, 0);
    opacity: 0;
  }
  .loginForm{
    .el-button--primary{
      background-color:#FF7C1A;
      /*border:1px solid #FF7C1A;*/
    }
  }
  .sanFangArea{
    border-top: 1px solid #DCDFE6;
    padding: 10px 0;
    display: none;
    .title{
      font-size: 14px;
      color: #8b9196;
      margin-bottom: 10px;
    }
    ul{
      li{
        flex:1;
        display: flex;
        align-items: center;
        justify-content: center;
        cursor: pointer;
        .svg-icon{
          font-size: 24px;
        }
      }
    }
  }
  .usernameStyle,.PasswordStyle{
    font-size:20px;
    font-family:ArialMT;
    color:rgba(94,110,167,1);
  }
</style>
<style>
  .leftMenu .el-input__inner{
    width:500px;
    height:60px;
    background:rgba(248,249,252,1);
    border-radius:2px;
    border:2px solid rgba(80,105,228,1);
  }
</style>
