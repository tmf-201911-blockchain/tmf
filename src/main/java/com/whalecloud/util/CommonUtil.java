package com.whalecloud.util;

import com.whalecloud.domain.ReportResDto;
import com.whalecloud.domain.re.Quotation;
import com.whalecloud.domain.re.ResDetail;
import com.whalecloud.domain.re.ResProgress;
import com.whalecloud.dto.BaseStationSpeed;
import com.whalecloud.dto.blockchain.code.AccessRecordInformation;
import com.whalecloud.dto.blockchain.code.Authorization;
import com.whalecloud.dto.blockchain.code.AuthorizationInformation;
import com.whalecloud.dto.blockchain.quotation.QuotationBlock;
import com.whalecloud.dto.blockchain.quotation.ReportBlock;
import com.whalecloud.dto.blockchain.tmf.ObjectOne;
import com.whalecloud.dto.blockchain.tmf.ObjectTwo;
import com.whalecloud.dto.enumdto.ApplicationType;
import com.whalecloud.dto.enumdto.ApplyStatus;
import com.whalecloud.dto.enumdto.EquipmentStatus;
import com.whalecloud.dto.enumdto.MaintainLevel;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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


    /**
     *
     * 上链鉴权记录
     *
     * @param phone
     * @param flag
     * @return
     * @throws Exception
     */
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

    /**
     *
     * 上链--竞价排名
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public static QuotationBlock fitQuotation(Quotation dto) throws Exception {
        QuotationBlock quotationBlock = new QuotationBlock();
        quotationBlock.setResourceID(dto.getResourceId());
        quotationBlock.setOfferor(dto.getOfferor());
        quotationBlock.setQuotationAmount(String.valueOf(dto.getQuotationAmount().setScale(2, BigDecimal.ROUND_DOWN)));
        String formatTime = DateUtils.getString(new Date(), DateUtils.DateFormatString_20);
        quotationBlock.setQuotationTime(formatTime);
        return quotationBlock;
    }

    /**
     *
     * 上链--报障信息
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public static ReportBlock fitReport(ReportResDto dto) throws Exception {
        ReportBlock reportBlock = new ReportBlock();
        reportBlock.setResourceID(dto.getResourceId());
        reportBlock.setTaskID(dto.getTaskId());
        reportBlock.setPhone(dto.getPhone());
        String formatTime = DateUtils.getString(new Date(), DateUtils.DateFormatString_20);
        reportBlock.setReportTime(formatTime);
        BaseStationSpeed randomOne = getRandomOne();
        if (!StringUtils.isEmpty(randomOne)) {
            reportBlock.setUploadRate(randomOne.getUploadRate());
            reportBlock.setDownloadRate(randomOne.getDownloadRate());
            reportBlock.setNetworkDelay(randomOne.getNetworkDelay());
            reportBlock.setCallingConnectionRate(randomOne.getCallingConnectionRate());
            reportBlock.setVoiceDropRate(randomOne.getVoiceDropRate());

        }
        return reportBlock;
    }

    /**
     *
     * 随机获取一个基站速度
     *
     * @return
     */
    public static BaseStationSpeed getRandomOne() {
        List<BaseStationSpeed> baseStationSpeeds = addStationSpeed();
        Random random = new Random();
        int i = random.nextInt(10);
        BaseStationSpeed baseStationSpeed = baseStationSpeeds.get(i);
        return baseStationSpeed;
    }

    /**
     * 随机增加10个基站
     *
     * @return
     */
    public static List<BaseStationSpeed> addStationSpeed() {
        ArrayList<BaseStationSpeed> speeds = new ArrayList<>();

        BaseStationSpeed speed1 = new BaseStationSpeed();
        speed1.setUploadRate("1.3G/s");
        speed1.setDownloadRate("2.62G/s");
        speed1.setNetworkDelay("4ms");
        speed1.setCallingConnectionRate("97.5%");
        speed1.setVoiceDropRate("2%");
        speeds.add(speed1);
        BaseStationSpeed speed2 = new BaseStationSpeed();
        speed2.setUploadRate("1.4G/s");
        speed2.setDownloadRate("2.7G/s");
        speed2.setNetworkDelay("2ms");
        speed2.setCallingConnectionRate("96.5%");
        speed2.setVoiceDropRate("2%");
        speeds.add(speed2);
        BaseStationSpeed speed3 = new BaseStationSpeed();
        speed3.setUploadRate("1.2G/s");
        speed3.setDownloadRate("2.4G/s");
        speed3.setNetworkDelay("1ms");
        speed3.setCallingConnectionRate("94.5%");
        speed3.setVoiceDropRate("2%");
        speeds.add(speed3);
        BaseStationSpeed speed4 = new BaseStationSpeed();
        speed4.setUploadRate("0.7G/s");
        speed4.setDownloadRate("1.41G/s");
        speed4.setNetworkDelay("3ms");
        speed4.setCallingConnectionRate("96.5%");
        speed4.setVoiceDropRate("1%");
        speeds.add(speed4);
        BaseStationSpeed speed5 = new BaseStationSpeed();
        speed5.setUploadRate("1.7G/s");
        speed5.setDownloadRate("3.41G/s");
        speed5.setNetworkDelay("1ms");
        speed5.setCallingConnectionRate("98.5%");
        speed5.setVoiceDropRate("3%");
        speeds.add(speed5);
        BaseStationSpeed speed6 = new BaseStationSpeed();
        speed6.setUploadRate("1.56G/s");
        speed6.setDownloadRate("3.87G/s");
        speed6.setNetworkDelay("1ms");
        speed6.setCallingConnectionRate("92.5%");
        speed6.setVoiceDropRate("2.1%");
        speeds.add(speed6);
        BaseStationSpeed speed7 = new BaseStationSpeed();
        speed7.setUploadRate("1.63G/s");
        speed7.setDownloadRate("2.96G/s");
        speed7.setNetworkDelay("3ms");
        speed7.setCallingConnectionRate("99.5%");
        speed7.setVoiceDropRate("1%");
        speeds.add(speed7);
        BaseStationSpeed speed8 = new BaseStationSpeed();
        speed8.setUploadRate("1.35G/s");
        speed8.setDownloadRate("2.78G/s");
        speed8.setNetworkDelay("3ms");
        speed8.setCallingConnectionRate("97.51%");
        speed8.setVoiceDropRate("2.6%");
        speeds.add(speed8);
        BaseStationSpeed speed9 = new BaseStationSpeed();
        speed9.setUploadRate("1.89G/s");
        speed9.setDownloadRate("2.68G/s");
        speed9.setNetworkDelay("7ms");
        speed9.setCallingConnectionRate("92.5%");
        speed9.setVoiceDropRate("1%");
        speeds.add(speed9);
        BaseStationSpeed speed10 = new BaseStationSpeed();
        speed10.setUploadRate("1.78G/s");
        speed10.setDownloadRate("2.99G/s");
        speed10.setNetworkDelay("1ms");
        speed10.setCallingConnectionRate("93.5%");
        speed10.setVoiceDropRate("2%");
        speeds.add(speed10);
        return speeds;
    }

}


