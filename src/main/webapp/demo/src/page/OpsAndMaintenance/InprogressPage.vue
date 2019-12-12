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
            label="ID"
            align="center"
            :index="indexMethod">
          </el-table-column>
          <el-table-column
            prop="resourceName"
            label="Resource Name"
            align="center"
            width="150">
            <template slot-scope="scope">
              <el-button @click="TodoDealInfo(scope.row)" type="text" size="small">{{scope.row.resourceName}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="reportContent"
            label="Report Content"
            align="center">
          </el-table-column>
          <el-table-column
            prop="taskId"
            align="center"
            label="Task ID">
          </el-table-column>
          <el-table-column
            prop="areaCode"
            align="center"
            width="120"
            label="Area Code">
          </el-table-column>
          <el-table-column
            prop="feedbackUser"
            align="center"
            label="Feedback User">
          </el-table-column>
          <el-table-column
            prop="resourceType"
            align="center"
            label="Resource Type">
          </el-table-column>
          <el-table-column
            prop="reportingTime"
            align="center"
            label="Reporting Time">
          </el-table-column>
          <el-table-column
            prop="rate"
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
      @upList="fetchList"
    ></Todo-deal>
    <resource-manageMentDialog></resource-manageMentDialog>
  </div>
</template>

<script>
  import Api from "./api/index";
  import { mapState } from 'vuex';
  import { getCurrentDateStr } from '@/utils/dateUtil';
  import TodoDeal from "../Todo/TodoDeal";
  import ResourceManageMentDialog from "../ResourceManagement/ResourceManageMentDialog";

  export default {
    name: "InprogressPage",
    data() {
      return {
        tableData: [],
        pageConfig: {
          pageNum: 1,
          pageSize: 10,
          total: 0,
          pages: 0,
        },
        dialogVisible: false,
      }
    },
    mounted() {
      this.fetchList();
    },
    methods: {
      fetchList() {
        const params = {
          treatmentState: 1,
          pageNum: this.pageConfig.pageNum,
          pageSize: this.pageConfig.pageSize,
          ...this.$store.state.searchCriteria,
        };
        Api.fetchList(params).then(result => {
          const { data = {} } = result;
          const { list = [], pages = 0, total = 0 } = data;
          list.forEach(item => {
            if (item.star == 0 || item.star == '0.0') {
              item.star = '-';
            }
          })
          this.pageConfig = {
            total,
            pages,
            pageNum: params.pageNum,
            pageSize: params.pageSize,
          };
          list.forEach(item => {
            if (item.reportingTime) {
              item.reportingTime = getCurrentDateStr(item.reportingTime);
            }
            Object.keys(item).forEach(keyItem => {
              if (!item[keyItem]) {
                item[keyItem] = '-';
              }
            });
            if (list.length > 0) {
              this.$store.commit('IS_HAS_INPROGRESS', true);
            } else {
              this.$store.commit('IS_HAS_INPROGRESS', false);
            }
          })
          this.tableData = list;
        }).catch(error => {
          console.log(error);
        })
      },

      indexMethod(index) {
        return index + 1;
      },

      TodoDeal(item = {}) {
        const { taskId } = item;
        this.$store.dispatch('getOne', { params: { taskId }, OpsProcess: true });
        this.dialogVisible = true;
      },

      TodoDealInfo(item = {}) {
        this.$store.commit('RESOURCE_DIALOG_VISIBLEADAT', item.resourceId);
      },

      handleSizeChange(val) {
        this.pageConfig.pageNum = 1;
        this.pageConfig.pageSize = val;
        this.fetchList();
      },
      handleCurrentChange(val) {
        this.pageConfig.pageNum = val;
        this.fetchList();
      },

      closeDialog() {
        this.dialogVisible = false;
      },


    },
    computed: {
      ...mapState(['userInfo']),
      search() {
        return this.$store.state.searchCriteria;
      }
    },
    watch: {
      search() {
        this.fetchList();
      },
    },
    components: {
      TodoDeal,
      ResourceManageMentDialog,
    },
  }
</script>

<style scoped>

</style>
