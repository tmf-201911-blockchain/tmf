package com.whalecloud.serviceimpl;

import com.google.gson.Gson;
import com.whalecloud.domain.ResAllInfo;
import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.domain.re.ResDetailExample;
import com.whalecloud.domain.re.ResProgress;
import com.whalecloud.domain.re.ResProgressExample;
import com.whalecloud.dto.blockchain.tmf.ObjectOne;
import com.whalecloud.dto.blockchain.tmf.ObjectTwo;
import com.whalecloud.dto.enumdto.ApplicationType;
import com.whalecloud.dto.enumdto.ApplyStatus;
import com.whalecloud.dto.enumdto.LeaseStatus;
import com.whalecloud.dto.enumdto.YNEnum;
import com.whalecloud.mapper.re.ResDetailMapper;
import com.whalecloud.mapper.re.ResProgressMapper;
import com.whalecloud.mapper.re.ResourceDetailMapper;
import com.whalecloud.service.ResourcesService;
import com.whalecloud.util.AtomException;
import com.whalecloud.util.CommonUtil;
import com.whalecloud.util.Exceptions;
import com.whalecloud.util.SeedUtil;
import com.whalecloud.util.httptool.HttpParamers;
import com.whalecloud.util.httptool.HttpService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoyanac
 * @date 2019/10/13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    ResDetailMapper resDetailMapper;
    @Autowired
    ResourceDetailMapper resourceDetailMapper;
    @Autowired
    ResProgressMapper resProgressMapper;



    @Override
    public ResDetail add(ResDetail dto) throws Exception {
        //非空检查
        if (dto == null) {
            throw Exceptions.atomException(Exceptions.c_ArgsNoEnough);
        }

        //重复性检验
        ResDetailExample example = new ResDetailExample();
        example.createCriteria().andResourceIdEqualTo(dto.getResourceId());
        int count = resDetailMapper.countByExample(example);
        if (count > 0) {
            throw Exceptions.atomException("基站ID%s已存在", dto.getResourceId());
        }

        Gson gson = new Gson();
        String json = gson.toJson(dto);
        ResDetail resDetail = gson.fromJson(json, ResDetail.class);



        resDetail.setId(SeedUtil.getId());


        if (resDetail.getResourceName() == null) {
            resDetail.setResourceName("");
        }

        if (resDetail.getResourceId() == null) {
            resDetail.setResourceId("");
        }

//        resDetail.setEffectiveTime(new Date());
        //出租状态
        resDetail.setLeaseStatus(LeaseStatus.notUse.getCode());
        resDetail.setCreateTime(new Date());
        resDetail.setYn(YNEnum.NO.getCode());
        resDetail.setLessor(resDetail.getOwner());
        //新增

        int i = resDetailMapper.insert(resDetail);


        // 需要往区块链塞值

        //需要传递的参数
        HttpParamers paramers = HttpParamers.httpPostParamers();

        ObjectOne objectOne = CommonUtil.fitObj(resDetail);

        String objectOneJson = gson.toJson(objectOne);
        paramers.setJsonParamer(objectOneJson);
        String response = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response = httpService.service("/api/createInfrastructureComponent", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resDetail;

    }

    @Override
    public ResDetail edit(ResDetail dto) throws Exception {
        //非空检查
        if (dto == null) {
            throw Exceptions.atomException(Exceptions.c_ArgsNoEnough);
        }

        //实体内容必填校验
        if (dto.getResourceId() == null) {
            throw Exceptions.atomException("ID必填");
        }
        ResDetailExample example = new ResDetailExample();
        example.createCriteria().andResourceIdEqualTo(dto.getResourceId());


        List<ResDetail> resDetails = resDetailMapper.selectByExample(example);
        ResDetail resDetail = resDetails.get(0);

        //修改
        dto.setUpdateTime(new Date());


        int i = resDetailMapper.updateByExampleSelective(dto, example);


        //  更新操作，往区块链塞值
        //需要传递的参数
        HttpParamers paramers = HttpParamers.httpPostParamers();

        ObjectOne objectOne = CommonUtil.fitObj(dto);

        Gson gson = new Gson();
        String objectOneJson = gson.toJson(objectOne);

        paramers.setJsonParamer(objectOneJson);
        String response = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response = httpService.service("/api/updateInfrastructureComponent", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;

    }

    @Override
    public void delete(String resourcesId) throws AtomException {
        if (resourcesId == null) {
            throw Exceptions.atomException(Exceptions.c_ArgsNoEnough);

        }
        ResDetailExample example = new ResDetailExample();
        example.createCriteria().andResourceIdEqualTo(resourcesId);


        List<ResDetail> resDetails = resDetailMapper.selectByExample(example);
        ResDetail resDetail = resDetails.get(0);

        //假删除
        resDetail.setYn(YNEnum.YES.getCode());
        resDetail.setUpdateTime(new Date());


        int i = resDetailMapper.updateByExampleSelective(resDetail, example);

    }

    @Override
    public ResDetail getOne(String resourcesId) throws Exception {
        ResDetailExample example = new ResDetailExample();
        example.createCriteria().andResourceIdEqualTo(resourcesId);
        List<ResDetail> resDetails = resDetailMapper.selectByExample(example);
        if (resDetails.size() == 0) {
            throw new Exception("找不到该基站");
        }
        if (resDetails == null) {
            throw new Exception("找不到该基站");
        }
        ResDetail one = resDetails.get(0);
        return one;
    }

    @Override
    public ResAllInfo getDetail(String resourcesId) throws Exception {
        //获取详情信息
        ResDetail one = getOne(resourcesId);
        Gson gson = new Gson();
        String json = gson.toJson(one);

        //获取审批的信息
        ResProgressExample resProgressExample = new ResProgressExample();
        ResProgressExample.Criteria criteria = resProgressExample.createCriteria();
        criteria.andResourceIdEqualTo(resourcesId);
        List<ResProgress> resProgresses = resProgressMapper.selectByExample(resProgressExample);
        ResProgress resProgress;

        //数据组装
        ResAllInfo resAllInfo = gson.fromJson(json, ResAllInfo.class);
        if (resProgresses.size() == 0) {
            resProgress = new ResProgress();
        } else {
            resProgress = resProgresses.get(0);
            resAllInfo.setTaskId(resProgress.getTaskId());
            resAllInfo.setApplicationType(resProgress.getApplicationType());
            resAllInfo.setProgress(resProgress.getProgress());
            resAllInfo.setApplicationTime(resProgress.getApplicationTime());
            resAllInfo.setProcessTime(resProgress.getProcessTime());
            resAllInfo.setOpinion(resProgress.getOpinion());
            resAllInfo.setApplyReason(resProgress.getApplyReason());
        }

        return resAllInfo;
    }

    @Override
    public ResAllInfo getAllInfo(String resourceId, String taskId) throws Exception {

        //获取详情信息
        ResDetail one = getOne(resourceId);
        Gson gson = new Gson();
        String json = gson.toJson(one);


        ResProgress resProgress = new ResProgress();

        //获取审批的信息
        ResProgressExample example = new ResProgressExample();
        ResProgressExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId).andTaskIdEqualTo(taskId);
        List<ResProgress> resProgresses = resProgressMapper.selectByExample(example);
        if (resProgresses.size() != 0) {
            resProgress = resProgresses.get(0);
        }


        //数据组装
        ResAllInfo resAllInfo = gson.fromJson(json, ResAllInfo.class);


        resAllInfo.setTaskId(resProgress.getTaskId());
        resAllInfo.setApplicationType(resProgress.getApplicationType());
        resAllInfo.setProgress(resProgress.getProgress());
        resAllInfo.setApplicationTime(resProgress.getApplicationTime());
        resAllInfo.setProcessTime(resProgress.getProcessTime());
        if (resProgress.getOpinion() != null) {
            resAllInfo.setOpinion(resProgress.getOpinion());
        } else {
            resAllInfo.setOpinion("");
        }
        if (resProgress.getApplyReason() != null) {
            resAllInfo.setApplyReason(resProgress.getApplyReason());
        } else {
            resAllInfo.setApplyReason("");
        }


        return resAllInfo;
    }

    @Override
    public List<ResDetail> getAll(String resourceType) {
        ResDetailExample example = new ResDetailExample();
        example.createCriteria().andYnEqualTo(YNEnum.NO.getCode()).andResourceTypeEqualTo(resourceType);
        List<ResDetail> resDetails = resDetailMapper.selectByExample(example);

        return resDetails;
    }

    @Override
    public List<ResDetail> getResByName(String resName, String resourceType) {

        String resNameLike = "%"+resName+"%";


        ResDetailExample example = new ResDetailExample();
        ResDetailExample.Criteria criteria = example.createCriteria();
        criteria.andResourceNameLike("%"+resName+"%").andResourceTypeEqualTo(resourceType);


        List<ResDetail> reResDetails = resDetailMapper.selectByExample(example);


        return reResDetails;
    }


    @Override
    public List<ResDetail> getMore() {
        ResDetailExample example = new ResDetailExample();
        example.setOrderByClause("effective_time DESC");
        ResDetailExample.Criteria criteria = example.createCriteria();
        RowBounds rowBounds = new RowBounds(0,5);
        List<ResDetail> resDetails = resDetailMapper.selectByExampleWithRowbounds(example, rowBounds);


        return resDetails;
    }


    @Override
    public void updateStatus(String resourcesId, Integer newStatus) throws Exception {
        ResDetail one = getOne(resourcesId);
        one.setStatus(newStatus);
        one.setUpdateTime(new Date());


        ResDetailExample example = new ResDetailExample();
        ResDetailExample.Criteria criteria = example.createCriteria();
        criteria.andYnEqualTo(YNEnum.NO.getCode()).andResourceIdEqualTo(resourcesId);

        resDetailMapper.updateByExampleSelective(one, example);


        //区块链 修改状态
        //需要传递的参数
        HttpParamers paramers = HttpParamers.httpPostParamers();

        ObjectOne objectOne = CommonUtil.fitObj(one);

        Gson gson = new Gson();
        String objectOneJson = gson.toJson(objectOne);
        paramers.setJsonParamer(objectOneJson);
        String response = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response = httpService.service("/api/updateInfrastructureComponent", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ResDetail> getResByStatus(Integer nowStatus, String resourceType) throws Exception {

        ResDetailExample example = new ResDetailExample();
        ResDetailExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(nowStatus).andYnEqualTo(YNEnum.NO.getCode()).andResourceTypeEqualTo(resourceType);

        List<ResDetail> reResDetails = resDetailMapper.selectByExample(example);


        return reResDetails;
    }


    @Override
    public Boolean apply(ResDetail resDetail, String username, String effectiveTime,String opinion) throws Exception {
        //资源申请
        ResProgress resProgress = new ResProgress();
        resProgress.setId(SeedUtil.getId());
        //事务号
        resProgress.setTaskId("SQ"+ SeedUtil.getId().toString());
        //出租方
        resProgress.setLessor(resDetail.getOwner());
        //承租方  一般为当前用户
        resProgress.setLessee(username);
        resProgress.setResourceType(resDetail.getResourceType());

        resProgress.setResourceId(resDetail.getResourceId());
        resProgress.setResourceName(resDetail.getResourceName());

        resProgress.setApplicationType(ApplicationType.apply.getCode());

        //申请状态
        resProgress.setProgress(ApplyStatus.awaiting.getCode());
        //申请时间
        resProgress.setApplicationTime(new Date());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date parse = simpleDateFormat.parse(effectiveTime);


        //起租时间
        resProgress.setEffectiveTime(parse);
        //审核意见  这里第一次申请不需要
        resProgress.setOpinion(opinion);
        resProgress.setApplyReason(opinion);

        resProgress.setCreateTime(new Date());

        resProgress.setYn(YNEnum.NO.getCode());

        //新增
        int result = resProgressMapper.insertSelective(resProgress);


        //  区块链 往object2里面塞值
        //需要传递的参数
        HttpParamers paramers1 = HttpParamers.httpPostParamers();

        ObjectTwo objectTwo = CommonUtil.fitObjTwo(resProgress);

        Gson gson = new Gson();
        String objectTwoJson = gson.toJson(objectTwo);


        paramers1.setJsonParamer(objectTwoJson);
        String response1 = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response1 = httpService.service("/api/createApplicationFlow", paramers1);
        } catch (Exception e) {
            e.printStackTrace();
        }



        //修改
        resDetail.setLessee(resProgress.getLessee());
        resDetail.setLessor(resDetail.getLessor());
        resDetail.setEffectiveTime(parse);
        resDetail.setUpdateTime(new Date());
        //出租状态
        resDetail.setLeaseStatus(LeaseStatus.applying.getCode());
        ResDetailExample example = new ResDetailExample();
        ResDetailExample.Criteria criteria = example.createCriteria();
        criteria.andYnEqualTo(YNEnum.NO.getCode()).andResourceIdEqualTo(resDetail.getResourceId());

        resDetailMapper.updateByExampleSelective(resDetail, example);

        //区块链 更新detail表的操作
        //需要传递的参数
        HttpParamers paramers = HttpParamers.httpPostParamers();

        ObjectOne objectOne = CommonUtil.fitObj(resDetail);


        String objectOneJson = gson.toJson(objectOne);
        paramers.setJsonParamer(objectOneJson);
        String response = "";
        try {
            HttpService httpService = new HttpService("http://39.99.148.130:8013");
            response = httpService.service("/api/updateInfrastructureComponent", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        resDetailMapper.updateByPrimaryKeySelective(resDetail);
        if (result == 0) {

            return false;
        } else {
            return true;
        }
    }


    @Override
    public Boolean end(String resourceId, String option, String effectiveTime) throws Exception {

        try {
            ResDetail one = getOne(resourceId);

            ResProgress resProgress = new ResProgress();
            resProgress.setId(SeedUtil.getId());
            resProgress.setTaskId("TS"+SeedUtil.getId());
            resProgress.setResourceName(one.getResourceName());
            resProgress.setLessor(one.getLessor());
            resProgress.setLessee(one.getLessee());
            resProgress.setResourceId(one.getResourceId());
            resProgress.setApplicationType(ApplicationType.termination.getCode());
            resProgress.setProgress(ApplyStatus.awaiting.getCode());
            resProgress.setApplicationTime(new Date());
            resProgress.setApplyReason(option);
            resProgress.setResourceType(one.getResourceType());
            //处理时间，成功之后在处理
//        resProgress.setProcessTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date parse = simpleDateFormat.parse(effectiveTime);

            resProgress.setEffectiveTime(parse);
            resProgress.setCreateTime(new Date());
            resProgress.setYn(YNEnum.NO.getCode());
            resProgressMapper.insertSelective(resProgress);

            //区块链 往object2里面塞入终止单
            //需要传递的参数
            HttpParamers paramers1 = HttpParamers.httpPostParamers();

            ObjectTwo objectTwo = CommonUtil.fitObjTwo(resProgress);

            Gson gson = new Gson();
            String objectTwoJson = gson.toJson(objectTwo);
            paramers1.setJsonParamer(objectTwoJson);

            String response1 = "";
            try {
                HttpService httpService = new HttpService("http://39.99.148.130:8013");
                response1 = httpService.service("/api/createApplicationFlow", paramers1);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //成功之后在处理之后的

            //出租状态
            one.setLeaseStatus(LeaseStatus.applying.getCode());
            one.setUpdateTime(new Date());
            ResDetailExample example = new ResDetailExample();
            ResDetailExample.Criteria criteria = example.createCriteria();
            criteria.andYnEqualTo(YNEnum.NO.getCode()).andResourceIdEqualTo(one.getResourceId());

            resDetailMapper.updateByExampleSelective(one, example);

            //区块链 更新操作
            //需要传递的参数
            HttpParamers paramers = HttpParamers.httpPostParamers();

            ObjectOne objectOne = CommonUtil.fitObj(one);


            String objectOneJson = gson.toJson(objectOne);
            paramers.setJsonParamer(objectOneJson);

            String response = "";
            try {
                HttpService httpService = new HttpService("http://39.99.148.130:8013");
                response = httpService.service("/api/updateInfrastructureComponent", paramers);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } catch (Exception e) {
            return false;
        }


    }


    @Override
    public int haveApplied(String resourceId, String userName) throws Exception {


        ResProgressExample example = new ResProgressExample();
        ResProgressExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId).andLesseeEqualTo(userName).andApplicationTypeEqualTo(ApplicationType.apply.getCode());
        List<ResProgress> resProgresses = resProgressMapper.selectByExample(example);

        if (resProgresses.size() > 0) {
            return resProgresses.size();
        } else {
            return 0;
        }
    }

    @Override
    public int haveApplyEnd(String resourceId, String userName) throws Exception {
        ResProgressExample example = new ResProgressExample();
        ResProgressExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId).andLesseeEqualTo(userName).andApplicationTypeEqualTo(ApplicationType.termination.getCode());
        List<ResProgress> resProgresses = resProgressMapper.selectByExample(example);

        if (resProgresses.size() > 0) {
            return resProgresses.size();
        } else {
            return 0;
        }
    }

    @Override
    public int haveEnd(String resourceId) {
        ResDetailExample example = new ResDetailExample();
        ResDetailExample.Criteria criteria = example.createCriteria();
        criteria.andResourceIdEqualTo(resourceId).andLeaseStatusEqualTo(LeaseStatus.RentOut.getCode());
        List<ResDetail> resDetails = resDetailMapper.selectByExample(example);

        if (resDetails.size() > 0) {
            return resDetails.size();
        } else {
            return 0;
        }
    }
}


