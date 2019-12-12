package com.whalecloud.service;

import com.whalecloud.domain.OpsInfo;
import com.whalecloud.domain.ReportInfoResponse;
import com.whalecloud.domain.ReportResDto;
import com.whalecloud.domain.ReportResWithSpeed;
import com.whalecloud.domain.re.ReportRes;

import java.util.List;

/**
 *
 * 评论和打分
 *
 * @author zhaoyanac
 * @date 2019/10/31
 */
public interface ReportResService {

    void cochain(String taskId) throws Exception;


    /**
     * 报障
     *
     * @param dto
     * @return
     */
    ReportInfoResponse report(ReportRes dto) throws Exception;


    /**
     * 打分
     *
     * @param phone
     * @param mark
     */
    ReportRes  mark(String phone, Integer mark, String resourceId) throws Exception;


    /**
     *
     * 根据手机号和基站ID获取信息
     *
     * @param phone
     * @return
     */
    ReportRes getOneByPhone(String phone, String resourceId);

    /**
     *
     * 把流程转换成1
     *
     * @param taskId
     * @throws  Exception
     */
    void updateYn(String taskId) throws Exception;


    /**
     *
     * 同意
     *
     * @param taskId
     * @param isSolved   是否同意  1：同意 0: 不同意
     *
     */
    void  process(String taskId, Integer isSolved) throws Exception;

    /**
     *
     * 查询出所有信息
     *
     * @param dto
     * @return
     * @throws Exception
     */
    List<OpsInfo> fetchList(OpsInfo dto) throws Exception;


    /**
     *
     * 是否已经申请过，并且处理过
     *
     * @param phone
     * @return
     */
    int haveReported(String phone);

    /**
     * 通过taskId获取
     *
     * @param taskId
     * @return
     * @throws Exception
     */
    ReportResDto getOneByTaskId(String taskId) throws Exception;


    /**
     * 通过基站ID获取所有信息
     *
     * @param resourceId
     * @return
     * @throws Exception
     */
    List<ReportRes> getOneByResourceId(String resourceId) throws Exception;


    List<ReportResWithSpeed> showStationReport(String resourceId) throws Exception;

}


