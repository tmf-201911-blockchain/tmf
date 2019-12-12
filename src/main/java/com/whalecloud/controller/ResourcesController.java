package com.whalecloud.controller;

import com.google.gson.Gson;
import com.whalecloud.domain.ReportResWithSpeed;
import com.whalecloud.domain.ResAllInfo;
import com.whalecloud.domain.ResDetailDto;
import com.whalecloud.domain.re.ReportRes;
import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.domain.re.SearchInfo;
import com.whalecloud.dto.ApplyEndCount;
import com.whalecloud.dto.Respsonse;
import com.whalecloud.mapper.re.ResourceDetailMapper;
import com.whalecloud.service.ReportResService;
import com.whalecloud.service.ResourcesService;
import com.whalecloud.service.SearchInfoService;
import com.whalecloud.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 资源管理
 *
 * @author zhaoyanac
 * @date 2019/10/12
 */

@Controller
@RequestMapping("/resources/")
public class ResourcesController {

    private static Logger logger = LoggerFactory.getLogger(ResourcesController.class);

    @Autowired
    ResourcesService resourcesService;
    @Autowired
    ResourceDetailMapper resourceDetailMapper;
    @Autowired
    SearchInfoService searchInfoService;
    @Autowired
    ReportResService reportResService;


    /**
     * 新增
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String create(HttpServletRequest request,HttpServletResponse response, ResDetail detail) {
        try {

            response.setHeader("Access-Control-Allow-Origin", "*");

            ResDetail add = resourcesService.add(detail);
            Gson gson = new Gson();
            String jsonString = gson.toJson(add);

            return jsonString;
        } catch (Exception ex) {

            logger.error(CommonUtil.getMethodName(), ex);
            return  "新增失败";
        }

    }


    /**
     * 测试接口--新增
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String create(HttpServletRequest request,HttpServletResponse response, String detail) {
        try {

            Gson gson1 = new Gson();
            ResDetail resDetail = gson1.fromJson(detail, ResDetail.class);


            response.setHeader("Access-Control-Allow-Origin", "*");

            ResDetail add = resourcesService.add(resDetail);
            Gson gson = new Gson();
            String jsonString = gson.toJson(add);

            return jsonString;
        } catch (Exception ex) {

            logger.error(CommonUtil.getMethodName(), ex);
            return  "新增失败";
        }

    }



    /**
     * 修改
     *
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String update(ResDetail dto, HttpServletResponse response, HttpServletRequest request) {
        Gson gson = new Gson();
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");

            ResDetail resDetail = resourcesService.edit(dto);
            String json = gson.toJson(new Respsonse("200", "OK", resDetail));

            return json;
        } catch (Exception ex) {
            logger.error(CommonUtil.getMethodName(), ex);
            String json = gson.toJson(new Respsonse("404", "ERROR"));

            return json;
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "delete", produces = "application/json;charset=utf-8")
    public String delete(String resourceId, HttpServletResponse response) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");

            //假删除
            resourcesService.delete(resourceId);

            return "处理成功";
        } catch (Exception e) {
            return "处理失败";
        }

    }

    /**
     * 通过资源名称获取资源
     *
     * @param resName
     * @param resourceType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getResByName", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getResByName(String resName, String resourceType,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //返回的内容
        ArrayList<ResDetail> resDetails = new ArrayList<>();

        ResDetail resDetail1 = new ResDetail();
        List<ResDetail> reResDetails = resourcesService.getResByName(resName, resourceType);
        Gson gson = new Gson();
        String json = "";
        if (reResDetails.size() == 0) {
            //塞入数据到List
//            resDetails.add(resDetail1);
            json = gson.toJson(resDetails);
            //新增搜索记录
            searchInfoService.add(resName, resDetail1);
        } else {
            json = gson.toJson(reResDetails);
            ResDetail resDetail = reResDetails.get(0);

            //新增搜索记录
            searchInfoService.add(resName, resDetail);
        }

        return json;
    }

    /**
     * 得到一条信息
     */
    @ResponseBody
    @RequestMapping(value = "getOne", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getOne(String resourceId, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (resourceId == null) {
            throw new Exception("资源ID的信息为空");
        }

        //去数据库查询

        ResDetail resDetail = resourcesService.getOne(resourceId);

        Gson gson = new Gson();
        String json = gson.toJson(resDetail);
        return json;
    }

    /**
     *
     * 得到资源的详细信息
     *
     * @param resourceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDetail", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getDetail(String resourceId, HttpServletResponse response) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");
        ResDetail detail = resourcesService.getOne(resourceId);
//        ResAllInfo detail = resourcesService.getDetail(resourceId);
        Gson gson = new Gson();
        String json = gson.toJson(detail);
        ResDetailDto resDetailDto = gson.fromJson(json, ResDetailDto.class);

        List<ReportRes> reportRes = reportResService.getOneByResourceId(resourceId);
        if (reportRes.size() > 0) {

            resDetailDto.setReportRes(reportRes);
        }
        //塞入时间戳
        if (!StringUtils.isEmpty(detail.getDeadline())) {
            resDetailDto.setQuotationDeadline(detail.getDeadline().getTime());
        }
        String jsonString = gson.toJson(resDetailDto);
        return jsonString;
    }

    /**
     *
     * 得到资源的所有信息，包括申请记录
     *
     * @param resourceId
     * @param taskId
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "getAllInfo", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getAllInfo (String resourceId, String taskId, HttpServletResponse response) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");
        ResAllInfo detail = resourcesService.getAllInfo(resourceId, taskId);
        Gson gson = new Gson();
        String json = gson.toJson(detail);
        return json;

    }

    /**
     * 得到最新的5条信息
     *
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMore", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getFiveInfo(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //
        List<ResDetail> more = resourcesService.getMore();

        Gson gson = new Gson();
        String json = gson.toJson(more);
        return json;
    }


    /**
     * 更新基站状态
     *
     * @param resourceId
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateStatus", produces = "application/json;charset=utf-8")
    public String updateStatus(String resourceId, Integer status) {

        try {

            resourcesService.updateStatus(resourceId, status);


            return "修改状态成功";
        } catch (Exception ex) {

            logger.error(CommonUtil.getMethodName(), ex);
            return "修改状态失败";
        }
    }

    /**
     *
     * 通过状态获取基站
     *
     * @param status
     * @param resourceType
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "getResByStatus", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getResByStatus(Integer status, String resourceType, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<ResDetail> resByStatus = resourcesService.getResByStatus(status, resourceType);
        Gson gson = new Gson();
        String jsonString = gson.toJson(resByStatus);
        return jsonString;
    }


    /**
     *
     * 得到所有的资源信息
     */
    @ResponseBody
    @RequestMapping(value = "getAll",  method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getAll(HttpServletResponse response, String resourceType) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<ResDetail> all = resourcesService.getAll(resourceType);
        Gson gson = new Gson();
        String json = gson.toJson(all);
        return json;
    }

    /**
     *
     * 资源申请
     *
     * @param resourceId  资源ID
     * @param username    用户名
     * @param effectiveTime   截止时间
     * @param opinion     意见
     */
    @ResponseBody
    @RequestMapping(value = "apply", produces = "application/json;charset=utf-8")
    public String apply(HttpServletResponse response, String resourceId,String username, String effectiveTime, String opinion) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");

        //根据资源ID去查询资源
        ResDetail resDetail = resourcesService.getOne(resourceId);

        if (StringUtils.isEmpty(resDetail)) {
            return "未找到该基站,请联系运维人员";
        }

        //存储数据到资源申请进度表中
        Boolean applyResult = resourcesService.apply(resDetail, username, effectiveTime, opinion);

        if (applyResult) {
            return "资源正在申请中";
        } else {
            return "资源申请失败";
        }

    }

