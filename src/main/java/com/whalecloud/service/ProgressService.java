package com.whalecloud.service;

import com.whalecloud.domain.Progress;
import com.whalecloud.domain.QueryResult;


public interface ProgressService {

    ///联通收到的资源申请的审批
    int updateResourceApply(String progress,String lessor,String lessee, String opinion, String effectiveTime,String leaseStatus, String taskId);

    //联通收到的终止申请的审批
    int updateTerminationApply(String progress, String opinion,String effectiveTime,String leaseStatus,String taskId);

    //根据条件查询联通待办
    QueryResult<Progress> getUnicomUnfinishedByCondition(String resourceType,String applicationType,String resourceName,Integer pageNum,Integer pageSize);

    //根据条件查询联通已办
    QueryResult<Progress> getUnicomFinishedByCondition(String resourceType,String applicationType,String resourceName,Integer pageNum,Integer pageSize);

    //根据条件查询联通的申请
    QueryResult<Progress> getUnicomApplyByCondition(String resourceType,String applicationType,String resourceName,Integer pageNum,Integer pageSize);

    //根据条件查询电信待办
    QueryResult<Progress> getTelecomUnfinishedByCondition(String resourceType,String applicationType,String resourceName,Integer pageNum,Integer pageSize);

    //根据条件查询电信已办
    QueryResult<Progress> getTelecomFinishedByCondition(String resourceType,String applicationType,String resourceName,Integer pageNum,Integer pageSize);

    //根据条件查询电信的申请
    QueryResult<Progress> getTelecomApplyByCondition(String resourceType,String applicationType,String resourceName,Integer pageNum,Integer pageSize);

    //第三方申请建站审批
    int updatePlaning(String investmentCost,String progress, String opinion,String taskId);

}
