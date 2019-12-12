# tmf

#### Description

TMF is a 5G Shared application platform.
How to reduce CAPEX & OPEX is the current fundamental challenges for CSP in 5G era.
High 5G network roll-out investment:
    5G networks construction requires a huge capital investment.
	Compared with 4G network, 5G needs more than 5 times base stations. 
	China will invest 1.6 trillion RMB to realize 5G covering national wide. 
	The operating cost of 5G will also be astronomical,  because each 5G base station’s power consumption is 2.5-4 times higher than the 4G base station.
A Practice of Collaborative Economy:Co-build & Co-share 5G network
Facilitating 5G network roll-out by applying the Collaborative Economy methodology might be a solution to the CAPEX and OPEX challenges.
Impact：
    The network infrastructure co-building and co-sharing among CSPs and third parties such as China Tower could significantly reduce construction costs.
    Attract other industry financial investors by opening economic interests. Therefore, we can achieve greater-scale 5G network roll-out collaborations to accelerate the pace of 5G construction.

#### Software Architecture

1. The development environment:
   - Language : java 8
   - Dependency Management : Maven
   - Database : MySQL 5.7
2. Back End Dependency :
   - Spring SSM
   - MyBatis 3.2.8
   - Druid 1.0.9
   - log4j
   - Gson 
3. Front End Dependency : 
   - Vue 2.6.10
   - axios 0.19.0
   - ant-design-vue 1.4.4
   - echarts 4.4.0
   - vue-router 3.0.1
   - vuex 3.1.1
   - webpack

#### Function Module: 

> User Sign In

> > There are two types of users : Operators and third parties
> >
> > > The operator has the highest authority
> > >
> > > Third parties have the approval to bid for and view the base station or tower they own
> > >
> > > 

> Resource Management
>
> > Resource distribution, Resource running state, New resources(Tower or the base station), To find the resources, Resources for details,Resource modification,Resources to delete

> Resource Statistics
>
> > Resource statistics module, It is divided into base station statistics and tower statistics
> >
> > > The statistics can be divided into total number, proportion of each operator, proportion of each region, proportion of each resource state, proportion of leasing in and leasing out, amount of leasing out by each operator in each month, amount of resources owned by a third party and income, etc

> Application & Approval
>
> > Resource approval module, Divided into pending, completed, my application

> Ops & Maintenance
>
> > Obstacle report maintenance module
> >
> > > The operator shall examine and approve the obstacle reported by ordinary users to the base station, which is divided into in progress and solved

> Settlement
>
> > The income module is divided into rent in, rent out and investment sharing

#### Back-end controllers and their functions: 

1. BidController: bidding-->Interface sample :	/bid/**
   - /auction : Personal bidding
   - /getAllByResourceId : Bidding information display
   - /getEncryptInfo : Get encrypted bidding information displayed
2. CodeController : Captcha controller->Interface sample:   /code/**
   - /send: Send verification code
   - /verify: Verify that the verification code is correct
   - /report: Declare failure
   - /mark: mark
   - /process: Declare failure approval
   - /haveReported: Have you applied for
   - /fetchList: Mobile terminal query
   - /fetchListPC: PC query
   - /getOne: Click approval to get the barrier information
   - /getDetailByTaskId: Click the base station name for details	
     3. CountController:Resources statistics-->Interface sample:   /count/**
   - /getTotalCount: Query the total number of base stations
   - /getUnicomCount: Query unicom base station number
   - /getTelecomCount: Query the number of telecom base stations
   - /getCountByResourceType:Query total/unicom total/telecom total by resource type
   - /getPercent: The percentage of base station Numbers of China unicom and China telecom
   - /getStationStatus: General use, construction, planning, maintenance
   - /getTotalUsing: In total use
   - /getTotalBuilding: Under construction
   - /getTotalPlaning: The overall planning
   - /getTotalMaintenance: The general maintenance
   - /getUnicomPercent: Unicom in use, construction, planning, maintenance in the total percentage of unicom
   - /getTelecomPercent: The total percentage of telecom in use, construction, planning and maintenance
   - /getUnicomRent: Unicom rent in and rent out
   - /getTelecomRent: Telecom rent in and rent out
   - /getRentMonth: Unicom and telecom base station leasing trend statistics
   - /getMapCount: The number of base stations in each district of Beijing
   - /getInvestorCount: Third party statistics
   - /getPlanStation: Base station distribution in planning
   - /getInvestorCost: Total amount awarded by the third party
3. ProgressController: Pending approval-->Interface sample:   /progress/**
   - /updateResourceApply: Approval of resource applications received by China unicom and China telecom
   - /updateTerminationApply:Approval of termination applications received by China unicom and China telecom
   - /getUnicomUnfinishedByCondition: According to the conditions of the query connection backlog
   - /getUnicomFinishedByCondition: According to the conditions of the query unicom has done
   - /getUnicomApplyByCondition: Inquire the application of unicom according to the conditions
   - /getTelecomUnfinishedByCondition: Query the telecom backlog according to the conditions
   - /getTelecomFinishedByCondition: According to the conditions of the inquiry has been done
   - /getTelecomApplyByCondition: Query telecom applications based on conditions
   - /updatePlaning: The third party applies for the approval of the construction of the station
4. ResourceController: Resource management-->Interface sample:  /resources/**
   - /create: create
   - /update: update
   - /delete: delete
   - /getResByName: Gets the resource by its name
   - /getOne: Get a message
   - /getDetail: Get the resource details
   - /getAllInfo: Get all information about the resource, including application records
   - /getMore: Get the latest 5 pieces of information
   - /updateStatus: Update base station status
   - /getResByStatus: Get the base station by state
   - /getAll: Get all the resource information
   - /apply:Resource application
   - /deleteAllInfo: Delete all search records
   - /getNewInfo: Get the latest 10 records
   - /end: Resources to terminate
   - /haveApplied: Whether the resource is rented out
   - /isApplyRes: Has the resource been applied
   - /isApplyEnd: Has the resource been terminated
   - /evaluate: User reviews on the blockchain
   - /showStationReport: Based on the details of the base station -- get the information of the user
5. SettlementController: settlement-->Interface sample:   /rent/**
   - /findRentInSettlement: Rent insettlement
   - /findRentOutSettlement: Rent out settlement
   - /getInvestorApply: Third party application for construction, bidding details show
   - /getISPSharing: Operator revenue
   - /getInvestmentSharing: Third party revenue
6. UploadDownController:Encapsulates the control layer for file upload and download-->Interface sample:   /upload/**
   - /getFile: File upload
7. UserController: The user to control-->Interface sample: /user/**
   - /login: login

#### Use middleware:

1. nginx 1.8.0
2. tomcat 8.5.47

#### Install the tutorial:

1. Install jdk1.8
2. find the file-->resource/sql/tmf.sql. run the sql file in the database(now use mysql)
3. edit the project jdbc.properties(use your database)
4. update the file
   
   ResourceManagement.vue
   
   ResourceManageMentDialog.vue
   
   axios.js
   
   replace all the words (http://122.192.9.52:20205) with your owner project service such as http://ip:port
5. Enter webapp directory demo, command line **npm run dev**   to run the demo

ops: if have any questions to email ren.ning01@iwhalecloud.com