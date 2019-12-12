package com.whalecloud.mapper.re;


import com.github.pagehelper.Page;
import com.whalecloud.domain.Progress;
import com.whalecloud.dto.ProgressDetail;
import org.apache.ibatis.annotations.Param;


/**
 *
 * 待办审批
 *
 * @date 2019/10/15
 */
public interface ProgressMapper {

    //联通和电信收到的资源申请的审批updateResourceApply
    int updateResourceApply(@Param("progress")String progress,@Param("lessor")String lessor,@Param("lessee")String lessee,@Param("opinion") String opinion,@Param("effectiveTime")String effectiveTime, @Param("leaseStatus")String leaseStatus,@Param("taskId")String taskId);

    //联通和电信收到的终止申请的审批
    int updateTerminationApply(@Param("progress")String progress,@Param("opinion") String opinion,@Param("effectiveTime")String effectiveTime, @Param("leaseStatus")String leaseStatus,@Param("taskId")String taskId);

    //根据条件查询联通待办
    Page<Progress> getUnicomUnfinishedByCondition(@Param("resourceType")String resourceType,@Param("applicationType") String applicationType,@Param("resourceName")String resourceName);

    //根据条件查询联通已办
    Page<Progress> getUnicomFinishedByCondition(@Param("resourceType")String resourceType,@Param("applicationType") String applicationType,@Param("resourceName")String resourceName);

    //根据条件查询联通的申请
    Page<Progress> getUnicomApplyByCondition(@Param("resourceType")String resourceType,@Param("applicationType") String applicationType,@Param("resourceName")String resourceName);

    //根据条件查询电信待办
    Page<Progress> getTelecomUnfinishedByCondition(@Param("resourceType")String resourceType,@Param("applicationType") String applicationType,@Param("resourceName")String resourceName);

    //根据条件查询电信已办
    Page<Progress> getTelecomFinishedByCondition(@Param("resourceType")String resourceType,@Param("applicationType") String applicationType,@Param("resourceName")String resourceName);

    //根据条件查询电信的申请
    Page<Progress> getTelecomApplyByCondition(@Param("resourceType")String resourceType,@Param("applicationType") String applicationType,@Param("resourceName")String resourceName);

    //上链查询
    ProgressDetail getProgressByTaskId(String taskId);

    //第三方申请建站审批
    int updatePlaning(@Param("investmentCost")String investmentCost,@Param("progress")String progress,@Param("opinion") String opinion,@Param("taskId")String taskId);


}
