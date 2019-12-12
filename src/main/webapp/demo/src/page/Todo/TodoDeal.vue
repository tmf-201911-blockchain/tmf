<template>
  <div>
    <el-dialog
      :title="todoItem.dialogTitile"
      :visible.sync="dialogVisible"
      width="70%"
      @close="closeDialog"
      @upList="upList"
    >
      <div class="dialog-wrap">
        <TodoDetails></TodoDetails>
        <TodoForm @closeDialog="closeDialog" ref="submitFn"></TodoForm>
      </div>
      <div class="todoBtnStyle">
        <div @click="updateUnicomProgress">Confirm</div>
        <div @click="closeDialog" style="color: rgba(0,0,0,0.65);border: 1px solid #D9D9D9;">Cancel</div>
      </div>
    </el-dialog>
  </div>

</template>

<script>
  import {mapState} from 'vuex';
  import TodoForm from "./TodoForm";
  import TodoDetails from "./TodoDetails";

  export default {
    name: "Todo-deal",
    data() {
      return {
        dialogVisible: this.isShow,
      };
    },
    props: {
      isShow: Boolean,
    },
    methods: {
      handleClose(done) {
        this.$confirm('Confirm shutdown？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      closeDialog() {
        this.$emit('closeDialog');
      },
      upList() {
        this.$emit('upList');
      },
      updateUnicomProgress() {
        if (this.todoItem.todo || this.todoItem.OpsProcess) {
          this.$refs.submitFn.updateUnicomProgress().then(result => {
            if (result === '操作成功' || result.code == 200) {
              // this.$msg.success("Success！");
              this.$msg({
                message: 'Success',
                type: 'success',
                duration: '1000',
              });
              this.closeDialog();
              this.upList();
            }
          }).catch(error => {
            console.log(error);
            // this.$msg.error('Error:', error);
            this.$msg({
              message: 'Error',
              type: 'error',
              duration: '1000',
            });
          });
        } else {
          this.closeDialog();
        }
      },
    },
    computed: {
      ...mapState(['todoItem']),
    },
    components: {
      TodoDetails,
      TodoForm,
    },
    destroyed() {
      this.$store.commit('TODO_ITEM', {});
    }
  }
</script>

<style lang="less" scoped>
  .dialog-wrap {
    height: 350px;
    overflow: hidden;
    overflow-y: scroll;
  }

  .todoBtnStyle {
    display: flex;
    justify-content: center;

    div {
      /*border: solid 1px rgb(198, 0, 37);*/
      border: solid 1px #5069E4;
      padding: 10px 23px;
      margin-left: 10px;
      margin-top: 10px;
      font-size: 13px;
      cursor: pointer;
      border-radius: 4px;
    }

    div:nth-of-type(1) {
      background: #5069E4;
      color: #ffffff;
    }

    div:nth-of-type(2) {
      background: #ffffff;
      color: #5069E4;
    }
  }
</style>
