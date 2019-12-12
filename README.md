# tmf

#### 介绍

TMF 是一个5G共享应用平台
如何降低CAPEX和OPEX是5G时代运营商面临的最根本挑战
5G网络投资巨大:
    5G网络基站数量将是4G的4到5倍
    5G基站功耗是4G基站的2.5-4倍，运营成本也将激增
    预计中国5G投资周期十年，总投资金额1.6万亿
共享经济实践：5G网络共建共享
利用共享经济的商业模式，共建共享5G网络可能是应对当前挑战的选择之一
方案：
    在运营商和铁塔等的传统通信运营商间展开基站、铁塔、光线等基础设施的共建共享，能在现有框架下大幅降低建设投资
    通过开放利益，引入外部投资方，能实现更大规模的共建共享，分摊运营商投资风险，加快5G建设步伐

#### 软件架构

软件架构说明

  1. 开发环境:
      * 语言:java 8
      
      * 依赖管理: Maven
      
      * 数据库:MySQL 5.7
      
  2. 后端 :

      * 基础框架: Spring SSM

      * 持久层框架: MyBatis 3.2.8

      * 数据库连接池: 阿里巴巴Druid 1.0.9

      * 日志打印: log4j

      * 其他: Gson 

  3. 前端 : 

      * Vue 2.6.10
      
      * axios 0.19.0

      * ant-design-vue 1.4.4

      * echarts 4.4.0

      * vue-router 3.0.1

      * vuex 3.1.1

      * webpack


#### 功能模块: 

> User Sign In

> > 用户分两种: 运营商和第三方
> >
> > > 运营商具有最高权限
> > >
> > > 第三方具有竞标和查看以及自己所拥有的基站或塔的审批
> > >
> > > 


> Resource Management
>
> > 资源分布情况, 资源运行状态, 新建资源(塔或基站), 查找资源, 资源详情,资源修改,资源删除

> Resource Statistics
>
> > 资源统计模块, 分为基站统计和塔统计两个部分
> >
> > > 统计分为总数, 各运营商占比, 各地区占比, 各资源状态占比, 租入租出占比, 各个月份各运营商租出数量, 第三方拥有资源数量情况及收益情况等

> Application & Approval
>
> > 资源审批模块, 分为待审批, 已审批, 我的申请

> Ops & Maintenance
>
> > 报障维护模块
> >
> > > 普通用户对基站的报障情况, 运营商对其进行审批,分为待审批和已审批

> Settlement
>
> > 收益模块, 分为租入支出, 租出收入以及收益情况
  

#### 后端各个控制器及其功能: 

1. BidController: 竞价-->接口示例:	/bid/**
      * /auction: 个人竞价
      * /getAllByResourceId: 竞价信息展示
      * /getEncryptInfo: 得到加密的竞价信息展示
   
2. CodeController: 验证码控制类-->接口示例:   /code/**
      * /send: 发送验证码
      * /verify: 验证验证码是否正确
      * /report: 报障
      * /mark: 打分
      * /process: 报障审批
      * /haveReported: 是否申请过
      * /fetchList: 手机端查询
      * /fetchListPC: PC端查询
      * /getOne: 点击审批，获取报障信息
      * /getDetailByTaskId: 点击基站名称获取详情	

 3. CountController:资源统计-->接口示例:   /count/**
      * /getTotalCount: 查询总的基站数量
      * /getUnicomCount: 查询联通的基站数量
      * /getTelecomCount: 查询电信的基站数量
      * /getCountByResourceType:根据资源类型查询总数/联通总数/电信总数
      * /getPercent: 查询联通和电信的基站数量所占百分
      * /getStationStatus: 总的使用中,建设中,规划中,维护中
      * /getTotalUsing: 总的使用中
      * /getTotalBuilding: 总的建设中
      * /getTotalPlaning: 总的规划
      * /getTotalMaintenance: 总的维护
      * /getUnicomPercent: 联通使用中,建设中,规划中,维护中占联通总的百分比
      * /getTelecomPercent: 电信使用中,在建中,规划中,维护中占电信总的百分比
      * /getUnicomRent: 联通租入租出
      * /getTelecomRent: 电信租入租出
      * /getRentMonth: 联通和电信基站租赁走势统计
      * /getMapCount: 北京各区基站数量
      * /getInvestorCount: 第三方统计
      * /getPlanStation: 规划中基站分布
      * /getInvestorCost: 第三方中标金额总和

4. ProgressController: 待办审批-->接口示例:   /progress/**
       * /updateResourceApply: 联通和电信收到的资源申请的审批
       * /updateTerminationApply: 联通和电信收到的终止申请的审批
       * /getUnicomUnfinishedByCondition: 根据条件查询联通待办
       * /getUnicomFinishedByCondition: 根据条件查询联通已办
       * /getUnicomApplyByCondition: 根据条件查询联通的申请
       * /getTelecomUnfinishedByCondition: 根据条件查询电信待办
       * /getTelecomFinishedByCondition: 根据条件查询电信已办
       * /getTelecomApplyByCondition: 根据条件查询电信的申请
       * /updatePlaning: 第三方申请建站的审批

5. ResourceController: 资源管理-->接口示例:  /resources/**
       * /create: 新增
       * /update: 修改
       * /delete: 删除
       * /getResByName: 通过资源名称获取资源
       * /getOne: 得到一条信息
       * /getDetail: 得到资源的详细信息
       * /getAllInfo: 得到资源的所有信息，包括申请记录
       * /getMore: 得到最新的5条信息
       * /updateStatus: 更新基站状态
       * /getResByStatus: 通过状态获取基站
       * /getAll: 得到所有的资源信息
       * /apply:资源申请
       * /deleteAllInfo: 删除所有的搜索记录
       * /getNewInfo: 得到最新的10条记录
       * /end: 资源终止
       * /haveApplied: 该资源是否租出
       * /isApplyRes: 该资源是否已经申请过
       * /isApplyEnd: 该资源是否已经终止过
       * /evaluate: 用户评论上链
       * /showStationReport: 根据基站详情--获取用户报障信息

6. SettlementController: 结算-->接口示例:   /rent/**
       * /findRentInSettlement: 租入结算
       * /findRentOutSettlement: 租出结算
       * /getInvestorApply: 第三方申请建站,竞标详情显示
       * /getISPSharing: 运营商收入
       * /getInvestmentSharing: 第三方收入

 7. UploadDownController:封装了文件上传和下载的控制层-->接口示例:   /upload/**
       * /getFile: 文件上传

 8. UserController: 用户控制-->接口示例: /user/**
       * /login: 登录

#### 用到中间件:

  1. nginx 1.8.0: 前端工程存放目录
  
  2. tomcat 8.5.47: 后端工程存放目录

#### 安装教程

1. 安装jdk1.8
2. 安装MySQL数据库并修改配置文件中数据库连接地址
3. 使用IDEA打开


#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

