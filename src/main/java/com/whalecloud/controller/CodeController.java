package com.whalecloud.controller;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.whalecloud.domain.*;
import com.whalecloud.domain.re.ReportRes;
import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.dto.Respsonse;
import com.whalecloud.dto.VerifyCode;
import com.whalecloud.dto.blockchain.code.Authorization;
import com.whalecloud.service.CodeService;
import com.whalecloud.service.ReportResService;
import com.whalecloud.service.ResourcesService;
import com.whalecloud.util.BuildCodeUtil;
import com.whalecloud.util.CommonUtil;
import com.whalecloud.util.httptool.HttpParamers;
import com.whalecloud.util.httptool.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * 验证码控制类
 *
 * @author zhaoyanac
 * @date 2019/10/31
 */
@Controller
@RequestMapping("/code/")
public class CodeController {

    private static Logger logger = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    private CodeService codeService;
    @Autowired
    private ReportResService reportResService;
    @Autowired
    private ResourcesService resourcesService;



    /**
     *
     * 发送验证码
     *
     * @param httpServletResponse
     * @param phone
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "send",method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String send(HttpServletResponse httpServletResponse, String phone) throws Exception {

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");


        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou1");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "TMF");
        request.putQueryParameter("TemplateCode", "SMS_177245191");

        Gson gson = new Gson();
        //验证码生成
        VerifyCode code = new VerifyCode();
        String verifyCode = new BuildCodeUtil().getVerifyCode();
        code.setCode(verifyCode);
        String jsonString = gson.toJson(code);

        //往数据库中插入验证码
        codeService.add(phone, verifyCode);

        //区块链--插入授权记录
        HttpParamers paramers = HttpParamers.httpPostParamers();
        Authorization authorization = CommonUtil.fitAuthorization(phone, verifyCode);
        String objectOneJson = gson.toJson(authorization);
        paramers.setJsonParamer(objectOneJson);
        String response1 = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response1 = httpService.service("/api/createAuthorization", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }



        request.putQueryParameter("TemplateParam", jsonString);
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getData();
        } catch (ClientException e) {
            e.printStackTrace();
            return "error";
        }


    }
    /**
     *
     * 验证验证码是否正确
     *
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "verify", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String verify(HttpServletResponse httpServletResponse, String phone, String code) throws Exception {

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        Boolean codeFlag = codeService.isTrue(phone, code);
        Gson gson = new Gson();

        if (codeFlag) {
            String json = gson.toJson(new Respsonse("200", "OK"));
            return json;
        } else {
            String json = gson.toJson(new Respsonse("404", "ERROR"));

            return json;
        }

    }

    /**
     *
     * 报障
     *
     * @param httpServletResponse
     * @param dto
     */
    @ResponseBody
    @RequestMapping(value = "report", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String  report(HttpServletResponse httpServletResponse,HttpServletRequest request, @RequestBody String dto) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        try {
            ReportRes res = gson.fromJson(dto, ReportRes.class);
            ReportInfoResponse report = reportResService.report(res);
            String jsonString = gson.toJson(report);

            String json = gson.toJson(new Respsonse("200", jsonString));
            return json;
        } catch (Exception e) {

            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }

    /**
     *
     * 打分
     *
     */
    @ResponseBody
    @RequestMapping(value = "mark", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String mark(HttpServletResponse httpServletResponse, String phone, Integer mark, String resourceId) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        try {
            ReportRes reportRes = reportResService.mark(phone, mark, resourceId);
            String jsonString = gson.toJson(reportRes);


            String json = gson.toJson(new Respsonse("200", jsonString));
            return json;

        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }

    }

    /**
     *
     * 审批
     *
     * @param httpServletResponse
     * @param taskId   任务号
     * @param isSolved  是否同意  1：同意 0: 不同意
     */
    @ResponseBody
    @RequestMapping(value = "process", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String process(HttpServletResponse httpServletResponse, String taskId, Integer isSolved) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        try {
            reportResService.process(taskId, isSolved);
            String json = gson.toJson(new Respsonse("200", "OK"));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }

    }

    /**
     *
     * 是否申请过
     *
     * @param httpServletResponse
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "haveReported", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String haveReported(HttpServletResponse httpServletResponse, String phone) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        try {
            int i = reportResService.haveReported(phone);
            String json = gson.toJson(new Respsonse("200", String.valueOf(i)));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }



    /**
     * 查询
     *
     * @param httpServletResponse
     * @param dto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "fetchList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String fetchList(HttpServletResponse httpServletResponse, HttpServletRequest request, @RequestBody String dto) {

        Gson gson = new Gson();
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        OpsInfo info = gson.fromJson(dto, OpsInfo.class);
        try {
            List<OpsInfo> opsInfos = reportResService.fetchList(info);
            PageHelper.startPage(info.getPageNum(), info.getPageSize());
            //得到分页的结果对象
            PageInfo<OpsInfo> personPageInfo = new PageInfo<>(opsInfos);

            QueryResult queryResult = new QueryResult();
            queryResult.setList(personPageInfo.getList());
            queryResult.setTotal(opsInfos.size());
            queryResult.setPages(personPageInfo.getPages());
            String json = gson.toJson(new Respsonse("200", "OK", queryResult));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }

    /**
     * 查询
     *
     * @param httpServletResponse
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "fetchListPC", method = RequestMethod.POST)
    public String fetchListPC(HttpServletResponse httpServletResponse, HttpServletRequest request, OpsInfo info) {

        Gson gson = new Gson();
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        try {
            List<OpsInfo> opsInfos = reportResService.fetchList(info);
            PageHelper.startPage(info.getPageNum(), info.getPageSize());
            //得到分页的结果对象
            PageInfo<OpsInfo> personPageInfo = new PageInfo<>(opsInfos);

            QueryResult queryResult = new QueryResult();
            queryResult.setList(personPageInfo.getList());
            queryResult.setTotal(opsInfos.size());
            queryResult.setPages(personPageInfo.getPages());
            String json = gson.toJson(new Respsonse("200", "OK", queryResult));
            return json;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }




    /**
     *
     * 点击审批，获取报障信息
     *
     * @param httpServletResponse
     * @param taskId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getOne", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getOne(HttpServletResponse httpServletResponse, String taskId)  {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        try {
            ReportResDto dto = reportResService.getOneByTaskId(taskId);

            String json = gson.toJson(new Respsonse("200", "OK", dto));
            return json;

        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }
    }

    /**
     *
     * 点击基站名称获取详情
     *
     * @param httpServletResponse
     * @param taskId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getDetailByTaskId", method =  RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getDetailByTaskId(HttpServletResponse httpServletResponse, String taskId) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        try {
            ReportResDto dto = reportResService.getOneByTaskId(taskId);
            ResDetail detail = resourcesService.getOne(dto.getResourceId());
            String json = gson.toJson(detail);
            ResDetailDto resDetailDto = gson.fromJson(json, ResDetailDto.class);

            List<ReportRes> reportRes = reportResService.getOneByResourceId(dto.getResourceId());
            if (reportRes.size() > 0) {

                resDetailDto.setReportRes(reportRes);
            }

            String jsonStr = gson.toJson(new Respsonse("200", gson.toJson(resDetailDto)));
            return jsonStr;
        } catch (Exception e) {
            String json = gson.toJson(new Respsonse("404", "error"));
            return json;
        }

    }


}


