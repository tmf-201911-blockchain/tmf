<template>
  <div style="position: relative;height: 100%">
    <div style="background: white;height: 100%">
      <div id="allmap" class="baidumap" style="height: 100%;"></div>
    </div>
    <div class="basestationName">
      <a-dropdown class="dropdown-btn">
        <a-menu slot="overlay" @click="handleMenuClick">
          <a-menu-item
            v-for="(item, index) in resourceTypeList"
            :key="index"
          >
            {{ item }}
          </a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px">{{ resourceTypeName }}
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
      <input type="text" placeholder="Search resource name" class="basestationInput" @focus="focusfns"
             v-model="basestationInput" @keyup.enter="search()"/>
      <div class="cleanInput" v-if="clearImgInput" @click="clearclick">
        <img src="../../assets/clear.png" class="clearImg"/>
      </div>
      <div class="serch" @click="search()">
        <svg t="1572247110719" class="icon searchImg" viewBox="0 0 1024 1024" version="1.1"
             xmlns="http://www.w3.org/2000/svg" p-id="1020" width="200" height="200">
          <path
            d="M1004.7744 1004.7488a65.6384 65.6384 0 0 1-92.8768 0l-177.664-177.6128c-76.7488 57.4464-171.008 92.672-274.2784 92.672A459.9296 459.9296 0 0 1 0.0256 459.9808a459.9296 459.9296 0 1 1 919.808 0c0 103.2192-35.2256 197.4784-92.672 274.3296l177.664 177.5616c25.6 25.6512 25.6 67.2768 0 92.928zM460.8256 136.5504a324.2496 324.2496 0 1 0 0.1024 648.4992 324.2496 324.2496 0 0 0-0.1024-648.4992z"
            p-id="1021" fill="#ffffff"></path>
        </svg>
      </div>
    </div>
    <!--地图上的四个按钮代表的意思标注-->
    <div class="mapicon">
      <div style="display: flex;margin-bottom: 6px;margin-left: 16px;margin-top: 12px;">
        <div
          style="width:8px;height:8px;background:#0093FE;border-radius: 50%;margin-top: 5px;margin-right: 5px;"></div>
        <div class="mapiconTitle">Planning</div>
      </div>
      <div style="display: flex;margin-bottom: 6px;margin-left: 16px">
        <div
          style="width:8px;height:8px;background:#07BE1B;border-radius: 50%;margin-top: 5px;margin-right: 5px;"></div>
        <div class="mapiconTitle">Constructing</div>
      </div>
      <div style="display: flex;margin-bottom: 6px;margin-left: 16px">
        <div
          style="width:8px;height:8px;background:#FF8E00;border-radius: 50%;margin-top: 5px;margin-right: 5px;"></div>
        <div class="mapiconTitle">Running</div>
      </div>
      <div style="display: flex;margin-bottom: 15px;margin-left: 16px">
        <div
          style="width:8px;height:8px;background:#AEAEAE;border-radius: 50%;margin-top: 5px;margin-right: 5px;"></div>
        <div class="mapiconTitle">Maintaining</div>
      </div>
    </div>
    <div class="basestationContent" v-show="show">
      <div class="selectwhatType">
         <span style="text-align: center;" @click="typeclick('Planning')">
          <img src="../../assets/5G_plan.png" class="fivegImg" v-if="resourceTypeName=='BaseStation'"/>
          <img src="../../assets/tower_plan.png" class="fivegImg" v-if="resourceTypeName=='Tower'"/>
           <div class="mapiconTitle">Planning</div>
         </span>
        <span style="text-align: center;" @click="typeclick('Constructing')">
          <img src="../../assets/5G_build.png" class="fivegImg" v-if="resourceTypeName=='BaseStation'"/>
          <img src="../../assets/tower_build.png" class="fivegImg" v-if="resourceTypeName=='Tower'"/>
           <div class="mapiconTitle">Constructing</div>
         </span>
        <span style="text-align: center;" @click="typeclick('Running')">
           <img src="../../assets/5G_use.png" class="fivegImg" v-if="resourceTypeName=='BaseStation'"/>
           <img src="../../assets/tower_use.png" class="fivegImg" v-if="resourceTypeName=='Tower'"/>
           <div class="mapiconTitle">Running</div>
         </span>
        <span style="text-align: center;" @click="typeclick('Maintaining')">
           <img src="../../assets/5G_maintain.png" class="fivegImg" v-if="resourceTypeName=='BaseStation'"/>
           <img src="../../assets/tower_maintain.png" class="fivegImg" v-if="resourceTypeName=='Tower'"/>
           <div class="mapiconTitle">Maintaining</div>
         </span>
      </div>
      <div class="searchRecord" style="color:rgba(87,87,87,1);">
        <span>Search History</span>
        <span @click="deleteAllInfo()">
           <img src="../../assets/clean.png"
                style="width:14px;height:14px;float: left;margin-top: 8px;margin-right: 3px;"/>
           <span>Clean</span>
         </span>
      </div>
      <div class="serRecord">
        <div v-for="item in getNewInfo" @click="searchRecond(item)" class="hoverSeacsh">
          <div>
            <img src="../../assets/serRecond.png" class="recongImg"/>
            <span style="color:rgba(87,87,87,1);">{{item.info}}</span>
          </div>
        </div>
      </div>
      <div v-if="dataEdit" style="text-align: center;padding: 10px;color: rgba(0,0,0,0.65);font-size: 12px;">noData
      </div>
    </div>
    <div class="basestationContent1" v-show="show1">
      <div v-for="item in clicktypeInfo" @click="clickType(item)" class="clicktypeItem">
        <div style="display: flex">
          <div>
            <img src="../../assets/5G_use.png" class="fivegImg"
                 v-if="resourceTypeName=='BaseStation' && item.status==0"/>
            <img src="../../assets/tower_use.png" class="fivegImg" v-if="resourceTypeName=='Tower' && item.status==0"/>

            <img src="../../assets/5G_build.png" class="fivegImg"
                 v-if="resourceTypeName=='BaseStation' && item.status==1"/>
            <img src="../../assets/tower_build.png" class="fivegImg"
                 v-if="resourceTypeName=='Tower' && item.status==1"/>

            <img src="../../assets/5G_plan.png" class="fivegImg"
                 v-if="resourceTypeName=='BaseStation' && item.status==2"/>
            <img src="../../assets/tower_plan.png" class="fivegImg" v-if="resourceTypeName=='Tower' && item.status==2"/>

            <img src="../../assets/5G_maintain.png" class="fivegImg"
                 v-if="resourceTypeName=='BaseStation' && item.status==3"/>
            <img src="../../assets/tower_maintain.png" class="fivegImg"
                 v-if="resourceTypeName=='Tower' && item.status==3"/>
          </div>
          <div style="margin-left: 11px;margin-right: 5px;word-break: break-all;">
            <div style="margin-bottom: 6px;color: rgba(0,0,0,1);font-size: 16px;">
              {{item.resourceName}}
            </div>
            <div style="margin-bottom: 6px;color: rgba(0,0,0,0.65);font-size: 12px;">
              {{item.resourceId}}
            </div>
            <div style="margin-bottom: 6px;color:rgba(0,0,0,0.85);;font-size: 14px;">
              {{item.address}}
            </div>
          </div>
        </div>
        <div style="word-break:keep-all;">
          <span style="border:solid 1px;border-radius: 10px;padding:0px 5px;box-sizing: border-box"
                :style="{'color':(item.isp=='CU'?'#E5231E':item.isp=='CT'?'#3166E7':''),'border-color':(item.isp=='CU'?'#E5231E':item.isp=='CT'?'#3166E7':'')}">{{item.isp}}</span>
        </div>
        <!--:style="{'color':(item.isp=='ChinaUnicom'?'#E5231E':item.isp=='ChinaTelecom'?'#3166E7':''),'border-color':(item.isp=='ChinaUnicom'?'#E5231E':item.isp=='ChinaTelecom'?'#3166E7':'')}">{{item.isp}}</span>-->
      </div>
      <div v-if="nodata" style="text-align: center;padding: 10px;color: rgba(0,0,0,0.65);font-size: 12px;">noData</div>
    </div>
    <!--资源发布-->
    <div class="add" @click="selectType=true" v-if="userNameYes">
      <svg t="1572250001099" class="icon addImg" viewBox="0 0 1024 1024" version="1.1"
           xmlns="http://www.w3.org/2000/svg" p-id="1690" width="200" height="200">
        <path
          d="M1004.945512 6.973211a42.400708 42.400708 0 0 0-46.592042 0L28.462084 468.994717c-18.519849 0-28.462084 18.519849-28.462084 36.990962s9.406134 36.942226 18.519849 36.942226l223.407868 139.240025c18.568586 11.599274 42.888072 7.700358 56.875432-9.113715l493.212831-443.306711 18.51985 9.113716-447.790464 471.720058a34.115512 34.115512 0 0 0-9.064979 28.413348v203.133506c-0.292419 19.640788 10.770755 37.673273 28.413348 46.299624a40.938614 40.938614 0 0 0 46.299623-9.357398l111.75267-113.068554 223.456604 148.061322c7.602886 7.164258 18.081221 10.527072 28.462085 9.064979h18.471113c18.763532-9.064979 28.413348-18.471113 28.413348-36.942226L1023.465361 62.38655c0-28.413348 0-46.007205-18.519849-55.413339z"
          p-id="1691" fill="#ffffff"></path>
      </svg>
    </div>

    <div class="lulu">
      <el-dialog
        :visible.sync="dialogVisible"
        width="80%"
        title="Resource publishing"
        :before-close="handleClose">
        <div style="overflow-x: hidden;overflow-y: scroll;margin-top: 18px;margin-left: 34px;">
          <div class="resourceRelease">
            <div class="information">
              <!--基础信息-->
              <div class="basicInformation">
                <span class="basicHeader">Basic Info</span>
                <div class="bmation">
                  <div class="item-row ">
                    <label for="typeResourceType" class="bmationName" style="width: 34%;">ResourceType</label>
                    <select required id="typeResourceType" v-model="resourceType" @change="resourceTypechange($event)">
                      <option value="" selected disabled>Choose</option>
                      <option value="BaseStation">BaseStation</option>
                      <option value="Tower">Tower</option>
                    </select>
                  </div>
                  <span class="item-row flexPos">
                    <span class="bmationName">ResourceName</span>
                    <input type="text" placeholder="Input here" class="inputs" v-model="resourceName"/>
                </span>
                  <span class="item-row posFlex">
              <span class="bmationName4">ResourceId</span>
              <input type="text" placeholder="Input here" class="inputs" v-model="resourceId"/>
              </span>
                </div>
                <div class="bmation">
                  <div class="item-row">
                    <label for="typeStatus" class="bmationName" style="width: 22%">Status</label>
                    <select required id="typeStatus" v-model="status" @change="capitalchange($event)">
                      <option value="" selected disabled>Choose</option>
                      <option value="0">Running</option>
                      <option value="2">Planning</option>
                      <option value="1">Constructing</option>
                      <option value="3">Maintaining</option>
                    </select>
                  </div>
                  <div class="item-row flexPos">
                    <label for="typeImportance" class="bmationName" style="width: 30%">Importance</label>
                    <select required id="typeImportance" v-model="importance">
                      <option value="" selected disabled>Choose</option>
                      <option value="0">High</option>
                      <option value="1">Middle</option>
                      <option value="2">Low</option>
                    </select>
                  </div>
                  <span class="item-row posFlex">
                      <span class="bmationName3" style="width: 23%">Owner</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="owner" :disabled="disabled=true" style="background: #ebebeb"/>
                  </span>


                </div>
                <div class="bmation">
                  <span class="item-row">
                       <span class="bmationName2" style="width: 23%">Rate</span>
                       <input type="text" placeholder="Input here" class="inputs" v-model="rate"/>
                  </span>

                  <span class="item-row flexPos" v-if="towerIdhidden">
                        <span class="bmationName3">TowerId</span>
                        <input type="text" placeholder="Input here" class="inputs" v-model="towerId"/>
                   </span>

                  <span class="item-row posFlex" v-if="towerIdhidden">
                        <span class="bmationName" style="width: 18%">NEP</span>
                        <input type="text" placeholder="Input here" class="inputs" v-model="nep"/>
                  </span>


                </div>
                <div class="bmation" v-if="capitalCost">
                  <span class="item-row">
                       <span class="bmationName">Startingamount</span>
                       <input type="text" placeholder="Input here" class="inputs" v-model="capital"/>
                  </span>
                  <span class="item-row flexPos timeSelect">
                       <span class="bmationName5">Quotationdeadline</span>
                       <!--<input type="text" placeholder="Input here" class="inputs" v-model="deadline"/>-->
                    <el-date-picker
                      v-model="deadline"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      type="datetime"
                      placeholder="Choose"
                      default-time="12:00:00">
                  </el-date-picker>
                  </span>

                </div>

              </div>
              <!--位置信息-->
              <div class="locationInformation">
                <span
                  style="margin-bottom: 20px;display: block;padding-left: 7px;border-left: 3px solid rgb(80, 105, 228);color:rgba(0,0,0,0.85);font-size: 14px;">Position Info</span>
                <div class="bmationScond">
                  <span class="item-row">
                    <span class="bmationNameRes" style="width: 32%;">Longitude</span>
                    <div style="position: relative">
                      <input type="text" placeholder="Input here" class="inputs" v-model="longitude"/>
                      <div @click="btnIng('add')" type="button" class="selectLaandlo" style="-webkit-appearance:none;position: absolute;right: 5px;top: 0px;">
                        <!--Choose-->
                        <img src="../../assets/position.png" style="width: 21px;height: 21px"/>
                      </div>
                    </div>
                  </span>
                  <span class="item-row">
                      <span class="bmationNameRes1" style="width: 29%">Latitude</span>
                      <div style="position: relative">
                        <input type="text" placeholder="Input here" class="inputs" v-model="latitude"/>
                        <div @click="btnIng('add')" type="button" class="selectLaandlo" style="-webkit-appearance:none;position: absolute;right: 5px;top: 0px;">
                        <!--Choose-->
                        <img src="../../assets/position.png" style="width: 21px;height: 21px"/>
                      </div>
                      </div>

                   </span>
                </div>
                <div class="bmationScond1">
            <span class="item-row">
              <span class="bmationName" style="width: 28%">AreaCode</span>
              <input type="text" placeholder="Input here" class="inputs" v-model="areaCode"/>
            </span>
                  <span class="item-row">
              <span class="bmationName2" style="width: 29%">Address</span>
              <input type="text" placeholder="Input here" class="inputs" v-model="address"/>
            </span>
                </div>
              </div>
              <!--资源照片-->
              <div class="resourcePhoto">
                <span
                  style="margin-bottom: 20px;display: block;padding-left: 7px;border-left: 3px solid rgb(80, 105, 228);color:rgba(0,0,0,0.85);font-size: 14px;">Resource photo</span>
                <el-upload ref="upload"
                           action="http://122.192.9.52:20205/tmf/upload/getFile"
                           list-type="picture-card"
                           :multiple="true"
                           :auto-upload="true"
                           name="files"
                           :on-success="handlesuccess"
                           :on-remove="handleRemove">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisibleurl">
                  <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
              </div>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handOk()">Confirm</el-button>
        <el-button @click="handleClose()">Cancel</el-button>
        </span>
      </el-dialog>
    </div>
    <!--资源详情-->
    <div class="lulu">
      <el-dialog
        :visible.sync="dialogVisibledat"
        v-if='dialogVisibledat'
        width="80%"
        title="Resource details"
        :before-close="handleClosedat">
        <div style="overflow-x: hidden;overflow-y: scroll;margin-left: 40px;margin-top: 28px">
          <div class="resourceDetails">
            <div>
              <div style="display: flex">
                <div class="detailsPhoto">
                  <img v-if="imgUrl" :src="imgUrl" style="width: 100%;height: 100%"/>
                  <img src="../../assets/photo@2x.png" v-if="!imgUrl" style="width: 100%;height: 100%;"/>
                </div>
                <div class="detailsMiddle">
                  <div style="display: flex;justify-content: space-between;">
                    <div>
                      <span style="font-size:24px;color:rgba(0,0,0,1);">{{resdetails.resourceName}}</span>
                      <span style="margin-left: 14px;margin-top: 8px;color:rgba(0,0,0,0.45);font-size: 14px">(ResourceId：{{resdetails.resourceId}})</span>
                    </div>
                    <div style="text-align: center">
                      <span class="point" v-if="resdetails.status==0"></span>
                      <span class="pointg" v-if="resdetails.status==1"></span>
                      <span class="pointb" v-if="resdetails.status==2"></span>
                      <span class="pointy" v-if="resdetails.status==3"></span>
                      <span class="usetype">{{statusMap[resdetails.status]}}</span>
                    </div>
                  </div>
                  <div style="margin-top: 7px;color: rgba(0, 0, 0, 0.45);font-size: 15px;">
                    <span>admin：{{resdetails.isp}}</span>
                  </div>
                  <div style="margin-top: 7px;display: flex;justify-content: space-between;">
                    <div style="font-size: 14px;color:rgba(0,0,0,0.45)">Address：{{resdetails.address}}</div>
                    <div v-if="dayResource">
                      <div v-model="quotation" v-if="deadlineTime">{{resdetails.quotationDeadline | formatDate}}</div>
                      <p v-if="timeBtn"><span style="font-size:12px;color:rgba(59,70,100,1);">Winning countdown : </span><span style="width:56px;height:22px;background:rgba(0,0,0,1);line-height: 22px;color: rgb(255, 255, 255);display: inline-block;text-align: center;">{{day}}days</span> : <span style="width:28px;height:22px;background:rgba(0,0,0,1);line-height: 22px;color:rgba(255,255,255,1);display: inline-block;text-align: center;">{{hour}}</span> : <span style="width:28px;height:22px;background:rgba(0,0,0,1);line-height: 22px;color:rgba(255,255,255,1);display: inline-block;text-align: center;">{{min}}</span> : <span style="width:28px;height:22px;background:rgba(0,0,0,1);line-height: 22px;color:rgba(255,255,255,1);display: inline-block;text-align: center;">{{second}}</span></p>
                      <div class="Invested" v-if="investedBtn">Invested</div>
                    </div>
                  </div>
                  <div style="display: flex;justify-content: space-between;margin-top: 41px;">
                    <div class="typestyleN" v-if="startingAmount">
                      <div class="typeStyle" style="color: red;">¥{{resdetails.investmentCost}}</div>
                      <div class="usetype">Starting amount</div>
                    </div>
                    <div class="typestyleN">
                      <div class="typeStyle">{{resdetails.resourceType}}</div>
                      <div class="usetype">ResourceType</div>
                    </div>
                    <div class="typestyleN">
                      <div v-if="resdetails.importance==0" class="typeStyle">High</div>
                      <div v-if="resdetails.importance==1" class="typeStyle">Middle</div>
                      <div v-if="resdetails.importance==2" class="typeStyle">Low</div>
                      <div class="usetype">Importance</div>
                    </div>
                    <div class="typestyleN">
                      <div class="typeStyle">{{resdetails.areaCode}}</div>
                      <div class="usetype">AreaCode</div>
                    </div>
                    <div class="typestyleN">
                      <div class="typeStyle">{{resdetails.longitude}}</div>
                      <div class="usetype">Longitude</div>
                    </div>
                    <div class="typestyleN">
                      <div class="typeStyle">{{resdetails.latitude}}</div>
                      <div class="usetype">Latitude</div>
                    </div>
                  </div>
                </div>
              </div>

            </div>
            <div style="border-bottom: solid 1px #E9E9E9;width: 96%;">
              <ul class="tabs">
                <li class="li-tab" v-for="(item,index) in tabsParam" v-if="(!(index==2&&resdetails.status==2)) && (!(index==3&&resdetails.status!=2))"
                    @click="toggleTabs(index)"
                    :class="{active:index==nowIndex}">{{item}}
                </li>
              </ul>
            </div>
            <div class="divTab" v-show="nowIndex===0">
              <div style="border-bottom: solid 1px #E9E9E9">
                <div class="infoStyle">Basic Info</div>
                <div class="basicInfor" style="margin-top: 26px">
                  <div>
                    <span class="colorStyle">ResourceName：</span>
                    <span class="titlecolorStyle">{{resdetails.resourceName}}</span>
                  </div>
                  <div>
                    <span class="colorStyle">ResourceId：</span>
                    <span class="titlecolorStyle">{{resdetails.resourceId}}</span>
                  </div>
                  <div>
                    <span class="colorStyle">ResourceType：</span>
                    <span class="titlecolorStyle">{{resdetails.resourceType}}</span>
                  </div>
                </div>
                <div class="basicInfor" style="margin-top: 26px">
                  <div>
                    <span class="colorStyle">Status：</span>
                    <span v-if="resdetails.status==0" class="titlecolorStyle">Running</span>
                    <span v-if="resdetails.status==1" class="titlecolorStyle">Constructing</span>
                    <span v-if="resdetails.status==2" class="titlecolorStyle">Planning</span>
                    <span v-if="resdetails.status==3" class="titlecolorStyle">Maintaining</span>
                  </div>
                  <div>
                    <span class="colorStyle">Importance：</span>
                    <span v-if="resdetails.importance==0" class="titlecolorStyle">High</span>
                    <span v-if="resdetails.importance==1" class="titlecolorStyle">Middle</span>
                    <span v-if="resdetails.importance==2" class="titlecolorStyle">Low</span>
                  </div>
                  <div>
                    <span class="colorStyle">Rate：</span>
                    <span class="titlecolorStyle">{{resdetails.rate}}</span>
                  </div>
                </div>
                <div class="basicInfor" style="margin-top: 26px">
                  <div>
                    <span class="colorStyle">Owner：</span>
                    <span class="titlecolorStyle">{{resdetails.owner?resdetails.owner:'-'}}</span>
                  </div>
                  <div>
                    <span class="colorStyle">Lessor：</span>
                    <span class="titlecolorStyle">{{resdetails.lessor?resdetails.lessor:'-'}}</span>
                  </div>
                  <div>
                    <span class="colorStyle">Lessee：</span>
                    <span class="titlecolorStyle">{{resdetails.lessee?resdetails.lessee:'-'}}</span>
                  </div>
                </div>
                <div class="basicInfor" style="margin-top: 26px">
                  <div>
                    <span class="colorStyle">EffectiveTime：</span>
                    <span class="titlecolorStyle">{{resdetails.effectiveTime?resdetails.effectiveTime:'-'}}</span>
                  </div>
                  <div v-if="towerTowerId">
                    <span class="colorStyle">TowerId：</span>
                    <span class="titlecolorStyle">{{resdetails.towerId?resdetails.towerId:'-'}}</span>
                  </div>
                  <div v-if="towerNep">
                    <span class="colorStyle">NEP：</span>
                    <span class="titlecolorStyle">{{resdetails.nep?resdetails.nep:'-'}}</span>
                  </div>

                </div>
              </div>
              <div>
                <div class="infoStyle">Position Info</div>
                <div class="basicInfor" style="margin-top: 20px">
                  <div>
                    <span class="colorStyle">Longitude：</span>
                    <span class="titlecolorStyle">{{resdetails.longitude}}°E</span>
                  </div>
                  <div>
                    <span class="colorStyle">Latitude：</span>
                    <span class="titlecolorStyle">{{resdetails.latitude}}°N</span>
                  </div>
                  <div>
                    <span class="colorStyle">AreaCode：</span>
                    <span class="titlecolorStyle">{{resdetails.areaCode}}</span>
                  </div>
                </div>
                <div class="basicInfor">
                  <div style="width: 100%">
                    <span class="colorStyle" style="width: 122px">Address：</span>
                    <span class="titlecolorStyle">{{resdetails.address}}</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="divTab" v-show="nowIndex===1" style="margin-top: 30px">
              <div class="basicphoto" v-for="item in imgUrlArray">
                <img :src="'http://122.192.9.52:20205'+item" style="width: 100%;height: 100%"/>
              </div>
              <div style="margin-top: 30px;text-align: center" v-if="noPictures">No pictures at this time</div>
              <!--<img src="../../assets/logo5g.png" v-if="!imgUrlArray.length" style="width: 187px;height: 160px;"/>-->
            </div>
            <div class="divTab" v-show="nowIndex===2">
               <div style="display: flex;margin-top: 30px;" v-for="item in reportRes">
                 <div>
                   <img src="../../assets/avatar.png" class="avatar"/>
                 </div>
                 <div style="margin-left: 20px;border-bottom: solid 1px #E9E9E9;width: 85%;">
                   <div style="display: flex;justify-content: space-between">
                     <div class="avatarTelephone">{{item.phone}}</div>
                     <div class="avatarInprogress" v-if="item.treatmentState=='1'">
                       <span class="avatarInprogressTitle">Inprogress</span>
                     </div>
                     <div class="avatarSolved" v-if="item.treatmentState=='2'">
                       <span class="avatarSolvedTitle">Solved</span>
                     </div>
                   </div>
                   <div v-if="item.star=='1'">
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                   </div>
                   <div v-if="item.star=='2'">
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                   </div>
                   <div v-if="item.star=='3'">
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                   </div>
                   <div v-if="item.star=='4'">
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/starGray.png" class="starGray"/>
                   </div>
                   <div v-if="item.star=='5'">
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                     <img src="../../assets/star.png" class="starGray"/>
                   </div>
                   <div class="avatarTitle">{{item.suggestion}}</div>
                   <!--<div>-->
                     <!--<img src="../../assets/avatarphoto.png" class="avatarphoto"/>-->
                   <!--</div>-->
                   <div class="avatarTime">{{item.updateTime}}</div>
                 </div>
               </div>
              <div style="margin-top: 30px;text-align: center" v-if="nocomments">No comments yet</div>
            </div>
            <div class="divTab" v-show="nowIndex===3">
              <div style="margin-top: 30px">
                <div style="margin-bottom: 20px;font-size: 16px;" v-if="nowinningAmount">
                  <span>Your current investment amount：</span>
                  <span>¥</span>
                  <span>{{winningBidDetails.winningAmount?winningBidDetails.winningAmount:''}}</span>

                </div>
                <div style="margin-bottom: 20px;display: flex;font-size: 16px;" v-if="winningAmount">
                  <div>
                    <span>Successful bidder：</span>
                    <span>{{noWinningBidDetails.successfulBidder}}</span>
                  </div>
                  <div style="margin-left: 50px">
                    <span>Winning amount：</span>
                    <span style="color: red">
                      <span>¥</span>
                      <span>{{noWinningBidDetails.winningAmount?noWinningBidDetails.winningAmount:''}}</span>
                    </span>
                  </div>
                </div>
                <div v-if="encryption" style="margin-right: 40px;">
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
                      prop="offeror"
                      label="Offeror"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="encryptAmount"
                      label="Quotation amount（¥）"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="quotationTime"
                      label="Quotation time">
                    </el-table-column>
                  </el-table>
                </div>
                <div v-if="notencryption" style="margin-right: 40px;">
                  <el-table
                    :data="tableData1"
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
                      prop="offeror"
                      label="Offeror"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="quotationAmount"
                      label="Quotation amount（¥）"
                    >
                    </el-table-column>
                    <el-table-column
                      prop="quotationTime"
                      label="Quotation time">
                    </el-table-column>
                  </el-table>
                </div>
              </div>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="editdetails(resdetails)" v-if="editbtn">Modify</el-button>
        <el-button @click="deletedetails(resdetails)" v-if="deletebtn">Delete</el-button>
        <el-button @click="resourceDetailsapply()" v-if="applybtn">Resource Application</el-button>
        <el-button @click="resourceTerminationdiv()" v-if="terminationbtn">Resource Termination</el-button>
        <el-button @click="resourceInvestmentClick()" v-if="investment">Application ForInvestment</el-button>
        <el-button @click="resourceEvaluateClick()" v-if="resEvaluate">Report</el-button>

        </span>
      </el-dialog>
      <!--资源修改-->
      <el-dialog
        :visible.sync="dialogVisibleEdit"
        width="80%"
        title="Resource modification"
        :modal-append-to-body='false'
        :before-close="handleCloseEdit">
        <div style="overflow-x: hidden;overflow-y: scroll;margin-top: 18px;margin-left: 34px;">
          <div class="resourceRelease">
            <div class="information">
              <!--基础信息-->
              <div class="basicInformation">
                <span
                  style="margin-bottom: 20px;display: block;padding-left: 7px;border-left: solid 3px #5069E4;color:rgba(0,0,0,0.85);font-size: 14px;">Basic Info</span>
                <div class="bmation">
                  <div class="item-row">
                    <label for="typeResourceType2" class="bmationName" style="width: 34%;">ResourceType</label>
                    <select id="typeResourceType2" v-model="resourceTypeedit" @change="resourceTypechange1($event)">
                      <option value="" selected disabled>Choose</option>
                      <option value="BaseStation">BaseStation</option>
                      <option value="Tower">Tower</option>
                    </select>
                  </div>
                  <span class="item-row flexPos">
                            <span class="bmationName">ResourceName</span>
                            <input type="text" placeholder="Input here" class="inputs" v-model="resourceNameedit"/>
                        </span>
                  <span class="item-row posFlex">
                      <span class="bmationName4">ResourceId</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="resourceIdedit"
                             :disabled="disabled=true" style="background: #ebebeb"/>
                      </span>

                </div>
                <div class="bmation">
                  <div class="item-row">
                    <label for="typeStatus2" class="bmationName" style="width: 22%">Status</label>
                    <select id="typeStatus2" v-model="statusedit" @change="capitalchange1($event)">
                      <option value="" selected disabled>Choose</option>
                      <option value="0">Running</option>
                      <option value="2">Planning</option>
                      <option value="1">Constructing</option>
                      <option value="3">Maintaining</option>
                    </select>
                  </div>
                  <div class="item-row flexPos">
                    <label for="type" class="bmationName" style="width: 30%">Importance</label>
                    <select id="type" v-model="importanceedit">
                      <option value="" selected disabled>Choose</option>
                      <option value="0">High</option>
                      <option value="1">Middle</option>
                      <option value="2">Low</option>
                    </select>
                  </div>
                  <span class="item-row posFlex">
                      <span class="bmationName3" style="width: 23%">Owner</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="owneredit"
                             :disabled="disabled=true" style="background: #ebebeb"/>
                    </span>
                </div>
                <div class="bmation">
                  <span class="item-row">
                      <span class="bmationName2" style="width: 23%">Rate</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="rateedit"/>
                    </span>
                  <span class="item-row flexPos" v-if="towerIdhidden1">
                      <span class="bmationName3">TowerId</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="towerIdedit"/>
                    </span>
                  <span class="item-row posFlex" v-if="towerIdhidden1">
                      <span class="bmationName" style="width: 18%">NEP</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="nepedit"/>
                    </span>


                </div>
                <div class="bmation" v-if="capitalCost1">
                  <span class="item-row">
                       <span class="bmationName">Startingamount</span>
                       <input type="text" placeholder="Input here" class="inputs" v-model="capitaledit"/>
                  </span>
                  <span class="item-row flexPos timeSelect">
                       <span class="bmationName5">Quotationdeadline</span>
                    <!--<input type="text" placeholder="Input here" class="inputs" v-model="deadline"/>-->
                    <el-date-picker
                      v-model="deadlineedit"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      type="datetime"
                      placeholder="Choose"
                      default-time="12:00:00">
                  </el-date-picker>
                  </span>

                </div>
              </div>
              <!--位置信息-->
              <div class="locationInformation">
                <span
                  style="margin-bottom: 20px;display: block;padding-left: 7px;border-left: 3px solid rgb(80, 105, 228);color:rgba(0,0,0,0.85);font-size: 14px;">Position Info</span>
                <div class="bmationScond">
                    <span class="item-row">
                      <span class="bmationNameRes" style="width: 32%;">Longitude</span>
                      <div style="position: relative">
                        <input type="text" placeholder="Input here" class="inputs" v-model="longitudeedit"/>
                        <div @click="btnIng('edit')" type="button" class="selectLaandlo" style="-webkit-appearance:none;position: absolute;right: 5px;top: 0px;">
                      <!--Choose-->
                      <img src="../../assets/position.png" style="width: 21px;height: 21px"/>
                    </div>
                      </div>

                    </span>
                  <span class="item-row">
                      <span class="bmationNameRes1" style="width: 29%">Latitude</span>
                      <div style="position: relative">
                        <input type="text" placeholder="Input here" class="inputs" v-model="latitudeedit"/>
                        <div @click="btnIng('edit')" type="button" class="selectLaandlo" style="-webkit-appearance:none;position: absolute;right: 5px;top: 0px;">
                      <!--Choose-->
                      <img src="../../assets/position.png" style="width: 21px;height: 21px"/>
                    </div>
                      </div>

                    </span>
                  <!--<el-button type="primary" plain @click="btnIng('edit')">点击选择经纬度</el-button>-->

                </div>
                <div class="bmationScond1">
                    <span class="item-row">
                      <span class="bmationName" style="width: 28%">AreaCode</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="areaCodeedit"/>
                    </span>
                  <span class="item-row">
                      <span class="bmationName2" style="width: 29%">Address</span>
                      <input type="text" placeholder="Input here" class="inputs" v-model="addressedit"/>
                    </span>
                </div>
              </div>
              <!--资源照片-->
              <div class="resourcePhoto">
                <span
                  style="margin-bottom: 20px;display: block;padding-left: 7px;border-left: 3px solid rgb(80, 105, 228);color:rgba(0,0,0,0.85);font-size: 14px;">Resource photo</span>
                <div style="display: flex;flex-wrap: wrap">
                  <div class="basicphoto" v-for="item in imgUrlArray">
                    <img :src="'http://122.192.9.52:20205'+item" style="width: 100%;height: 100%"/>
                  </div>
                </div>
                <el-upload ref="upload"
                           action="http://122.192.9.52:20205/tmf/upload/getFile"
                           list-type="picture-card"
                           :multiple="true"
                           :auto-upload="true"
                           name="files"
                           :on-success="handlesuccessEdit"
                           :on-remove="handleRemoveEdit">
                  <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisibleurlEdit">
                  <img width="100%" :src="dialogImageUrlEdit" alt="">
                </el-dialog>
              </div>
            </div>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handOkEdit()">Confirm</el-button>
                <el-button @click="handleCloseEdit()">Cancel</el-button>
                </span>
      </el-dialog>
      <!--资源删除-->
      <el-dialog
        :visible.sync="dialogVisibleDElete"
        width="30%"
        title="Resource deletion"
        :modal-append-to-body='false'
        :before-close="handleCloseDElete">
        <div style="text-align: center;margin-top: 20px;margin-bottom: 20px">
          <h3>Are you sure you want to delete this site?</h3>
          <span>After deletion, all the site contents will be deleted!</span>
        </div>
        <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handOkDElete()">Confirm</el-button>
                <el-button @click="handleCloseDElete()">Cancel</el-button>
                </span>
      </el-dialog>
      <!--资源申请-->
      <el-dialog
        :visible.sync="resourceDetailsdiv"
        width="80%"
        title="Resource Application"
        :modal-append-to-body='false'
        :before-close="handleCloseresource">
        <div style="margin-top: 30px;margin-left: 50px">
          <div style="display: flex;position: relative">
            <div class="sharingDate" style="width: 15%">Lease start time</div>
            <div ref="agm-datepicker" class="agm-datepicker">
              <div :class="{active: show}" class="agm-input" @click.stop="show=!show">
                <input :value="current | dateFormat" type="text" readonly>
              </div>
              <div v-if="show" :class="{positionTop:positionTop}" class="agm-datepicker-wrap">
                <div class="agm-datepicker-header" @click.stop="">
                  <span v-if="!selectMonth" class="agm-datepicker-header-btn agm-datepicker-header-btn-pre"
                        @click.stop="switchYear(-1)">&lt;&lt;</span>
                  <span v-if="!selectMonth && !selectYear"
                        class="agm-datepicker-header-btn agm-datepicker-header-btn-pre" @click.stop="switchMonth(-1)">&lt;</span>
                  <span class="agm-datepicker-header-btn-day">
                    <span class="agm-datepicker-header-btn" @click.stop="openSelectYear">{{ select.year }}year </span>
                    <span class="agm-datepicker-header-btn" @click.stop="openSelectMonth">{{ select.month }}month</span>
                  </span>
                  <span v-if="!selectMonth" class="agm-datepicker-header-btn agm-datepicker-header-btn-next"
                        @click.stop="switchYear(1)">&gt;&gt;</span>
                  <span v-if="!selectMonth && !selectYear"
                        class="agm-datepicker-header-btn agm-datepicker-header-btn-next" @click.stop="switchMonth(1)">&gt;</span>
                </div>
                <div class="agm-datepicker-content">
                  <table>
                    <thead>
                    <th>Sun</th>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                    </thead>
                    <tbody>
                    <tr v-for="(week,index) of list" :key="'week_'+index">
                      <td v-for="(weekday,idx) of week" :key="'weekday_'+index+idx"
                          :class="{'flag': weekday.flag,'active': !weekday.flag && weekday.text == current.date&& select.month == current.month && select.year == current.year}"
                          @click="pick(weekday)">
                        {{ weekday.text }}
                      </td>
                    </tr>
                    </tbody>
                  </table>
                  <div v-if="selectYear" class="agm-date-picker-year-panel">
                    <div class="panel-list">
                      <div v-for="(y, index) of years" :key="'year_'+index" class="panel-item">
                        <div :class="{active: y===select.year}" class="panel-item-inner" @click.stop="pickYear(y)">{{ y
                          }}
                        </div>
                      </div>
                    </div>
                  </div>
                  <div v-if="selectMonth" class="agm-date-picker-year-panel">
                    <div class="panel-list">
                      <div v-for="(m, index) of months" :key="'month_'+index" class="panel-item">
                        <div :class="{active: (index+1)===select.month}" class="panel-item-inner"
                             @click.stop="pickMonth(index+1)">{{ m }}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div style="display: flex;position: relative;margin-top: 20px;">
            <div class="sharingDate" style="width: 15%">Lease reasons</div>
            <textarea style="width: 80%;height: 200px;outline: none" v-model="applyopinion" placeholder="Input here"></textarea>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handOkresource()">Confirm</el-button>
                <el-button @click="handleCloseresource()">Cancel</el-button>
                </span>
      </el-dialog>
      <!--资源终止-->
      <el-dialog
        :visible.sync="resourceTermination"
        width="80%"
        title="Resource Termination"
        :modal-append-to-body='false'
        :before-close="handleClosemination">
        <div style="margin-top: 30px;margin-left: 50px">
          <div style="display: flex;position: relative">
            <div class="sharingDate" style="width: 15%">End sharing date</div>
            <div ref="agm-datepicker" class="agm-datepicker">
              <div :class="{active: show}" class="agm-input" @click.stop="show=!show">
                <input :value="current2 | dateFormat" type="text" readonly>
              </div>
              <div v-if="show" :class="{positionTop:positionTop}" class="agm-datepicker-wrap">
                <div class="agm-datepicker-header" @click.stop="">
                  <span v-if="!selectMonth" class="agm-datepicker-header-btn agm-datepicker-header-btn-pre"
                        @click.stop="switchYear(-1)">&lt;&lt;</span>
                  <span v-if="!selectMonth && !selectYear"
                        class="agm-datepicker-header-btn agm-datepicker-header-btn-pre" @click.stop="switchMonth(-1)">&lt;</span>
                  <span class="agm-datepicker-header-btn-day">
                    <span class="agm-datepicker-header-btn" @click.stop="openSelectYear">{{ select.year }}year </span>
                    <span class="agm-datepicker-header-btn" @click.stop="openSelectMonth">{{ select.month }}month</span>
                  </span>
                  <span v-if="!selectMonth" class="agm-datepicker-header-btn agm-datepicker-header-btn-next"
                        @click.stop="switchYear(1)">&gt;&gt;</span>
                  <span v-if="!selectMonth && !selectYear"
                        class="agm-datepicker-header-btn agm-datepicker-header-btn-next" @click.stop="switchMonth(1)">&gt;</span>
                </div>
                <div class="agm-datepicker-content">
                  <table>
                    <thead>
                    <th>Sun</th>
                    <th>Mon</th>
                    <th>Tue</th>
                    <th>Wed</th>
                    <th>Thu</th>
                    <th>Fri</th>
                    <th>Sat</th>
                    </thead>
                    <tbody>
                    <tr v-for="(week,index) of list" :key="'week_'+index">
                      <td v-for="(weekday,idx) of week" :key="'weekday_'+index+idx"
                          :class="{'flag': weekday.flag,'active': !weekday.flag && weekday.text == current2.date&& select.month == current2.month && select.year == current2.year}"
                          @click="pick(weekday)">
                        {{ weekday.text }}
                      </td>
                    </tr>
                    </tbody>
                  </table>
                  <div v-if="selectYear" class="agm-date-picker-year-panel">
                    <div class="panel-list">
                      <div v-for="(y, index) of years" :key="'year_'+index" class="panel-item">
                        <div :class="{active: y===select.year}" class="panel-item-inner" @click.stop="pickYear(y)">{{ y
                          }}
                        </div>
                      </div>
                    </div>
                  </div>
                  <div v-if="selectMonth" class="agm-date-picker-year-panel">
                    <div class="panel-list">
                      <div v-for="(m, index) of months" :key="'month_'+index" class="panel-item">
                        <div :class="{active: (index+1)===select.month}" class="panel-item-inner"
                             @click.stop="pickMonth(index+1)">{{ m }}
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div style="display: flex;position: relative;margin-top: 20px;">
            <div class="sharingDate" style="width: 15%">Termination reason</div>
            <textarea style="width: 80%;height: 200px;outline: none" v-model="applyopinionmination" placeholder="Input here"></textarea>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handOkmination()">Confirm</el-button>
                <el-button @click="handleClosemination()">Cancel</el-button>
                </span>
      </el-dialog>
      <!--招标-->
      <el-dialog
        :visible.sync="resourceInvestment"
        width="80%"
        title="Resource details"
        :modal-append-to-body='false'
        :before-close="handleCloseInvestment">
        <div style="margin-top: 30px;margin-left: 50px">
          <div style="margin-bottom: 20px">
            <span class="investmentWidth" style="display: inline-block">Starting  amount</span>
            <span style="color: red;">
              <span>¥</span>
              <!--<span>{{resdetails.investmentCost}}</span>-->
              <span>{{winningBidDetails.winningAmount?winningBidDetails.winningAmount:resdetails.investmentCost}}</span>
            </span>
          </div>
          <div style="display: flex;margin-bottom: 20px">
            <div class="investmentWidth">
              <span style="color: red">*</span>
              <span>Investment amount</span>
            </div>
            <input type="text" placeholder="Input here" class="inputs" v-model="investmentAmount">
          </div>
          <!--<div style="display: flex;margin-bottom: 20px">-->
            <!--<div class="investmentWidth">-->
              <!--<span style="color: red">*</span>-->
              <!--<span>Investment reason</span>-->
            <!--</div>-->
            <!--<textarea style="width: 80%;height: 200px;outline: none" v-model="investmentReason" placeholder="Input here"></textarea>-->
          <!--</div>-->
        </div>
        <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handOkInvestment()">Confirm</el-button>
                <el-button @click="handleCloseInvestment()">Cancel</el-button>
                </span>
      </el-dialog>
      <!--评论-->
      <el-dialog
        :visible.sync="resourceEvaluate"
        width="80%"
        title="Select the reported fault list"
        :modal-append-to-body='false'
        :before-close="handleCloseEvaluate">
        <div style="margin-top: 30px;margin-left: 20px;margin-right: 20px;">
          <!--<div style="display: flex;margin-bottom: 20px">-->
            <!--<div class="investmentWidth">-->
              <!--<span style="color: red">*</span>-->
              <!--<span>Comments</span>-->
            <!--</div>-->
            <!--<textarea style="width: 80%;height: 200px;outline: none" v-model="comments" placeholder="Input here"></textarea>-->
          <!--</div>-->
          <div>
            <el-table
              :data="tableData2"
              @row-click="handdle"
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
                prop="phone"
                label="Reporter"
              >
              </el-table-column>
              <el-table-column
                prop="taskId"
                label="Task ID"
              >
              </el-table-column>
              <el-table-column
                prop="suggestion"
                label="Report Content">
              </el-table-column>
              <el-table-column
                prop="reportTime"
                label="Report Time">
              </el-table-column>
              <el-table-column
                prop="UploadRate"
                label="Upload rate">
              </el-table-column>
              <el-table-column
                prop="DownloadRate"
                label="Download rate">
              </el-table-column>
              <el-table-column
                prop="NetworkDelay"
                label="Network delay">
              </el-table-column>
              <el-table-column
                prop="CallingConnectionRate"
                label="Calling connection rate">
              </el-table-column>
              <el-table-column
                prop="VoiceDropRate"
                label="Voice drop rate">
              </el-table-column>
            </el-table>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="handOkEvaluate()">Confirm</el-button>
                <el-button @click="handleCloseEvaluate()">Cancel</el-button>
                </span>
      </el-dialog>
    </div>
    <!--选择经纬度弹框-->
    <el-dialog
      title="choose location"
      :visible.sync="btnIngdialogVisible"
      width="80%"
      :before-close="btnInghandleClose">
      <div id="mapcontainer" style="width:100%;height:532px">
      </div>
    </el-dialog>

    <!--  选择资源类型  -->
    <Select-resource-type
      :visible.sync="selectType"
      @ResourcePublishing="SelectTypeFun"
    ></Select-resource-type>
  </div>
