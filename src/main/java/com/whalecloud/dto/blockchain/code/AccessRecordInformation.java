package com.whalecloud.dto.blockchain.code;

/**
 * @author zhaoyanac
 * @date 2019/11/1
 */
public class AccessRecordInformation {

    private String DataOwner;
    private String DataConsumer;
    private String CalledAPI;
    private String CallTime;
    private String CallResult;


    public String getDataOwner() {
        return DataOwner;
    }

    public void setDataOwner(String dataOwner) {
        DataOwner = dataOwner;
    }

    public String getDataConsumer() {
        return DataConsumer;
    }

    public void setDataConsumer(String dataConsumer) {
        DataConsumer = dataConsumer;
    }

    public String getCalledAPI() {
        return CalledAPI;
    }

    public void setCalledAPI(String calledAPI) {
        CalledAPI = calledAPI;
    }

    public String getCallTime() {
        return CallTime;
    }

    public void setCallTime(String callTime) {
        CallTime = callTime;
    }

    public String getCallResult() {
        return CallResult;
    }

    public void setCallResult(String callResult) {
        CallResult = callResult;
    }
}


