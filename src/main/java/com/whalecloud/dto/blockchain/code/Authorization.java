package com.whalecloud.dto.blockchain.code;

/**
 *
 * 确权交互模型
 *
 * @author zhaoyanac
 * @date 2019/11/1
 */
public class Authorization {

    private String DataOwner;
    private String DataConsumer;
    private String AuthorizedDataType;
    private String AuthorizedAPI;
    private String AuthorizationTime;
    private String AuthorizationResult;
    private String AuthorizationToken;


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
}


