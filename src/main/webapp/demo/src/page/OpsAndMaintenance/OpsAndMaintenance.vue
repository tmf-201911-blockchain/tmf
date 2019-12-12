<template>
  <div style="background: rgba(243,246,254,1);height: 100%">
    <span style="display: inline-block;padding: 10px;overflow-y: scroll;max-height: 622px">
        <span style="margin-right: 4px;font-size:14px;color:rgba(0,0,0,0.45);margin-left: 25px">current position:</span>
        <span style="font-size:14px;color:rgba(0,0,0,0.65);">Ops & Maintenance</span>
    </span>
    <div style="background: white;margin-left: 24px;height: 100%">
      <div class="el-tabView">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="Inprogress" name="/Index/OpsAndMaintenance">
            <el-badge is-dot class="item" slot="label" v-if="isHasInprogress">In Progress</el-badge>
          </el-tab-pane>
          <el-tab-pane label="Solved" name="/Index/OpsAndMaintenance/Solved"></el-tab-pane>
        </el-tabs>
      </div>


      <div class="Todo-search">
        <div>
          <div>Report Content</div>
          <input placeholder="Input here" v-model="searchCriteria.reportContent"></input>
        </div>
        <div>
          <div>Task ID</div>
          <input placeholder="Input here" v-model="searchCriteria.taskId"></input>
        </div>
        <div>
          <div>ResourceType</div>
          <!--          <select placeholder="Input here" v-model="searchCriteria.taskId"></select>-->
          <el-select v-model="searchCriteria.resourceType" placeholder="Choose">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </div>
        <div class="searchBtn" @click="searchBtn">Query</div>
        <div class="searchBtn cancleBtn" @click="cancleBtn">Reset</div>
      </div>
      <div class="todo-list">
        <router-view></router-view>
      </div>

    </div>
  </div>
</template>

<script>
  import { mapState } from 'vuex';
  export default {
    name: "OpsAndMaintenance",
    data() {
      return {
        activeName: '/Index/OpsAndMaintenance',
        searchCriteria: {
          // resourceType: '',
          // taskId: '',
          // reportContent: '',
        },
        options: [
          { label: 'All', value: '' },
          { label: 'Base Station', value: 'BaseStation' },
          { label: 'Tower', value: 'Tower' },
        ],
      };
    },
    methods: {
      handleClick(tab, event) {
        this.$router.push({ path: this.activeName })
      },
      searchBtn() {
        this.$store.commit('SEARCH_CRITERIA', this.searchCriteria);
      },
      cancleBtn() {
        this.searchCriteria = {
          // resourceType: '',
          // taskId: '',
          // reportContent: '',
        }
        // this.$refs.mySelect.handleClose() 关闭下拉框，有个小问题可能需要用到，暂时先记录
        // this.$store.commit('SEARCH_CRITERIA', {});
      }
    },
    watch: {
      $route(to, from) {
        // 当路由变化时，清空搜索条件
        this.searchCriteria = {
          resourceType: '',
          taskId: '',
          reportContent: '',
        }
        const { path = '' } = to;
        this.$store.commit('SEARCH_CRITERIA', {});
        this.$store.commit('ROUTER_PATH', path);
      }
    },
    computed: {
      ...mapState(['isHasInprogress']),
    }
  }
</script>

<style lang="less" scoped>
  .Todo-search {
    display: flex;
    padding: 20px 20px;
    align-items: flex-end;
    div {
      /*height: 30px;*/
      /*display: flex;*/
      margin-right: 20px;
      /*align-items: center;*/

      span {
        color: #666666;
        font-size: 13px;
        margin-right: 10px;
      }

      input {
        height: 32px;
        width: 230px;
        text-indent: 5px;
        box-sizing: border-box;
        border-radius: 4px;
        border: 1px solid #DCDFE6;
        margin-top: 8px;
      }
    }

    .searchBtn {
      padding: 0px 20px;
      height: 32px;
      line-height: 32px;
      border: solid 1px #ccc;
      background: #5069E4;
      font-size: 13px;
      cursor: pointer;
      color: #ffffff;
      border-radius: 2px;
    }

    .cancleBtn {
      background: #ffffff;
      color: #000000;
    }
  }

  .paddingH {
    padding: 5px 10px;
  }

  .tabView {
    height: 40px;
    display: flex;
    align-items: center;
    border-bottom: solid 1px #ccc;
  }

  .el-tabView {
    margin-left: 20px;
  }
  .todo-list {
    margin-left: 10px;
  }
</style>

<style>
  .el-tabView .el-badge__content.is-dot {
    width: 6px;
    height: 6px;
    right: -3px;
    top: 13px;
  }
  .Todo-search .el-input__inner {
    height: 32px;
    width: 230px;
    margin-top: 8px;
  }
</style>
