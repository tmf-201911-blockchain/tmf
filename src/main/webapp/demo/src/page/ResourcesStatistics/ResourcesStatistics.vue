<template>
  <div style="background: rgba(243,246,254,1);overflow: hidden;overflow-y: scroll;height: 100%; width: 100%">
    <!--height: 100%-->
    <div>
      <span style="display: inline-block;padding: 10px;overflow-y: scroll;max-height: 622px">
        <span style="margin-right: 4px;font-size:14px;color:rgba(0,0,0,0.45);margin-left: 25px">current position:</span>
        <span style="font-size:14px;color:rgba(0,0,0,0.65);">Resource Statistics</span>
      </span>
    </div>
    <div style="display: flex;justify-content: space-between;margin-bottom: 24px;background: white;margin-left: 24px;height: 60px;line-height: 44px;">
      <span style="font-size: 16px;color: rgba(0, 0, 0, 0.85);padding: 8px 20px;box-sizing: border-box">Resource Statistics</span>
      <div class="headStyle">
        <span>Resource Type：</span>
        <el-select v-model="resourceTypevalue" placeholder="请选择" @change="resourceTypechange($event)">
          <el-option
            v-for="item in optionsType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </div>
    </div>
    <div style="margin-left: 24px;">
      <!--overflow-y: scroll;max-height: 622px-->
      <div
        style="height: 80px;line-height: 80px;background: rgb(255, 255, 255);padding-left: 24px;box-sizing: border-box">
        <img src="../../assets/pieChart.png" class="pieChart"/>
        <span style="font-size:16px;color:rgba(0,0,0,0.85);margin-right: 19px">Overall Statistics</span>
        <!--<span class="resourceType">Resource Type:</span>-->
        <!--<span style="color:rgba(0,0,0,0.65);">gNB</span>-->
      </div>
      <div style="background:rgba(255,255,255,1);border-radius:2px;">
        <!--width:1136px;height:1038px;-->
        <!--第一部分-->
        <div class="baseFirst">
          <!--第一部分第一个统计图-->
          <div class="baseStation">
            <!--第一部分第一个统计图头部-->
            <div>
              <div class="basestationNum" style="margin-left: 20px">
                <div class="basesnum">{{basesnum}}</div>
                <div class="bases" v-if="BaseStationNum">gNB</div>
                <div class="bases" v-if="TowerNum">Tower</div>
              </div>
              <div class="basestationNum">
                <div class="basesnum">{{ltbasesnum}}</div>
                <div class="bases" v-if="BaseStationNum">CU gNB</div>
                <div class="bases" v-if="TowerNum">CU Tower</div>
              </div>
              <div class="basestationNum">
                <div class="basesnum">{{dxbasesnum}}</div>
                <div class="bases" v-if="BaseStationNum">CT gNB</div>
                <div class="bases" v-if="TowerNum">CT Tower</div>
              </div>
            </div>
            <!--第一部分第一个统计图echarts-->
            <div>
              <div style="width:100%;height:400px" ref="chart"></div>
            </div>
          </div>
          <!--第一部分第二个统计图-->
          <div class="baseMap" style="display: flex">
            <!--第一部分第二个统计图头部-->
            <div class="currentCity">Current City：</div>
            <div class="beijing">Beijing</div>
            <!--第一部分第二个统计图echarts-->
            <div style="width:70%;height:400px" class='mychart' id='mychart' ref="chart1"></div>
          </div>
        </div>
        <!--第二部分-->
        <div class="baseSecond">
          <!--第二部分统计图头部-->
          <div style="display: flex;margin-top: 24px;justify-content: space-between;margin-right: 24px;">
            <div class="using commom">
              <div>
                <span class="bases">Running</span>
                <span class="usecom" style="color:rgba(0,0,0,0.85);">{{getTotalUsing}}</span>
              </div>
              <div
                style="width:10px;height:10px;background:#F26E15;margin-top: 16px;margin-right: 24px;border-radius: 50%;"></div>
            </div>
            <div class="underConstruction commom">
              <div>
                <span class="bases">Constructing</span>
                <span class="usecom">{{getTotalBuilding}}</span>
              </div>
              <div
                style="width:10px;height:10px;background:#07BE1B;margin-top: 16px;margin-right: 24px;border-radius: 50%;"></div>
            </div>
            <div class="planning commom">
              <div>
                <span class="bases">Planning</span>
                <span class="usecom">{{getTotalPlaning}}</span>
              </div>
              <div
                style="width:10px;height:10px;background:#0093FE;margin-top: 16px;margin-right: 24px;border-radius: 50%;"></div>
            </div>
            <div class="inMaintenance commom">
              <div>
                <span class="bases">Maintaining</span>
                <span class="usecom">{{getTotalMaintenance}}</span>
              </div>
              <div
                style="width:10px;height:10px;background:#AEAEAE;margin-top: 16px;margin-right: 24px;border-radius: 50%;"></div>
            </div>
          </div>
          <!--第二部分饼图-->
          <div style="display:flex;">
            <div style="width:45%;height:350px" ref="chart2"></div>
            <div style="width:55%;height:350px" ref="chart22"></div>
          </div>
        </div>
        <div style="background: white;height: 24px;"></div>

      </div>
      <div style="background:rgba(255,255,255,1);border-radius:2px;margin-top: 24px">
        <!--width:1136px;height:901px;-->
        <!--第三部分-->
        <div style="margin-left: 24px;height: 50px;line-height: 50px;font-size:16px;color:rgba(0,0,0,0.85);">
          <img src="../../assets/pieChart.png" class="pieChart"/>
          Infra Sharing Statistics
        </div>
        <div class="baseThree" style="margin-top: 0px;position: relative;">
          <!--第三部分统计图头部-->
          <div>
            <div style="margin-top: 20px;margin-left: 24px;color:rgba(0,0,0,0.85);font-size: 14px">By CSP</div>
            <!--<el-button type="text" @click="CU()" style="margin-left: 24px">CUDetails</el-button>-->
            <!--<el-button type="text" @click="CT()">CTDetails</el-button>-->
          </div>
          <!--第三部分饼图-->
          <div style="margin-left: 20px;">
            <div class="details">
              <el-dialog
                title="By CSP"
                :visible.sync="dialogVisible"
                width="80%"
                :before-close="handleClose">
                <div style="display: flex;margin-top: 20px;margin-bottom: 20px" class="echartsinput">
                  <div class="inputsearch">
                    <span class="inputname">StationName</span>
                    <el-input v-model="input" placeholder="Input here"></el-input>
                  </div>
                  <div class="inputsearch">
                    <span class="inputname">LeaseStatus</span>
                    <el-select v-model="value" placeholder="Choose">
                      <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </div>
                  <div class="inputsearch">
                    <span class="inputname">Status</span>
                    <el-select v-model="valuestatus" placeholder="Choose">
                      <el-option
                        v-for="item in optionsStatus"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </div>
                  <div class="searchBtn inputsearch" @click="inputsearch()">Confirm</div>
                </div>
                <div>
                  <el-table
                    :data="tableData"
                    border
                    style="width: 100%"
                    empty-text="Temporarily no data">
                    <el-table-column
                      type="index"
                      label="ID"
                      :index="indexMethod"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="resourceName"
                      label="StationName"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="owner"
                      label="Owner"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="lessor"
                      label="Lessor">
                    </el-table-column>
                    <el-table-column
                      prop="leaseStatus"
                      label="LeaseStatus">
                    </el-table-column>
                    <el-table-column
                      prop="status"
                      label="Status">
                    </el-table-column>
                    <el-table-column
                      prop="areaCode"
                      label="AreaCode">
                    </el-table-column>
                    <el-table-column
                      prop="effectiveTime"
                      label="EffectiveTime">
                    </el-table-column>
                  </el-table>
                </div>
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
                  :is-show="dialogVisiblee"
                  v-if="dialogVisiblee"
                  @closeDialog="closeDialog"
                ></Todo-deal>
              </el-dialog>
            </div>

            <div class="details2">
              <el-dialog
                title="By CSP"
                :visible.sync="dialogVisible2"
                width="80%"
                :before-close="handleClose2">
                <div style="display: flex;margin-top: 20px;margin-bottom: 20px" class="echartsinput">
                  <div class="inputsearch">
                    <span class="inputname">StationName</span>
                    <el-input v-model="input2" placeholder="Input here"></el-input>
                  </div>
                  <div class="inputsearch">
                    <span class="inputname">LeaseStatus</span>
                    <el-select v-model="value2" placeholder="Choose">
                      <el-option
                        v-for="item in options2"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </div>
                  <div class="inputsearch">
                    <span class="inputname">Status</span>
                    <el-select v-model="valuestatus2" placeholder="Choose">
                      <el-option
                        v-for="item in optionsStatus2"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                      </el-option>
                    </el-select>
                  </div>
                  <div class="searchBtn inputsearch" @click="inputsearch2()">Confirm</div>
                </div>
                <div>
                  <el-table
                    :data="tableData2"
                    border
                    style="width: 100%"
                    empty-text="Temporarily no data">
                    <el-table-column
                      type="index"
                      label="ID"
                      :index="indexMethod2"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="resourceName"
                      label="StationName"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="owner"
                      label="Owner"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="lessor"
                      label="Lessor">
                    </el-table-column>
                    <el-table-column
                      prop="leaseStatus"
                      label="LeaseStatus">
                    </el-table-column>
                    <el-table-column
                      prop="status"
                      label="Status">
                    </el-table-column>
                    <el-table-column
                      prop="areaCode"
                      label="AreaCode">
                    </el-table-column>
                    <el-table-column
                      prop="effectiveTime"
                      label="EffectiveTime">
                    </el-table-column>
                  </el-table>
                </div>
                <div class="block">
                  <el-pagination
                    @size-change="handleSizeChange2"
                    @current-change="handleCurrentChange2"
                    :current-page="pageConfig.pageNum"
                    :page-sizes="[10, 20, 30, 40]"
                    :page-size="10"
                    layout="prev, pager, next, jumper, total, sizes"
                    :total="pageConfig.total">
                  </el-pagination>
                </div>
                <Todo-deal
                  :is-show="dialogVisiblee2"
                  v-if="dialogVisiblee2"
                  @closeDialog="closeDialog2"
                ></Todo-deal>
              </el-dialog>
            </div>

          </div>
          <div style="display:flex;">
            <div style="width:45%;height:350px" ref="chart3"></div>
            <div style="width:55%;height:350px" ref="chart32"></div>
          </div>
        </div>
        <!--第四部分-->
        <div class="baseFour">
          <!--第四部分统计图头部-->
          <div style="display: flex;margin-left: 24px;margin-top: 24px">
            <span style="font-size:14px;color:rgba(0,0,0,0.85);" v-if="BaseStationNum">Monthly Trend: Sharing gNB Numbers</span>
            <span style="font-size:14px;color:rgba(0,0,0,0.85);" v-if="TowerNum">Monthly Trend: Sharing Tower Numbers</span>
            <span style="font-size:14px;color:rgba(0,0,0,0.45);margin-left: 24px">Year:2019</span>
          </div>
          <!--第四部分饼图-->
          <div>
            <div style="width:100%;height:350px" ref="chart4"></div>
          </div>
        </div>
        <div style="background: white;height: 24px;"></div>
      </div>

      <div style="background:rgba(255,255,255,1);border-radius:2px;margin-top: 24px">
        <div style="margin-left: 24px;height: 50px;line-height: 50px;font-size:16px;color:rgba(0,0,0,0.85);">
          <img src="../../assets/pieChart.png" class="pieChart"/>
          Capital source statistics
        </div>
        <div style="display: flex;justify-content: space-between;margin-left: 22px;margin-right: 22px;">
          <div class="CapitalStyle">
            <div>{{externalFunds[0]}}</div>
            <div class="CapitalTitle" v-if="BaseStationNum">BaseStation planned</div>
            <div class="CapitalTitle" v-if="TowerNum">Tower planned</div>
          </div>
          <div class="CapitalStyle">
            <div>{{externalFunds[1]}}</div>
            <div class="CapitalTitle">Completion of bidding</div>
          </div>
          <div class="CapitalStyle">
            <div>¥{{externalFunds[2]}}</div>
            <div class="CapitalTitle">fund raised</div>
          </div>
          <div class="CapitalStyle">
            <div>{{externalFunds[3]}}</div>
            <div class="CapitalTitle">investors</div>
          </div>
        </div>
        <div style="display:flex;">
          <div style="width:45%;height:350px" ref="chart5"></div>
          <div style="width:55%;height:350px" ref="chart55"></div>
        </div>
      </div>


    </div>
  </div>
