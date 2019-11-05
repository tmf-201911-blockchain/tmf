package com.whalecloud.util;

import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.domain.re.ResProgress;
import com.whalecloud.dto.blockchain.code.AccessRecordInformation;
import com.whalecloud.dto.blockchain.code.Authorization;
import com.whalecloud.dto.blockchain.code.AuthorizationInformation;
import com.whalecloud.dto.blockchain.tmf.ObjectOne;
import com.whalecloud.dto.blockchain.tmf.ObjectTwo;
import com.whalecloud.dto.enumdto.ApplicationType;
import com.whalecloud.dto.enumdto.ApplyStatus;
import com.whalecloud.dto.enumdto.EquipmentStatus;
import com.whalecloud.dto.enumdto.MaintainLevel;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author zhaoyanac
 * @date 2019/10/14
 */
public class CommonUtil {

    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }


    /**
     *
     * 组装object one的信息
     *
     * @param resDetail
     * @return
     * @throws Exception
     */
    public static ObjectOne fitObj(ResDetail resDetail) throws  Exception{

        ObjectOne objectOne = new ObjectOne();
        objectOne.setAddress(resDetail.getAddress());
        objectOne.setAreaCode(resDetail.getAreaCode());
        if (StringUtils.isEmpty(resDetail.getEffectiveTime())) {
            objectOne.setEffectiveTime("");
        } else {
            String formatTime = DateUtils.getString(resDetail.getEffectiveTime(), DateUtils.DateFormatString_18);
            objectOne.setEffectiveTime(formatTime);
        }
        objectOne.setImportance(MaintainLevel.getByCode(resDetail.getImportance()).getEnName());
        objectOne.setLatitude(resDetail.getLatitude());
        objectOne.setLessee(resDetail.getLessee());
        objectOne.setLessor(resDetail.getLessor());
        objectOne.setLongitude(resDetail.getLongitude());
        objectOne.setNEP(resDetail.getNep());
        objectOne.setOwner(resDetail.getOwner());
        objectOne.setRate(String.valueOf(resDetail.getRate()));
        objectOne.setResourceId(resDetail.getResourceId());
        objectOne.setResourceName(resDetail.getResourceName());
        objectOne.setResourceType(resDetail.getResourceType());
        objectOne.setStatus(EquipmentStatus.getByCode(resDetail.getStatus()).getEnName());
        objectOne.setTowerId(resDetail.getTowerId());

        return objectOne;
    }

    /**
     *
     * 组装objectTwo信息
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public static ObjectTwo fitObjTwo(ResProgress dto) throws  Exception{
        ObjectTwo objectTwo = new ObjectTwo();

        if (StringUtils.isEmpty(dto.getApplicationTime())) {
            objectTwo.setApplicationTime("");
        } else {
            String formatTime = DateUtils.getString(dto.getApplicationTime(), DateUtils.DateFormatString_18);
            objectTwo.setApplicationTime(formatTime);
        }

        objectTwo.setApplicationType(ApplicationType.getApplyEnum(dto.getApplicationType()).getEnName());


        if (StringUtils.isEmpty(dto.getEffectiveTime())) {
            objectTwo.setEffectiveTime("");
        } else {
            String formatTime = DateUtils.getString(dto.getEffectiveTime(), DateUtils.DateFormatString_18);
            objectTwo.setEffectiveTime(formatTime);
        }

        objectTwo.setLessee(dto.getLessee());
        objectTwo.setLessor(dto.getLessor());

        if (StringUtils.isEmpty(dto.getProcessTime())) {
            objectTwo.setProcessTime("");
        } else {
            String formatTime = DateUtils.getString(dto.getProcessTime(), DateUtils.DateFormatString_18);
            objectTwo.setProcessTime(formatTime);
        }

        objectTwo.setProgress(ApplyStatus.getApplyEnum(dto.getProgress()).getEnName());

        objectTwo.setResourceId(dto.getResourceId());
        objectTwo.setResourceName(dto.getResourceName());
        objectTwo.setTaskId(dto.getTaskId());
        return objectTwo;


    }

    /**
     * 组装授权记录
     *
     * @param phone
     * @param code
     * @return
     * @throws Exception
     */
    public static Authorization fitAuthorization(String phone, String code) throws Exception {
        Authorization authorization = new Authorization();

        authorization.setDataOwner(phone);
        authorization.setDataConsumer("Beijing Loan Technology Co.Ltd");
        authorization.setAuthorizedDataType("Location");
        authorization.setAuthorizedAPI("221");
        authorization.setAuthorizationToken(code);
        authorization.setAuthorizationResult("Success");

        String formatTime = DateUtils.getString(new Date(), DateUtils.DateFormatString_19);
        authorization.setAuthorizationTime(formatTime);
        return authorization;
    }

    /**
     *
     * 组装鉴权记录
     *
     * @param phone
     * @param code
     * @param trueCode
     * @param flag
     * @return
     * @throws Exception
     */
    public static AuthorizationInformation fitAuthorizationInfo(String phone, String code, String trueCode, String flag) throws Exception {
        AuthorizationInformation authorizationInformation = new AuthorizationInformation();

        authorizationInformation.setDataOwner(phone);
        authorizationInformation.setDataConsumer("P2P");
        authorizationInformation.setAuthorizedDataType("Location");
        authorizationInformation.setAuthorizedAPI("221");
        authorizationInformation.setRequestAPI("221");
        String formatTime = DateUtils.getString(new Date(), DateUtils.DateFormatString_19);
        authorizationInformation.setAuthorizationTime(formatTime);


        authorizationInformation.setAuthorizationToken(trueCode);
        authorizationInformation.setReceivedToken(code);
        authorizationInformation.setAuthorizationResult(flag);

        return authorizationInformation;
    }




    public static AccessRecordInformation fitAccessRecord(String phone, String flag) throws Exception {
        AccessRecordInformation accessRecordInformation = new AccessRecordInformation();

        accessRecordInformation.setDataOwner(phone);

        accessRecordInformation.setDataConsumer("Beijing Loan Technology Co.Ltd");

        accessRecordInformation.setCalledAPI("221");

        String formatTime = DateUtils.getString(new Date(), DateUtils.DateFormatString_19);
        accessRecordInformation.setCallTime(formatTime);
        accessRecordInformation.setCallResult(flag);
        return accessRecordInformation;
    }




}


