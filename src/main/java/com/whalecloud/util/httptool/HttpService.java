package com.whalecloud.util.httptool;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author zhaoyanac
 * @date 2019/10/27
 */
public class HttpService {
    private String serverUrl;
    private int connectTimeout = 15000;
    private int readTimeout = 30000;
    public HttpService(String serverUrl) {
        this.serverUrl = serverUrl.trim();
    }
    public Map<String, Object> commonService(String serviceUrl, HttpParamers paramers) throws Exception{
        return commonService(serviceUrl, paramers, null);
    }
    public Map<String, Object> commonService(String serviceUrl, HttpParamers paramers, HttpHeader header) throws Exception{
        String response = service(serviceUrl, paramers, header);
        try {
            Map<String, Object> result = JSONObject.parseObject(response, new TypeReference<Map<String, Object>>() {});
            if ((result == null) || (result.isEmpty())) {
                throw new Exception("远程服务返回的数据无法解析");
            }
            Integer code = (Integer) result.get("code");
            if ((code == null) || (code.intValue() != 0)) {
                throw new Exception((String) result.get("message"));
            }
            return result;
        } catch (Exception e) {
            throw new Exception("返回结果异常,response:" + response, e);
        }
    }
    public String service(String serviceUrl, HttpParamers paramers) throws Exception {
        return service(serviceUrl, paramers, null);
    }
    public String service(String serviceUrl, HttpParamers paramers, HttpHeader header) throws Exception {
        String url = this.serverUrl + serviceUrl;
        String responseData = "";
        try {
            responseData = HttpClient.doService(url, paramers, header, this.connectTimeout, this.readTimeout);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return responseData;
    }

    public String getServerUrl() {
        return this.serverUrl;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}