</template>

<script>
  import api from "./api/api";

  const echarts = require('echarts');
  export default {
    name: "ResourcesStatistics",
    data() {
      return {
        myChart: null,
        myChart2: null,
        myChart22: null,
        myChart3: null,
        myChart32: null,
        myChart4: null,
        myChart5: null,
        myChart55: null,
        echarDataArr: [],
        echarDataArrNum: [],
        echarDataArrName: [],
        basesnum: '',
        ltbasesnum: '',
        dxbasesnum: '',
        unicomPercentage: '',
        getTotalUsing: '',
        getTotalBuilding: '',
        getTotalPlaning: '',
        getTotalMaintenance: '',
        dialogVisible: false,
        dialogVisiblee: false,
        externalFunds: "",
        input: '',
        options: [{
          value: '',
          label: 'All'
        }, {
          value: '0',
          label: 'rent in'
        }, {
          value: '1',
          label: 'applying'
        }, {
          value: '2',
          label: 'rent out'
        }],
        optionsStatus: [{
          value: '',
          label: 'All'
        }, {
          value: '0',
          label: 'Running'
        }, {
          value: '1',
          label: 'Constructing'
        }, {
          value: '2',
          label: 'Planning'
        }, {
          value: '3',
          label: 'Maintaining'
        }],
        value: '',
        valuestatus: '',
        currentPage4: 2,
        tableData: [],
        pageConfig: {
          pageNum: 1,
          pageSize: 10,
          total: 0,
          pages: 0,
        },
        //电信
        dialogVisible2: false,
        dialogVisiblee2: false,
        input2: '',
        value2: '',
        valuestatus2: '',
        options2: [{
          value: '',
          label: 'All'
        }, {
          value: '0',
          label: 'rent in'
        }, {
          value: '1',
          label: 'applying'
        }, {
          value: '2',
          label: 'rent out'
        }],
        optionsStatus2: [{
          value: '',
          label: 'All'
        }, {
          value: '0',
          label: 'Running'
        }, {
          value: '1',
          label: 'Constructing'
        }, {
          value: '2',
          label: 'Planning'
        }, {
          value: '3',
          label: 'Maintaining'
        }],
        tableData2: [],
        pageConfig2: {
          pageNum: 1,
          pageSize: 10,
          total: 0,
          pages: 0,
        },
        status: {
          '0': 'Running',
          '1': 'Constructing',
          '2': 'Planning',
          '3': 'Maintaining',
        },
        leaseStatus: {
          '0': 'rent in',
          '1': 'applying',
          '2': 'rent out',
        },
        optionsType: [{
          value: 'BaseStation',
          label: 'BaseStation'
        }, {
          value: 'Tower',
          label: 'Tower'
        }],
        resourceTypevalue: 'BaseStation',
        BaseStationNum:true,
        TowerNum:false,
        MarTop:false,
      };
    },
    created() {
      this.getUnicomFinished();
      this.rentDetailByCondition();
    },
    methods: {
      //联通接口
      //ID
      indexMethod(index) {
        return index + 1;
      },
      //列表展示
      getUnicomFinished() {
        if (this.value == "" && this.valuestatus == "") {
          const params = {
            pageNum: this.pageConfig.pageNum,
            pageSize: this.pageConfig.pageSize,
            resourceName: this.input,
            // leaseStatus: this.value,
            // status: this.valuestatus,
            ...this.$store.state.searchCriteria,
            resourceType:this.resourceTypevalue

          }
          api.getUnicomRentDetailByCondition(params).then(result => {
            // console.log(result)
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData = list;


          }).catch(error => {
            console.log(error);
          })
        } else {
          const params = {
            pageNum: this.pageConfig.pageNum,
            pageSize: this.pageConfig.pageSize,
            resourceName: this.input,
            leaseStatus: this.value,
            status: this.valuestatus,
            ...this.$store.state.searchCriteria,
            resourceType:this.resourceTypevalue

          }
          api.getUnicomRentDetailByCondition(params).then(result => {
            // console.log(result)
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData = list;


          }).catch(error => {
            console.log(error);
          })
        }

      },
      //列表搜索
      inputsearch() {
        if (this.value == "" && this.valuestatus == "") {
          const params = {
            pageNum: this.pageConfig.pageNum,
            pageSize: this.pageConfig.pageSize,
            resourceName: this.input,
            resourceType:this.resourceTypevalue
          }
          api.getUnicomRentDetailByCondition(params).then(result => {
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData = list;
          }).catch(error => {
            console.log(error);
          })
        } else {
          const params = {
            pageNum: this.pageConfig.pageNum,
            pageSize: this.pageConfig.pageSize,
            resourceName: this.input,
            leaseStatus: this.value,
            status: this.valuestatus,
            resourceType:this.resourceTypevalue
          }
          api.getUnicomRentDetailByCondition(params).then(result => {
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData = list;


          }).catch(error => {
            console.log(error);
          })

        }

      },
      handleClose(done) {
        this.dialogVisible = false;
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
      closeDialog() {
        this.dialogVisiblee = false;
      },


      CU() {
        this.dialogVisible = true;
        this.inputsearch();
      },

      CT() {
        this.dialogVisible2 = true;
        this.inputsearch2();
      },


      //电信接口
      handleClose2(done) {
        this.dialogVisible2 = false;
      },
      indexMethod2(index) {
        return index + 1;
      },
      closeDialog2() {
        this.dialogVisiblee2 = false;
      },
      handleSizeChange2(val) {
        this.pageConfig2.pageNum = 1;
        this.pageConfig2.pageSize = val;
        this.rentDetailByCondition();
      },
      handleCurrentChange2(val) {
        this.pageConfig2.pageNum = val;
        this.rentDetailByCondition();
      },
      inputsearch2() {
        if (this.value2 == "" && this.valuestatus2 == "") {
          const params = {
            pageNum: this.pageConfig2.pageNum,
            pageSize: this.pageConfig2.pageSize,
            resourceName: this.input2,
            resourceType:this.resourceTypevalue
          }
          api.getTelecomRentDetailByCondition(params).then(result => {
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData2 = list;
          }).catch(error => {
            console.log(error);
          })
        } else {
          const params = {
            pageNum: this.pageConfig2.pageNum,
            pageSize: this.pageConfig2.pageSize,
            resourceName: this.input2,
            leaseStatus: this.value2,
            status: this.valuestatus2,
            resourceType:this.resourceTypevalue
          }
          api.getTelecomRentDetailByCondition(params).then(result => {
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData2 = list;
          }).catch(error => {
            console.log(error);
          })
        }
      },
      //列表展示
      rentDetailByCondition() {
        if (this.value2 == "" && this.valuestatus2 == "") {
          const params = {
            pageNum: this.pageConfig2.pageNum,
            pageSize: this.pageConfig2.pageSize,
            resourceName: this.input2,
            ...this.$store.state.searchCriteria,
            resourceType:this.resourceTypevalue
          }
          api.getTelecomRentDetailByCondition(params).then(result => {
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig2 = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData2 = list;
            // console.log(this.tableData2)
          }).catch(error => {
            console.log(error);
          })
        } else {
          const params = {
            pageNum: this.pageConfig2.pageNum,
            pageSize: this.pageConfig2.pageSize,
            resourceName: this.input2,
            leaseStatus: this.value2,
            status: this.valuestatus2,
            ...this.$store.state.searchCriteria,
            resourceType:this.resourceTypevalue
          }
          api.getTelecomRentDetailByCondition(params).then(result => {
            // console.log(result)
            const {list = [], total = 0, pages = 0} = result;
            this.pageConfig2 = {
              total,
              pages,
              pageNum: params.pageNum,
              pageSize: params.pageSize,
            };
            list.forEach(item => {
              if (item.status) {
                item.status = this.status[item.status];
              }
              if (item.leaseStatus) {
                item.leaseStatus = this.leaseStatus[item.leaseStatus];
              }
            })
            this.tableData2 = list;
          }).catch(error => {
            console.log(error);
          })
        }

      },
      initCharts(result) {
        this.myChart = echarts.init(this.$refs.chart);
        // 绘制图表
        let option = {
          tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
          },
          legend: {
            type: 'scroll',
            bottom: 40,
            right: 70,
            data: ['China Telecom', 'China Unicom'],
            // data:['中国电信','中国联通'],
          },
          color: ["#26A6F1", "#00CECD"],
          series: [
            {
              // name: '姓名',
              type: 'pie',
              radius: '55%',
              center: ['50%', '40%'],
              data: result,
              label: {
                normal: {
                  show: true,
                  position: 'inside',
                  // formatter:"{b}:{d}%"
                  formatter: "{d}%"
                },
              },


              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              },
            }
          ]
        };
        this.myChart.setOption(option, true);
      },
      initCharts1(result) {
        // 基于准备好的dom，初始化echarts实例
        let initCharts = this.echarts.init(this.$refs.chart1)
        // let cityArr=[
        //   { name: '延庆区', value: 200, use: 180,build: 12,plan: 8,maintain: 0,lng: 115.981186, lat: 40.462706 },
        //   { name: '怀柔区', value: 300, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.63853, lat: 40.322563 },
        //   { name: '密云区', value: 100, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.849551, lat: 40.382999 },
        //   { name: '昌平区', value: 300, use: 180,build: 12,plan: 8,maintain: 0,lng: 116.237832, lat: 40.226854 },
        //   { name: '顺义区', value: 500, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.663242, lat: 40.1362 },
        //   { name: '平谷区', value: 200, use: 180,build: 12,plan: 8,maintain: 0, lng: 117.128025, lat: 40.147115 },
        //   { name: '门头沟区', value: 100, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.108179, lat: 39.94648 },
        //   { name: '海淀区', value: 50, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.304872, lat: 39.96553 },
        //   { name: '石景山区', value: 30, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.229612, lat: 39.912017 },
        //   { name: '西城区', value: 20, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.372397, lat: 39.918561 },
        //   { name: '东城区', vvalue: 70, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.42272, lat: 39.934579 },
        //   { name: '朝阳区', value: 50, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.449767, lat: 39.927254 },
        //   { name: '通州区', value: 80, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.662928, lat: 39.917001 },
        //   { name: '大兴区', value: 90, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.348053, lat: 39.732833 },
        //   { name: '房山区', value: 30, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.149892, lat: 39.755039 },
        //   { name: '丰台区', value: 10, use: 180,build: 12,plan: 8,maintain: 0, lng: 116.293105, lat: 39.865042 }
        // ]
        let cityArr = result;
        // 监听屏幕变化自动缩放图表
        window.addEventListener('resize', function () {
          initCharts.resize()
        })
        // 绘制图表
        initCharts.setOption({
          // 图表主标题
          title: {
            // 文本
            text: '北京',
            // 值: 'top', 'middle', 'bottom' 也可以是具体的值或者百分比
            top: 25,
            // 值: 'left', 'center', 'right' 同上
            left: 'center',
            // 文本样式
            textStyle: {
              // 字体大小
              fontSize: 25,
              // 字体粗细
              fontWeight: 650,
              // 字体颜色
              color: '#fff'
            }
          },
          // 提示框组件
          tooltip: {
            // 触发类型, 数据项图形触发
            trigger: 'item',
            // 使用函数模板，传入的数据值 ——> value: number | Array
            formatter: function (val) {
              let name=(val.data.name=='延庆区'?'YangQingQu'
                :val.data.name=='怀柔区'?'HuaiRouQu'
                :val.data.name=='密云区'?'MiYunQu'
                :val.data.name=='昌平区'?'ChangPingQu'
                :val.data.name=='顺义区'?'ShunYiQu'
                :val.data.name=='平谷区'?'PingGuQu'
                :val.data.name=='门头沟区'?'MenTouGouQu'
                :val.data.name=='海淀区'?'HaiDianQu'
                :val.data.name=='石景山区'?'ShiJingShanQu'
                :val.data.name=='西城区'?'XiChengQu'
                :val.data.name=='东城区'?'DongChengQu'
                :val.data.name=='朝阳区'?'ChaoYangQu'
                :val.data.name=='通州区'?'TongZhouQu'
                :val.data.name=='大兴区'?'DaXingQu'
                :val.data.name=='房山区'?'FangShanQu'
                :val.data.name=='丰台区'?'FengTaiQu'
                :'');

              return name + '<br>total: ' + val.data.value + '<br>Running: ' + val.data.use + '<br>Constructing: ' + val.data.build + '<br>Planning: ' + val.data.plan + '<br>Maintaining: ' + val.data.maintain
            }
          },
          // 视觉映射组件
          visualMap: {
            // continuous 类型为连续型
            type: 'continuous',
            show: true, // 是否显示 visualMap-continuous 组件 如果设置为 false，不会显示，但是数据映射的功能还存在
            // 指定 visualMapContinuous 组件的允许的最小/大值 min/max 必须用户指定
            min: 0,
            // min,max 形成了视觉映射的定义域
            max: 400,
            // 文本样式
            textStyle: {
              // 字体大小
              fontSize: 15,
              // 字体颜色
              color: '#fff'
            },
            // 拖拽时，是否实时更新
            realtime: false,
            // 是否显示拖拽用的手柄
            calculable: true,
            // 定义在选中范围中的视觉元素

            inRange: {
              // 图元的颜色
              color: ['#D8D8FF', '#A3A3FF', '#5B5AFE', '#3531FE', '#2200F4', '#5475f5']
            }
          },
          dataRange: {
            x: '400',
            y: 'bottom',
            splitList: [
              // {start:100,color:"green"},
              {start: 4, end: 5, label: '4-5', color: '#2200F4'},
              {start: 3, end: 4, label: '3-4', color: '#3531FE'},
              {start: 2, end: 3, label: '2-3', color: '#5B5AFE'},
              {start: 1, end: 2, label: '1-2', color: '#A3A3FF'},
              {start: 0, end: 1, label: '0-1', color: '#D8D8FF'},
              // {end:1,color:'#f00'}
            ],
          },
          series: [
            {
              // 类型
              type: 'map',
              // 系列名称，用于tooltip的显示，legend 的图例筛选 在 setOption 更新数据和配置项时用于指定对应的系列
              map: '北京',
              // 地图类型
              mapType: 'province',
              // 是否开启鼠标缩放和平移漫游 默认不开启
              // 如果只想要开启缩放或者平移，可以设置成 'scale' 或者 'move' 设置成 true 为都开启
              roam: false,
              // 定位 值: 'top', 'middle', 'bottom' 也可以是具体的值或者百分比
              top: 70,
              left: 0,
              // 图形上的文本标签
              label: {
                show: false // 是否显示对应地名
              },
              // 地图区域的多边形 图形样式
              itemStyle: {
                // 地图区域的颜色 如果设置了visualMap, 这个属性将不起作用
                areaColor: '#7B68EE',
                // 描边线宽 为 0 时无描边
                borderWidth: 0.5,
                // 图形的描边颜色 支持的颜色格式同 color
                borderColor: '#000',
                // 描边类型，默认为实线，支持 'solid', 'dashed', 'dotted'
                borderType: 'solid'
              },
              // 高亮状态
              emphasis: {
                // 文本标签
                label: {
                  // 是否显示标签
                  show: true,
                  // 文字的颜色 如果设置为 'auto'，则为视觉映射得到的颜色，如系列色
                  color: '#fff'
                },
                // 图形样式
                itemStyle: {
                  // 地图区域的颜色
                  areaColor: '#7056E6'
                }
              },
              // 地图系列中的数据内容数组，数组项可以为单个数值
              data: cityArr
            }
          ]
        })
      },
      initCharts2(result) {
        this.myChart2 = echarts.init(this.$refs.chart2);
        // 绘制图表
        let option = {
          graphic: [{ //环形图中间添加文字
            type: 'text', //通过不同top值可以设置上下显示
            left: 'center',
            top: '45%',
            style: {
              text: 'CU',//环形中间的文字
              textAlign: 'center',
              fill: 'black', //文字的颜色
              width: 30,
              height: 30,
              fontSize: 20,
              fontFamily: "Microsoft YaHei"
            }
          }],
          tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            right: 0,
            // data:['使用中','在建中','规划中','维护中'],
            data: ['Running', 'Constructing', 'Planning', 'Maintaining'],
            padding: [120, 20, 0, 0]
          },
          color: ['#F26E15', '#07BE1B', '#0093FE', '#AEAEAE'],
          series: [
            {
              // name:'联通',
              type: 'pie',
              radius: ['40%', '60%'],
              center: ['50%','50%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: result
            }
          ]
        };
        this.myChart2.setOption(option, true);
      },
      initCharts22(result) {
        this.myChart22 = echarts.init(this.$refs.chart22);
        // 绘制图表
        let option = {
          graphic: [{ //环形图中间添加文字
            type: 'text', //通过不同top值可以设置上下显示
            left: 'center',
            top: '45%',
            style: {
              text: 'CT',//环形中间的文字
              textAlign: 'center',
              fill: 'black', //文字的颜色
              width: 30,
              height: 30,
              fontSize: 20,
              fontFamily: "Microsoft YaHei"
            }
          }],
          tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            right: 0,
            // data:['使用中','在建中','规划中','维护中'],
            data: ['Running', 'Constructing', 'Planning', 'Maintaining'],
            padding: [100, 70, 0, 0]
          },
          color: ['#F26E15', '#07BE1B', '#0093FE', '#AEAEAE'],
          series: [
            {
              // name:'联通',
              type: 'pie',
              radius: ['40%', '60%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: result
            }
          ]
        };
        this.myChart22.setOption(option, true);
      },
      initCharts3(result) {
        this.myChart3 = echarts.init(this.$refs.chart3);
        // 绘制图表
        let option = {
          tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            right: 5,
            // data:['租入','租出'],
            data: ['RentIn', 'RentOut'],
            padding: [120, 40, 0, 0]
          },
          graphic: [{ //环形图中间添加文字
            type: 'text', //通过不同top值可以设置上下显示
            left: 'center',
            top: '45%',
            style: {
              text: 'CU',//环形中间的文字
              textAlign: 'center',
              fill: 'black', //文字的颜色
              width: 30,
              height: 30,
              fontSize: 20,
              fontFamily: "Microsoft YaHei"
            }
          }],
          color: ['#5069E4', '#98A6ED'],
          series: [
            {
              // name:'联通',
              type: 'pie',
              radius: ['40%', '60%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: result
            }
          ]
        };
        this.myChart3.setOption(option, true);
      },
      initCharts32(result) {
        this.myChart32 = echarts.init(this.$refs.chart32);
        // 绘制图表
        let option = {
          tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            right: 5,
            // data:['租入','租出'],
            data: ['RentIn', 'RentOut'],
            padding: [100, 90, 0, 0]
          },
          graphic: [{ //环形图中间添加文字
            type: 'text', //通过不同top值可以设置上下显示
            left: 'center',
            top: '45%',
            style: {
              text: 'CT',//环形中间的文字
              textAlign: 'center',
              fill: 'black', //文字的颜色
              width: 30,
              height: 30,
              fontSize: 20,
              fontFamily: "Microsoft YaHei"
            }
          }],
          color: ['#5069E4', '#98A6ED'],
          series: [
            {
              // name:'联通',
              type: 'pie',
              radius: ['40%', '60%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: result
            }
          ]
        };
        this.myChart32.setOption(option, true);
      },
      initCharts4(telecomRentMonth, unicomRentMonth) {
        this.myChart4 = echarts.init(this.$refs.chart4);
        // 绘制图表
        let option = {
          tooltip: {},
          // legend:{
          //   data:['销量']
          // },
          legend: {
            orient: 'vertical', /* 设置位置的垂直方向 */
            selectedMode: false,//右上角图例不让点击
            right: 30,
            // data: ['联通', '电信']
            data: ['ChinaUnicom', 'ChinaTelecom'],
          },
          xAxis: {
            data: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            axisLine:{
              lineStyle:{
                color:'#D9D9D9',
              }
            },
            axisLabel: {
              show: true,
              textStyle: {
                color: '#000000'
              }
            }
          },
          yAxis: {
            axisLine:{
              lineStyle:{
                color:'#ffffff',
              }
            },
            axisLabel: {
              show: true,
              textStyle: {
                color: '#000000'
              }
            },
            splitLine :{    //网格线
              lineStyle:{
                type:'dashed'    //设置网格线类型 dotted：虚线   solid:实线
              },
              show:true //隐藏或显示
            }

          },
          color: ['#46D1DE', '#5069E4'],
          series: [
            {
              name: 'ChinaUnicom',
              type: 'bar',
              barGap: 0,
              data: telecomRentMonth
            },
            {
              name: 'ChinaTelecom',
              type: 'bar',
              data: unicomRentMonth
            },

          ]
        };
        this.myChart4.setOption(option, true);
      },
      initCharts5(result) {
        this.myChart5 = echarts.init(this.$refs.chart5);
        // 绘制图表
        let option = {
          graphic: [{ //环形图中间添加文字
            type: 'text', //通过不同top值可以设置上下显示
            left: 'center',
            top: '45%',
            style: {
              // text: 'BaseStation',//环形中间的文字
              textAlign: 'center',
              fill: 'black', //文字的颜色
              width: 30,
              height: 30,
              fontSize: 20,
              fontFamily: "Microsoft YaHei"
            }
          }],
          tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            right: 0,
            data: ['ChinaUnicom', 'ChinaTelecom', 'Investment'],
            padding: [100, 20, 0, 0]
          },
          color: ["#26A6F1", "#00CECD","#FFC246"],
          series: [
            {
              // name:'联通',
              type: 'pie',
              radius: ['40%', '60%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: result
            }
          ]
        };
        this.myChart5.setOption(option, true);
      },
      initCharts55(result) {
        this.myChart55 = echarts.init(this.$refs.chart55);
        // 绘制图表
        let option = {
          graphic: [{ //环形图中间添加文字
            type: 'text', //通过不同top值可以设置上下显示
            left: 'center',
            top: '45%',
            style: {
              // text: 'BaseStation',//环形中间的文字
              textAlign: 'center',
              fill: 'black', //文字的颜色
              width: 30,
              height: 30,
              fontSize: 20,
              fontFamily: "Microsoft YaHei"
            }
          }],
          tooltip: {
            trigger: 'item',
            formatter: "{b}: {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            right: 0,
            // data: ['ChinaUnicom', 'ChinaTelecom', 'Investment'],
            padding: [100, 100, 0, 0]
          },
          color: ['#F26E15', '#07BE1B', '#0093FE', '#46D1DE','#5069E4', '#98A6ED'],
          series: [
            {
              // name:'联通',
              type: 'pie',
              radius: ['40%', '60%'],
              avoidLabelOverlap: false,
              label: {
                normal: {
                  show: false,
                  position: 'center'
                },
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: result
            }
          ]
        };
        this.myChart55.setOption(option, true);
      },

      resourceTypechange(data){
        this.resourceTypevalue=data;
        if(this.resourceTypevalue == "Tower"){
           this.BaseStationNum = false;
           this.TowerNum = true;
        }else{
          this.BaseStationNum = true;
          this.TowerNum = false;
        }
        this.api();
      },
      api() {
        const params = {
          resourceType:this.resourceTypevalue
        }
        //第一个饼图数据
        api.getTotalCount(params).then(result => {
          this.basesnum = result;
        }).catch(error => {
          console.log(error);
        })
        api.getUnicomCount(params).then(result => {
          this.ltbasesnum = result;
        }).catch(error => {
          console.log(error);
        })
        api.getTelecomCount(params).then(result => {
          this.dxbasesnum = result;
        }).catch(error => {
          console.log(error);
        })
        api.getPercent(params).then(result => {

          // result.map(item=>{
          //   if(item.name=="中国电信"){
          //      return item.name="China Telecom"
          //   }else if(item.name=="中国联通"){
          //     return item.name="China Unicom"
          //   }
          // })
          // console.log(result)
          this.initCharts(result);
        }).catch(error => {
          console.log(error);
        })

        //第三个饼图
        api.getTotalUsing(params).then(result => {
          this.getTotalUsing = result;
        }).catch(error => {
          console.log(error);
        })
        api.getTotalBuilding(params).then(result => {
          this.getTotalBuilding = result;
        }).catch(error => {
          console.log(error);
        })
        api.getTotalPlaning(params).then(result => {
          this.getTotalPlaning = result;
        }).catch(error => {
          console.log(error);
        })
        api.getTotalMaintenance(params).then(result => {
          this.getTotalMaintenance = result;
        }).catch(error => {
          console.log(error);
        })
        api.getUnicomPercent(params).then(result => {
          this.initCharts2(result);
        }).catch(error => {
          console.log(error);
        })
        //第四个饼图
        api.getTelecomPercent(params).then(result => {
          this.initCharts22(result);
        }).catch(error => {
          console.log(error);
        })
        //第五个饼图
        api.getUnicomRent(params).then(result => {
          this.initCharts3(result);
        }).catch(error => {
          console.log(error);
        })
        //第六个饼图
        api.getTelecomRent(params).then(result => {
          this.initCharts32(result);
        }).catch(error => {
          console.log(error);
        })
        //第七个饼图
        api.getRentMonth(params).then(result => {
          this.initCharts4(result[0].value, result[1].value);
        }).catch(error => {
          console.log(error);
        })

        api.getMapCount(params).then(result => {
          this.initCharts1(result);
        }).catch(error => {
          console.log(error);
        })
        //最后饼图
        api.getInvestorCount(params).then(result => {
          this.externalFunds = result;
        }).catch(error => {
          console.log(error);
        })
        api.getPlanStation(params).then(result => {
          this.initCharts5(result);
        }).catch(error => {
          console.log(error);
        })
        api.getInvestorCost(params).then(result => {
          this.initCharts55(result);
        }).catch(error => {
          console.log(error);
        })

      }
    },
    mounted() {
      this.initCharts();
      this.initCharts1();
      this.initCharts2();
      this.initCharts22();
      this.initCharts3();
      this.initCharts32();
      this.initCharts4();
      this.initCharts5();
      this.initCharts55();
      this.api();
    },
    computed: {
      search() {
        return this.$store.state.searchCriteria;
      }
    },
    watch: {
      search() {
        this.getUnicomFinished();
        this.rentDetailByCondition();
      }
    }
  }
