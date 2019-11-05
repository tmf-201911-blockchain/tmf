package com.whalecloud.service;

import com.whalecloud.domain.ResAllInfo;
import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.util.AtomException;

import java.util.List;

/**
 *
 * 资源查询接口
 * @author zhaoyanac
 * @date 2019/10/13
 */
public interface ResourcesService {

    /**
     *
     * 新增
     *
     * @param dto
     * @throws Exception
     */
    ResDetail add(ResDetail dto) throws Exception;

    /**
     * 修改
     *
     * @param dto
     * @throws Exception
     */
    ResDetail edit(ResDetail dto) throws Exception;

    /**
     * 删除
     *
     * @param resourcesId
     */
    void delete(String resourcesId) throws AtomException;



    /**
     * 根据资源Id查询资源信息
     *
     * @param resourcesId
     * @return
     */
    ResDetail getOne(String resourcesId) throws Exception;

    /**
     *
     * 获取详情
     *
     * @param resourcesId
     * @return
     */
    ResAllInfo getDetail(String resourcesId) throws Exception;

    /**
     *
     *  得到所有的信息包括申请信息
     *
     * @param resourceId
     * @param taskId
     * @return
     * @throws Exception
     */
    ResAllInfo getAllInfo(String resourceId, String taskId) throws Exception;

    /**
     * 根据资源名称进行查询
     *
     * @param resName
     * @param resourceType
     * @return
     */
    List<ResDetail> getResByName(String resName, String resourceType);
    /**
     * 得到更多信息
     *
     * @return
     */
    List<ResDetail> getMore();

    /**
     * 更新状态
     *
     * @param resourcesId
     * @param newStatus
     */
    void updateStatus(String resourcesId, Integer newStatus) throws Exception;

    /**
     * 获取所有基站的信息
     *
     * @return
     */
    List<ResDetail> getAll(String resourceType);


    /**
     *
     * 根据基站状态进行查询
     *
     * @param nowStatus
     * @param resourceType
     * @return
     */
    List<ResDetail> getResByStatus(Integer nowStatus, String resourceType) throws Exception;

    /**
     * 申请资源
     *
     * @param resDetail
     * @return
     */
    Boolean apply(ResDetail resDetail, String username, String effectiveTime, String opinion) throws Exception;

    /**
     *
     * @param resourceId
     */
    Boolean end(String resourceId, String option, String effectiveTime) throws Exception;

    /**
     * 已经申请过的
     *
     * @param resourceId
     * @param userName
     * @return
     */
    int haveApplied(String resourceId, String userName) throws Exception;


    /**
     * 是否已经终止过
     *
     * @param resourceId
     * @param userName
     * @return
     */
    int haveApplyEnd(String resourceId, String userName) throws Exception;

    /**
     * 已经终止共享
     *
     * @param resourceId
     * @return
     */
    int haveEnd(String resourceId);

}

