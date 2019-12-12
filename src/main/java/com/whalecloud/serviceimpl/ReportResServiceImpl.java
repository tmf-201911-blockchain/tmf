package com.whalecloud.serviceimpl;

import com.google.gson.Gson;
import com.whalecloud.domain.OpsInfo;
import com.whalecloud.domain.ReportInfoResponse;
import com.whalecloud.domain.ReportResDto;
import com.whalecloud.domain.ReportResWithSpeed;
import com.whalecloud.domain.re.ReportRes;
import com.whalecloud.domain.re.ReportResExample;
import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.dto.BaseStationSpeed;
import com.whalecloud.dto.blockchain.code.AccessRecordInformation;
import com.whalecloud.dto.blockchain.quotation.ReportBlock;
import com.whalecloud.dto.enumdto.TreatmentStateEnum;
import com.whalecloud.dto.enumdto.YNEnum;
import com.whalecloud.mapper.re.ReportResMapper;
import com.whalecloud.service.ReportResService;
import com.whalecloud.service.ResourcesService;
import com.whalecloud.util.CommonUtil;
import com.whalecloud.util.SeedUtil;
import com.whalecloud.util.httptool.HttpParamers;
import com.whalecloud.util.httptool.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaoyanac
 * @date 2019/10/31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportResServiceImpl implements ReportResService {

    @Autowired
    ReportResMapper reportResMapper;

    @Autowired
    ResourcesService resourcesService;


    @Override
    public void cochain(String taskId) throws Exception {
        //得到这条信息
        ReportResDto oneByTaskId = getOneByTaskId(taskId);

        Gson gson = new Gson();
        //区块链--用户评论
        HttpParamers paramers = HttpParamers.httpPostParamers();
        ReportBlock reportBlock = CommonUtil.fitReport(oneByTaskId);
        String objectOneJson = gson.toJson(reportBlock);
        paramers.setJsonParamer(objectOneJson);
        String response1 = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response1 = httpService.service("/api/createReport", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReportInfoResponse report(ReportRes dto) throws Exception {

        dto.setId(SeedUtil.getId());
        dto.setTaskId("RE"+SeedUtil.getId());
        dto.setReportTime(new Date());
        dto.setCreateTime(new Date());
        dto.setUpdateTime(new Date());
        //待解决状态--如果是手机评论就是待解决
        if (StringUtils.isEmpty(dto.getPhone())) {
            dto.setTreatmentState(TreatmentStateEnum.marked.getCode());
        } else {
            dto.setTreatmentState(TreatmentStateEnum.pending.getCode());
        }
        dto.setYn(YNEnum.NO.getCode());
        reportResMapper.insertSelective(dto);

        //获取基站详情信息
        ResDetail one = resourcesService.getOne(dto.getResourceId());

        //拼装返回的response信息
        ReportInfoResponse response = new ReportInfoResponse();
        response.setResourceId(dto.getResourceId());
        response.setResourceName(one.getResourceName());
        response.setAddress(one.getAddress());
        response.setLastTime(dto.getUpdateTime());
        return response;
    }

    @Override
    public ReportRes mark(String phone, Integer mark, String resourceId) throws Exception {
        ReportRes oneByPhone = getOneByPhone(phone, resourceId);
        if (oneByPhone == null || "".equals(oneByPhone)) {
            throw new Exception("没有找到该基站信息");
        }
        oneByPhone.setUpdateTime(new Date());
        oneByPhone.setStar(String.valueOf(mark));
        oneByPhone.setTreatmentState(TreatmentStateEnum.marked.getCode());
        oneByPhone.setYn(YNEnum.YES.getCode());

        reportResMapper.updateByPrimaryKeySelective(oneByPhone);

        return oneByPhone;


    }


    @Override
    public ReportRes getOneByPhone(String phone, String resourceId) {

        ReportResExample reportResExample = new ReportResExample();
        ReportResExample.Criteria criteria = reportResExample.createCriteria();
        criteria.andPhoneEqualTo(phone).andYnEqualTo(YNEnum.NO.getCode()).andResourceIdEqualTo(resourceId);
        List<ReportRes> reportRes = reportResMapper.selectByExample(reportResExample);

        if (reportRes.size() > 0) {
            return reportRes.get(0);
        } else {
            return null;
        }

    }


    @Override
    public void updateYn(String taskId) throws Exception {
        ReportResExample reportResExample = new ReportResExample();
        ReportResExample.Criteria criteria = reportResExample.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        List<ReportRes> reportRes = reportResMapper.selectByExample(reportResExample);

        ReportRes res;
        if (reportRes.size() > 0) {
            res = reportRes.get(0);
        } else {
            throw new Exception("没有找到目标");
        }
        res.setYn(YNEnum.NO.getCode());

        res.setUpdateTime(new Date());
        reportResMapper.updateByPrimaryKeySelective(res);
    }

    @Override
    public void process(String taskId,  Integer isSolved) throws Exception {
        ReportResExample reportResExample = new ReportResExample();
        ReportResExample.Criteria criteria = reportResExample.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        List<ReportRes> reportRes = reportResMapper.selectByExample(reportResExample);

        ReportRes res;
        if (reportRes.size() > 0) {
            res = reportRes.get(0);
        } else {
            throw new Exception("没有找到目标");
        }
        Gson gson = new Gson();
        //同意
        String solvedFlag = String.valueOf(isSolved);
        if (solvedFlag.equals("1")) {
            res.setTreatmentState(TreatmentStateEnum.solved.getCode());
            //区块链--插入行权记录
            HttpParamers paramers = HttpParamers.httpPostParamers();
            AccessRecordInformation accessRecordInformation = CommonUtil.fitAccessRecord(res.getPhone(), "success");
            String objectOneJson = gson.toJson(accessRecordInformation);
            paramers.setJsonParamer(objectOneJson);
            String response1 = "";
            try {
                HttpService httpService = new HttpService("http://39.99.148.130:8013");
                response1 = httpService.service("/api/createAccessRecord", paramers);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //不同意
        } else {
            //区块链--插入行权记录
            HttpParamers paramers = HttpParamers.httpPostParamers();
            AccessRecordInformation accessRecordInformation = CommonUtil.fitAccessRecord(res.getPhone(), "failure");
            String objectOneJson = gson.toJson(accessRecordInformation);
            paramers.setJsonParamer(objectOneJson);
            String response1 = "";
            try {
                HttpService httpService = new HttpService("http://39.99.148.130:8013");
                response1 = httpService.service("/api/createAccessRecord", paramers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        res.setUpdateTime(new Date());
        reportResMapper.updateByPrimaryKeySelective(res);

    }

    @Override
    public List<OpsInfo> fetchList(OpsInfo dto) throws Exception{

        ReportResExample example = new ReportResExample();
        ReportResExample.Criteria criteria = example.createCriteria();

        example.setOrderByClause("report_time desc");

        if (!StringUtils.isEmpty(dto.getTreatmentState())) {
            criteria.andTreatmentStateEqualTo(dto.getTreatmentState());
        }
        if (!StringUtils.isEmpty(dto.getResourceId())) {
            criteria.andResourceIdEqualTo(dto.getResourceId());
        }

        if (!StringUtils.isEmpty(dto.getReportContent())) {
            criteria.andSuggestionLike("%" +dto.getReportContent() +"%");
        }
        if (!StringUtils.isEmpty(dto.getTaskId())) {
            criteria.andTaskIdLike("%" +dto.getTaskId() +"%");
        }
        if (!StringUtils.isEmpty(dto.getFeedbackUser())) {
            criteria.andPhoneEqualTo(dto.getFeedbackUser());
        }
        List<ReportRes> reportRes = reportResMapper.selectByExample(example);


        ArrayList<OpsInfo> opsInfos = new ArrayList<>();

        for (ReportRes res : reportRes) {
            ResDetail one = resourcesService.getOne(res.getResourceId());

            OpsInfo opsInfo = new OpsInfo();
            opsInfo.setTaskId(res.getTaskId());
            opsInfo.setReportContent(res.getSuggestion());
            opsInfo.setFeedbackUser(res.getPhone());
            opsInfo.setReportingTime(res.getReportTime());
            opsInfo.setTreatmentState(res.getTreatmentState());
            opsInfo.setStar(res.getStar());
            opsInfo.setResourceId(res.getResourceId());
            if (StringUtils.isEmpty(res.getSuggestion())) {
                opsInfo.setIsEvaluate(YNEnum.NO.getName());
            } else {
                opsInfo.setIsEvaluate(YNEnum.YES.getName());
            }

            opsInfo.setAreaCode(one.getAreaCode());
            opsInfo.setResourceName(one.getResourceName());
            opsInfo.setResourceType(one.getResourceType());
            opsInfo.setAddress(one.getAddress());

            opsInfos.add(opsInfo);
        }



        if (!StringUtils.isEmpty(dto.getResourceType())) {
            List<OpsInfo> collect = opsInfos.stream().filter(i -> i.getResourceType().equals(dto.getResourceType())).collect(Collectors.toList());
            return collect;
        } else {
            return opsInfos;
        }

    }


    @Override
    public int haveReported(String phone) {
        ReportResExample example = new ReportResExample();
        ReportResExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneEqualTo(phone).andTreatmentStateEqualTo(TreatmentStateEnum.solved.getCode());

        List<ReportRes> reportRes = reportResMapper.selectByExample(example);

        if (reportRes.size() > 0) {
            return reportRes.size();
        } else {
            return 0;
        }

    }


    @Override
    public ReportResDto getOneByTaskId(String taskId) throws Exception {
        ReportResExample example = new ReportResExample();
        ReportResExample.Criteria criteria = example.createCriteria();
        criteria.andTaskIdEqualTo(taskId);

        List<ReportRes> reportRes = reportResMapper.selectByExample(example);
        ReportResDto dto;

        if (reportRes.size() > 0) {
            ReportRes res = reportRes.get(0);
            //得到基站信息
            ResDetail one = resourcesService.getOne(res.getResourceId());

            Gson gson = new Gson();
            String json = gson.toJson(res);
            dto = gson.fromJson(json, ReportResDto.class);

            dto.setResourceName(one.getResourceName());
            dto.setAreaCode(one.getAreaCode());
            dto.setResourceType(one.getResourceType());
            dto.setImportance(one.getImportance());
            dto.setLatitude(one.getLatitude());
            dto.setLongitude(one.getLongitude());
            dto.setAddress(one.getAddress());
            dto.setResourcePath(one.getPath());
            dto.setStatus(one.getStatus());

            BaseStationSpeed randomOne = CommonUtil.getRandomOne();
            dto.setUploadRate(randomOne.getUploadRate());
            dto.setDownloadRate(randomOne.getDownloadRate());
            dto.setNetworkDelay(randomOne.getNetworkDelay());
            dto.setCallingConnectionRate(randomOne.getCallingConnectionRate());
            dto.setVoiceDropRate(randomOne.getVoiceDropRate());


            return dto;
        } else {
            return null;
        }
    }


    @Override
    public List<ReportRes> getOneByResourceId(String resourceId) throws Exception {
        ReportResExample example = new ReportResExample();
        ReportResExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId);


        List<ReportRes> reportRes = reportResMapper.selectByExample(example);

        return reportRes;
    }

    @Override
    public List<ReportResWithSpeed> showStationReport(String resourceId) throws Exception {

        List<ReportRes> reportResList = getOneByResourceId(resourceId);

        ArrayList<ReportResWithSpeed> resWithSpeeds = new ArrayList<>();
        Gson gson = new Gson();

        if (reportResList.size() > 0) {

            for (ReportRes res : reportResList) {
                String json = gson.toJson(res);
                ReportResWithSpeed resWithSpeed = gson.fromJson(json, ReportResWithSpeed.class);

                //获取随机速率
                BaseStationSpeed randomOne = CommonUtil.getRandomOne();
                resWithSpeed.setUploadRate(randomOne.getUploadRate());
                resWithSpeed.setDownloadRate(randomOne.getDownloadRate());
                resWithSpeed.setNetworkDelay(randomOne.getNetworkDelay());
                resWithSpeed.setCallingConnectionRate(randomOne.getCallingConnectionRate());
                resWithSpeed.setVoiceDropRate(randomOne.getVoiceDropRate());
                //往List里面塞入数据
                resWithSpeeds.add(resWithSpeed);
            }
        }
        return resWithSpeeds;
    }
}