</template>

<script>
  import api from "./api/api";
  import axios from '@/utils/axios';
  import SelectResourceType from "./SelectResourceType";

  export default {
    inject: ['reload'], // 引入方法
    name: "ResourceManagement",
    components: {
      SelectResourceType,
    },
    //日历
    filters: {
      // 日期格式过滤器
      dateFormat(val) {
        if (!val) {
          return ''
        }
        return `${val.year}-${val.month}-${val.date}`.replace(/\d+/g, a => {
          return a.length === 4 ? a : a.length === 2 ? a : '0' + a
        })
      },
      formatDate: function (value) {
        let date = new Date(value);
        let y = date.getFullYear();
        let MM = date.getMonth() + 1;
        MM = MM < 10 ? ('0' + MM) : MM;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let m = date.getMinutes();
        m = m < 10 ? ('0' + m) : m;
        let s = date.getSeconds();
        s = s < 10 ? ('0' + s) : s;
        return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
      }
    },
    inheritAttrs: false,
    props: {
      moment: {
        type: Number,
        default() {
          return new Date().getTime()
        }
      }
    },
    watch: {
      rate: function(val){
        this.rate = val.replace(/\D/g, '')
      },
      rateedit: function(val){
        this.rateedit = val.replace(/\D/g, '')
      },
      select: {
        handler(newVal) {
          let pre
          if (newVal.month == 1) {
            pre = new Date(newVal.year - 1, 12, 0)
          } else {
            pre = new Date(newVal.year, newVal.month - 1, 0)
          }
          this.lastMonthEndDate = pre.getDate()
          // 获取日历排表
          this.getDateList()
        },
        deep: true
      },
      // 打开年份选择器的时候使当前年份、月份出现在窗口顶部
      selectYear(newVal) {
        if (newVal) {
          this.years = []
          this.$nextTick(() => {
            let year = this.select.year
            let startYear = ((year / 10) | 0) * 10
            // 获得年份列表： 1900-2100
            for (let i = startYear; i < startYear + 10; i++) {
              this.years.push(i)
            }
          })
        }
      },
      show(newVal) {
        if (newVal) {

        }
      }
    },
    created() {
      var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
      this.owner = usenamedetails.userName;
      this.transform(this.moment)
      this.complete()
      const date = new Date()
      let year = date.getFullYear()
      let startYear = ((year / 10) | 0) * 10
      // 获得年份列表： 1900-2100
      for (let i = startYear; i < startYear + 10; i++) {
        this.years.push(i)
      }
    },
    //日历
    data() {
      return {
        statusMap: {
          "0": "Running",
          "1": "Constructing",
          "2": "Planning",
          "3": "Maintaining",
        },
        resourceTypeList: ['BaseStation', 'Tower'],
        resourceTypeName: 'BaseStation',
        SelectTypeName: '', // 选择添加的类型
        map: null,
        dialogVisible: false,
        selectType: false,
        dialogVisibledat: false,
        dialogVisiblemod: false,
        application: false,
        tabsParam: ['Resource Info', 'Resource Photos',"Service Evaluation","Quotation Record"],//（这个也可以用对象key，value来实现）
        nowIndex: 0,//默认第一个tab为激活状态
        radio: null,
        dialogImageUrl: '',
        dialogVisibleurl: false,
        dialogImageUrlEdit: '',
        dialogVisibleurlEdit: false,
        dialogVisibleEdit: false,
        dialogVisibleDElete: false,
        resourceDetailsdiv: false,
        disabled: false,
        multiple: true,
        show: false,
        show1: false,
        basestationInput: "",
        getNewInfo: "",
        clicktypeInfo: "",
        resdetails: "",
        reportRes: "",
        //添加里面的值
        resourceName: "",
        resourceId: "",
        resourceType: "",
        status: "",
        importance: "",
        towerId: "",
        owner: "",
        lessor: "",
        nep: "",
        rate: "",
        longitude: "",
        latitude: "",
        areaCode: "",
        address: "",
        path: "",
        filelist: [],
        //修改
        resourceNameedit: "",
        resourceIdedit: "",
        resourceTypeedit: "",
        statusedit: "",
        importanceedit: "",
        towerIdedit: "",
        owneredit: "",
        lessoredit: "",
        nepedit: "",
        rateedit: "",
        longitudeedit: "",
        latitudeedit: "",
        areaCodeedit: "",
        addressedit: "",
        pathedit: "",
        filelistedit: [],
        //日历
        show: false, // 控制日历面板的显示与隐藏
        selectYear: false, // 控制年份的面板的显示和隐藏
        selectMonth: false, // 控制月份的面板的显示和隐藏
        positionTop: false, // 控制面板显示方向
        current: '', // 已选择的日期时间。yyyy-MM-dd
        current2: '', // 已选择的日期时间。yyyy-MM-dd
        select: {
          // 已选择的日期对象
          year: '',
          month: '',
          date: '',
          day: ''
        },
        currentMonthFirstDay: null, // 当前月的1号属于星期几
        currentMonthEndDate: null, // 当前月的最后一天是几号
        currentMonthEndDay: null, // 当前月的最后一天属于星期几
        lastMonthEndDate: null, // 上个月的最后一天是几号
        list: [], // 日历的二维数组
        years: [], // 1900-2100
        months: [
          '1月',
          '2月',
          '3月',
          '4月',
          '5月',
          '6月',
          '7月',
          '8月',
          '9月',
          '10月',
          '11月',
          '12月'
        ],
        //资源申请
        applyopinion: "",
        //资源终止
        resourceTermination: false,
        applyopinionmination: "",
        //招标
        resourceInvestment: false,
        //评论
        resourceEvaluate: false,
        resDate: "",
        //图片地址
        imgUrl: "",
        imgUrlArray: [],
        otherImg: [],
        //判断按钮显示隐藏
        editbtn: true,
        deletebtn: true,
        applybtn: true,
        terminationbtn: true,
        investment: true,
        resEvaluate: true,
        //暂无数据
        nodata: true,
        dataEdit: true,
        //清除input
        clearImgInput: false,
        disabled: false,
        //选择经纬度
        btnIngdialogVisible: false,
        towerIdhidden: true,
        towerIdhidden1: true,
        //新增加的投资
        capital:"",
        capitaledit:"",
        deadline:"",
        deadlineedit:"",
        capitalCost:false,
        capitalCost1:false,
        tableData: [],
        tableData1: [],
        tableData2: [],
        investmentAmount:"",
        investmentReason:"",
        comments:"",
        curStartTime: '2019-11-16 08:00:00',
        day: '0',
        hour: '00',
        min: '00',
        second: '00',
        startingAmount:true,
        dayResource:true,
        quotation:"",
        deadlineTime:false,
        investedBtn:false,
        timeBtn:false,
        nowinningAmount:true,
        winningAmount:true,
        //中标
        winningBidDetails:"",
        noWinningBidDetails:"",
        encryption:"false",
        notencryption:"false",
        userNameYes:"true",
        taskId:"",
        towerTowerId:true,
        towerNep:true,
        nocomments:false,
        noPictures:false,
      };
    },
    // watch: {
    //
    // },
    methods: {
      handdle(row, event, column) {
       console.log(row);
       this.taskId = row.taskId;
       console.log(this.taskId);
       this.handOkEvaluate(this.taskId)
      },
      indexMethod(index) {
        return index + 1;
      },
      // addResource(){
      //   this.selectType=true;
      //   if (this.resourceTypeName == "Tower") {
      //      this.towerIdhidden = false;
      //      this.resourceType = "Tower";
      //   }else{
      //     this.towerIdhidden = true;
      //     this.resourceType = "BaseStation";
      //   }
      // },
      SelectTypeFun(e) {
        this.dialogVisible = true;
        this.SelectTypeName = e;
        // console.log(this.SelectTypeName)
        if (this.SelectTypeName == "Tower") {
           this.towerIdhidden = false;
           this.resourceType = "Tower";
           this.resourceTypeName = "Tower";
           this.api();
        }else{
          this.towerIdhidden = true;
          this.resourceType = "BaseStation";
          this.resourceTypeName = "BaseStation";
          this.api();
        }
      },
      renderStar(index, star) {
        let imgSrc = "@/assets/starGray.png";
        if (index < star) {
          imgSrc = "@/assets/star.png"
        }

        return imgSrc;
      },
      capitalchange(data){
        var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
        if(this.status == 2){
          this.capitalCost = true;
          this.owner = "";
        }else{
          this.capitalCost = false;
          this.owner = usenamedetails.userName;
        }
      },
      capitalchange1(data){
        var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
        if(this.statusedit == 2){
          this.capitalCost1 = true;
          this.owneredit = "";
        }else{
          this.capitalCost1 = false;
          this.owneredit = usenamedetails.userName;
        }
      },
      resourceTypechange(data){
        if (this.resourceType == "Tower") {
          this.towerIdhidden = false;
          this.resourceTypeName = "Tower";
          this.api();
        }else{
          this.towerIdhidden = true;
          this.resourceTypeName = "BaseStation";
          this.api();
        }
      },
      resourceTypechange1(data){
        if (this.resourceTypeedit == "Tower") {
          this.towerIdhidden1 = false;
          this.resourceTypeName = "Tower";
          this.api();
        }else{
          this.towerIdhidden1 = true;
          this.resourceTypeName = "BaseStation";
          this.api();
        }
      },
      handleMenuClick({key}) {
        this.resourceTypeName = this.resourceTypeList[key];
        this.api();
        // this.focusfns();
        this.basestationInput = "";
        this.show = false;
        this.show1 = false;
        this.clearImgInput = false;
        const params = {
          resourceType:this.resourceTypeName
        }
        api.getNewInfo(params).then(result => {
          // this.getNewInfo=result;
          if (result == "") {
            this.dataEdit = true;
            this.getNewInfo = "";
          } else {
            this.getNewInfo = result;
            this.dataEdit = false;
          }
        }).catch(error => {
          console.log(error);
        })
      },
      //日历
      /*** 将时间转化为具体的 年、月、日、星期**/
      transform(time) {
        const date = new Date(time)
        this.select.year = date.getFullYear()
        this.select.month = date.getMonth() + 1
        this.select.date = date.getDate()
        this.select.day = date.getDay()
        this.currentMonthFirstDay = new Date(
          this.select.year,
          this.select.month - 1,
          1,
          0
        ).getDay()
        this.currentMonthEndDate = new Date(
          this.select.year,
          this.select.month,
          0
        ).getDate()
        this.currentMonthEndDay = new Date(
          this.select.year,
          this.select.month,
          0
        ).getDay()
      },
      /** 计算出日历列表，二维数组* 第一层为星期，第二层为每星期的第几天*/
      getDateList() {
        this.list = []
        // 获取日历第一行的数据（需加上第一个星期中所包含上个月的几天）
        let temp = this.lastMonthEndDate - (this.currentMonthFirstDay - 1)
        let list = this.numberList(temp, this.lastMonthEndDate, true).concat(
          this.numberList(1, 7 - this.currentMonthFirstDay)
        )
        this.list.push(list)
        temp = 7 - this.currentMonthFirstDay + 1
        /* * 剩下的行数*/
        // 计算除了第一行剩下的天数
        const leftDays =
          this.currentMonthEndDate - (7 - this.currentMonthFirstDay)
        // 剩下的星期数
        const lineNumber = Math.ceil(leftDays / 7)
        // 包含下个月日历的天数
        const nextDays = 7 - (leftDays % 7)
        for (let i = 0; i < lineNumber; i++) {
          if (i === lineNumber - 1 && nextDays > 0 && nextDays !== 7) {
            this.list[lineNumber] = this.numberList(
              temp,
              this.currentMonthEndDate
            ).concat(this.numberList(1, nextDays, true))
          } else {
            this.list.push(this.numberList(temp, temp + 6))
          }
          temp = temp + 7
        }
        // 补齐6排
        if (lineNumber < 5) {
          this.list.push(this.numberList(nextDays + 1, nextDays + 7, true))
        }
      },
      /* * 获得日历数组* start: 开始日* end: 结束日* flag： boolean值，判断是否属于本月*/
      numberList(start, end, flag) {
        let list = []
        for (let i = start; i <= end; i++) {
          list.push({
            text: i,
            flag: !!flag
          })
        }
        return list
      },
      /* * 切换月份， -1为当前月份-1，+1为当前月份+1*/
      switchMonth(n) {
        let year = this.select.year
        if (n === -1) {
          const pre = this.select.month === 1 ? 12 : this.select.month - 1
          if (pre === 12) {
            this.transform(new Date(year - 1, pre - 1, this.select.date))
          } else {
            this.transform(new Date(year, pre - 1, this.select.date))
          }
        } else {
          const next = this.select.month === 12 ? 1 : this.select.month + 1
          if (next === 1) {
            this.transform(new Date(year + 1, next - 1, this.select.date))
          } else {
            this.transform(new Date(year, next - 1, this.select.date))
          }
        }
      },
      /** 切换年份， -1为当前年份-1，+1为当前年份+1*/
      switchYear(n) {
        if (this.selectYear) {
          let year = this.years[0]
          this.years = []
          if (n === -1) {
            for (var i = year - 10; i < year; i++) {
              this.years.push(i)
            }
          } else {
            for (var i = year + 10; i < year + 20; i++) {
              this.years.push(i)
            }
          }
        } else {
          let year = this.select.year
          if (n === -1) {
            this.transform(
              new Date(year - 1, this.select.month - 1, this.select.date)
            )
          } else {
            this.transform(
              new Date(year + 1, this.select.month - 1, this.select.date)
            )
          }
        }
      },
      pick(day) {
        if (!!day.flag) {
          // 当页日历上可能还会显示部分上个月或者下个月的部分天数，根据标识来做判断
          // 并对月份作出相应的处理
          if (parseInt(day.text) > 15) {
            this.transform(
              new Date(
                this.select.year,
                this.select.month - 2,
                parseInt(day.text)
              )
            )
          } else {
            this.transform(
              new Date(this.select.year, this.select.month, parseInt(day.text))
            )
          }
        } else {
          this.transform(
            new Date(this.select.year, this.select.month - 1, parseInt(day.text))
          )
        }
        this.complete()
        this.bindEvent()
      },
      // 绑定事件：点击关闭日历面板
      bindEvent() {
        this.show = false
        this.selectYear = false
        this.selectMonth = false
      },
      // 打开选择年份
      openSelectYear() {
        this.selectYear = !this.selectYear
        this.selectMonth = false
      },
      // 打开选择月份
      openSelectMonth() {
        this.selectMonth = !this.selectMonth
        this.selectYear = false
      },
      // 选取年
      pickYear(n) {
        // this.transform(new Date(n, this.select.month - 1, this.select.date))
        this.select.year = n
        this.selectYear = !this.selectYear
        this.selectMonth = true
      },
      // 选取月
      pickMonth(n) {
        this.select.month = n
        this.selectMonth = !this.selectMonth
      },
      // 更改选中时间并向父组件派发事件
      complete() {
        // 触发父组件的传过来的picked事件。三个参数: 年，月，日
        this.$emit(
          'picked',
          this.select.year,
          this.select.month,
          this.select.date
        )
        this.current = {
          year: this.select.year,
          month: this.select.month,
          date: this.select.date
        }
        this.current2 = {
          year: this.select.year,
          month: this.select.month,
          date: this.select.date
        }
      },
      api() {
        //得到所有的基站的信息
        const params = {
          resourceType:this.resourceTypeName
        }
        api.getAll(params).then(result => {
          console.log(result);
          this.baiduMap(result);
        }).catch(error => {
          console.log(error);
        })
        //得到最新的10条记录
        api.getNewInfo(params).then(result => {
          this.getNewInfo = result;
        }).catch(error => {
          console.log(error);
        })
      },
      //根据基站状态搜索
      typeclick(reset) {
        var data = {
          "Running": "0",
          "Constructing": "1",
          "Planning": "2",
          "Maintaining": "3",
        }
        this.basestationInput = reset;
        this.show = false;
        this.show1 = true;
        const params = {
          status: data[reset],
          resourceType:this.resourceTypeName
        }
        api.getResByStatus(params).then(result => {
          if (result == "") {
            this.nodata = true;
            this.clicktypeInfo = "";
          } else {
            this.clicktypeInfo = result;
            this.nodata = false;
          }
        }).catch(error => {
          console.log(error);
        })
      },
      //根据状态搜索
      clickType(data) {
        this.basestationInput = data.resourceName;
        const getResByName = {
          resName: data.resourceName,
          resourceType:this.resourceTypeName
        }
        api.getResByName(getResByName).then(result => {
        }).catch(error => {
          console.log(error);
        })
        this.map.centerAndZoom(new BMap.Point(data.longitude, data.latitude), 18);

      },
      //根据搜索记录搜索
      searchRecond(data) {
        this.basestationInput = data.info;
        const getResByName = {
          resName: data.info,
          resourceType:this.resourceTypeName
        }
        api.getResByName(getResByName).then(result => {
        }).catch(error => {
          console.log(error);
        })
        this.map.centerAndZoom(new BMap.Point(data.longitude, data.latitude), 18);
      },


      //根据基站名称进行搜索
      search() {
        const getResByName = {
          resName: this.basestationInput,
          resourceType:this.resourceTypeName
        }
        api.getResByName(getResByName).then(result => {
          this.show = false;
          this.show1 = true;
          if (result == "") {
            this.nodata = true;
            this.clicktypeInfo = "";
          } else {
            this.clicktypeInfo = result;
            this.nodata = false;
          }
        }).catch(error => {
          console.log(error);
        })
      },
      //删除搜索记录
      deleteAllInfo() {
        const params = {
          resourceType:this.resourceTypeName
        }
        api.deleteAllInfo(params).then(result => {
          this.getNewInfo = "";
          this.dataEdit = true;
          this.$msg({
            message: 'successfully deleted',
            type: 'success',
            duration: '1000',
          });
        }).catch(error => {
          console.log(error);
        })
      },
      //得到最新的10条记录
      focusfns() {
        this.show = true;
        this.show1 = false;
        this.clearImgInput = true;
        const params = {
          resourceType:this.resourceTypeName
        }
        api.getNewInfo(params).then(result => {
          // this.getNewInfo=result;
          if (result == "") {
            this.dataEdit = true;
            this.getNewInfo = "";
          } else {
            this.getNewInfo = result;
            this.dataEdit = false;
          }
        }).catch(error => {
          console.log(error);
        })
      },
      //清除input框
      clearclick() {
        this.clearImgInput = false;
        this.basestationInput = "";
        this.show = false;
        this.show1 = false;
      },
      blurfns() {
        this.show = false;
      },
      //删除资源照片
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlesuccess(res, file) {
        // this.$msg({
        //   type: 'info',
        //   message: '图片上传成功',
        //   duration: 6000
        // });
        this.filelist.push(file.response[0].path);
      },

      //取消新增页面
      handleClose(done) {
        this.dialogVisible = false;
        this.api();
      },
      //新增基站
      handOk() {
        var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
        let flag=true;
        // if(this.resourceTypeName=="Tower"){
        if(this.resourceType=="Tower"){
          if(this.status == 2){
            if(this.resourceName == "" || this.resourceId == "" || this.resourceType == "" || this.status == "" || this.importance == "" ||  this.rate == "" || this.latitude == "" || this.longitude == "" || this.areaCode == "" || this.address == ""|| this.capital =="" || this.deadline == ""){
              flag=false;
              this.dialogVisible = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }else{
            if(this.resourceName == "" || this.resourceId == "" || this.resourceType == "" || this.status == "" || this.importance == "" || this.owner == "" || this.rate == "" || this.latitude == "" || this.longitude == "" || this.areaCode == "" || this.address == ""){
              flag=false;
              this.dialogVisible = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }


        }else{
          if(this.status == 2){
            if (this.resourceName == "" || this.resourceId == "" || this.resourceType == "" || this.status == "" || this.importance == "" || this.towerId == ""  || this.nep == "" || this.rate == "" || this.latitude == "" || this.longitude == "" || this.areaCode == "" || this.address == "" || this.capital =="" || this.deadline == "") {
              flag=false
              this.dialogVisible = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }else{
            if (this.resourceName == "" || this.resourceId == "" || this.resourceType == "" || this.status == "" || this.importance == "" || this.towerId == "" || this.owner == "" || this.nep == "" || this.rate == "" || this.latitude == "" || this.longitude == "" || this.areaCode == "" || this.address == "") {
              flag=false
              this.dialogVisible = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }

        }
        //this.$refs.upload.submit();//点击确认手动上传
        if (flag) {
          let ResDetail = {};
          if(this.status == "2"){
            ResDetail = {
              resourceName: this.resourceName,
              resourceId: this.resourceId,
              resourceType: this.resourceType,
              status: this.status,
              importance: this.importance,
              towerId: this.towerId,
              owner: this.owner,
              // lessor:this.lessor ,
              nep: this.nep,
              rate: this.rate,
              longitude: this.longitude,
              latitude: this.latitude,
              areaCode: this.areaCode,
              address: this.address,
              path: this.filelist.toString(),
              investmentCost: this.capital,
              deadlineTime: this.deadline,
              isp: usenamedetails.userName
            }
          }else{
            ResDetail = {
              resourceName: this.resourceName,
              resourceId: this.resourceId,
              resourceType: this.resourceType,
              status: this.status,
              importance: this.importance,
              towerId: this.towerId,
              owner: this.owner,
              // lessor:this.lessor ,
              nep: this.nep,
              rate: this.rate,
              longitude: this.longitude,
              latitude: this.latitude,
              areaCode: this.areaCode,
              address: this.address,
              path: this.filelist.toString(),
              investmentCost: "",
              deadlineTime: "",
              isp: usenamedetails.userName
            }
          }
          api.create(ResDetail, 'post').then(result => {
            if (result == "新增失败") {
              this.$msg({
                message: 'Resource release failed',
                type: 'error',
                duration: '1000',
              });
              this.map.centerAndZoom(new BMap.Point(116.4035, 39.915), 14);
              return;
            }
            this.$msg({
              message: 'Successful resource release',
              type: 'success',
              duration: '1000',
            });
            let imgArr = [];
            if (this.resourceType == "BaseStation") {
              imgArr = ['5G_use.png', '5G_build.png', '5G_plan.png', '5G_maintain.png']
            } else {
              imgArr = ['tower_use.png', 'tower_build.png', 'tower_plan.png', 'tower_maintain.png']
            }
            var point = new BMap.Point(result["longitude"] + "", result['latitude'] + "");
            this.map.centerAndZoom(point, 14);
            var icon = new BMap.Icon(require("../../assets/" + imgArr[result['status']]), new BMap.Size(32, 32), {imageSize: new BMap.Size(32, 32)});
            var mkr = new BMap.Marker(point, {
              icon: icon
            });
            this.map.addOverlay(mkr);
            mkr.setAnimation(BMAP_ANIMATION_BOUNCE)
            this.addMarker1(mkr, result['resourceId']);
            this.dialogVisible = false;
            this.resourceName = "";
            this.resourceId = "";
            this.resourceType = "";
            this.status = "";
            this.importance = "";
            this.towerId = "";
            this.owner = "";
            this.nep = "";
            this.rate = "";
            this.longitude = "";
            this.latitude = "";
            this.areaCode = "";
            this.address = "";
            this.filelist = [];
            this.capital = "";
            this.deadline = "";
            this.$refs.upload.clearFiles();
          }).catch(error => {
            console.log(error);
            this.$msg({
              message: 'Resource release failed',
              type: 'error',
              duration: '1000',
            });
          })

        }

      },
      //修改基站
      editdetails(resdetails) {
        var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
        if(resdetails.resourceType == "Tower"){
          this.towerIdhidden1 = false;
        }else{
          this.towerIdhidden1 = true;
        }
        if(resdetails.status == 2){
           this.capitalCost1 = true;
          resdetails.owner = "";
        }else{
          this.capitalCost1 = false;
          resdetails.owner = usenamedetails.userName;
        }
        this.dialogVisibleEdit = true;
        // console.log(resdetails);
        this.resourceNameedit = resdetails.resourceName;
        this.resourceIdedit = resdetails.resourceId;
        this.resourceTypeedit = resdetails.resourceType;
        this.statusedit = resdetails.status;
        this.importanceedit = resdetails.importance;
        this.towerIdedit = resdetails.towerId;
        this.owneredit = resdetails.owner;
        this.lessoredit = resdetails.lessor;
        this.nepedit = resdetails.nep;
        this.rateedit = resdetails.rate;
        this.longitudeedit = resdetails.longitude;
        this.latitudeedit = resdetails.latitude;
        this.areaCodeedit = resdetails.areaCode;
        this.addressedit = resdetails.address;
        this.capitaledit = resdetails.investmentCost;
        this.deadlineedit = resdetails.deadlineTime;

      },
      handOkEdit() {
        var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
        let flag=true;
        // if(this.resourceTypeName=="Tower"){
        if(this.resourceTypeedit=="Tower"){
          if(this.statusedit == 2){
            if (this.resourceNameedit == "" || this.resourceIdedit == "" || this.resourceTypeedit == "" || this.statusedit == null || this.importanceedit == null || JSON.stringify(this.rateedit) == "" || this.latitudeedit == "" || this.longitudeedit == "" || this.areaCodeedit == "" || this.addressedit == ""|| this.capitaledit =="" || this.deadlineedit == "") {
              flag=false;
              this.dialogVisibleEdit = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }else{
            if (this.resourceNameedit == "" || this.resourceIdedit == "" || this.resourceTypeedit == "" || this.statusedit == null || this.importanceedit == null ||  this.owneredit == "" ||  JSON.stringify(this.rateedit) == "" || this.latitudeedit == "" || this.longitudeedit == "" || this.areaCodeedit == "" || this.addressedit == "") {
              flag=false;
              this.dialogVisibleEdit = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }

        }else{
          if(this.statusedit == 2){
            if (this.resourceNameedit == "" || this.resourceIdedit == "" || this.resourceTypeedit == "" || this.statusedit == null || this.importanceedit == null || this.towerIdedit == "" ||  this.nepedit == "" || JSON.stringify(this.rateedit) == "" || this.latitudeedit == "" || this.longitudeedit == "" || this.areaCodeedit == "" || this.addressedit == ""|| this.capitaledit =="" || this.deadlineedit == "") {
              flag=false;
              this.dialogVisibleEdit = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }else{
            if (this.resourceNameedit == "" || this.resourceIdedit == "" || this.resourceTypeedit == "" || this.statusedit == null || this.importanceedit == null || this.towerIdedit == "" || this.owneredit == "" || this.nepedit == "" || JSON.stringify(this.rateedit) == "" || this.latitudeedit == "" || this.longitudeedit == "" || this.areaCodeedit == "" || this.addressedit == "") {
              flag=false;
              this.dialogVisibleEdit = true;
              this.$msg({
                message: 'Please fill in the "*"',
                type: 'error',
                duration: '1000',
              });
            }
          }
        }
        if (flag){
          let editDetail = {};
          if(this.statusedit == "2"){
            editDetail = {
              resourceName: this.resourceNameedit,
              resourceId: this.resourceIdedit,
              resourceType: this.resourceTypeedit,
              status: this.statusedit,
              importance: this.importanceedit,
              towerId: this.towerIdedit,
              owner: this.owneredit,
              nep: this.nepedit,
              rate: this.rateedit,
              longitude: this.longitudeedit,
              latitude: this.latitudeedit,
              areaCode: this.areaCodeedit,
              address: this.addressedit,
              path: this.filelistedit.toString(),
              investmentCost: this.capitaledit,
              deadlineTime: this.deadlineedit,
              isp: usenamedetails.userName
            }
          }else{
            editDetail = {
              resourceName: this.resourceNameedit,
              resourceId: this.resourceIdedit,
              resourceType: this.resourceTypeedit,
              status: this.statusedit,
              importance: this.importanceedit,
              towerId: this.towerIdedit,
              owner: this.owneredit,
              nep: this.nepedit,
              rate: this.rateedit,
              longitude: this.longitudeedit,
              latitude: this.latitudeedit,
              areaCode: this.areaCodeedit,
              address: this.addressedit,
              path: this.filelistedit.toString(),
              investmentCost: "",
              deadlineTime: "",
              isp: usenamedetails.userName
            }
          }
          api.update(editDetail, 'post').then(result => {
            // console.log(result);
            this.$msg({
              message: 'Successful resource modification',
              type: 'success',
              duration: '1000',
            });
            this.dialogVisibleEdit = false;
            this.otherImg = [];
            if(this.resourceTypeedit == "Tower"){
              this.api();
              this.details(this.resourceIdedit);
            }else{
              this.api();
              this.details(this.resourceIdedit);
            }
            // this.reload() // 调用方法
          }).catch(error => {
            console.log(error);
            this.$msg({
              message: 'Resource modification failed',
              type: 'error',
              duration: '1000',
            });
          })

        }

      },
      handleCloseEdit() {
        this.dialogVisibleEdit = false;
      },
      handleRemoveEdit(file, fileList) {
        console.log(file, fileList);
      },
      handlesuccessEdit(res, file) {
        // this.$msg({
        //   type: 'info',
        //   message: '图片上传成功',
        //   duration: 6000
        // });
        // console.log(file.response[0].path)
        this.otherImg.push(file.response[0].path);
        this.filelistedit = this.otherImg;
      },

      //删除基站
      deletedetails(resdetails) {
        this.dialogVisibleDElete = true;
        this.resourceIddetele = resdetails.resourceId;
      },
      handOkDElete() {
        const deleteDetail = {
          resourceId: this.resourceIddetele,
        }
        api.deletedetails(deleteDetail).then(result => {
          // console.log(result);
          this.$msg({
            message: 'Resource deleted successfully',
            type: 'success',
            duration: '1000',
          });
        }).catch(error => {
          console.log(error);
          this.$msg({
            message: 'Resource deletion failed',
            type: 'error',
            duration: '1000',
          });
        })
        this.dialogVisibleDElete = false;
        this.reload() // 调用方法

      },

      handleCloseDElete() {
        this.dialogVisibleDElete = false;
      },

      //资源申请
      resourceDetailsapply() {
        var usenamedetails=JSON.parse(localStorage.getItem('user_info'));
        const params = {
          resourceId: this.resdetails.resourceId,
          userName: usenamedetails.userName
        }
        api.isApplyRes(params).then(result => {
          console.log(result);
          if(result.data.applyCount == 0){
            // this.applybtn = true;
            this.resourceDetailsdiv = true;
          }else if(result.data.applyCount > 0){
            // this.applybtn = false;
            this.resourceDetailsdiv = false;
            this.$msg({
              message: 'Resources are pending',
              type: 'error',
              duration: '1000',
            });
          }
        }).catch(error => {
          console.log(error);
        })
      },
      handOkresource() {
        var usename = JSON.parse(localStorage.getItem('user_info'));
        console.log(usename.userName);
        if (this.applyopinion == "") {
          this.resourceDetailsdiv = true;
          this.$msg({
            message: 'Please fill in the "*"',
            type: 'error',
            duration: '1000',
          });
        } else {
          var current = this.current;
          var year = this.current.year;
          var month = this.current.month;
          var date = this.current.date;
          var datatime = year + "-" + month + "-" + date;
          // console.log(datatime);
          const deleteDetail = {
            resourceId: this.resdetails.resourceId,
            username: usename.userName,
            effectiveTime: datatime,
            opinion: this.applyopinion,
          }
          api.apply(deleteDetail).then(result => {
            // console.log(result);
            this.$msg({
              message: 'Successful resource application',
              type: 'success',
              duration: '1000',
            });
          }).catch(error => {
            console.log(error);
            this.$msg({
              message: 'Resource request failed',
              type: 'error',
              duration: '1000',
            });
            // this.$msg.error('资源申请失败');
          })
          this.resourceDetailsdiv = false;
          this.reload() // 调用方法
        }

      },
      handleCloseresource() {
        this.resourceDetailsdiv = false;
      },
      //资源终止
      resourceTerminationdiv() {
        // this.resourceTermination = true;
        var usenamedetails=JSON.parse(localStorage.getItem('user_info'));
        const params = {
          resourceId: this.resdetails.resourceId,
          userName: usenamedetails.userName
        }
        api.isApplyEnd(params).then(result => {
          if(result.data.applyCount == 0){
            this.resourceTermination = true;
          }else if(result.data.applyCount > 0){
            this.resourceTermination = false;
            this.$msg({
              message: 'The resource is being terminated',
              type: 'error',
              duration: '1000',
            });
          }
        }).catch(error => {
          console.log(error);
        })
      },
      handOkmination() {
        if (this.applyopinionmination == "") {
          this.resourceTermination = true;
          this.$msg({
            message: 'Please fill in the "*"',
            type: 'error',
            duration: '1000',
          });
        } else {
          var current2 = this.current2;
          var year2 = this.current2.year;
          var month2 = this.current2.month;
          var date2 = this.current2.date;
          var datatime2 = year2 + "-" + month2 + "-" + date2;
          console.log(datatime2);
          const deleteDetail = {
            resourceId: this.resdetails.resourceId,
            option: this.applyopinionmination,
            effectiveTime: datatime2,
          }
          api.end(deleteDetail).then(result => {
            // console.log(result);
            this.$msg({
              message: 'Resource termination succeeded',
              type: 'success',
              duration: '1000',
            });
          }).catch(error => {
            console.log(error);
            // this.$msg.error('资源终止失败');
            this.$msg({
              message: 'Resource termination failed',
              type: 'error',
              duration: '1000',
            });
          })
          this.resourceTermination = false;
          this.reload() // 调用方法
        }

      },
      handleClosemination() {
        this.resourceTermination = false;
      },
      //招标
      resourceInvestmentClick(){
        this.resourceInvestment = true;
      },
      handleCloseInvestment(){
        this.resourceInvestment = false;
      },
      // 招标按钮
      handOkInvestment(){
        if (this.investmentAmount == "") {
          this.resourceInvestment = true;
          this.$msg({
            message: 'Please fill in the "*"',
            type: 'error',
            duration: '1000',
          });
        } else if(Number(this.resdetails.investmentCost) > Number(this.investmentAmount) || Number(this.winningBidDetails.winningAmount) > Number(this.investmentAmount)){
          this.resourceInvestment = true;
          this.$msg({
            message: 'Amount cannot be less than last',
            type: 'error',
            duration: '1000',
          });
        }
        else {
          this.resourceInvestment = false;
          var usenamedetails=JSON.parse(localStorage.getItem('user_info'));
          const deleteDetail = {
            resourceId: this.resdetails.resourceId,
            userName: usenamedetails.userName,
            quotationAmount: this.investmentAmount,
          }
          api.auction(deleteDetail).then(result => {
            this.$msg({
              message: 'Successful bidding',
              type: 'success',
              duration: '1000',
            });
            this.investmentAmount = "";
          }).catch(error => {
            console.log(error);
            this.$msg({
              message: 'Tender failed',
              type: 'error',
              duration: '1000',
            });
          })
          this.details(this.resdetails.resourceId);
        }
      },
      //评论
      resourceEvaluateClick(){
        this.resourceEvaluate = true;
        const params = {
          resourceId: this.resdetails.resourceId,
        }
        api.showStationReport(params).then(result => {
          this.tableData2 = result.data;
        }).catch(error => {
          console.log(error);
        })
      },
      handleCloseEvaluate(){
        this.resourceEvaluate = false;
      },
      handOkEvaluate(taskId){
        this.resourceEvaluate = false;
        const params = {
          taskId:taskId
        }
        api.evaluate(params).then(result => {
          this.$msg({
            message: 'Report successful',
            type: 'success',
            duration: '1000',
          });
          this.details(this.resdetails.resourceId);
        }).catch(error => {
          console.log(error);
          this.$msg({
            message: 'Report failed',
            type: 'error',
            duration: '1000',
          });
        })
        // if (this.comments == "") {
        //   this.resourceEvaluate = true;
        //   this.$msg({
        //     message: 'Please fill in the "*"',
        //     type: 'error',
        //     duration: '1000',
        //   });
        // } else {
        //   this.resourceEvaluate = false;
        //   var usenamedetails=JSON.parse(localStorage.getItem('user_info'));
        //   const deleteDetail = {
        //     resourceId: this.resdetails.resourceId,
        //     content: this.comments,
        //     userName: usenamedetails.userName,
        //   }
        //   api.evaluate(deleteDetail).then(result => {
        //     this.$msg({
        //       message: 'Comment successful',
        //       type: 'success',
        //       duration: '1000',
        //     });
        //     this.comments = "";
        //   }).catch(error => {
        //     console.log(error);
        //     this.$msg({
        //       message: 'Comment failed',
        //       type: 'error',
        //       duration: '1000',
        //     });
        //   })
        //
        //   this.details(this.resdetails.resourceId);
        //
        // }
      },
      //查看详情页面
      details(resourceId) {
        var usenamedetails=JSON.parse(localStorage.getItem('user_info'));
        this.dialogVisibledat = true;
        const params = {
          resourceId: resourceId
        }
        api.getDetail(params).then(result => {
          if(result.status == 2){
            this.startingAmount = true;
            this.dayResource = true;
          }else{
            this.startingAmount = false;
            this.dayResource = false;
          }
          const params = {
            resourceId: resourceId,
            userName: usenamedetails.userName
          }
          api.haveApplied(params).then(result => {
            console.log(result);
            //判断按钮显示隐藏
            var usenamedetails=JSON.parse(localStorage.getItem('user_info'));
            if(usenamedetails.userName == "ChinaUnicom" || usenamedetails.userName =="ChinaTelecom"){
               if(result.state == "2"){
                 if(result.owner == usenamedetails.userName || result.owner==""){
                   this.deletebtn = true;
                   this.editbtn = true;
                   this.applybtn = false;
                   this.terminationbtn = false;
                   this.resEvaluate = false;

                 }else{
                   if(result.isp == usenamedetails.userName){
                     this.deletebtn = true;
                     this.editbtn = true;
                     this.applybtn = false;
                     this.terminationbtn = false;
                     this.resEvaluate = false;
                   }else{
                     this.deletebtn = false;
                     this.editbtn = false;
                     this.applybtn = false;
                     this.terminationbtn = false;
                     this.resEvaluate = false;
                   }
                   // this.deletebtn = false;
                   // this.editbtn = false;
                   // this.applybtn = false;
                   // this.terminationbtn = false;
                   // this.resEvaluate = false;
                 }
               }else{
                 if(result.owner == usenamedetails.userName){
                   if(result.applyCount>0){
                     this.deletebtn = false;
                     this.editbtn = false;
                     this.applybtn = false;
                     this.terminationbtn = true;
                     this.resEvaluate = true;
                   }else{
                     this.deletebtn = true;
                     this.editbtn = true;
                     this.applybtn = false;
                     this.terminationbtn = false;
                     this.resEvaluate = true;
                   }
                 }else{
                   // if(result.applyCount>0){
                   //   this.deletebtn = false;
                   //   this.editbtn = false;
                   //   this.applybtn = false;
                   //   this.terminationbtn = true;
                   //   this.resEvaluate = false;
                   // }else{
                   //   this.deletebtn = false;
                   //   this.editbtn = false;
                   //   this.applybtn = true;
                   //   this.terminationbtn = false;
                   //   this.resEvaluate = false;
                   // }
                   if(result.isp == usenamedetails.userName){
                     if(result.applyCount>0){
                       this.deletebtn = true;
                       this.editbtn = true;
                       this.applybtn = false;
                       this.terminationbtn = true;
                       this.resEvaluate = false;
                     }else{
                       this.deletebtn = true;
                       this.editbtn = true;
                       this.applybtn = true;
                       this.terminationbtn = false;
                       this.resEvaluate = false;
                     }

                   }else{
                     if(result.applyCount>0){
                       this.deletebtn = false;
                       this.editbtn = false;
                       this.applybtn = false;
                       this.terminationbtn = true;
                       this.resEvaluate = false;
                     }else{
                       this.deletebtn = false;
                       this.editbtn = false;
                       this.applybtn = true;
                       this.terminationbtn = false;
                       this.resEvaluate = false;
                     }
                   }
                 }

               }
            }else{
              this.terminationbtn = false;
              this.applybtn = false;
              this.editbtn = false;
              this.deletebtn = false;
              this.resEvaluate = false;
            }
          }).catch(error => {
            console.log(error);
          })
          this.resdetails = result;
          if(this.resdetails.resourceType == "Tower"){
            this.towerTowerId = false;
            this.towerNep = false;
          }else{
            this.towerTowerId = true;
            this.towerNep = true;
          }
          this.countTime();
          if(result.reportRes == undefined){
            this.nocomments = true;
          }else{
            this.nocomments = false;
          }
          this.reportRes = result.reportRes;
          if (result.path != "") {
            this.noPictures = false;
            this.imgUrlArray = result.path.split(",");
            this.imgUrl = 'http://122.192.9.52:20205' + result.path.split(",")[0];
            this.imgUrlArray.map((item) => {
              this.otherImg.push(item);
              // console.log(this.otherImg)
              this.filelistedit = this.otherImg
            });
          } else {
            this.imgUrl = "";
            this.imgUrlArray = [];
            this.noPictures = true;
          };
        }).catch(error => {
          console.log(error);
        })
      },
      //状态修改获取当前的状态
      modifyStatus(data) {
        this.dialogVisiblemod = true;
        this.radio = data.status;
      },
      handleClosedat(done) {
        this.otherImg = [];
        this.dialogVisibledat = false;
      },
      handleClosemod(done) {
        this.dialogVisiblemod = false;
      },

      //状态修改成功
      handOkstatus(data) {
        const params = {
          resourceId: data.resourceId,
          status: this.radio
        }
        api.updateStatus(params).then(result => {
          // this.$msg.success("状态修改成功");
          this.$msg({
            message: '状态修改成功',
            type: 'success',
            duration: '1000',
          });
          // 状态修改成功之后重新获取详情
          api.getDetail(params).then(result => {
            this.details(result.resourceId);
            this.resdetails = result;
          }).catch(error => {
            console.log(error);
          })

        }).catch(error => {
          console.log(error);
        })
        this.dialogVisiblemod = false;
      },
      //资源申请
      resourceapplication(done) {
        this.application = false;
      },
      applicationcancel(done) {
        this.application = false;
      },
      baiduMap(result) {
        let vue_p = this;
        var map = new BMap.Map("allmap", {enableMapClick: false});//不显示地图上的在哪里。
        map.centerAndZoom(new BMap.Point(116.4035, 39.915), 14);
        map.enableScrollWheelZoom(true);
        //地图上的城市定位
        // var size = new BMap.Size(528, 20);
        // var size = new BMap.Size(542, 20);
        // map.addControl(new BMap.CityListControl({
        //   anchor: BMAP_ANCHOR_TOP_LEFT,
        //   offset: size,
        // }));
        this.map = map;
        let imgArr = [];
        if (this.resourceTypeName == "BaseStation") {
          imgArr = ['5G_use.png', '5G_build.png', '5G_plan.png', '5G_maintain.png']
        } else {
          imgArr = ['tower_use.png', 'tower_build.png', 'tower_plan.png', 'tower_maintain.png']
        }

        // 向地图添加标注
        for (let i = 0; i < result.length; i++) {
          // console.log(result);
          // result[i]['longitude']=result[i]['longitude'].split("°E")[0];//经度
          // result[i]['latitude']=result[i]['latitude'].split("°N")[0];//纬度
          //单独设定图标的样式
          // var point = new BMap.Point(result[i]["latitude"],result[i]['longitude']);
          var point = new BMap.Point(result[i]["longitude"] + "", result[i]['latitude'] + "");
          var icon = new BMap.Icon(require("../../assets/" + imgArr[result[i]['status']]), new BMap.Size(32, 32), {imageSize: new BMap.Size(32, 32)});
          //创建一个图像标注实例。point参数指定了图像标注所在的地理位置
          var mkr = new BMap.Marker(point, {
            icon: icon
          });
          map.addOverlay(mkr);
          this.addMarker1(mkr, result[i]['resourceId']);

        }
        ;

      },
      //点击地图上的小图标展示详情
      addMarker1(mkr, id) {
        let vue_p = this;
        mkr.addEventListener("click", function (e) {
          const getOne = {
            resourceId: id,
          }
          api.getOne(getOne).then(result => {
            // console.log(result);
            var item = "<div id='info-item'>" +
              "<div style='color:rgba(0,0,0,1);font-size:18px;'>" + result['resourceName'] + "</div>" +
              "<div style='display: flex;justify-content: space-between;margin-top: 3px'>" +
              "<span style='color:rgba(0,0,0,0.65);font-size: 12px'>" + result['resourceId'] + "</span>" +
              "<div><span style='width: 6px;height: 6px;margin-top: 3px;background: " + (result['status'] == 0 ? '#F26E15' : result['status'] == 1 ? '#07BE1B' : result['status'] == 2 ? '#0093FE' : result['status'] == 3 ? '#AEAEAE' : '') + ";border-radius: 50%;display: inline-block;'></span>" +
              "<span style='font-size: 12px;margin-left: 5px'>" + (result['status'] == 0 ? 'Running' : result['status'] == 1 ? 'Constructing' : result['status'] == 2 ? 'Planning' : result['status'] == 3 ? 'Maintaining' : '') + "</span>" +
              "</div>" +
              "</div>" +
              "<div style='margin-top: 20px;color:rgba(0,0,0,0.85);font-size: 14px;margin-bottom: 10px;'><span>AreaCode：</span><span>" + result['areaCode'] + "</span></div>" +
              "<div style='color:rgba(0,0,0,0.85);font-size: 14px;margin-bottom: 10px;'><span>Address：</span><span>" + result['address'] + "</span></div>" +
              "<div style='color:rgba(0,0,0,0.85);font-size: 14px;margin-bottom: 10px;'><span>Longtitude：</span><span>" + result['longitude'] + "</span>°E</div>" +
              "<div style='color:rgba(0,0,0,0.85);font-size: 14px;margin-bottom: 10px;'><span>Latitude：</span><span>" + result['latitude'] + "</span>°N</div> " +
              "<button type='button' id='btn" + result['resourceId'] + "' " + "style='width: 74px;height: 32px;background:rgba(80,105,228,1);border-radius:2px;color: white;text-align: center;line-height: 32px;margin-top: 20px;border: none;outline: none;'>Details</button>" +
              "</div>";

            var p = e.target;
            var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            var opts = {
              width: 316,     // 信息窗口宽度
              // height: 250,     // 信息窗口高度
            };
            var infoWindow = new BMap.InfoWindow(item, opts);  // 创建信息窗口对象
            this.map.openInfoWindow(infoWindow, point); //开启信息窗口
            setTimeout(() => {
              var bt = document.getElementById('btn' + result['resourceId']);
              bt.onclick = function (e) {
                vue_p.details(result['resourceId']);
                vue_p.toggleTabs(0);
              }
            }, 20)
          }).catch(error => {
            console.log(error);
          })
        });
      },
      toggleTabs: function (index) {
        this.nowIndex = index;
      },
      btnIng(type) {
        this.btnIngdialogVisible = true;
        this.btnmap(type);
      },
      btnInghandleClose() {
        this.btnIngdialogVisible = false;
      },
      btnmap(type) {
        let that = this;
        setTimeout(() => {
          var map = new BMap.Map("mapcontainer", {enableMapClick: false});
          var point = new BMap.Point(116.404, 39.915);
          map.centerAndZoom(point, 14);
          map.enableScrollWheelZoom(true);
          map.addEventListener("click", showInfo);

          function showInfo(e) {
            // alert(e.point.lng + ", " + e.point.lat);
            if (type == 'add') {
              that.latitude = e.point.lat;
              that.longitude = e.point.lng;
            }
            else if (type == 'edit') {
              that.latitudeedit = e.point.lat;
              that.longitudeedit = e.point.lng;
            }
            that.btnIngdialogVisible = false;
          }
        }, 200)

      },
      // 倒计时
      countTime () {
        // 获取当前时间
        // let date = new Date(this.resdetails.quotationDeadline)
        let date = new Date()
        let now = date.getTime();
        // 设置截止时间
        let endDate = new Date(this.resdetails.quotationDeadline) // this.curStartTime需要倒计时的日期
        let end = endDate.getTime()
        // 时间差
        let leftTime = end - now
        // 定义变量 d,h,m,s保存倒计时的时间
        if (leftTime >= 0) {
          // 天
          this.day = Math.floor(leftTime / 1000 / 60 / 60 / 24)
          // 时
          let h = Math.floor(leftTime / 1000 / 60 / 60 % 24)
          this.hour = h < 10 ? '0' + h : h
          // 分
          let m = Math.floor(leftTime / 1000 / 60 % 60)
          this.min = m < 10 ? '0' + m : m
          // 秒
          let s = Math.floor(leftTime / 1000 % 60)
          this.second = s < 10 ? '0' + s : s
        } else {
          this.day = 0
          this.hour = '00'
          this.min = '00'
          this.second = '00'
        }
        // 等于0的时候不调用
        if (Number(this.hour) === 0 && Number(this.day) === 0 && Number(this.min) === 0 && Number(this.second) === 0) {
          this.investedBtn=true;
          this.timeBtn=false;
          this.nowinningAmount=false;
          this.winningAmount=true;
          this.investment = false;
          this.getAllByRes(this.resdetails.resourceId);//不加密
          this.notencryption = true;
          this.encryption = false;
          return
        } else {
          // 递归每秒调用countTime方法，显示动态时间效果,
          setTimeout(this.countTime, 1000)
          this.investedBtn=false;
          this.timeBtn=true;
          this.nowinningAmount=true;
          this.winningAmount=false;
          var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
          if(this.resdetails.isp == usenamedetails.userName){
            this.investment = false;
          }else{
            if(usenamedetails.userName == "ChinaUnicom" || usenamedetails.userName =="ChinaTelecom"){
              this.investment = false;
            }else{
              this.investment = true;
            }
          }
          this.getEncryptByRes(this.resdetails.resourceId);//加密
          this.encryption = true;
          this.notencryption = false;
        }
      },
      // 招标详情不加密
      getAllByRes(resourceId){
        const params = {
          resourceId: resourceId,
        }
        api.getAllByResourceId(params).then(result => {
          const { data = {} } = result;
          this.noWinningBidDetails = data;
          this.tableData1 = data.quotations;
        }).catch(error => {
          console.log(error);
        })
      },
      // 招标详情加密
      getEncryptByRes(resourceId){
        var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
        const params = {
          resourceId: resourceId,
          userName: usenamedetails.userName,
        }
        api.getEncryptInfo(params).then(result => {
          console.log(result);
          const { data = {} } = result;
          this.winningBidDetails = data;
          this.tableData = data.quotations;
        }).catch(error => {
          console.log(error);
        })

        // try {
        //
        //   const result = await api.getEncryptInfo(params);
        //   this.winningBidDetails = result.data;
        //   this.tableData = result.data.quotations;
        //   console.log(result)
        // } catch (e) {
        //   console.log('error  ===>', e);
        // }
      },
      //判断用户不是CU或者CT
      usernameYes(){
        var usenamedetails = JSON.parse(localStorage.getItem('user_info'));
        console.log(usenamedetails.userName);
        if(usenamedetails.userName!="ChinaUnicom" && usenamedetails.userName!="ChinaTelecom"){
           this.userNameYes = false;

        }
      },
    },
    mounted() {
      this.api();
      this.usernameYes();
    }

  }
</script>

<style lang="less" scoped>
  .basestationName {
    position: absolute;
    left: 20px;
    top: 20px;
    display: flex;
  }

  .basestationContent {
    position: absolute;
    left: 161px;
    top: 60px;
    width: 308px;
    background: white;
    font-size: 14px;
    /*border: solid 1px #bebebe;*/
    border-top: none;
    background: rgba(255, 255, 255, 1);
    box-shadow: 0px 1px 4px 0px rgba(161, 162, 167, 0.54);
    border-radius: 2px;
  }

  .basestationContent1 {
    position: absolute;
    left: 161px;
    top: 60px;
    width: 308px;
    background: white;
    font-size: 14px;
    /*border: solid 1px #bebebe;*/
    border-top: none;
    max-height: 522px;
    overflow-y: scroll;
    background: rgba(255, 255, 255, 1);
    box-shadow: 0px 1px 4px 0px rgba(161, 162, 167, 0.54);
    border-radius: 2px;
  }

  .searchRecord {
    line-height: 30px;
    height: 30px;
    background: rgba(241, 241, 241, 1);
    display: flex;
    justify-content: space-between;
    padding-left: 16px;
    padding-right: 16px;
  }

  .serRecord {
    /*margin-left: 18px;*/
    /*margin-top: 10px;*/
    overflow-y: scroll;
    /*height: 100px;*/
  }

  .serRecord div {
    /*margin-bottom: 5px;*/
    line-height: 32px;
  }

  .recongImg {
    width: 14px;
    height: 14px;
    margin-right: 5px;
    float: left;
    margin-top: 9px;
  }

  .basestationInput {
    width: 308px;
    height: 40px;
    background: rgba(255, 255, 255, 1);
    /*box-shadow:0px 1px 4px 0px rgba(161,162,167,0.54);*/
    border: none;
    outline: none;
    padding: 0px 16px;
    box-sizing: border-box;
    /*border: solid 1px #bebebe;*/
    box-shadow: 0px 1px 4px 0px rgba(161, 162, 167, 0.54);
    border-radius: 2px;
  }

  .basestationInput::-webkit-input-placeholder {
    color: rgba(187, 187, 187, 1);
  }

  .serch {
    width: 60px;
    height: 40px;
    position: absolute;
    left: 100%;
    top: 0px;
    background: rgba(80, 105, 228, 1);
    border-radius: 0px 2px 2px 0px;
  }

  .cleanInput {
    position: absolute;
    right: 6px;
    top: 25%;
  }

  .add {
    width: 48px;
    height: 48px;
    background: rgba(80, 105, 228, 1);
    box-shadow: 0px 1px 4px 0px rgba(161, 162, 167, 0.54);
    position: absolute;
    right: 20px;
    top: 20px;
    border-radius: 50%;
  }

  .addImg {
    width: 21px;
    height: 21px;
    float: left;
    margin-left: 13px;
    margin-top: 12px;
  }

  .details {
    width: 40px;
    height: 30px;
    position: absolute;
    right: 30px;
    top: 100px;
    border-radius: 50%;
    background: blue;
  }

  .resourceRelease {
    width: 100%;
    height: 400px;
    /*overflow-x: hidden;*/
    /*overflow-y: scroll;*/
    /*font-size:13px;*/
  }

  .resourceDetails {
    width: 100%;
    height: 400px;
  }

  .releaseTitle, .detailsTitle {
    height: 50px;
    line-height: 50px;
    display: flex;
    background: ghostwhite;
    justify-content: space-between;
    padding: 0px 15px;
  }

  .information {
    /*padding: 20px;*/

  }

  .basicInformation, .locationInformation {
    border-bottom: solid 1px #E9E9E9;
    /*padding-left: 15px;*/
    /*padding: 20px;*/
  }

  select {
    width: 227px;
    height: 32px;
    border-radius: inherit;
    border: solid 1px #bebebe;
    /*Chrome和Firefox里面的边框是不一样的，所以复写了一下*/
    /*border: solid 1px #000;*/
    /*很关键：将默认的select选择框样式清除*/
    appearance: none;
    -moz-appearance: none;
    -webkit-appearance: none;
    /*在选择框的最右侧中间显示小箭头图片*/
    background: url("../../assets/down.png") no-repeat scroll right center transparent;
    /*为下拉小箭头留出一点位置，避免被文字覆盖*/
    padding-right: 14px;
    outline: none;
    border: 1px solid rgba(0, 0, 0, 0.15);
    padding: 5px;
    box-sizing: border-box;
    background-size: 10px;
    background-position: 97% 50%;
  }

  /*清除ie的默认选择框样式清除，隐藏下拉箭头*/
  select::-ms-expand {
    display: none;
  }

  .locationInformation, .resourcePhoto {
    margin-top: 15px;
  }

  .bmationName {
    /*text-align: right;*/
    width: 35%;
    display: inline-block;
    margin-right: 5px;
    position: relative;
    line-height: 35px;
    margin-bottom: 8px;
  }

  .bmationName:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    right: 9px;
    top: 1px;
  }

  .bmationName2 {
    /*text-align: right;*/
    width: 35%;
    display: inline-block;
    margin-right: 5px;
    position: relative;
    line-height: 35px;
    margin-bottom: 8px;
  }

  .bmationName2:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    right: 21px;
    top: 1px;
  }
  .bmationNameRes {
    /*text-align: right;*/
    width: 35%;
    display: inline-block;
    margin-right: 5px;
    position: relative;
    line-height: 35px;
    margin-bottom: 8px;
  }

  .bmationNameRes:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    right: -2px;
    top: 1px;
  }
  .bmationNameRes1 {
    /*text-align: right;*/
    width: 35%;
    display: inline-block;
    margin-right: 5px;
    position: relative;
    line-height: 35px;
    margin-bottom: 8px;
  }

  .bmationNameRes1:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    right: 3px;
    top: 1px;
  }
  .bmationName3 {
    /*text-align: right;*/
    width: 25%;
    display: inline-block;
    margin-right: 5px;
    position: relative;
    line-height: 35px;
    margin-bottom: 8px;
  }

  .bmationName3:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    right: 11px;
    top: 1px;
  }

  .bmationName4 {
    /*text-align: right;*/
    width: 35%;
    display: inline-block;
    margin-right: 5px;
    position: relative;
    line-height: 35px;
    margin-bottom: 8px;
  }

  .bmationName4:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    right: 26px;
    top: 1px;
  }
  .bmationName5 {
    /*text-align: right;*/
    width: 35%;
    display: inline-block;
    margin-right: 5px;
    position: relative;
    line-height: 35px;
    margin-bottom: 8px;
  }

  .bmationName5:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    right: -3px;
    top: 1px;
  }


  .bmation {
    /*justify-content: space-between;*/
    display: flex;
    margin-bottom: 35px;
    margin-left: 10px;
    position: relative;
  }

  .inputs {
    width: 228px;
    height: 32px;
    outline: none;
    border: 1px solid rgba(0, 0, 0, 0.15);
    padding: 5px;
    box-sizing: border-box;
  }

  .item-row {
    /*width: 30%;*/
  }

  .bmationScond {
    display: flex;
    margin-bottom: 20px;
    margin-left: 20px;
    justify-content: space-between;
    width: 54.5%;
  }
  .bmationScond1 {
    display: flex;
    margin-bottom: 20px;
    margin-left: 20px;
    justify-content: space-between;
    width: 60%;
  }

  .detailsPhoto {
    width: 187px;
    height: 160px;
    /*background-image: url(../../assets/logo5g.png);*/
    /*background:red;*/
    border-radius: 2px;
  }

  .point {
    width: 5px;
    height: 5px;
    border-radius: 50%;
    display: inline-block;
    background: #F26E15;
  }

  .pointg {
    width: 5px;
    height: 5px;
    border-radius: 50%;
    display: inline-block;
    background: #07BE1B;
  }

  .pointb {
    width: 5px;
    height: 5px;
    border-radius: 50%;
    display: inline-block;
    background: #0093FE;
  }

  .pointy {
    width: 5px;
    height: 5px;
    border-radius: 50%;
    display: inline-block;
    background: #AEAEAE;
  }

  .usetype {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.25);
  }

  .typeStyle {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.85);
    margin-bottom: 4px;
  }

  .typestyleN {
    text-align: center;
    margin-right: 49px;
  }

  .stateModification {
    width: 100px;
    height: 30px;
    background: red;
    text-align: center;
    color: white;
  }

  .resourceApplication {
    width: 100px;
    height: 30px;
    background: red;
    text-align: center;
    color: white;
  }

  .detailsMiddle {
    margin-left: 30px;
    width: 75%;
  }

  .tabs {
    width: 500px;
    height: 30px;
    padding: 0;
    margin-top: 44px;
    line-height: 30px;
    display: flex;
  }

  .li-tab {
    width: 50%;
    height: 100%;
    display: inline-block;
    text-align: center;
    height: 46px;
    display: inline-table;
  }

  /*.divTab{*/
  /*width: 200px;*/
  /*height: 300px;*/
  /*}*/
  .basicInfor {
    display: flex;
    margin-left: 20px;
    margin-bottom: 30px;
  }

  .basicInfor div {
    width: 30%;
  }

  .basicphoto {
    width: 187px;
    height: 160px;
    /*background: red;*/
    margin-bottom: 10px;
  }

  .resourceModification .el-dialog {
    margin-top: 35vh !important;
  }

  .clearImg {
    width: 20px;
    height: 20px;
  }

  .searchImg {
    width: 20px;
    height: 20px;
    /* display: table-cell; */
    /* vertical-align: middle; */
    position: absolute;
    top: 30%;
    left: 30%;
  }

  .fivegImg {
    width: 32px;
    height: 32px;
  }

  .dropdown-btn {
    /*height: 41px;*/
    /*border-radius: 0;*/
    /*width: 133px;*/
    height: 40px;
    border-radius: 0;
    width: 133px;
    border: none;
    box-shadow: 0px 1px 4px 0px rgba(161, 162, 167, 0.54);
  }
