package com.whalecloud.dto;

import java.io.Serializable;

/**
 * @author zhaoyanac
 * @date 2019/10/14
 */
public class ResultDto implements Serializable {
    private Integer result;
    private String message;
    private Object data;

    public Integer getResult() {
        return this.result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultDto(boolean result, String message, Object data) {
        if (result) {
            this.result = 1;
        } else {
            this.result = 0;
        }

        this.message = message;
        this.data = data;
    }

    public ResultDto(boolean result, String message) {
        if (result) {
            this.result = 1;
        } else {
            this.result = 0;
        }

        this.message = message;
        this.data = null;
    }

    public ResultDto(boolean result) {
        if (result) {
            this.result = 1;
        } else {
            this.result = 0;
        }

        this.message = "";
        this.data = null;
    }

    public ResultDto() {
    }

    public void setResult(boolean result) {
        if (result) {
            this.result = 1;
        } else {
            this.result = 0;
        }

    }

    public Boolean isSuccess() {
        return this.result == 1;
    }

    public void setSuccess(boolean succ) {
        this.result = succ ? 1 : 0;
    }


}



