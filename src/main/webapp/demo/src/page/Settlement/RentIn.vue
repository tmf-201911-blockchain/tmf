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
            align="center"
            label="Resource Name"
            width="150">
            <template slot-scope="scope">
              <el-button @click="TodoDeal(scope.row)" type="text" size="small">{{scope.row.resourceName}}</el-button>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="resourceType"
            label="Resource Type"
            width="120">
          </el-table-column>
          <el-table-column
            align="center"
            prop="owner"
            label="Owner">
          </el-table-column>
          <el-table-column
            prop="status"
            align="center"
            label="Status">
          </el-table-column>
          <el-table-column
            prop="areaCode"
            align="center"
            label="Area Code">
          </el-table-column>
          <el-table-column
            prop="effectiveTime"
            align="center"
            label="Effective Time">
          </el-table-column>
          <el-table-column
            prop="star"
            align="center"
            label="User Rating">
          </el-table-column>
          <el-table-column
            prop="ratePlus"
            align="center"
            label="Expense（¥）">
          </el-table-column>
          <el-table-column
            prop="actualRate"
            align="center"
            label="Actual Expense（¥）">
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

  export default {
    name: "RentIn",
    components: {ResourceManageMentDialog},
    data() {
      return {
        tableData: [],
        pageConfig: {
          pageNum: 1,
          pageSize: 10,
          total: 0,
          pages: 0,
        },
        Status: {
          0: 'Running',
          1: 'Constructing',
          2: 'Planning',
          3: 'Maintaining',
        },
      }
    },
    mounted() {
      this.findRentInSettlement();
    },
    methods: {
      findRentInSettlement() {
        const params = {
          lessee: this.userInfo.userName,
          pageNum: this.pageConfig.pageNum,
          pageSize: this.pageConfig.pageSize,
          ...this.$store.state.searchCriteria,
        };
        RentApi.findRentInSettlement(params).then(result => {
          const { list = [], pages = 0, total = 0, totalActualRate =0 } = result;
          list.forEach(item => {
            if (item.star == 0 || item.star == '0.0') {
              item.star = '-';
            }
            if (item.status) {
              item.status = this.Status[item.status];
            }
            Object.keys(item).forEach(keyItem => {
              if (!item[keyItem]) {
                item[keyItem] = '-';
              }
            });
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
        this.findRentInSettlement();
      },
      handleCurrentChange(val) {
        this.pageConfig.pageNum = val;
        this.findRentInSettlement();
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
        this.findRentInSettlement();
      },
    },
  }
</script>

<style scoped>

</style>