</style>
<style>
  .el-dialog__header {
    background: #f8f8f8;
  }

  .lulu .el-dialog__body {
    padding: 0px 0px;
  }

  .el-dialog__footer {
    text-align: center;
  }

  .el-button--primary {
    color: #FFF;
    background-color: #5069E4;
    border-color: #5069E4;
  }

  .el-button--default {
    border: 1px solid #D9D9D9;
    color: rgba(0, 0, 0, 0.65);
  }

  .el-button--primary:focus, .el-button--primary:hover {
    background: #5069E4;
    border-color: #5069E4;
    color: #FFF;
  }

  .el-button--default:focus, .el-button--default:hover {
    color: #5069E4;
    border-color: #5069E4;
    background-color: #FFF;
  }

  .resourceDetails .active {
    /*background: #eee;*/
    border-bottom: solid 2px #4C67EC;
    color: #4C67EC;

  }

  .el-message {
    top: 50% !important;
  }

  .resourcePhoto .el-dialog__wrapper {
    display: none;
  }
</style>
<style>
  .sharingDate:before {
    position: absolute;
    font-size: 14px;
    content: "*";
    color: red;
    left: -12px;
    top: 1px;
  }

  /*日历的样式*/
  .agm-datepicker {
    position: relative;
    width: 200px;
    height: 36px;
    color: #515a6e;
  }

  .agm-input {
    height: 100%;
    width: 100%;
    box-sizing: border-box;
    border: 1px solid #bfcbd7;
    border-radius: 3px;
    cursor: pointer;
  }

  .agm-input input {
    height: 100%;
    width: 100%;
    box-sizing: border-box;
    padding: 5px 10px;
    border: none;
    background: none;
    font-size: 14px;
    outline: none;
  }

  .agm-datepicker-content .active {
    /*box-shadow: 0 0 0 2px rgba(45, 140, 240, 0.2);*/
    /*border: 1px solid #20a0ff;*/
    background: green;
    color: white;
  }

  .agm-datepicker-wrap {
    width: 240px;
    box-shadow: 0 1px 6px rgba(0, 0, 0, 0.2);
    border-radius: 4px;
    z-index: 10;
    position: absolute;
    top: 100%;
    left: 0;
    margin: 5px 0;
    background-color: #fff;
  }

  .agm-datepicker-header {
    padding: 0px 10px;
    font-size: 14px;
    text-align: center;
    line-height: 36px;
    border-bottom: 1px solid #e8eaec;
  }

  .agm-datepicker-header-btn {
    cursor: pointer;
    transition: color 0.2s ease-in-out;
    user-select: none;
  }

  .agm-datepicker-header-btn:hover {
    color: #008afe;
  }

  .agm-datepicker-header-btn-pre {
    float: left;
    padding-right: 5px;
    font-size: 18px;
    transform: scale(0.5, 1);
  }

  .agm-datepicker-header-btn-next {
    padding-left: 5px;
    float: right;
    font-size: 18px;
    transform: scale(0.5, 1);
  }

  .agm-datepicker-content {
    position: relative;
  }

  .agm-datepicker-content table {
    width: 100%;
    border-collapse: collapse;
  }

  .agm-datepicker-content table thead {
    line-height: 30px;
    font-size: 12px;
    color: #999;
  }

  .agm-datepicker-content table tbody tr {
    line-height: 28px;
  }

  .agm-datepicker-content table tbody td {
    font-size: 12px;
    text-align: center;
    cursor: pointer;
    border-radius: 3px;
    transition: all 0.2s ease-in-out;
  }

  .agm-datepicker-content table tbody td .active:hover {
    color: #fff;
    background: #008afe;
  }

  .agm-datepicker-content table tbody td .flag {
    color: #999;
  }

  .agm-datepicker-content table tbody td:hover {
    background: #eee;
  }

  .agm-date-picker-year-panel {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: #fff;
    font-size: 14px;
  }

  .agm-date-picker-year-panel .panel-list {
  @include list(row);
    height: 100%;
  }

  .agm-date-picker-year-panel .panel-item {
  @include list(row);
    justify-content: center;
    align-items: center;
    width: 33.33333%;
    height: 25%;
  }

  .agm-date-picker-year-panel .panel-item-inner {
    width: 80%;
    text-align: center;
    padding: 10px 0;
    border-radius: 3px;
    transition: all 0.2s ease-in-out;
    cursor: pointer
  }

  .agm-date-picker-year-panel .panel-item-inner:hover {
    background-color: #e1f0fe;
  }

  .agm-date-picker-year-panel .panel-item-inner .active {
    background-color: #e1f0fe;
  }

  input, select {
    box-sizing: border-box;
  }

  .flexPos {
    position: absolute;
    left: 35%;
  }

  .posFlex {
    position: absolute;
    left: 70%;
  }

  .fiveTitle {
    font-size: 14px;
  }

  .infoStyle {
    margin-top: 26px;
    font-size: 14px;
    font-family: PingFangSC-Medium, PingFang SC;
    font-weight: 500;
    color: rgba(0, 0, 0, 0.85);
    padding-left: 7px;
    border-left: 3px solid rgb(80, 105, 228);
  }

  .colorStyle {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.45);
    display: inline-block;
    width: 37%;
    text-align: right;
  }

  .titlecolorStyle {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.85);
  }

  .mapicon {
    position: absolute;
    left: 20px;
    bottom: 28px;
    /*background: red;*/
    /*text-align: center;*/
    /*border-radius: 5px;*/
    /*margin-left: 16px;*/
    width: 117px;
    /*height:132px;*/
    background: rgba(255, 255, 255, 1);
    box-shadow: 0px 1px 4px 0px rgba(161, 162, 167, 0.54);
    border-radius: 2px;
    /*padding: 10px;*/
    /*box-sizing: border-box;*/
  }

  .mapiconTitle {
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.65);
  }

  .selectLaandlo {
    /*background: #5069E4;*/
    color: #ffffff;
    /*border: solid 1px #5069E4;*/
    height: 30Px;
    line-height: 30px;
    font-size: 13px;
    cursor: pointer;
    border-radius: 4px;
  }

  .selectwhatType {
    display: flex;
    justify-content: space-between;
    margin-left: 20px;
    margin-right: 20px;
    margin-top: 19px;
    margin-bottom: 19px
  }

  .clicktypeItem {
    display: flex;
    justify-content: space-between;
    /*margin-bottom: 13px;*/
    /*padding-bottom: 11px;*/
    border-bottom: solid 1px #EAEAEA;
    padding: 13px 15px;
  }

  .basicHeader {
    margin-bottom: 20px;
    display: block;
    padding-left: 7px;
    border-left: solid 3px #5069E4;
    color: rgba(0, 0, 0, 0.85);
    font-size: 14px;
  }

  .inputs::-webkit-input-placeholder {
    color: rgba(0, 0, 0, 0.25);
  }

  select:invalid {
    color: rgba(0, 0, 0, 0.25);
  }

  .hoverSeacsh {
    padding: 5px 18px;
    box-sizing: border-box;
  }

  .hoverSeacsh:hover {
    background: #F8F8F8;
    cursor: pointer;

  }

  .clicktypeItem:hover {
    background: #F8F8F8;
    cursor: pointer;

  }

  .ui_city_change_inner {
    padding: 19px !important;
    line-height: 3px !important;
  }

  .citylist_popup_main .city_content_top {
    height: 45px !important;
  }

  #selCityWd {
    margin-top: 3px !important;
  }
  .avatar{
    width: 62px;
    height: 62px;
  }
  .avatarTelephone{
    font-size:18px;
    color:rgba(0,0,0,1);
  }
  .avatarInprogress{
    width:70px;
    height:18px;
    background:rgba(255,166,0,1);
    border-radius:2px;
    /*opacity:0.2;*/
    margin-left: 750px;
    text-align: center;
    line-height: 18px;
  }
  /*.avatarInprogressTitle{*/
    /*color: #FFA600;*/
  /*}*/
  .avatarSolved{
    width:70px;
    height:18px;
    background:rgba(82,196,26,1);
    border-radius:2px;
    /*opacity:0.2;*/
    margin-left: 750px;
    text-align: center;
    line-height: 18px;
  }
  /*.avatarSolvedTitle{*/
    /*color: #52C41A;*/
  /*}*/
  .avatarTitle{
    font-size:14px;
    color:rgba(0,0,0,0.85);
    margin-top: 14px;
  }
  .avatarphoto{
    width: 79px;
    height: 71px;
    margin-top: 14px;
  }
  .avatarTime{
    font-size:12px;
    color:rgba(0,0,0,0.65);
    margin-top: 14px;
    margin-bottom: 24px;
  }
  .starGray{
    width: 22px;
    height: 22px;
    margin-right: 12px;
    margin-top: 14px;
  }
  .timeSelect .el-input__icon{
    display: none;
  }
  .timeSelect .el-input__inner{
    width: 228px;
    height: 32px;
    outline: none;
    border: 1px solid rgba(0, 0, 0, 0.15);
    padding: 5px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }
  .investmentWidth{
    width: 15%;
  }
  textarea{
    border: 1px solid rgba(0, 0, 0, 0.15);
    padding: 5px;
    box-sizing: border-box;
  }
  textarea::-webkit-input-placeholder {
    color: rgba(0, 0, 0, 0.25);
  }
  .Invested{
    text-align: center;
    width: 64px;
    height: 18px;
    line-height: 18px;
    background: #d6f4ce;
    color: #52C41A;
    border-radius:2px;
  }
  .BMap_shadow{
    display: none;
  }
  .BMap_pop div{
    border-top: none!important;
    border-left: none!important;
    border-bottom: none!important;
    border-right: none!important;
  }
  .BMap_pop .BMap_bottom {
    border-bottom: none!important;
  }
  .BMap_pop .BMap_center {
    border-left: none!important;
    border-right: none!important;
  }
  .ant-dropdown-placement-bottomLeft .ant-dropdown-menu-item{
    margin-left: 7px;

  }
  .add:hover{
    background: #213ED0;
  }
</style>
