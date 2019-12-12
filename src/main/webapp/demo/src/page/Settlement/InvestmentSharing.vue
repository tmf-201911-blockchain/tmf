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
            align="center"
            label="Resource Name"
          >
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="small">{{scope.row.resourceName}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="resourceType"
            label="Resource Type"
          >
          </el-table-column>
          <el-table-column
            align="center"
            prop="owner"
            label="Owner">
          </el-table-column>
          <el-table-column
            align="center"
            prop="lessee"
            label="Lessee">
          </el-table-column>
          <el-table-column
            align="center"
            prop="settlementDateFormat"
            label="Settlement Date">
          </el-table-column>
          <el-table-column
            prop="ISPRevenue"
            align="center"
            label="CSP Revenue（¥）">
          </el-table-column>
          <el-table-column
            prop="investorRevenue"
            align="center"
            label="Investor Revenue（¥）">
          </el-table-column>
          <el-table-column
            prop="totalRevenue"
            align="center"
            label="Total Revenue（¥）">
          </el-table-column>
        </el-table>
      </div>

      <div v-if="!userInfo.Operator">
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
            align="center"
            label="Resource Name"
          >
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="small">{{scope.row.resourceName}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="resourceType"
            align="center"
            label="Resource Type"
          >
          </el-table-column>
          <el-table-column
            prop="ISP"
            align="center"
            label="ISP">
          </el-table-column>
          <el-table-column
            prop="settlementDate"
            align="center"
            label="Settlement Date">
          </el-table-column>
          <el-table-column
            prop="ISPRevenue"
            align="center"
            label="CSP Revenue（¥）">
          </el-table-column>
          <el-table-column
            prop="investorRevenue"
            align="center"
            label="Investor Revenue（¥）">
          </el-table-column>
          <el-table-column
            prop="totalRevenue"
            label="Total Revenue（¥）">
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
    <resource-manageMentDialog></resource-manageMentDialog>
  </div>
</template>

<script>
  import RentApi from "./api/RentApi";
  import { mapState } from 'vuex';
  import ResourceManageMentDialog from "../ResourceManagement/ResourceManageMentDialog";
  import { getCurrentDateStr } from '@/utils/dateUtil';

  export default {
    name: "InvestmentSharing",
    data() {
      return {
        tableData: [],
        pageConfig: {
          pageNum: 1,
          pageSize: 10,
          total: 0,
          pages: 0,
        },
      }
    },
    computed: {
      ...mapState(['userInfo']),
    },
    components: {
      ResourceManageMentDialog,
    },
    mounted() {
      this.findRentOutSettlement();
    },
    methods: {
      findRentOutSettlement() {
        const params = {
          offeror: this.userInfo.userName,
          pageNum: this.pageConfig.pageNum,
          pageSize: this.pageConfig.pageSize,
          ...this.$store.state.searchCriteria,
        };
        RentApi.getSharing(params).then(result => {
          const { list = [], pages = 0, total = 0, totalActualRate = 0 } = result;
          list.forEach(item => {
            if (item.star == 0 || item.star == '0.0') {
              item.star = '-';
            }
            if (item.settlementDate) {
              item.settlementDate = getCurrentDateStr[item.settlementDate];
            }
            Object.keys(item).forEach(keyItem => {
              if (!item[keyItem]) {
                item[keyItem] = '-';
              }
            });
            if (!item.owner) {
              item.owner = '-';
            }
            if (!item.lessee) {
              item.lessee = '-';
            }
          })
          this.pageConfig = {
            total,
            pages,
            pageNum: params.pageNum,
            pageSize: params.pageSize,
          };
          this.tableData = list;
          this.$store.commit('ACTUALTOTAL', totalActualRate);
        }).catch(error => {
          console.log(error);
        })
      },

      indexMethod(index) {
        return index + 1;
      },

      TodoDeal(item = {}) {
        this.$store.commit('RESOURCE_DIALOG_VISIBLEADAT', item.resourceId);
      },

      handleSizeChange(val) {
        this.pageConfig.pageNum = 1;
        this.pageConfig.pageSize = val;
        this.findRentOutSettlement();
      },
      handleCurrentChange(val) {
        this.pageConfig.pageNum = val;
        this.findRentOutSettlement();
      },
    }
  }
</script>

<style scoped>

</style>