    /**
     * 删除所有的搜索记录
     */
    @RequestMapping("deleteAllInfo")
    public void deleteAllInfo(HttpServletResponse response, String resourceType) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        searchInfoService.deleteAll(resourceType);

    }

    /**
     * 得到最新的10条记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getNewInfo",  method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getNewInfo(HttpServletResponse response, String resourceType) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<SearchInfo> searchInfos = searchInfoService.showList(resourceType);

        Gson gson = new Gson();
        String jsonString = gson.toJson(searchInfos);

        return jsonString;
    }


    /**
     *
     * 资源终止
     *
     * @param response
     * @param resourceId  资源ID
     * @param option   意见
     * @param effectiveTime   执行时间
     */
    @ResponseBody
    @RequestMapping(value = "end", produces = "application/json;charset=utf-8")
    public String end(HttpServletResponse response, String resourceId, String option, String effectiveTime) throws Exception {

        response.setHeader("Access-Control-Allow-Origin", "*");


        Boolean endResult = resourcesService.end(resourceId, option, effectiveTime);

        if (endResult) {
            return "终止共享申请中";
        } else {
            return "终止共享失败";
        }

    }


    /**
     *
     * 该资源是否租出
     *
     *
     * @param response
     * @param resourceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "haveApplied", produces = "application/json;charset=utf-8")
    public String haveApplied(HttpServletResponse response, String resourceId, String userName) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        int applyCount = resourcesService.haveEnd(resourceId);
        ResDetail one = resourcesService.getOne(resourceId);
        ApplyEndCount applyEndCount = new ApplyEndCount();
        applyEndCount.setApplyCount(applyCount);
        applyEndCount.setOwner(one.getOwner());
        if (!StringUtils.isEmpty(one.getIsp())) {
            applyEndCount.setIsp(one.getIsp());
        }
        applyEndCount.setState(one.getStatus());
        Gson gson = new Gson();
        String jsonString = gson.toJson(applyEndCount);

        return jsonString;

    }

    /**
     *
     * 该资源是否已经申请过
     *
     * @param response
     * @param resourceId
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isApplyRes", produces = "application/json;charset=utf-8")
    public String isApplyRes(HttpServletResponse response, String resourceId, String userName) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();

        try {
            int i = resourcesService.haveApplied(resourceId, userName);
            ResDetail one = resourcesService.getOne(resourceId);

            ApplyEndCount applyEndCount = new ApplyEndCount();
            applyEndCount.setApplyCount(i);
            applyEndCount.setOwner(one.getOwner());
            if (!StringUtils.isEmpty(one.getIsp())) {
                applyEndCount.setIsp(one.getIsp());
            }
            String json = gson.toJson(new Respsonse("200", "OK", applyEndCount));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }

    }

    /**
     *
     * 该资源是否已经终止过
     *
     * @param response
     * @param resourceId
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "isApplyEnd", produces = "application/json;charset=utf-8")
    public String isApplyEnd(HttpServletResponse response, String resourceId, String userName) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();

        try {
            int i = resourcesService.haveApplyEnd(resourceId, userName);
            ResDetail one = resourcesService.getOne(resourceId);

            ApplyEndCount applyEndCount = new ApplyEndCount();
            applyEndCount.setApplyCount(i);
            applyEndCount.setOwner(one.getOwner());
            if (!StringUtils.isEmpty(one.getIsp())) {
                applyEndCount.setIsp(one.getIsp());
            }
            String json = gson.toJson(new Respsonse("200", "OK", applyEndCount));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }

    }

    /**
     *
     * 上链
     *
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "evaluate", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String evaluate(HttpServletResponse response, String taskId) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        try {

            reportResService.cochain(taskId);

            String json = gson.toJson(new Respsonse("200", "OK"));
            return json;

        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }

    /**
     *
     * 根据基站详情--获取用户报障信息
     *
     * @param response
     * @param resourceId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "showStationReport", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String showStationReport(HttpServletResponse response, String resourceId) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        try {
            List<ReportResWithSpeed> reportResWithSpeeds = reportResService.showStationReport(resourceId);

            String json = gson.toJson(new Respsonse("200", "OK", reportResWithSpeeds));
            return json;

        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }

}


