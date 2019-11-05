package com.whalecloud.util.httptool;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * 请求参数
 *
 * @author zhaoyanac
 * @date 2019/10/27
 */
public class HttpParamers {

    private Map<String, String> params = new HashMap<String, String>();
    private HttpMethod httpMethod;
    private String jsonParamer = "";

    public HttpParamers(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public static HttpParamers httpPostParamers() {
        return new HttpParamers(HttpMethod.POST);
    }

    public static HttpParamers httpGetParamers() {
        return new HttpParamers(HttpMethod.GET);
    }

    public HttpParamers addParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public String getQueryString(String charset) throws IOException {
        if ((this.params == null) || (this.params.isEmpty())) {
            return null;
        }
        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, String>> entries = this.params.entrySet();

        for (Map.Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            query.append("&").append(name).append("=").append(URLEncoder.encode(value, charset));
        }
        return query.substring(1);
    }

    public boolean isJson() {
        return !isEmpty(this.jsonParamer);
    }

    public Map<String, String> getParams() {
        return this.params;
    }

    @Override
    public String toString() {
        return "HttpParamers " + JSON.toJSONString(this);
    }

    public String getJsonParamer() {
        return this.jsonParamer;
    }

    public void setJsonParamer() {
        this.jsonParamer = JSON.toJSONString(this.params);
    }

    public void setJsonParamer(String jsonParamer) {
        this.jsonParamer = jsonParamer;
    }

    private static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }
}


