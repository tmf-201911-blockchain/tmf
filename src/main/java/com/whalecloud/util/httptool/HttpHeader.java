package com.whalecloud.util.httptool;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求头
 *
 * @author zhaoyanac
 * @date 2019/10/27
 */
public class HttpHeader {
    private Map<String, String> params = new HashMap<String, String>();

    public HttpHeader addParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public Map<String, String> getParams() {
        return this.params;
    }
}


