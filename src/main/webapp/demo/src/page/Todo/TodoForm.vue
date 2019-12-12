<template>
  <div>
    <div class="TodoFormBorder">
      <div class="formTitle">{{ this.todoItem.processName ? 'Performance Index' : 'Application content' }}</div>

      <div class="NetworkParameters" v-if="this.todoItem.hideSome">
        <div>
          <p>{{this.todoItem.UploadRate}}</p>
          <p>Upload rate</p>
        </div>
        <div>
          <p>{{this.todoItem.DownloadRate}}</p>
          <p>Download rate</p>
        </div>
        <div>
          <p>{{this.todoItem.NetworkDelay}}</p>
          <p>Network delay</p>
        </div>
        <div>
          <p>{{this.todoItem.CallingConnectionRate}}</p>
          <p>Calling connection rate</p>
        </div>
        <div>
          <p>{{this.todoItem.VoiceDropRate}}</p>
          <p>Voice drop rate</p>
        </div>
      </div>

      <div v-if="this.todoItem.hideSome" class="formTitle">Customers' complaints</div>

      <div class="leaseInfo" v-if="!this.todoItem.hideSome">
        <div>
          <p>{{ this.todoItem.applicationType == '2' ? 'End sharing date' : 'Lease start time' }}</p>
          <span>{{ this.todoItem.effectiveTime }}</span>
        </div>
        <div>
          <p>{{this.todoItem.applicationType == '2' ? 'Termination reasons' : 'Lease reasons'}}</p>
          <span>{{ this.todoItem.applyReason }}</span>
        </div>
      </div>

      <div class="leaseInfo" v-if="this.todoItem.hideSome">
        <div>
          <p>Task ID</p>
          <span>{{ this.todoItem.taskId }}</span>
        </div>
        <div>
          <p>Report Content</p>
          <span>{{ this.todoItem.suggestion }}</span>
        </div>
        <div>
          <p>Reporting Time</p>
          <span>{{ this.todoItem.reportTime }}</span>
        </div>
        <div v-if="!this.todoItem.OpsProcess">
          <p>Service Evaluate</p>
          <span>{{ this.todoItem.star || '-' }}</span>
        </div>
      </div>

      <el-form ref="form" :model="form" class="formStyle" v-if="this.todoItem.todo">
        <el-form-item :label="this.todoItem.processName ? 'Progress Result' : 'Application Resultt'">
          <el-radio-group v-model="form.progress">
            <el-radio label='2'>Approved</el-radio>
            <el-radio label='1'>Refused</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item
          label="Commnets"
          prop="opinion"
          :rules="[{ required: form.progress == '1' ? true : false, message: 'Please enter your comments', trigger: 'blur' }]"
          v-if="!this.todoItem.hideSome"
        >
          <el-input type="textarea" v-model="form.opinion" placeholder="Input here"></el-input>
        </el-form-item>
      </el-form>
    </div>
    <!--    <div class="todoBtnStyle">-->
    <!--      <div @click="onSubmit">确定</div>-->
    <!--      <div @click="closeDialog">取消</div>-->
    <!--    </div>-->
  </div>
</template>

<script>
  import {mapState, mapMutations} from 'vuex';
  import TodoApi from "./api/TodoApi";
  import OpsApi from '../OpsAndMaintenance/api/index';

  export default {
    name: "TodoForm",
    data() {
      return {
        form: {
          progress: '2',
          opinion: ''
        },
      }
    },
    methods: {
      ...mapMutations(['TODO_ITEM']),
      onSubmit() {
        this.$emit('closeDialog');
      },
      closeDialog() {
        this.$emit('closeDialog');
      },
      updateUnicomProgress(params = {}) {
        const {
          resourceId = '',
          applicationType = '',
          lessor = '',
          lessee = '',
          leaseStatus = '',
          effectiveTime = '',
          taskId = '',
        } = this.todoItem;
        const newParams = {
          // lessor,
          // lessee,
          leaseStatus,
          effectiveTime,
          taskId,
          ...this.form,
        }
        if (this.form.progress == '1' && this.form.opinion == '' && !this.todoItem.processName) {
          // this.$msg.error("Please enter your comments！");
          this.$msg({
            message: 'Please enter your comments',
            type: 'error',
            duration: '1000',
          });
          return;
        }
        if (applicationType == '1') {
          newParams.lessor = lessor || this.userInfo.userName;
          newParams.lessee = lessee;
          return TodoApi.updateResourceApply(newParams);
        } else if (applicationType == '2') {
          return TodoApi.updateTerminationApply(newParams);
        } else if (this.todoItem.processName == 'OPS') {
          return OpsApi.process({
            taskId,
            isSolved: this.form.progress == '1' ? 2 : 1, // 这个接口isSolved 1代表同意， 2 拒绝
          });
        }
      },
    },
    computed: {
      ...mapState(['todoItem', 'userInfo']),
    },
  }
</script>

<style lang="less" scoped>
  .TodoFormBorder {
    border: solid 1px #cccccc;
    padding: 10px 5px;
    margin-top: 10px;

    .formTitle {
      border-left: solid 3px #5069E4;
      padding-left: 10px;
      margin-bottom: 8px;
    }

    .formStyle {
      /*margin-left: 50px;*/
    }

    .leaseInfo {
      div {
        line-height: 36px;
        p {
          display: inline-block;
          min-width: 135px;
          text-align: right;
        }
        span {
          margin-left: 10px;
        }
      }
    }
  }

  .todoBtnStyle {
    display: flex;
    justify-content: center;

    div {
      border: solid 1px rgb(198, 0, 37);
      padding: 5px 25px;
      margin-left: 10px;
      margin-top: 10px;
      font-size: 13px;
      cursor: pointer;
    }

    div:nth-of-type(1) {
      background: rgb(198, 0, 37);
      color: #ffffff;
    }

    div:nth-of-type(2) {
      background: #ffffff;
      color: rgb(198, 0, 37);
    }
  }
  .NetworkParameters {
    display: flex;
    justify-content: space-around;
    background-color: #f9f7fb;
    padding: 17px 0;
    margin: 15px 13px 17px 13px;
    div {
      text-align: center;
      p {
        font-size: 15px;
        margin-bottom: 0;
      }
      p:first-of-type {
        font-size: 17px;
        color: #000000;
      }

    }
  }
</style>

<style>
  .formStyle .el-form-item__label {
    min-width: 135px;
    padding-right: 0px;
    margin-right: 14px;
  }
  .formStyle .el-textarea {
    width: 80%;
  }
</style>
