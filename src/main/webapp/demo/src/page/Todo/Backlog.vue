<template>
  <div>
    <template>
      <div>
        <el-table
          :data="tableData"
          style="width: 100%"
          empty-text="Temporarily no data"
          max-height="400px"
        >
          <el-table-column
            type="index"
            align="center"
            label="ID"
            :index="indexMethod">
          </el-table-column>
          <el-table-column
            prop="resourceName"
            align="center"
            label="Resource Applied"
            :show-overflow-tooltip="true"
            width="150">
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="mini">{{scope.row.resourceName}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="resourceId"
            align="center"
            label="Resource ID"
            width="120">
          </el-table-column>
          <el-table-column
            prop="taskId"
            align="center"
            label="Task ID">
          </el-table-column>
          <el-table-column
            prop="resourceType"
            align="center"
            label="Resource Type">
          </el-table-column>
          <el-table-column
            prop="lessee"
            align="center"
            label="Applicant">
          </el-table-column>
          <el-table-column
            prop="applicationTime"
            align="center"
            label="Application Time">
          </el-table-column>
          <el-table-column
            prop="applicationType"
            align="center"
            label="Application Type">
          </el-table-column>
          <el-table-column
            prop="caozuo"
            align="center"
            label="Action">
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="small">Process</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </template>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageConfig.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="10"
        layout="prev, pager, next, jumper, total, sizes"
        :total="pageConfig.total">
      </el-pagination>
    </div>
    <Todo-deal
      :is-show="dialogVisible"
      v-if="dialogVisible"
      @closeDialog="closeDialog"
      @upList="TodoApi"
    ></Todo-deal>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  import TodoDeal from "./TodoDeal";
  import TodoApi from "./api/TodoApi";
  import { getCurrentDateStr } from '@/utils/dateUtil';

  export default {
    name: "Backlog",
    data() {
      return {
        dialogVisible: false,
        tableData: [],
        pageConfig: {
          pageNum: 1,
          pageSize: 10,
          total: 0,
          pages: 0,
        },
        progress: {
          0: 'Pending',
          1: 'Refuse',
          2: 'Approved',
        },
        applicationType: {
         '2': 'Resource termination',
         '1': 'Resource application',
        }
      }
    },
    created() {
      this.TodoApi();
    },
    methods: {
      ...mapMutations(['TODO_ITEM']),
      indexMethod(index) {
        return index + 1;
      },
      handleSizeChange(val) {
        this.pageConfig.pageNum = 1;
        this.pageConfig.pageSize = val;
        this.TodoApi();
      },
      handleCurrentChange(val) {
        this.pageConfig.pageNum = val;
        this.TodoApi();
      },
      TodoDeal(item = {}) {
        const { resourceId = '', taskId = '' } = item;
        TodoApi.getAllInfo({ resourceId, taskId }).then(result => {
          this.dialogVisible = true;
          result.todo = true; // 表示从待办页面进入
          result.dialogTitile = 'To be processed'; // 设置dialogTitile
          this.TODO_ITEM(result);
        }).catch(error => {
          console.log(error);
        })
      },
      closeDialog() {
        this.dialogVisible = false;
      },

      TodoApi() {
        const params = {
          pageNum: this.pageConfig.pageNum,
          pageSize: this.pageConfig.pageSize,
          ...this.$store.state.searchCriteria,
        }

        TodoApi.getUnicomUnfinished(params).then(result => {
          const {list = [], total = 0, pages = 0} = result;
          this.pageConfig = {
            total,
            pages,
            pageNum: params.pageNum,
            pageSize: params.pageSize,
          };
          if (list.length > 0) {
            this.$store.commit('IS_HAS_APPROVAL', true);
          } else {
            this.$store.commit('IS_HAS_APPROVAL', false);
          }
          list.forEach(item => {
            if (item.applicationTime) {
              item.applicationTime = getCurrentDateStr(item.applicationTime);
            }
            if (item.progress) {
              item.progress = this.progress[item.progress];
            }
            if (item.applicationType) {
              item.applicationType = this.applicationType[item.applicationType];
            }
            Object.keys(item).forEach(keyItem => {
              if (!item[keyItem]) {
                item[keyItem] = '-';
              }
            });
          })
          this.tableData = list;
        }).catch(error => {
          console.log(error);
        })
      }
    },
    components: {
      TodoDeal,
    },
    computed: {
      search() {
        return this.$store.state.searchCriteria;
      }
    },
    watch: {
      search() {
        this.TodoApi();
      }
    }
  }
</script>


<style lang="less" scoped>

</style>
