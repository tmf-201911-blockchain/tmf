package com.whalecloud.dto;

/**
 * @author zhaoyanac
 * @date 2019/11/1
 */
public class Respsonse {
    private String code;
    private String message;
    private Object data;


    public Respsonse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Respsonse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


