<template>
  <div>
    <template>
      <div v-if="userInfo.Operator">
        <el-table
          :data="tableData"
          style="width: 100%"
          empty-text="Temporarily no data"
          max-height="400px"
        >
          <el-table-column
            type="index"
            label="ID"
            align="center"
            :index="indexMethod">
          </el-table-column>
          <el-table-column
            prop="resourceName"
            label="Resource Applied"
            align="center"
            width="150">
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="small">{{scope.row.resourceName}}</el-button>
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
            prop="progress"
            align="center"
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

      <div v-if="!this.userInfo.Operator">
        <el-table
          :data="tableData"
          style="width: 100%"
          empty-text="Temporarily no data"
          max-height="400px"
        >
          <el-table-column
            type="index"
            label="ID"
            align="center"
            :index="indexMethod">
          </el-table-column>
          <el-table-column
            prop="resourceName"
            label="Planning base station"
            align="center"
            width="150">
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="small">{{scope.row.resourceName}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="resourceId"
            label="Resource ID"
            align="center"
            width="120">
          </el-table-column>
          <el-table-column
            prop="resourceType"
            align="center"
            label="Resource Type">
          </el-table-column>
          <el-table-column
            prop="startAmount"
            align="center"
            label="Starting amount">
          </el-table-column>
          <el-table-column
            prop="quotationAmount"
            align="center"
            label="Quatation amount(¥)">
          </el-table-column>
          <el-table-column
            prop="isBid"
            align="center"
            label="Final result">
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
    ></Todo-deal>
  </div>
</template>

<script>
  import {mapMutations, mapState} from 'vuex';
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
        },
        IsBid: {
          '0': 'Lose',
          '1': 'Win'
        }
      }
    },
    created() {
      this.getUnicomApply();
    },
    methods: {
      ...mapMutations(['TODO_ITEM']),
      indexMethod(index) {
        return index + 1;
      },
      handleSizeChange(val) {
        this.pageConfig.pageNum = 1;
        this.pageConfig.pageSize = val;
        this.getUnicomApply();
      },
      handleCurrentChange(val) {
        this.pageConfig.pageNum = val;
        this.getUnicomApply();
      },
      getUnicomApply() {
        const params = {
          pageNum: this.pageConfig.pageNum,
          pageSize: this.pageConfig.pageSize,
          ...this.$store.state.searchCriteria,
        }
        // 第三方登录查看详情
        if (!this.userInfo.Operator) {
          params.offeror = this.userInfo.userName;
        }
        TodoApi.getUnicomApply(params).then(result => {
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

            Object.keys(item).forEach(keyItem => {
              if (item[keyItem] != 0 && !item[keyItem]) {
                item[keyItem] = '-';
              }
              if (keyItem == 'isBid') {
                item.isBid = this.IsBid[item.isBid];
              }
            });
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
          result.dialogTitile = 'Detailed information';
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
      ...mapState(['userInfo']),
      search() {
        return this.$store.state.searchCriteria;
      }
    },
    watch: {
      search() {
        this.getUnicomApply();
      }
    }
  }
</script>

<style scoped>

</style>
