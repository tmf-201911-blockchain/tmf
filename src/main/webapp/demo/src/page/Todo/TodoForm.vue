<template>
  <div>
    <div class="TodoFormBorder">
      <div class="formTitle">Application content</div>

      <div class="leaseInfo">
        <div>
          <p>{{ this.todoItem.applicationType == '2' ? 'End sharing date' : 'Lease start time' }}</p>
          <span>{{ this.todoItem.effectiveTime }}</span>
        </div>
        <div>
          <p>{{this.todoItem.applicationType == '2' ? 'Termination reasons' : 'Lease reasons'}}</p>
          <span>{{ this.todoItem.applyReason }}</span>
        </div>
      </div>

      <el-form ref="form" :model="form" class="formStyle" v-if="this.todoItem.todo">
        <el-form-item label="Application Result">
          <el-radio-group v-model="form.progress">
            <el-radio label='2'>Approved</el-radio>
            <el-radio label='1'>Refused</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item
          label="Commnets"
          prop="opinion"
          :rules="[{ required: form.progress == '1' ? true : false, message: 'Please enter your comments', trigger: 'blur' }]"
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
        if (this.form.progress == '1' && this.form.opinion == '') {
          this.$msg.error("Please enter your comments！");
          return;
        }
        if (applicationType == '1') {
          newParams.lessor = lessor;
          newParams.lessee = lessee;
          return TodoApi.updateResourceApply(newParams);
        } else if (applicationType == '2') {
          return TodoApi.updateTerminationApply(newParams);
        }
      },
    },
    computed: {
      ...mapState(['todoItem']),
    },
  }
</script>

<style lang="less" scoped>
  .TodoFormBorder {
    border: solid 1px #cccccc;
    padding: 10px 5px;
    margin-top: 10px;

    .formTitle {
      border-left: solid 3px rgb(198, 0, 37);
      padding-left: 10px;
    }

    .formStyle {
      /*margin-left: 50px;*/
    }

    .leaseInfo {
      div {
        line-height: 40px;
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
