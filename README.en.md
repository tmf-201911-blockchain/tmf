# Blockchain-based 5G Collaboration Economy

#### Project Introduction

The 5G network infrastructure investment is astronomical. To tackle the CAPEX and OPEX challenges CSPs are facing in 5G era, CSPs like China Unicom and China Telecom have launched the 5G network infrastructure co-building and co-sharing strategy, which is a practice of collaborative economy business model. 

However, there are trust and efficiency issues to be addressed when carrying out the collaborations between multi-parties. Promisingly, these technical challenges could be facilitated by the features of blockchain. 

In a word, this catalyst aims at exploring the feasible solutions for 5G infrastructure co-building and co-sharing by utilizing the blockchain technology.

This repository contains all components of the demo showcased in the TMF 2019 DTA KL events, including the frontend design, backend code as well the runtime required. Hopefully, this work could be a reference for the relevant solution or even spurs some creative contribution based on that.
#### Software Structure

1. Runtime:
- Language: Java 8
- Dependency Management: Maven
- Database: MySQL 5.7
2. Backend:
- Framework: Spring SSM
- Persistence Framework: MyBatis 3.2.8
- Database Connection Pool: Alibaba Druid 1.0.9
- Log Printing: log4j
-Others: Gson 
3. Frontend: 
- Vue 2.6.10
- Axios 0.19.0
- Ant-design-vue 1.4.4
- Echarts 4.4.0
- Vue-router 3.0.1
- Vuex 3.1.1
- Webpack

#### Function Modules:

> User Sign In

> > Two Types of Users: CSP User and Third-party User
> >
> > > CSP User is designed to own all the operation rights in the platform
> > >
> > > Third-party User is allowed to:
Participate in resource bidding; 
Check the status of invested resources; 
Approve/refuse the renting application for the resource they published(auxiliary resources like tower).
> > >
> > > 

> Resource Management
>
> > Resource GIS location, Resource Status, Initialize New Resource(tower or base station),Resource Query, Resource Specification, Resource Modification, Resource Deletion

> Resource Statistics
>
> > Resource statistics module, including: Base Station statistics and Other Resource(eg.tower) statistics
> >
> > > The statistics are shown in different dimensions: resource total number, resource statistics by CSP, by region, by resource state, by leasing status, by time period, by revenue distribution etc.

> Application & Approval
>
> > Resource Application & Approval module includes 3 menus: pending application, approved application, my application

> Ops & Maintenance
>
> > Operation and maintenance issues report
> >
> > > End users report the base station signaling issues to serving CSP, serving CSP check reports and once verified, it would be sent to service providing CSP. Reports are of two types: verified or to be processed.

> Settlement
>
> > Revenue contains two menus, divided by rent in and rent out

#### Backend Controllers and Functions: 

1. BidController: Bidding-->Interface Example: /bid/**
- /auction: offering price
- /getAllByResourceId: bidding information display
- /getEncryptInfo: encrypted bidding information display
2. CodeController: Verification Code Controller->Interface Example: /code/**
- /send: Send Verification Code
- /verify: Verify the code 
- /report: Network fault report
- /mark: Service rating
- /process: Fault reports verification process
- /haveReported: Fault reports verification results
- /fetchList: End user query
- /fetchListPC: PC user query
- /getOne: Click on process, get fault report info
- /getDetailByTaskId: Click on resource, get base station name
3. CountController: Resource Statistics-->Interface Example: /count/**
- /getTotalCount: Query the number of base stations
- /getUnicomCount: Query the number of CU base station
- /getTelecomCount: Query the number of CT base station
- /getCountByResourceType: Query number by resource type
- /getPercent: Query resource contribution proportion by CSP
- /getStationStatus: Get resource number by filtering resource status
- /getTotalUsing: Get the number of running resource
- /getTotalBuilding: Get the number of resource under construction
- /getTotalPlaning: Get the number of in planning resource
- /getTotalMaintenance: Get the number of under maintenance resource
- /getUnicomPercent: Get CU resource percentage by status
- /getTelecomPercent: Get CT resource percentage by status
- /getUnicomRent: CU resource rent in/rent out statistics
- /getTelecomRent: CT resource rent in/rent out statistics
- /getRentMonth: CU and CT resource renting trend 
- /getMapCount: Resource number by district 
- /getInvestorCount: number of resources invested by third party
- /getPlanStation: In planning Base station distribution 
- /getInvestorCost: Total amount of bidding
3. ProgressController: Process application-->Interface Example: /progress/**
- /updateResourceApply: Leasing application received
- /updateTerminationApply: Leasing termination application received
- /getUnicomUnfinishedByCondition: Query CU's received application by condition
- /getUnicomFinishedByCondition: Query CU's processed application by condition
- /getUnicomApplyByCondition: Query CU's application by condition
- /getTelecomUnfinishedByCondition: Query CT's received application by condition
- /getTelecomFinishedByCondition: Query CT's processed application by condition
- /getTelecomApplyByCondition: Query CT's application by condition
- /updatePlaning: Approve the application from third party
4. ResourceController: Resource Management-->Interface Example: /resources/**
- /create: Create new resource
- /update: Modify resource
- /delete: Delete resource
- /getResByName: Get resource info by resource name
- /getOne: Receive one message
- /getDetail: Get detailed resource information
- /getAllInfo: Get all resource information, including application record
- /getMore: Get 5 latest messages
- /updateStatus: Update resource status
- /getResByStatus: Get resource info by status
- /getAll: Get all resource info
- /apply: Resource application
- /deleteAllInfo: Delete all search records
- /getNewInfo: Get lateset 10 messages
- /end: Resource termination
- /haveApplied: Get resource leasing status
- /isApplyRes: Get resource application records
- /isApplyEnd: Get resource termination records
- /evaluate: Put user reports on the chain
- /showStationReport: Get user report info from resource details page
5. SettlementController: Settlement View-->Interface Example: /rent/**
- /findRentInSettlement: Settlement data on resource rent in
- /findRentOutSettlement: Settlement data resource rent out
- /getInvestorApply: Bidding info display, which are from investors' application
- /getISPSharing: CSP’s revenue
- /getInvestmentSharing: Investors' revenue
6. UploadDownController: Support file upload and download-->Interface Example: /upload/**
- /getFile: Upload file
7. UserController: User Management function-->Interface Example: /user/**
- /login: User Login

#### Middleware:

1. Nginx 1.8.0: Path for frontend files
2. Tomcat 8.5.47: Path for backend code

#### Implementation Guideline:

1. Install jdk1.8;

2. Get sql script from path: resource/sql/tmf.sql，and run it in mysql;

3. Modify jdbc local configuration;

4. Modify Configuration file

ResourceManagement.vue

ResourceManageMentDialog.vue

axios.js

Replace all http://122.192.9.52:20205 with local project's http://ip:port;


5. Enter demo folder under the webapp, run command npm run dev;

