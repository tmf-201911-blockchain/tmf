package com.whalecloud.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whalecloud.domain.Progress;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.dto.ProgressDetail;
import com.whalecloud.mapper.re.ProgressMapper;
import com.whalecloud.mapper.re.ResDetailMapper;
import com.whalecloud.service.ProgressService;
import com.whalecloud.util.AtomException;
import com.whalecloud.util.Exceptions;
import com.whalecloud.util.httptool.HttpParamers;
import com.whalecloud.util.httptool.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    ProgressMapper progressMapper;

    @Autowired
    ResDetailMapper resDetailMapper;

    //联通和电信收到的资源申请的审批
    @Override
    public int updateResourceApply(String progress,String lessor,String lessee, String opinion, String effectiveTime,String leaseStatus, String taskId) {
        if (progress == null || taskId == null) {
            try {
                throw Exceptions.atomException(Exceptions.c_ArgsNoEnough);
            } catch (AtomException e) {
                e.printStackTrace();
            }
        }
        int i = progressMapper.updateResourceApply(progress, lessor, lessee, opinion, effectiveTime, leaseStatus, taskId);
        //区块链  资源审批
        ProgressDetail progressDetail=progressMapper.getProgressByTaskId(taskId);
        HttpParamers paramers = HttpParamers.httpPostParamers();

        String s = JSON.toJSONString(progressDetail);
        paramers.setJsonParamer(paramers.getJsonParamer().trim() + s);
//        paramers.addParam("progressDetail", objectOneJson );
        String response = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response = httpService.service("/api/updateApplicationFlow", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(response);
        return i;
    }

    //联通和电信收到的终止申请的审批
    @Override
    public int updateTerminationApply(String progress, String opinion, String effectiveTime,String leaseStatus, String taskId) {
        if (progress == null || taskId == null) {
            try {
                throw Exceptions.atomException(Exceptions.c_ArgsNoEnough);
            } catch (AtomException e) {
                e.printStackTrace();
            }
        }
        int i = progressMapper.updateTerminationApply(progress, opinion, effectiveTime, leaseStatus, taskId);

        //区块链  资源终止审批
        ProgressDetail progressDetail=progressMapper.getProgressByTaskId(taskId);
        HttpParamers paramers = HttpParamers.httpPostParamers();

        String s = JSON.toJSONString(progressDetail);
        paramers.setJsonParamer(paramers.getJsonParamer().trim() + s);
        String response = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response = httpService.service("/api/updateApplicationFlow", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    //根据条件查询联通待办
    @Override
    public QueryResult<Progress> getUnicomUnfinishedByCondition(String resourceType,String applicationType, String resourceName, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<Progress> unicomUnfinishedByCondition = progressMapper.getUnicomUnfinishedByCondition(resourceType, applicationType,resourceName);
        List<Progress> result = unicomUnfinishedByCondition.getResult();
        long total = unicomUnfinishedByCondition.getTotal();
        int pages = unicomUnfinishedByCondition.getPages();
        if (result == null) {
            return new QueryResult<Progress>();
        }

        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        return queryResult;
    }

    //根据条件查询联通已办
    @Override
    public QueryResult<Progress> getUnicomFinishedByCondition(String resourceType,String applicationType,  String resourceName, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Progress> unicomFinishedByCondition = progressMapper.getUnicomFinishedByCondition(resourceType,applicationType, resourceName);
        List<Progress> result = unicomFinishedByCondition.getResult();
        long total = unicomFinishedByCondition.getTotal();
        int pages = unicomFinishedByCondition.getPages();
        if (result == null) {
            return new QueryResult<Progress>();
        }

        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        return queryResult;
    }

    //根据条件查询联通的申请
    @Override
    public QueryResult<Progress> getUnicomApplyByCondition(String resourceType,String applicationType,  String resourceName, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Page<Progress> unicomApplyByCondition = progressMapper.getUnicomApplyByCondition(resourceType,applicationType, resourceName);
        List<Progress> result = unicomApplyByCondition.getResult();
        long total = unicomApplyByCondition.getTotal();
        int pages = unicomApplyByCondition.getPages();
        if (result == null) {
            return new QueryResult<Progress>();
        }

        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        return queryResult;
    }

    //根据条件查询电信待办
    @Override
    public QueryResult<Progress> getTelecomUnfinishedByCondition(String resourceType,String applicationType,  String resourceName, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Progress> telecomUnfinishedByCondition = progressMapper.getTelecomUnfinishedByCondition(resourceType,applicationType, resourceName);
        List<Progress> result = telecomUnfinishedByCondition.getResult();
        long total = telecomUnfinishedByCondition.getTotal();
        int pages = telecomUnfinishedByCondition.getPages();
        if (result == null) {
            return new QueryResult<Progress>();
        }

        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        return queryResult;
    }

    //根据条件查询电信已办
    @Override
    public QueryResult<Progress> getTelecomFinishedByCondition(String resourceType,String applicationType,  String resourceName, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Progress> telecomFinishedByCondition = progressMapper.getTelecomFinishedByCondition(resourceType,applicationType, resourceName);
        List<Progress> result = telecomFinishedByCondition.getResult();
        long total = telecomFinishedByCondition.getTotal();
        int pages = telecomFinishedByCondition.getPages();

        if (result == null) {
            return new QueryResult<Progress>();
        }

        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        return queryResult;
    }

    //根据条件查询电信的申请
    @Override
    public QueryResult<Progress> getTelecomApplyByCondition(String resourceType,String applicationType,  String resourceName, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Progress> telecomApplyByCondition = progressMapper.getTelecomApplyByCondition(resourceType, applicationType,resourceName);
        List<Progress> result = telecomApplyByCondition.getResult();
        long total = telecomApplyByCondition.getTotal();
        int pages = telecomApplyByCondition.getPages();
        if (result == null) {
            return new QueryResult<Progress>();
        }

        QueryResult<Progress> queryResult=new QueryResult<>();
        queryResult.setList(result);
        queryResult.setTotal(total);
        queryResult.setPages(pages);
        return queryResult;
    }

    @Override
    public int updatePlaning(String investmentCost, String progress, String opinion,String taskId) {
        if (investmentCost == null || taskId == null) {
            try {
                throw Exceptions.atomException(Exceptions.c_ArgsNoEnough);
            } catch (AtomException e) {
                e.printStackTrace();
            }
        }

        int i = progressMapper.updatePlaning(investmentCost, progress,opinion,taskId);

        //区块链  资源终止审批
        ProgressDetail progressDetail=progressMapper.getProgressByTaskId(taskId);
        HttpParamers paramers = HttpParamers.httpPostParamers();

        String s = JSON.toJSONString(progressDetail);
        paramers.setJsonParamer(paramers.getJsonParamer().trim() + s);
        String response = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response = httpService.service("/api/updateApplicationFlow", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }
}
