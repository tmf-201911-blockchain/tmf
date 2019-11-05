<template>
  <el-dialog
    width="50%"
    title="Select Resource Type"
    :visible.sync="visibleShow"
    @close="$emit('update:visible', false)"
  >
    <div class="container">
      <div class="icon-wrap" @click="selectTypeBaseStation">
        <i class="iconfont size" style="color: #56bb3e">&#xe651;</i>
        <div class="text">BaseStation</div>
      </div>
      <div class="icon-wrap" @click="selectTypeTower">
        <i class="iconfont size" style="color: #3e93f6">&#xe66d;</i>
        <div class="text">Tower</div>
      </div>
    </div>

  </el-dialog>
</template>

<script>
  export default {
    name: "SelectResourceType",
    data() {
      return {
        visibleShow: this.visible,
      }
    },
    props: {
      visible: {
        type: Boolean,
        default: false,
      }
    },
    methods: {
      selectTypeBaseStation() {
        this.visibleShow = false;
        this.$emit('ResourcePublishing', 'BaseStation')
      },
      selectTypeTower() {
        this.visibleShow = false;
        this.$emit('ResourcePublishing', 'Tower')
      }
    },
    watch: {
      visible () {
        this.visibleShow = this.visible;
      }
    }

  }
</script>

<style scoped>
  .size {
    font-size: 72px;
    display: inline-block;
    padding: 21px 29px;
    border: solid 1px #ddd;
    border-radius: 4px;
    line-height: normal;
  }
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 311px;
  }

  .text {
    text-align: center;
    margin-top: 10px;
    color: #000000;
  }

  .icon-wrap {
    display: inline-table;
    margin: 0 36px;
    cursor: pointer;
  }
</style>
