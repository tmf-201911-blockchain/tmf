<template>
  <div>
    <template>
      <div>
        <el-table
          :data="tableData"
          style="width: 100%"
          empty-text="Temporarily no data"
        >
          <el-table-column
            type="index"
            label="ID"
            :index="indexMethod">
          </el-table-column>
          <el-table-column
            prop="resourceName"
            label="Resource Applied"
            width="150">
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="small">{{scope.row.resourceName}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="resourceId"
            label="Resource ID"
            width="120">
          </el-table-column>
          <el-table-column
            prop="taskId"
            label="Task ID">
          </el-table-column>
          <el-table-column
            prop="areaCode"
            label="Area Code">
          </el-table-column>
          <el-table-column
            prop="lessee"
            label="Applicant">
          </el-table-column>
          <el-table-column
            prop="applicationTime"
            label="Application Time">
          </el-table-column>
          <el-table-column
            prop="applicationType"
            label="Application Type">
          </el-table-column>
          <el-table-column
            prop="progress"
            label="Status">
          </el-table-column>
          <!--          <el-table-column-->
          <!--            prop="caozuo"-->
          <!--            label="操作">-->
          <!--            <template slot-scope="scope">-->
          <!--              <el-button @click="TodoDeal(scope.row)" type="text" size="small">审批</el-button>-->
          <!--            </template>-->
          <!--          </el-table-column>-->
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
    ></Todo-deal>
  </div>
</template>

<script>
  import {mapMutations} from 'vuex';
  import TodoDeal from "./TodoDeal";
  import TodoApi from "./api/TodoApi";
  import { getCurrentDateStr } from '@/utils/dateUtil';

  export default {
    name: "HaveTodo",
    data() {
      return {
        currentPage4: 2,
        tableData: [],
        dialogVisible: false,
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
      this.getUnicomFinished();
    },
    methods: {
      ...mapMutations(['TODO_ITEM']),
      indexMethod(index) {
        return index + 1;
      },
      handleSizeChange(val) {
        this.pageConfig.pageNum = 1;
        this.pageConfig.pageSize = val;
        this.getUnicomFinished();
      },
      handleCurrentChange(val) {
        this.pageConfig.pageNum = val;
        this.getUnicomFinished();
      },
      getUnicomFinished() {
        const params = {
          pageNum: this.pageConfig.pageNum,
          pageSize: this.pageConfig.pageSize,
          ...this.$store.state.searchCriteria,
        }
        TodoApi.getUnicomFinished(params).then(result => {
          const {list = [], total = 0, pages = 0} = result;
          this.pageConfig = {
            total,
            pages,
            pageNum: params.pageNum,
            pageSize: params.pageSize,
          };
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
          })
          this.tableData = list;
        }).catch(error => {
          console.log(error);
        })
      },
      TodoDeal(item = {}) {
        const { resourceId = '', taskId = '' } = item;
        TodoApi.getAllInfo({ resourceId, taskId }).then(result => {
          this.dialogVisible = true;
          result.dialogTitile = '已办详情';
          this.TODO_ITEM(result);
        }).catch(error => {
          console.log(error);
        })
      },
      closeDialog() {
        this.dialogVisible = false;
      },
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
        this.getUnicomFinished();
      }
    }
  }
</script>

<style scoped>

</style>
