package com.whalecloud.controller;

import com.google.gson.Gson;
import com.whalecloud.domain.Progress;
import com.whalecloud.domain.QueryResult;
import com.whalecloud.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * 待办审批
 *
 * @date 2019/10/15
 */
@Controller
@RequestMapping("/progress/")
public class ProgressController {

    Gson gson=new Gson();
    @Autowired
    ProgressService progressService;


    //联通和电信收到的资源申请的审批
    @ResponseBody
    @RequestMapping(value = "updateResourceApply",produces = "application/json;charset=utf-8")
    public String updateResourceApply(String progress, String lessor,String lessee,String opinion, String effectiveTime,String taskId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        String leaseStatus="";
        if (progress.equals("2")){
            leaseStatus="2";
        }else {
            leaseStatus="3";
        }
        int i = progressService.updateResourceApply(progress,lessor,lessee,opinion,effectiveTime,leaseStatus,taskId);

        if (i==0){
            return "操作失败";
        }else {
            return "操作成功";
        }
    }

    //联通和电信收到的终止申请的审批
    @ResponseBody
    @RequestMapping(value = "updateTerminationApply",produces = "application/json;charset=utf-8")
    public String updateTerminationApply(String progress,String opinion,String effectiveTime,String taskId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String leaseStatus="";
        if (progress.equals("2")){
            leaseStatus="3";
        }else {
            leaseStatus="2";
        }
        int i = progressService.updateTerminationApply(progress, opinion,effectiveTime,leaseStatus,taskId);

        if (i==0){
            return "操作失败";
        }else {
            return "操作成功";
        }
    }

    //根据条件查询联通待办
    @ResponseBody
    @RequestMapping(value = "getUnicomUnfinishedByCondition",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getUnicomUnfinishedByCondition(String resourceType, String applicationType,String resourceName, HttpServletResponse response,Integer pageNum,Integer pageSize) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> unicomUnfinishedByCondition = progressService.getUnicomUnfinishedByCondition(resourceType, applicationType,resourceName,pageNum,pageSize);
        String s = gson.toJson(unicomUnfinishedByCondition);
        return s;
    }

    //根据条件查询联通已办
    @ResponseBody
    @RequestMapping(value = "getUnicomFinishedByCondition",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getUnicomFinishedByCondition(String resourceType, String applicationType, String resourceName, HttpServletResponse response,Integer pageNum,Integer pageSize) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> unicomFinishedByCondition = progressService.getUnicomFinishedByCondition(resourceType, applicationType,resourceName,pageNum,pageSize);
        String s = gson.toJson(unicomFinishedByCondition);
        return s;
    }

    //根据条件查询联通的申请
    @ResponseBody
    @RequestMapping(value = "getUnicomApplyByCondition",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getUnicomApplyByCondition(String resourceType, String applicationType, String resourceName, HttpServletResponse response,Integer pageNum,Integer pageSize) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> unicomApplyByCondition = progressService.getUnicomApplyByCondition(resourceType, applicationType,resourceName,pageNum,pageSize);
        String s = gson.toJson(unicomApplyByCondition);
        return s;
    }

    //根据条件查询电信待办
    @ResponseBody
    @RequestMapping(value = "getTelecomUnfinishedByCondition",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getTelecomUnfinishedByCondition(String resourceType, String applicationType, String resourceName, HttpServletResponse response,Integer pageNum,Integer pageSize) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> telecomUnfinishedByCondition = progressService.getTelecomUnfinishedByCondition(resourceType,applicationType, resourceName,pageNum,pageSize);
        String s = gson.toJson(telecomUnfinishedByCondition);
        return s;
    }

    //根据条件查询电信已办
    @ResponseBody
    @RequestMapping(value = "getTelecomFinishedByCondition",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getTelecomFinishedByCondition(String resourceType, String applicationType, String resourceName, HttpServletResponse response,Integer pageNum,Integer pageSize) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> telecomFinishedByCondition = progressService.getTelecomFinishedByCondition(resourceType,applicationType, resourceName,pageNum,pageSize);
        String s = gson.toJson(telecomFinishedByCondition);
        return s;
    }

    //根据条件查询电信的申请
    @ResponseBody
    @RequestMapping(value = "getTelecomApplyByCondition",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getTelecomApplyByCondition(String resourceType, String applicationType, String resourceName, HttpServletResponse response,Integer pageNum,Integer pageSize) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        QueryResult<Progress> telecomApplyByCondition = progressService.getTelecomApplyByCondition(resourceType,applicationType, resourceName,pageNum,pageSize);
        String s = gson.toJson(telecomApplyByCondition);
        return s;
    }

    //第三方申请建站的审批
    @ResponseBody
    @RequestMapping(value = "updatePlaning",produces = "application/json;charset=utf-8")
    public String updatePlaning(String investmentCost,String progress, String opinion,String taskId, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        int i = progressService.updatePlaning(investmentCost, progress,opinion,taskId);

        if (i==0){
            return "操作失败";
        }else {
            return "操作成功";
        }
    }
}
