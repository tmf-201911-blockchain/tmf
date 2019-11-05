package com.whalecloud.dto.blockchain.code;

/**
 * @author zhaoyanac
 * @date 2019/11/1
 */
public class AuthorizationInformation {
    private String DataOwner;
    private String DataConsumer;
    private String AuthorizedDataType;
    private String AuthorizedAPI;
    private String RequestAPI;
    private String AuthorizationTime;
    private String AuthorizationResult;
    private String AuthorizationToken;
    private String ReceivedToken;


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

    public String getAuthorizedDataType() {
        return AuthorizedDataType;
    }

    public void setAuthorizedDataType(String authorizedDataType) {
        AuthorizedDataType = authorizedDataType;
    }

    public String getAuthorizedAPI() {
        return AuthorizedAPI;
    }

    public void setAuthorizedAPI(String authorizedAPI) {
        AuthorizedAPI = authorizedAPI;
    }

    public String getRequestAPI() {
        return RequestAPI;
    }

    public void setRequestAPI(String requestAPI) {
        RequestAPI = requestAPI;
    }

    public String getAuthorizationTime() {
        return AuthorizationTime;
    }

    public void setAuthorizationTime(String authorizationTime) {
        AuthorizationTime = authorizationTime;
    }

    public String getAuthorizationResult() {
        return AuthorizationResult;
    }

    public void setAuthorizationResult(String authorizationResult) {
        AuthorizationResult = authorizationResult;
    }

    public String getAuthorizationToken() {
        return AuthorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        AuthorizationToken = authorizationToken;
    }

    public String getReceivedToken() {
        return ReceivedToken;
    }

    public void setReceivedToken(String receivedToken) {
        ReceivedToken = receivedToken;
    }
}