</script>
<style lang="less" scoped>
  .baseFirst {
    display: flex;
    margin-left: 24px;
  }

  .baseStation, .baseMap {
    /*width: 400px;*/
    /*height: 500px;*/
    /*border:solid 1px #bebebe;*/
    width: 396px;
    height: 492px;
    background: rgba(255, 255, 255, 1);
    border-radius: 2px;
    border: 1px solid rgba(232, 232, 232, 1);
    width: 35%;
  }

  .bases {
    color: #bebebe;
    font-size: 14px;
  }

  .basesnum {
    margin-top: 20px;
  }

  .basestationNum {
    /*width: 120px;*/
    /*height: 90px;*/
    /*text-align: center;*/
    margin-right: 8px;
    margin-top: 24px;
    text-align: center;
    width: 108px;
    height: 74px;
    background: rgba(255, 255, 255, 1);
    border-radius: 2px;
    border: 1px solid rgba(232, 232, 232, 1);
  }

  .basestationNum, .commom {
    border: 1px solid rgba(232, 232, 232, 1);
    display: inline-block;
  }

  .baseMap {
    margin-left: 30px;
    width: 60%;
    height: 492px;
    background: rgba(255, 255, 255, 1);
    border-radius: 2px;
    border: 1px solid rgba(232, 232, 232, 1);
  }

  .baseSecond {
    /*width: 1088px;*/
    /*height: 438px;*/
    /*border:solid 1px #bebebe;*/
    margin-left: 24px;
    margin-top: 24px;
    background: rgba(255, 255, 255, 1);
    border-radius: 2px;
    border: 1px solid rgba(232, 232, 232, 1);
    width: 96%;
  }

  .baseThree {
    /*width:1088px;*/
    /*height:384px;*/
    background: rgba(255, 255, 255, 1);
    border-radius: 2px;
    border: 1px solid rgba(232, 232, 232, 1);
    margin-left: 24px;
    width: 96%;
  }

  .baseFour {
    /*width:1088px;*/
    /*height:458px;*/
    background: rgba(255, 255, 255, 1);
    border-radius: 2px;
    border: 1px solid rgba(232, 232, 232, 1);
    margin-left: 24px;
    margin-top: 24px;
    width: 96%;
  }

  .commom {
    display: flex;
    justify-content: space-between;
    width: 242px;
    height: 88px;
    margin-left: 22px;
  }

  .commom span {
    display: block;
    /*text-align: center;*/
    margin-top: 16px;
    margin-left: 24px;
  }

  .resourceType {
    display: inline-block;
    margin-right: 5px;
    color: #bebebe;
    font-size: 14px;
    color: rgba(0, 0, 0, 0.65);
  }

  .currentCity {
    margin-top: 24px;
    margin-left: 20px;
    font-size: 14px;
    color: rgba(0, 0, 0, 0.65);
  }

  .beijing {
    margin-top: 24px;
    margin-left: 10px;
    font-size: 14px;
    color: rgba(0, 0, 0, 0.65);
  }

  /*.commom{*/
  /*padding: 15px 40px;*/
  /*margin-left: 40px;*/
  /*margin-right: 35px;*/
  /*margin-top: 20px;*/
  /*}*/
  .searchBtn {
    padding: 0px 20px;
    border: solid 1px #ccc;
    background: rgba(237, 236, 239, 1);
    font-size: 13px;
    cursor: pointer;
    background: rgba(80, 105, 228, 1);
    border-radius: 2px;
    color: rgba(255, 255, 255, 1);
  }

  .inputsearch {
    height: 30px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    margin-right: 20px;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
  }

  .inputname {
    color: #666666;
    font-size: 13px;
    margin-right: 10px;
    word-break: keep-all;
  }
  .pieChart{
    width: 21px;
    height: 21px;
    margin-right: 3px;
  }
</style>
<style>
  .baseThree .details .el-button--text span {
    position: absolute;
  }

  .baseThree .details2 .el-button--text span {
    position: absolute;
  }
  .CapitalStyle{
    width: 20%;
    height: 88px;
    border: 1px solid #e8e8e8;
    text-align: center;
  }
  .CapitalTitle{
    color: #bebebe;
  }
  .CapitalStyle div{
    margin-top: 16px;
    margin-left: 24px;
  }
  .headStyle{
    margin-right: 25px;
    margin-top: 7px;
  }
  .headStyle .el-input__inner{
    width: 130px;
  }
</style>
